# Restaurant Management System (Projekt 3 - JSON Persistence & Full CRUD)

## Overview
A Spring Boot web application implementing a layered MVC architecture (`Model` - `Repository` - `Service` - `Controller` - `View`) with persistent JSON file storage and full CRUD operations via Thymeleaf templates.

## Key Features Implemented in Project 3
- **Generic JSON Repository (`InFileRepository<T>`)**: Replaces `InMemoryRepository` while implementing `IRepository<T, String>`. Automatically reads and writes entity lists to JSON files using Jackson `ObjectMapper` and guarantees unique, valid String IDs (UUID).
- **Persistent Data Files (`src/main/resources/data/`)**: Dedicated JSON files (`customer.json`, `table.json`, `menuitem.json`, `chef.json`, `server.json`, `order.json`, `bill.json`, `orderassignment.json`), each initialized with 10 valid records.
- **Full CRUD Web Interface (Thymeleaf)**: Every entity supports Listing (`GET /<entity>`), Creating (`GET /<entity>/new`, `POST /<entity>`), Viewing Details (`GET /<entity>/{id}`), Editing/Updating (`GET /<entity>/{id}/edit`, `POST /<entity>/{id}`), and Deleting (`POST /<entity>/{id}/delete`).

## Running the Application
1. Ensure JDK 17+ and Maven are installed.
2. Run `RestaurantApplication.java` or execute `mvn spring-boot:run`.
3. Open `http://localhost:8080/` in your browser.
