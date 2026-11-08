# Restaurant Management System (Theme 7)

## Project Description

This project is a Java Spring Boot application developed as part of Project Task 1 (Projektaufgabe 1). The objective of the system is to manage restaurant operations, including customers, tables, menus, orders, staff assignments, and bill generation.

The application currently utilizes an In-Memory data storage strategy (without a relational database at this stage) and adheres to a clear layered architecture, demonstrating core Object-Oriented Programming (OOP) and Clean Code principles.

## Application Architecture (Layered Architecture)

The project is organized under the primary package `restaurantmanagement` and is divided into the following layers:

1. **`model`**
    - Contains the domain entities extracted directly from the UML diagram:
        - **Staff Hierarchy:** The abstract class `Staff` extended by `Chef` and `Server`.
        - **Core Entities:** `Customer`, `Table`, `MenuItem`.
        - **Relational / Transactional Entities:** `Order`, `OrderLine`, `OrderAssignment`, `Bill`.
    - All classes follow proper encapsulation (private fields, getter/setter methods, and constructors).

2. **`repository`**
    - Manages runtime data stored In-Memory.
    - Employs `Map<String, Entity>` collections to allow fast $O(1)$ access, save, and delete operations by ID.
    - Implemented Repositories: `CustomerRepository`, `MenuItemRepository`, `OrderRepository`, `TableRepository`.

3. **`service`**
    - Houses the core business logic (e.g., `OrderService`).
    - Handles order creation, adding items to orders, and calculating the total amount for the bill (`Bill`).

4. **`controller`**
    - The HTTP interface layer of the application.
    - `HelloController` exposes the `/hello` endpoint to verify the status and execution of the embedded Apache Tomcat web server.

## Model Extensions (Requirement 5)

In accordance with the project specification, 1-2 new properties were added to 3 classes in the model layer:

1. **`Customer`**: Added the `phoneNumber` (`String`) attribute for contacting customers regarding reservations.
2. **`Table`**: Added the `capacity` (`int`) attribute specifying the maximum number of seats at a table.
3. **`MenuItem`**: Added the `category` (`String`) attribute to categorize menu items (e.g., *Appetizer*, *Main Course*, *Dessert*, *Beverage*).

## Applied SOLID and Clean Code Principles

- **Single Responsibility Principle (SRP):** Each class has one distinct responsibility (classes in `model` store data, `repository` classes handle storage, and `service` classes manage business logic).
- **Open/Closed Principle (OCP):** The abstract class `Staff` enables easy extension with new employee types (e.g., `Bartender`, `Manager`) without modifying existing code.
- **Liskov Substitution Principle (LSP):** Objects of type `Chef` and `Server` can be substituted wherever a `Staff` object is expected.
- **Dependency Inversion Principle (DIP):** Services depend on repositories injected via constructors.
- **KISS & DRY:** Simple, maintainable code without unnecessary duplication.

## Running the Application

### Prerequisites:
- **JDK 17** or newer
- **IntelliJ IDEA** (Community or Ultimate Edition)
- **Maven** (integrated into IntelliJ IDEA)

### Execution Steps:
1. Clone the repository from GitHub:
   ```
   git clone <github-repository-link>
   ```
2. Open the project in IntelliJ IDEA as a Maven project.
3. Allow Maven to download the required dependencies specified in `pom.xml`.
4. Run the main class:
   `restaurantmanagement`
5. Open a web browser and navigate to:
   ```
   http://localhost:8080/hello
   ```
6. Expected response in browser:
   ```
   Die Anwendung funktioniert!
   ```

## Team Collaboration (Git & Pushes)

The project was developed collaboratively on GitHub, featuring distinct commits from both team members to demonstrate equitable contributions across architecture, models, repositories, and services.