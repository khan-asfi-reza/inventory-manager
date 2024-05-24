# Inventory Management and Billing Software:

## Project Summary: 
The primary goal of this project was to create an Inventory management and Billing software for a Vinyl & CD shop. There are mainly three types of products: Movies, Games, and Music. This software will help to operate the shop by categorizing products, managing discounts and maintaining an efficient inventory system.

Problem Statement: The client required a customized software solution to manage their diverse inventory of movies, games, and music.The challenge was to implement a system that will store the stock availability of various products and show their availability status to the user.

## Solution Approach:
- Modular Architecture:
The architecture segregates the system into distinct, self-contained modules, each tasked with specific functions such as managing products, users, and invoicing processes. This structure simplifies comprehension, maintenance, and future expansion of the system.
- Data Persistence:
The system employs JSON files for storing and retrieving product and user information across sessions. Utility functions for these operations are encapsulated within the JsonUtil class.
- Inventory Management:
Functionality to add, delete, and query inventory items, as well as to adjust stock levels, is provided by the Inventory class. This class utilizes the capabilities of the StockableProduct class for modifying inventory quantities.
- Authentication and User Administration:
User-related functionalities, including user creation, password encryption using MD5 (noting the need for enhanced security measures), and user authentication, are facilitated by the User class.
- Invoicing:
The Invoice class is responsible for managing product additions and deletions on invoices, calculating totals and discounts, and generating a structured invoice document.
Custom Exception Handling:
To address situations where a user attempts to register with a username that already exists, the UserAlreadyExistsException class offers specialized exception handling.

The Inventory Management System project uses object-oriented programming (OOP) and design patterns to make its design organized and easy to work with. This setup helps in reusing code, keeping it easy to maintain and grow. It does this by grouping data and actions into classes, using inheritance to share common parts, and using a special pattern to manage data access. This makes the system easier to handle and build on.

### Project Features:

The project developed an Inventory Management and Billing Software for a Vinyl & CD shop, focusing on movies, games, and music. Key features include:

1. Modular Architecture
2. Data Persistence with JSON files
3. Inventory Management
4. User Authentication
5. Automated Invoicing
6. Custom Exception Handling
7. Object-Oriented Design
8. Integrated FXML, stylesheets, and JavaFX
9. Security and Data Management
10. Direct Printing Invoice Option


### Class, Method and Variable Description:

- Class: Main
This class serves as the entry point for the JavaFX application. It initializes the application, sets up the primary stage with the login scene, and handles the logout functionality.

- Variable:
inventory (Inventory): An instance of the Inventory class used to manage and store various products.
user, user1, user2 (User): Instances of the User class representing different users.

- Method:
The main method of the application. It invokes the mainMethod() of the Tasks class and launches the JavaFX application.
start(Stage primaryStage): The overridden method from the Application class. It sets up the primary stage with the login scene, adds styling, sets the application title and icon, and displays the stage. It also sets up the logout confirmation dialog when the stage is closed.
logout(Stage stage): Displays a confirmation dialog when the user attempts to close the application. If the user confirms, it closes the application.

- External Resources:
FXML Files: Login.fxml
Stylesheets: styles/styles.css
Fonts: OpenSans-Regular.ttf, OpenSans-Medium.ttf, OpenSans-Bold.ttf
Icon: icon.png

Dependencies:
```java
javafx.application.Application;
javafx.fxml.FXMLLoader;
javafx.scene.Parent;
javafx.scene.Scene;
javafx.scene.control.Alert;
javafx.scene.control.ButtonType;
javafx.scene.image.Image;
javafx.scene.text.Font;
javafx.stage.Stage;
java.util.Objects;
```

- Class: Tasks
The Tasks class orchestrates the execution of different tasks, including user creation, inventory management, invoice generation, and data manipulation.

- Method:
Main Method:
Attempts to create multiple users with predefined usernames. Handles the scenario where the username already exists.
User Authentication:

## Demonstrates the custom feature of user authentication by attempting to log in with valid credentials.

- Task 1: Adding Items to Inventory:
Adds various products (music, movies, games) to the inventory.

- Task 2: Generating an Invoice:
Creates an invoice and adds products to it.

- Task 3: Modifying an Invoice:
Modifies an existing invoice by adding another product to it.

