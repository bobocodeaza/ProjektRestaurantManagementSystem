# Restaurant Management System (Theme 7)

## Project Description

This project is a Java Spring Boot application developed for Project Task 1 and Project Task 2 (Projektaufgabe 1 & 2). The system manages restaurant operations, including customers, tables, menu items, staff, orders, and bills. 

The application utilizes an In-Memory data storage strategy (without a relational database) and features a Web User Interface built with Thymeleaf. It adheres to a strict Layered Architecture (MVC) and demonstrates core Object-Oriented Programming (OOP) and Clean Code principles.

## Application Architecture (Layered Architecture)

The project is organized under the primary package `com.example.restaurantmanagement` and is divided into the following layers:

1. **`model`**
   * Contains the domain entities extracted directly from the UML diagram (e.g., `Customer`, `Table`, `Order`, `Staff`).
   * Includes Enum types for strict state management (`OrderStatus`, `TableStatus`).
   * All classes follow proper encapsulation (private fields, getter/setter methods, and constructors).

2. **`repository`**
   * Manages runtime data stored In-Memory.
   * Utilizes a generic base class `InMemoryRepository<T, ID>` that implements the `IRepository<T, ID>` interface to eliminate code duplication (DRY principle).
   * Employs `Map<ID, Entity>` collections to allow fast O(1) access.

3. **`service`**
   * Houses the core business logic (e.g., `OrderService`, `CustomerService`).
   * Ensures safe operations by validating the existence of relations (e.g., checking if a Customer and Table exist before creating an Order).

4. **`controller` (Web UI Layer)**
   * Contains Web Controllers (e.g., `CustomerWebController`, `OrderWebController`) annotated with `@Controller`.
   * Intercepts HTTP requests and routes them to the appropriate Service.
   * Connects the backend logic to the frontend Thymeleaf HTML templates.

## Web Interface (Projektaufgabe 2)

The application features a frontend generated via Spring Boot and Thymeleaf. 
Templates are located in `src/main/resources/templates/`.

* **Dashboard:** The root URL (`/`) provides a central navigation menu to manage all entities.
* **Supported Operations (CRUD):** 
  * **Read (GET):** List views for all entities displaying current records in tables.
  * **Create (POST):** HTML forms (`form.html`) to add new objects. Foreign keys (like assigning a customer to an order) are handled via dropdown selection lists.
  * **Delete (POST):** A safe delete mechanism using POST requests via a button in the list views.
* **Exclusions:** In accordance with the project requirements, update/edit operations, individual detail views, and complex form validations are intentionally omitted at this stage.

## Model Extensions (Projektaufgabe 1)

In accordance with the project specification, new properties were added to models:
1. **`Customer`**: Added `phoneNumber` (`String`) for contact purposes.
2. **`Table`**: Added `capacity` (`int`) specifying the maximum seats.
3. **`MenuItem`**: Added `category` (`String`) to categorize items.

## Applied Principles

* **Single Responsibility Principle (SRP):** Clear separation between Data (Model), Storage (Repository), Logic (Service), and Routing (Controller).
* **Interface Segregation & Generics:** The `IRepository` contract ensures all repositories share a uniform method signature.
* **Polymorphism:** The abstract `Staff` class is correctly inherited by `Chef` and `Server`, each having their respective persistence layers.

## Running the Application

### Prerequisites
* **JDK 17** or newer
* **IntelliJ IDEA**
* **Maven**

### Execution Steps
1. Clone the repository from GitHub.
2. Open the project in IntelliJ IDEA as a Maven project.
3. Run the main class: `RestaurantApplication.java`.
4. Open a web browser and navigate to the dashboard:
   ```
   http://localhost:8080/
   ```
5. Use the UI to create, view, and delete records. Data will persist as long as the server is running.