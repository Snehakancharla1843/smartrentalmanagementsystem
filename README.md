**Smart Rental Management System**
The Smart Rental Management System is a Java-based application designed to manage rental items, including both books and movies, in a flexible and efficient way. The system connects to a MySQL database to store item details and supports basic rental operations, including genre-based searching, item selection, budget calculation, and optional cost splitting.

**Features**
**User Registration and Login:** Secure user authentication with options for new users to register.
**Item Browsing by Genre and Type:** Users can view available items filtered by type (book or movie) and genre (e.g., romance, scary, sci-fi).
**Dynamic Genre Switching:** If no items are found in the chosen genre, the system prompts the user to select another genre.
**Cost Calculation and Splitting**:Calculates the total rental cost for selected items and provides an option to split the cost among multiple people.
**MySQL Database Integration**: Uses MySQL for storing item details, user credentials, and rental information.

**Technologies Used**
**Java: Core programming language.
JDBC: For connecting Java to the MySQL database.
MySQL: Relational database for storing user and item data.
Setup Instructions**

**Prerequisites**

**Java**: Ensure Java is installed on your system. You can check by running: java -version
**MySQL**: Ensure MySQL is installed and running. Set up a database called rental and create the items table with the necessary columns as outlined in the items schema.
**Git**: For cloning the repository.
**Database Setup**

Open MySQL and create a new database called rental: CREATE DATABASE rental;
Switch to the rental database: USE rental;
Create the items table with appropriate columns (e.g., item_id, name, author_or_director, genre, type, rental_price).
Populate the table with sample data for books and movies, categorized by genre.
Clone the Repository

**To clone this repository, run the following command in your terminal:**

**git clone https://github.com/Snehakancharla1843/smartrentalmanagementsystem.git
**
Running the Project

**Navigate to the project directory: cd smartrentalmanagementsystem/src**
Compile the Java files: javac *.java
Run the application: java App
Follow the prompts in the console to interact with the Smart Rental Management System. You can register as a new user, log in, browse items by genre, add items to your selection, and review your total rental cost.

Usage

Register/Login: Begin by registering a new user or logging in with an existing account.
Select Item Type and Genre: Choose between books or movies, and then select a genre (e.g., romance, scary, sci-fi).
Switch Genres: If no items are found in a genre, the system will prompt you to select another genre.
Add Items by ID: Enter the item ID to add items to your selection list.
View Total and Split Cost: The application will display your selected items and total cost, with an option to split the cost among multiple people.
Example Commands

Here are some example commands to help you get started:

Add a New Item to MySQL: INSERT INTO items (name, author_or_director, genre, type, rental_price) VALUES ('The Shining', 'Stephen King', 'scary', 'book', 50.00);

View All Items in a Genre: // When prompted, enter the genre of your choice (e.g., "scary") to view available items in that category.

Contributing

Feel free to fork this repository and make improvements! Submit a pull request with any enhancements, bug fixes, or additional features.

License

This project is licensed under the MIT License.