- Task 4: Finding Games:
Retrieves all games from the inventory and prints their information.
- Task 5: Finding the Cheapest Music:
Retrieves the cheapest music item from the inventory and prints its information.

- Task 6: Filtering Movies by Director:
Adds movies with a specific director to the inventory and filters them based on the director's name.

- Task 7: Displaying Available Stocks of Sold Products:
Creates a set of unique products from the invoice items and displays their information, representing available stocks of sold products in the inventory.

```

Class: Product

This class is an abstract class representing a product in the inventory management system.

Variables:
name (String): Represents the name of the product.
productId (int): A unique identifier for the product.
price (double): The price of the product.
genre (String): The genre or category of the product.
yearPublished (int): The year the product was published or released.
discount (double): The discount applied to the product.



Constructor:
Product(): Default constructor for the product class. It helps to Initialize a new instance of Product with default values.

Product(String name, int productId, double price, String genre, int yearPublished, double discount): Parameterized constructor for the Product class. Initializes a new instance of Product with specified values.
Parameters:
name (String): The name of the product.
productId (int): The unique identifier for the product.
price (double): The price of the product.
genre (String): The genre or category of the product.
yearPublished (int): The year the product was published or released.
discount (double): The discount applied to the product.


Method:
getPrimaryKey() (int): Returns the primary key (product ID) of the product.
getName() (String): Gets the name of the product
getProductId() (int):  Gets the unique identifier of the product.
getPrice() (double): Gets the price of the product.
getGenre() (String): Gets the genre or category of the product.
getYearPublished() (int): Gets the year the product was published or released.
getDiscount() (double): Gets the discount applied to the product.
setName(String name): Sets the name of the product.
setProductId(int productId): Sets the unique identifier of the product.
setPrice(double price): Sets the price of the product.
setGenre(String genre): Sets the genre or category of the product.
setYearPublished(int yearPublished): Sets the year the product was published or released.
setDiscount(double discount): Sets the discount applied to the product.
getInfo() (String) (abstract method): This is an abstract method to get the product's information.
This must be implemented by subclasses.
toString() (String) (abstract method): This is an abstract method to get the string representation of the product. Must be implemented by subclasses.
```

```
Interface: Stockable
This interface defines the methods required for managing stock of items in the inventory management system.

addStock(int num): Adds the specified number of items to the stock.
removeStock(int num):  Removes the specified number of items from the stock.
editStock(int num): Edits the stock by setting it to the specified number of items.
```

```
Class: StockableProduct<T>
This class is a generic class that extends the product class and implements the Stockable and Serializable interfaces. It adds functionality for managing the stock level of products. Including methods for adding, removing and editing stock levels.

Variable:
numberOfItemsStocked (int): Represents the number of items currently stocked for the product.
StockableProduct(): Default constructor for the StockableProduct class. Initializes a new instance of StockableProduct with default values.
StockableProduct(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked): Parameterized constructor for the StockableProduct class. Initializes a new instance of StockableProduct with specified values.


Method:
setProductId(int productId): Sets the unique identifier for the product.
setName(String name): Sets the name of the product.
setPrice(double price): Sets the price of the product.
setGenre(String genre): Sets the genre or category of the product.
setYearPublished(int yearPublished): Sets the year the product was published or released.
setDiscount(double discount): Sets the discount applied to the product.
setNumberOfItemsStocked(int numberOfItemsStocked): Sets the number of items currently stocked for the product.
addStock(int num): Increases the number of items stocked by the specified amount.
removeStock(int num): Decreases the number of items stocked by the specified amount.
editStock(int num): Sets the stock level to the specified number of items.
getNumberOfItemsStocked() (int): Gets the number of items currently stocked for the product.
toString() (String): Returns a string representation of the product, including its name, genre, and year published.
getInfo() (String): Returns detailed information about the product, including its name, product ID, price, genre, year published, and stock level.
```

