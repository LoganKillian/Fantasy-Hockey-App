import os
import pandas as pd
import psycopg2

# Database connection setup
db_username = os.getenv('DB_USERNAME')
db_password = os.getenv('DB_PASSWORD')
connection = psycopg2.connect(
    dbname='fantasy_hockey',
    user=db_username,  # export DB_USERNAME='your_username'
    password=db_password,  # export DB_PASSWORD='your_password'
    host='localhost'
)

cursor = connection.cursor()

# Define mappings from CSV columns to database columns
column_mappings = {
    'skater_stats': {
        'Name': 'name',
        'Team': 'team',
        'Age': 'age',
        'Pos': 'position',
        'GP': 'games_played',
        'G': 'goals',
        'A': 'assists',
        'P': 'points',
        'PIM': 'penalty_minutes',
        '+/-': 'plus_minus',
        'TOI': 'time_on_ice',
        'ES': 'even_strength_time_on_ice',
        'PP': 'power_play_time_on_ice',
        'SH': 'short_handed_time_on_ice',
        'ESG': 'even_strength_goals',
        'PPG': 'power_play_goals',
        'SHG': 'short_handed_goals',
        'GWG': 'game_winning_goals',
        'OTG': 'overtime_goals',
        'ESA': 'even_strength_assists',
        'PPA': 'power_play_assists',
        'SHA': 'short_handed_assists',
        'GWA': 'game_winning_assists',
        'OTA': 'overtime_assists',
        'ESP': 'even_strength_points',
        'PPP': 'power_play_points',
        'SHP': 'short_handed_points',
        'GWP': 'game_winning_points',
        'OTP': 'overtime_points',
        'PPP%': 'power_play_points_percentage',
        'G/60': 'goals_per_sixty',
        'A/60': 'assists_per_sixty',
        'P/60': 'points_per_sixty',
        'ESG/60': 'even_strength_goals_per_sixty',
        'ESA/60': 'even_strength_assists_per_sixty',
        'ESP/60': 'even_strength_points_per_sixty',
        'PPG/60': 'power_play_goals_per_sixty',
        'PPA/60': 'power_play_assists_per_sixty',
        'PPP/60': 'power_play_points_per_sixty',
        'G/GP': 'goals_per_game',
        'A/GP': 'assists_per_game',
        'P/GP': 'points_per_game',
        'SHOTS': 'shots',
        'SH%': 'shooting_percentage',
        'HITS': 'hits',
        'BS': 'blocked_shots',
        'FOW': 'faceoffs_won',
        'FOL': 'faceoffs_lost',
        'FO%': 'faceoff_percentage'
    },
    'goalie_stats': {
        'Name': 'name',
        'Team': 'team',
        'Age': 'age',
        'GP': 'games_played',
        'GAA': 'goals_against_average',
        'SV%': 'save_percentage',
        'W': 'wins',
        'L': 'losses',
        'GA': 'goals_against',
        'SV': 'saves',
        'SOG': 'shots_on_goals',
        'SO': 'shutouts',
        'TIME': 'time',
        'G': 'goals',
        'A': 'assists',
        'P': 'points',
        'PIM': 'penalty_minutes'
    }
}

def clean_value(x):
    if isinstance(x, str):
        # Remove percentage signs, commas, and whitespace
        x = x.replace('%', '').replace(',', '').strip()
        try:
            return float(x)
        except ValueError:
            return x  # If it can't be converted to float, return the original value
    return x

def clean_percentage_columns(df):
    for col in df.columns:
        if df[col].dtype == 'object':  # Only process string columns
            df[col] = df[col].apply(lambda x: clean_value(x) if pd.notnull(x) else None)
    return df

def load_csv_to_db(csv_path, table_name, season):
    df = pd.read_csv(csv_path)
    
    # Apply column mappings
    if table_name in column_mappings:
        df.rename(columns=column_mappings[table_name], inplace=True)
    
    # Add the season column to the DataFrame
    df['season'] = season
    
    # Clean all columns
    df = clean_percentage_columns(df)
    
    # Print DataFrame info for debugging
    print(f"DataFrame head for {table_name}:")
    print(df.head())
    print(f"\nDataFrame dtypes for {table_name}:")
    print(df.dtypes)
    
    # Generate a list of columns based on the DataFrame
    columns = ', '.join(df.columns)
    values = ', '.join(['%s'] * len(df.columns))
    insert_query = f"INSERT INTO {table_name} ({columns}) VALUES ({values})"
    
    for row in df.itertuples(index=False):
        cursor.execute(insert_query, row)
    connection.commit()

# Path where CSV files are stored
csv_directory = '/mnt/c/Users/killi/Documents/Fantasy-Hockey-App/backend/database/csv_data'

# Load skater CSV files
for csv_file in os.listdir(csv_directory):
    if csv_file.startswith('skaters_') and csv_file.endswith('.csv'):
        season = csv_file.split('_')[1].replace('.csv', '')  # Extract season from filename
        load_csv_to_db(os.path.join(csv_directory, csv_file), 'skater_stats', season)

# Load goalie CSV files
for csv_file in os.listdir(csv_directory):
    if csv_file.startswith('goalies_') and csv_file.endswith('.csv'):
        season = csv_file.split('_')[1].replace('.csv', '')  # Extract season from filename
        load_csv_to_db(os.path.join(csv_directory, csv_file), 'goalie_stats', season)

cursor.close()
connection.close()