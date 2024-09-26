# Fantasy-Hockey-App: FantasyRink
FantasyRink is a project I created to scrape National Hockey League (NHL) player statistics since the 2005-2006 lockout, predict player archetypes and their projected fantasy points outputs for the 2024-2025 season, and manipulate data in the database. The current tech stack (backend only currently) is Python, Selenium, PyTorch, Spring Boot, and PostgreSQL.

There were a couple of goals with this project, the first being to learn about building a RESTful API, and learning some Spring Boot. I hadn't worked with Java in several years, so getting to use it again while learning practical applications of it was great. The second goal was to learn about webscraping and data manipulation with PostgreSQL. The third goal was to see what kind of projections would be made for 2023-2024 players next year, and I also wanted to learn PyTorch, as I had already used TensorFlow/Keras before. It will be fun to go back to the model projections in this project as the season progresses and compare to see how accurate my model was. Of course fantasy sports are quite volatile, and my data did not include advanced stats or account for factors such as injuries and team quality, so I am sure there will be some surprises. I also gained more respect for existing fantasy sports apps, as they are extremely scalable and have many real-time features and projections, even for rookies with no prior NHL stats to predict off of.

In the future, I plan to expand on this project, with the main adds being in the Future Additions section below. Additionally, it would be cool to add some of those real-time fantasy features that top fantasy apps have as well.

# Features:
- **Data Scraping:** Created a webscraper to get skater and goalie statistics in the NHL for every season since 2005-2006 to 2023-2024 with Python and Selenium.
- **Database:** PostgreSQL database for real-time data manipulation.
- **Backend:** Spring Boot application for basic CRUD functionality using MVC architecture.
- **Player Archetype Prediction:** Used the past three seasons of NHL skater data to classify forwards and defensemen based on their playstyles and attributes with a SciKit-Learn random forest model.
- **2024-2025 Fantasy Points Projection:** Designed a PyTorch custom neural network to project skater and goalie fantasy points for the upcoming 2024-2025 NHL season using data since the 2005-2006 lockout.

# Future Additions:
- Docker
- JWT Authentication
- Frontend