```
Class: Game
The Game class represents a game product in the inventory management system. It extends StockableProduct<Game> and includes attributes and methods specific to games. It also provides functionalities to manage the game’s stock and accessing repository of games.

Variable:
developer (String): Represents the name of the developer of the game.
repository (Repository<Game>): A static repository for storing and managing instances of the Game class. It is initialized with the Game class type.

Constructor:
Game():  Default constructor for the Game class. Initializes a new instance of Game with default values.
Game(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String developer): Parameterized constructor for the Game class. Initializes a new instance of Game with specified values.

Method:
getDeveloper() (String):  Gets the name of the developer of the game.
setDeveloper(String developer): Sets the name of the developer of the game.
getInfo() (String): Overrides the getInfo method from the parent class to include the developer's name in the information string.
filterByDeveloper(String developer) (static Game): Searches the repository for a game developed by the specified developer and returns it.
```

```
Class: Music
The Music class represents a music product in the inventory management system. It extends StockableProduct<Music> and includes additional attributes and methods specific to music, such as the artist's name. It also provides functionalities for managing the music's stock and accessing a repository of music items.

Variable:
artistName (String): Represents the name of the artist who performed the music.
repository (Repository<Music>): A static repository for storing and managing instances of the Music class. It is initialized with the Music class type.

Constructor:
Music():  Default constructor for the Music class. Initializes a new instance of Music with default values.
Music(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String artistName): Parameterized constructor for the Music class. Initializes a new instance of Music with specified values.

Method:
getArtistName() (String): Gets the name of the artist who performed the music.
setArtistName(String artistName): Sets the name of the artist who performed the music.
getInfo() (String): Overrides the getInfo method from the parent class to include the artist's name in the information string.
filterByDirector(String artistName) (static Music): Searches the repository for a music item performed by the specified artist and returns it.
```

```
Class: Movie
The Movie class represents a movie product in the inventory management system. It extends StockableProduct<Movie> and includes additional attributes and methods specific to movies, such as the director's name. It also provides functionalities for managing the movie's stock and accessing a repository of movie items.

Variable:
director (String): Represents the name of the director of the movie.
repository (Repository<Movie>): A static repository for storing and managing instances of the Movie class. It is initialized with the Movie class type.

Constructor:
Movie(): Default constructor for the Movie class. Initializes a new instance of Movie with default values.
Movie(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String director): Parameterized constructor for the Movie class. Initializes a new instance of Movie with specified values.
Method:
getDirector() (String):  Gets the name of the director of the movie.
setDirector(String director): Sets the name of the director of the movie.
getInfo() (String): Overrides the getInfo method from the parent class to include the director's name in the information string.
filterByDirector(String director) (static Movie): Searches the repository for a movie directed by the specified director and returns it.
```

```
Class: Inventory
The Inventory class manages a collection of StockableProduct items. It supports adding, removing, and retrieving items, as well as sorting the inventory by price or available stock. The class implements the Iterable interface to allow iteration over the items.

Variable:
items (ArrayList<StockableProduct<?>>): A list that holds all stockable products in the inventory, including games, movies, and music items.

Constructor:
Inventory():  Default constructor for the Inventory class. Initializes a new instance of Inventory by populating the items list with all products from the repositories of Game, Movie, and Music.

Method: addItem(StockableProduct<?> product): Adds a new product to the inventory and saves it to the repository.
removeItem(int productId): Removes a product from the inventory based on its product ID and deletes it from the repository.
getItem(int productId) (StockableProduct<?>): Retrieves a product from the inventory based on its product ID and decreases its stock by one.
addProductStock(int productId, int numberOfNewStock): Adds a specified number of new stock items to a product in the inventory and saves the updated product to the repository.
<T extends StockableProduct<?>> getProductByClass(Class<T> type) (ArrayList<T>): Retrieves all products of a specified class type from the inventory.
sortByPrice(): Sorts the products in the inventory by their price in ascending order.
sortByAvailableStock(): Sorts the products in the inventory by their available stock in ascending order.
iterator() (Iterator<StockableProduct<?>>): Returns an iterator over the elements in the inventory.
```

