import pandas as pd

# replace file path with your own on where scraped Excel files are located
skater_file = '/mnt/c/Users/killi/Documents/Fantasy-Hockey-App/backend/webscraper/nhl_skater_stats.xlsx'
goalie_file = '/mnt/c/Users/killi/Documents/Fantasy-Hockey-App/backend/webscraper/nhl_goalie_stats.xlsx'

skater_data = pd.ExcelFile(skater_file)
goalie_data = pd.ExcelFile(goalie_file)

for sheet_name in skater_data.sheet_names:
    df = pd.read_excel(skater_file, sheet_name=sheet_name)
    # replace file path with your own, mine is going to be in databases folder of my project
    df.to_csv(f'/mnt/c/Users/killi/Documents/Fantasy-Hockey-App/backend/database/csv_data/skaters_{sheet_name}.csv', index=False)

for sheet_name in goalie_data.sheet_names:
    df = pd.read_excel(goalie_file, sheet_name=sheet_name)
    # replace file path with your own, mine is going to be in databases folder of my project
    df.to_csv(f'/mnt/c/Users/killi/Documents/Fantasy-Hockey-App/backend/database/csv_data/goalies_{sheet_name}.csv', index=False)