import logging
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from bs4 import BeautifulSoup
import time
import pandas as pd
from tqdm import tqdm

# Set up logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)

# Function to scrape a season's data
def scrape_data(season_start, season_end):
    formatted_season = f'{season_start}-{str(season_end)[-2:]}'
    base_url = f'https://www.quanthockey.com/nhl/seasons/{season_start}-{formatted_season.split("-")[1]}-nhl-goalies-stats.html'
    
    # Configure Selenium driver to improve scraping speed
    chrome_options = Options()
    chrome_options.add_argument("--headless")
    chrome_options.add_argument("--disable-gpu")
    chrome_options.add_argument("--no-sandbox")
    chrome_options.add_argument("--disable-dev-shm-usage")
    chrome_options.add_argument("--blink-settings=imagesEnabled=false")
    
    driver = webdriver.Chrome(options=chrome_options)
    driver.get(base_url)
    
    all_data = []
    
    # Parse html of page, use dynamic scraping to get goalie data
    try:
        logger.info(f"Scraping {season_start}-{season_end} season")
        soup = BeautifulSoup(driver.page_source, 'html.parser')
        table = soup.find('table', {'class': ['ps_tbl', 'nowrap', 'dt3']})
        
        if not table:
            logger.warning("No data table found. Stopping.")
            return [], []
        
        rows = table.find('tbody').find_all('tr')
        for row in rows:
            cells = row.find_all(['th', 'td'])
            if len(cells) > 1:
                player_data = [cell.text.strip() for cell in cells[2:]]
                all_data.append(player_data)
            
        logger.info(f"Found {len(rows)} players")
    finally:
        driver.quit()
    
    # Data columns we are extracting
    headers = [
        'Name', 'Team', 'Age', 'GP', 'GAA', 'SV%', 'W', 'L', 'GA', 'SV', 'SOG', 'SO', 'TIME', 
        'G', 'A', 'P', 'PIM'
    ]
    
    return headers, all_data

# Function to save scraped data to an Excel file
def save_to_excel(aggregated_data):
    excel_file_path = 'nhl_goalie_stats.xlsx'
    with pd.ExcelWriter(excel_file_path, engine='xlsxwriter') as writer:
        for season_start, data in aggregated_data.items():
            df = pd.DataFrame(data['data'], columns=data['headers'])
            df.to_excel(writer, sheet_name=f'{season_start}-{str(season_start + 1)[-2:]}', index=False)
    logger.info(f"All data aggregated into {excel_file_path}")

# Wrapper function to call scraper and aggregate its data
def scrape_season(season):
    season_start, season_end = season
    try:
        headers, data = scrape_data(season_start, season_end)
        if headers and data:
            return season_start, {'headers': headers, 'data': data}
    except Exception as e:
        logger.error(f"Error scraping season {season_start}-{season_end}: {e}")
    return season_start, None

def main():
    seasons = [(year, year+1) for year in range(2005, 2024)]
    aggregated_data = {}
    failed_seasons = []
    
    # Track progress of scraping
    for season in tqdm(seasons, desc="Seasons"):
        season_start, result = scrape_season(season)
        if result:
            aggregated_data[season_start] = result
            logger.info(f"Completed scraping and saving for season {season_start}-{season[1]}")
        else:
            failed_seasons.append(season_start)
            logger.warning(f"Failed to scrape or save data for season {season_start}-{season[1]}")
    
    if aggregated_data:
        save_to_excel(aggregated_data)
    
    logger.info("All seasons processed.")
    logger.info(f"Successfully scraped and saved data for {len(aggregated_data)} seasons.")
    if failed_seasons:
        logger.warning(f"Failed to scrape or save data for {len(failed_seasons)} seasons: {failed_seasons}")
    logger.info("Script completed successfully.")

if __name__ == "__main__":
    main()