```
Class: Invoice
The Invoice class represents an invoice for products in the inventory management system. It maintains a list of products, calculates prices with and without discounts, and generates invoice details. It includes functionalities for adding and removing products and applying discounts based on specific conditions.

Variable:
items (ArrayList<Product>): A list that holds the products included in the invoice.
date (LocalDateTime): The date and time when the invoice was created.

Constructor:
Default constructor for the Invoice class. Initializes a new instance of Invoice with an empty list of products and sets the date to the current date and time.

Method:
getLocalDateTime() (String): Returns the date and time of the invoice in a formatted string.
addProduct(Product product): Adds a product to the invoice if it is in stock and reduces the stock by one.
removeProduct(Product product): Removes a product from the invoice and increases the stock by one.
calculatePriceWithoutDiscount() (double):  Calculates the total price of all products in the invoice without applying any discounts.
isFullHouseDiscountAvailable() (boolean): Checks if the "Full House" discount is applicable, which requires at least two items from each product category (Game, Music, Movie).
calculateDiscountedPrice() (double): Calculates the total price of the invoice after applying the best available discount, considering regular product discounts and the "Full House" discount if applicable.
getInvoice() (String): This generates a string representation of the invoice, including the date, product details, total price without discount, and total price after discounts.
```

```
Class: Repository<T>
The Repository class manages a collection of objects of type T, providing functionalities for fetching, caching, and retrieving these objects from a storage location. It supports generic operations and handles different types of objects, facilitating data persistence and retrieval.

Variable:
type (Class<T>): The class type of the objects managed by the repository.
cached (ArrayList<T>): A list that caches the fetched objects to avoid redundant I/O operations.
repos (ArrayList<Repository<?>>): A list of repositories that can be used to manage different types of objects.

Constructor:
Repository(Class<T> type): Initializes a new instance of the Repository class for the specified type.
Repository(Repository<?>... repositories): Initializes a new instance of the Repository class with a collection of other repositories.

Method:

getAll() (ArrayList<T>): Returns all cached objects of the specified type. If the cache is empty, it fetches the objects from the storage location first.
```

```
Class: UserAlreadyExistsException
The UserAlreadyExistsException class is a custom exception used within the inventory management system. It is thrown when an attempt is made to create or add a user that already exists in the system.

Constructor:
Default constructor for the UserAlreadyExistsException class. It initializes a new instance of the exception with a default error message indicating that the user already exists.

Class: Model
The Model class is an abstract base class that provides common functionality for models in the inventory management system. It defines methods for saving and deleting instances, utilizing JSON files for persistent storage. Each subclass must implement the getPrimaryKey method to specify its unique identifier.

Variable:
None: The Model class does not define any instance variables.

Constructor:
Model(): Default constructor for the Model class. Initializes a new instance of Model. This constructor is typically called by subclasses.

Method:
getPrimaryKey() (int): Abstract method that must be implemented by subclasses to return the unique identifier (primary key) for the instance.
save(): Saves the instance's data to a JSON file. The file is named using the primary key of the instance and stored in a directory corresponding to the class name.
delete(): Deletes the JSON file corresponding to the instance's primary key from the storage directory. If the file does not exist, it prints a message indicating that no file was found.
```

```
Class: User
The User class represents a user in the inventory management system. It extends the Model class and includes attributes such as id, username, and password. It provides methods for user authentication and creation, utilizing a repository for persistent storage and retrieval.

Variable:
id (int): The unique identifier for the user.
username (String): The username of the user.
password (String): The encrypted password of the user.
repository (Repository<User>): A static repository instance for managing User objects.

Constructor:
User(): Default constructor for the User class. Initializes a new instance of User with default values.
User(int id, String username, String password): Parameterized constructor for the User class. Initializes a new instance of User with the specified id, username, and password.

Method:
getPrimaryKey() (int): Overrides the getPrimaryKey method from the Model class to return the user's unique identifier.
getId() (int): Gets the unique identifier of the user.
setId(int id): Sets the unique identifier of the user.
getUsername() (String): Gets the username of the user.
setUsername(String username): Sets the username of the user.
getPassword() (String): Gets the encrypted password of the user.
setPassword(String password): Sets the encrypted password of the user.
authenticate(String username, String password) (boolean): Authenticates a user by comparing the provided username and password against the stored values. The password is encrypted before comparison.
createUser(String username, String password) (User):  Creates a new user with the specified username and password, encrypting the password before storing it. If a user with the same username already exists, a UserAlreadyExistsException is thrown.
```

```
Class: Crypto
The Crypto class provides utility methods for cryptographic operations. It currently supports encrypting text using the MD5 hashing algorithm. The Crypto class is typically used for hashing sensitive information such as passwords. For instance, when creating or authenticating a user in an application, passwords can be hashed using the encrypt method to ensure they are stored and compared securely.

Method:
encrypt(String text): Encrypts a given text using the MD5 hashing algorithm.

Method Details:
MD5 Hashing Technique: The MD5 (Message Digest Algorithm 5) hashing technique is a popular cryptographic hash function generating a 128-bit (16-byte) hash value. Commonly utilized for verifying data integrity and the secure handling and transmission of passwords, MD5, however, has been deemed cryptographically compromised and inadequate for continued use because of its susceptibility to collision attacks. Consequently, for applications requiring heightened security, it is advisable to employ more robust hashing algorithms such as SHA-256.

Class: JasonUtil
Utility class for handling JSON serialization and deserialization using Jackson. This class provides static methods to serialize objects to JSON and to deserialize JSON into objects.

Variable:
mapper: It is an ObjectMapper. A Jackson ObjectMapper instance used for JSON serialization and deserialization.

Method:
writeToJson(T object, String path): Serializes an object to JSON and saves it to the specified file path.
readFromJson(String path, Class<T> klass): Deserializes JSON content from a specified file into an object of the specified class.

Usage: The JsonUtil class facilitates the transformation of Java objects into JSON format for storage in files, and also handles the reverse process of reading JSON files to convert them back into Java objects. This functionality is especially beneficial for storing data and configurations in JSON format and subsequently retrieving this information into the application.

This JsonUtil class makes it easy to manage JSON data within an application, providing a simple interface for common serialization and deserialization tasks.
```

```
Class: HomeController
The HomeController class implements the Initializable interface, allowing it to initialize various UI components when the application starts. This class manages product displays in tables, handles user actions for adding/removing items from the cart, updates stock levels, and navigates to different application views.

FXML Variables:

@FXML private ScrollPane scrollPane: A scrollable pane that contains the product tables.
@FXML private VBox vboxContainer: A vertical box layout to hold the product tables.
@FXML private Label usernameLabel: Displays the username of the logged-in user.
@FXML private Label itemCountLabel: Displays the count of items in the invoice.
@FXML private Label totalCountLabel: Displays the total cost of items in the invoice.

Non-FXML Variables:
private Stage stage: Represents the current stage of the application. It is used for handling the window or screen.
private Scene scene: Represents the current scene within the stage. It is used for setting and managing the visual content.
private Parent root: Represents the root node of the current scene graph. It is used for loading and setting the root element of the scene.
private Inventory inventory: An instance of the Inventory class. It holds and manages the inventory data, which includes the different products available.
private Invoice invoice: An instance of the Invoice class. It manages the invoice data, including the products added to the cart and their details.
ArrayList<TableView<?>> tableViews: A list to hold references to the product tables. It helps manage and access the different tables displayed in the UI.

Methods:
public void setUsernameLabel(String username): Sets the username label.
public void setItemCountLabel(String itemCount): Sets the item count label.
public void setTotalCountLabel(String totalCount): Sets the total count label.
public void setInvoice(Invoice invoice): Sets the invoice instance.
public void setInventory(Inventory inventory): Sets the inventory instance.
public void initialize(URL location, ResourceBundle resources): Initializes the controller, sets the scroll pane to fit its width, and calls setTables() to set up product tables.
private void setTables(): Creates and configures the tables for different product types (Games, Movies, Music) and adds them to the UI.
private <T extends StockableProduct<?>> void setupTableForType(ArrayList<T> items, Class<T> type, String labelName): Configures the table for a specific product type, adds columns for product properties, and sets up action buttons for cart and stock management.
private <T extends StockableProduct<?>> void setupCommonColumns(TableView<T> tableView): Adds common columns (Name, Price, Genre, Year Published, Discount, Stock) to the table.
private <T extends StockableProduct<?>> void addTypeSpecificColumn(TableView<T> tableView, Class<T> type): Adds type-specific columns (Developer, Director, Artist Name) based on the product type.
public void goToInvoice(ActionEvent event) throws IOException: Navigates to the invoice view, passing current invoice and inventory data to the InvoiceController.
public void goToCreate(ActionEvent event) throws IOException: Navigates to the product creation view, passing current invoice and inventory data to the ProductCreatorController.
```

```
Class: InvoiceController
Non-FXML Variables:

private Stage stage: Represents the current stage of the application. It is used for handling the window or screen transitions.
private Scene scene: Represents the current scene within the stage. It is used for setting and managing the visual content.
private Parent root: Represents the root node of the current scene graph. It is used for loading and setting the root element of the scene.
private Inventory inventory: An instance of the Inventory class. It holds and manages the inventory data, which includes the different products available.
private Invoice invoice: An instance of the Invoice class. It manages the invoice data, including the products added to the cart and their details.

FXML Variables:

@FXML private Label usernameLabel: Displays the username of the logged-in user.
@FXML private Label itemCountLabel: Displays the count of items in the invoice.
@FXML private Label totalCountLabel: Displays the total cost of items in the invoice.
@FXML private ScrollPane scrollPane: A scrollable pane that contains the product tables.
@FXML private VBox vboxContainer: A vertical box layout to hold the product tables and invoice details.

Methods:
public void setUsernameLabel(String username): Sets the username label.
public void setItemCountLabel(String itemCount): Sets the item count label.
public void setTotalCountLabel(String totalCount): Sets the total count label.
public void setInvoice(Invoice invoice): Sets the invoice instance and calls setupProductTable() to display the invoice details.
public void setInventory(Inventory inventory): Sets the inventory instance.
public void initialize(URL location, ResourceBundle resources): Initializes the controller, sets the scroll pane to fit its width, and calls setupProductTable() to set up the product table.
private void setupProductTable(): Configures the table to display the invoice details. It adds columns for product name, ID, and price. It also displays labels for the invoice date, total price without discount, and total price with discount.
public void goToProduct(ActionEvent event) throws IOException: Navigates back to the invoice view, passing current invoice and inventory data to the InvoiceController.
```

```
Class: LoginController
The LoginController class manages the login process, including validating user credentials and navigating to the home screen or displaying an error message for invalid credentials. It also handles the navigation to the signup screen.

Non-FXML Variables:

private Stage stage: Represents the current stage of the application. It is used for handling the window or screen transitions.
private Scene scene: Represents the current scene within the stage. It is used for setting and managing the visual content.
private Parent root: Represents the root node of the current scene graph. It is used for loading and setting the root element of the scene.

FXML Variables:

@FXML private Label invalidLabel: A label used to display an error message if the login credentials are invalid.
@FXML private TextField usernameField: A text field where the user enters their username.
@FXML private TextField passwordField: A text field where the user enters their password.

Method:
public void login(ActionEvent event) throws IOException: Handles the login process.
public void moveToSignup(ActionEvent event) throws IOException: Handles the navigation to the signup screen.
```

```
Class: ProductCreatorController
The ProductCreatorController class manages the creation of new products, validates the input data, and adds the products to the inventory. It also handles navigation to the home screen.
Non-FXML Variables:

private Stage stage: Represents the current stage of the application. It is used for handling the window or screen transitions.
private Scene scene: Represents the current scene within the stage. It is used for setting and managing the visual content.
private Parent root: Represents the root node of the current scene graph. It is used for loading and setting the root element of the scene.
ObservableList<String> categories: An observable list containing the categories of products available for selection.

FXML Variables:

@FXML private TextField nameField: TextField for entering the name of the product.
@FXML private TextField priceField: TextField for entering the price of the product.
@FXML private TextField genreField: TextField for entering the genre of the product.
@FXML private TextField discountField: TextField for entering the discount percentage of the product.
@FXML private TextField productIdField: TextField for entering the ID of the product.
@FXML private ChoiceBox<String> category: ChoiceBox for selecting the category of the product.
@FXML private TextField creatorField: TextField for entering the creator/developer/director of the product.
@FXML private TextField yearPublishedField: TextField for entering the year of publication/release of the product.
@FXML private Label errorLabel: Label to display error messages.
@FXML private Label successLabel: Label to display success messages.
@FXML private Label usernameLabel: Label to display the username of the logged-in user.

Methods:
setUsernameLabel: Sets the username label.
setInventory: Sets the inventory instance.
setInvoice: Sets the invoice instance.
initialize: Initializes the controller by setting the categories in the choice box.
goToProduct: Navigates to the home screen.
createProduct: Handles the creation of a new product and adds it to the inventory.
```

```
Class: SignupController
The SignupController class manages the user signup process, including input validation, account creation, and navigation to the login screen.

Variable:
stage: The current stage (window) of the application. Used to control the window.
scene: The current scene being displayed in the stage. Used to control the content of the window.
root: The root node of the scene graph. Used to load and set the root element of the scene.

Methods:
signup: Handles the user signup process, including input validation, account creation, and navigation.
moveToLogin: Navigates to the login screen, allowing users to log in with their newly created account.
```

## Application/Use of the Project:

Product Management:
- Ability to add, modify, or delete items such as games, movies, and music in the inventory.
- Keep track of stock quantities and manage details like price, genre, and release year.

User Management:
- Support for creating user accounts with unique identifiers and secure, encrypted passwords.
- Secure management of user operations.

Invoice Generation:
- Creation of invoices for items sold.
- Calculation of totals and discounts, including special promotions like "Full House" discounts.

Invoice Printing:
- Direct print invoice from the software

Storage System:
- Utilization of JSON files for the durable storage of item and user data.
- Data retrieval and caching for efficient performance.

Inventory Control:
- Functions to adjust stock levels for items.
- Monitoring of inventory quantities and organization of items by price and availability.

Limitations:

Scalability:
- The reliance on local JSON files for data storage may not be optimal for larger operations with extensive inventories.
- Potential performance issues as inventory size grows without a more sophisticated database solution.

Security:
- Current use of MD5 for password encryption is outdated. More advanced methods like bcrypt or Argon2 are recommended.
- Lack of advanced security features such as user role management and permissions.

Concurrency:
- The system does not efficiently manage simultaneous data access or edits, risking data integrity in multi-user scenarios.

Error Management:
- Limited mechanisms for error handling and user notifications need enhancement for better stability and user experience.

User Interaction:
- Absence of a graphical user interface (GUI), with reliance on command-line interactions or direct method invocations.

Future Work:

Database Integration:
- Transition to database systems (relational or NoSQL) for improved scalability and efficiency.
- Incorporation of an ORM layer for streamlined database interactions.

Security Enhancements:
- Upgrade to more secure password hashing algorithms.
- Implement access controls based on user roles to limit functionality access.

Concurrency Management:
- Introduce controls for managing concurrent data access and modifications.
- Implement transaction management for data consistency.

Interface Development:
- Design and develop a user-friendly GUI for easier interaction, using desktop or web technologies.

APIs and Integration:
- Create APIs (RESTful or GraphQL) for external system integration, facilitating broader application use.

Analytics and Reporting:
- Add reporting capabilities for insights into sales, inventory, and user activities.
- Leverage analytics for strategic decision-making and trend forecasting.

Testing and Reliability:
- Establish a comprehensive suite of automated tests for system reliability.
- Utilize frameworks like JUnit for backend testing and potentially Selenium for UI testing.

Cloud Deployment:
- Explore options for cloud hosting to enhance accessibility and scalability.
- Consider platforms like AWS, Google Cloud, or Azure for application and database hosting.

By addressing these challenges and incorporating the proposed future developments, the Inventory Management System can significantly improve, becoming a more secure, efficient, and user-friendly tool that caters to a broader audience.



Conclusion: This project demonstrated the effective application of JAVA’s object oriented principles to solve real- world problems. By delivering a customizable and scalable software solution, the Vinyl & CD shop was equipped with the necessary tools to manage its inventory and billing processes efficiently, paving the way for future growth and success.


## Contribution:
The Project on Inventory Management System was a Group Project, successfully undertaken with the collective efforts of four team members:

- Saadman Ibrahim Chowdhury
- Khan Asfi Reza
- Md. Abrar Mahbub Fida
- Fateh Tus Saad

Each individual contributed their distinct skills and knowledge to the project, playing a crucial role in its successful realization.

Saadman Ibrahim Chowdhury and Md. Abrar Mahbub Fida worked on the front end with JavaFX. Khan Asfi Reza designed the backend and added security features. Fateh Tus Saad oversaw the project , wrote the final report and implemented a UML diagram from the given description.


