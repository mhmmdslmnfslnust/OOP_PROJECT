# Software Design Document - OOP_PROJECT E-Commerce System

## Table of Contents
- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Architecture Overview](#architecture-overview)
- [Design Principles](#design-principles)
- [Design Patterns](#design-patterns)
  - [Creational Patterns](#creational-patterns)
  - [Structural Patterns](#structural-patterns)
  - [Behavioral Patterns](#behavioral-patterns)
- [Design Patterns by Module Coverage](#design-patterns-by-module-coverage)
- [Module Structure](#module-structure)
  - [Core Module](#core-module)
  - [UI Module](#ui-module)
  - [Data Access Module](#data-access-module)
  - [Service Module](#service-module)
- [Class Diagrams](#class-diagrams)
- [Sequence Diagrams](#sequence-diagrams)
- [Data Flow](#data-flow)
- [Error Handling Strategy](#error-handling-strategy)
- [Testing Approach](#testing-approach)
- [Future Enhancements](#future-enhancements)

## Introduction

This document outlines the architectural design and design patterns implemented in our Object-Oriented Programming project - an e-commerce web application built using Spring Boot. The design follows SOLID principles and utilizes various design patterns to ensure code maintainability, scalability, and robustness.

## Project Structure

Our project follows a standard Spring Boot application structure:

```
OOP_PROJECT/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── oopproject/
│   │   │               ├── OopprojectApplication.java     # Main application entry point
│   │   │               ├── configuration/                 # Configuration classes
│   │   │               ├── controller/                    # MVC Controllers
│   │   │               ├── model/                         # Domain entities
│   │   │               ├── dto/                           # Data Transfer Objects
│   │   │               ├── repository/                    # Data access interfaces
│   │   │               ├── service/                       # Business logic services
│   │   │               └── global/                        # Global utilities
│   │   ├── resources/
│   │   │   ├── static/                                    # Static resources (JS, CSS)
│   │   │   ├── templates/                                 # Thymeleaf templates
│   │   │   └── application.properties                     # Application configuration
│   └── test/
│       └── java/                                          # Test classes
...
```

For complete structure see file [STRUCTURE.md](STRUCTURE.md)

## Architecture Overview

The system follows a layered architecture with clear separation of concerns:

1. **Presentation Layer** - Implemented through Spring MVC Controllers and Thymeleaf templates
   - Handles user interface and user interactions
   - Location: `src/main/java/com/example/oopproject/controller/` and `src/main/resources/templates/`

2. **Business Logic Layer** - Implemented through Service classes
   - Implements core application logic and business rules
   - Location: `src/main/java/com/example/oopproject/service/`

3. **Data Access Layer** - Implemented through Repository interfaces using Spring Data JPA
   - Manages data persistence and retrieval
   - Location: `src/main/java/com/example/oopproject/repository/`

4. **Domain Model Layer** - Implemented through JPA Entity classes
   - Defines core business entities and their relationships
   - Location: `src/main/java/com/example/oopproject/model/`

The architecture enforces loose coupling between components through dependency injection provided by Spring Framework.

## Design Principles

The project adheres to the following key OOP principles:

1. **Single Responsibility Principle (SRP)**
   - Each class has only one reason to change
   - Example: `ProductService.java` handles only product-related business logic

2. **Open/Closed Principle (OCP)**
   - Classes are open for extension but closed for modification
   - Example: Spring's repository system allows extending functionality without modifying base classes

3. **Liskov Substitution Principle (LSP)**
   - Derived classes can substitute their base classes
   - Example: Custom user details implement Spring Security interfaces

4. **Interface Segregation Principle (ISP)**
   - Clients should not depend on interfaces they don't use
   - Example: Repository interfaces are specific to each entity type

5. **Dependency Inversion Principle (DIP)**
   - High-level modules should not depend on low-level modules
   - Example: Controllers depend on Service abstractions, not implementations

## Design Patterns

### Creational Patterns

#### Singleton Pattern
- **Implementation**: Used in Spring components through Spring's IoC container
- **Files**: All classes annotated with `@Component`, `@Service`, `@Controller`, `@Repository`
- **Purpose**: Ensures only one instance of service/repository classes exists

#### Factory Method Pattern
- **Implementation**: Used in various Spring components and repository creation
- **Files**: Spring creates repositories based on interfaces defined in `src/main/java/com/example/oopproject/repository/`
- **Purpose**: Centralizes object creation logic and abstracts instantiation process

#### Builder Pattern
- **Implementation**: Used for complex object creation like ProductDTO
- **Files**: `src/main/java/com/example/oopproject/dto/ProductDTO.java`
- **Purpose**: Separates construction of complex objects from their representation

### Structural Patterns

#### Adapter Pattern
- **Implementation**: Used for integrating with Spring Security and external services
- **Files**: `src/main/java/com/example/oopproject/service/CustomUserDetailService.java`
- **Purpose**: Adapts our User model to Spring Security's UserDetails interface

#### Composite Pattern
- **Implementation**: Used in UI template structure and component hierarchies
- **Files**: Thymeleaf templates in `src/main/resources/templates/`
- **Purpose**: Treats individual objects and compositions uniformly

#### Facade Pattern
- **Implementation**: Service layer acts as a facade to complex subsystems
- **Files**: `src/main/java/com/example/oopproject/service/ProductService.java`, `CategoryService.java`
- **Purpose**: Provides a simplified interface to complex underlying systems

### Behavioral Patterns

#### Observer Pattern
- **Implementation**: Used in Spring event system and model updates
- **Files**: Event listeners throughout the application
- **Purpose**: Implements one-to-many dependency between objects

#### Strategy Pattern
- **Implementation**: Used for implementing different business rules
- **Files**: Authentication strategies in `src/main/java/com/example/oopproject/configuration/`
- **Purpose**: Defines a family of algorithms and makes them interchangeable

#### Command Pattern
- **Implementation**: Used for implementing controller actions
- **Files**: Controller methods in `src/main/java/com/example/oopproject/controller/`
- **Purpose**: Encapsulates requests as objects

## Design Patterns by Module Coverage

This section provides a detailed mapping of design patterns used in each module of the OOP_PROJECT codebase.

### configuration/ Module

1. **Strategy Pattern**
   - **Files**: `SecurityConfig.java`
   - **Purpose**: Defines different authentication strategies (form login, OAuth2)
   - **Implementation**: SecurityFilterChain configurers allow swapping different authentication mechanisms

2. **Chain of Responsibility**
   - **Files**: `SecurityConfig.java`
   - **Purpose**: Security filters form a chain where each filter handles a specific aspect of security
   - **Implementation**: Spring Security filter chain processes authentication/authorization requests sequentially

3. **Observer Pattern**
   - **Files**: `GoogleOAuth2SuccessHandler.java`
   - **Purpose**: Reacts to successful authentication events
   - **Implementation**: `AuthenticationSuccessHandler` observes authentication success events

### controller/ Module

1. **Front Controller Pattern**
   - **Files**: All controller classes (`AdminController.java`, `HomeController.java`, `LoginController.java`, etc.)
   - **Purpose**: Centralizes request handling through Spring MVC's DispatcherServlet
   - **Implementation**: `@Controller` classes handle specific URL endpoints

2. **Command Pattern**
   - **Files**: All controller methods with `@GetMapping`, `@PostMapping`, etc.
   - **Purpose**: Encapsulates requests as methods, allowing for different actions on resources
   - **Implementation**: Controller methods with HTTP verb annotations act as commands

3. **MVC Pattern**
   - **Files**: All controller classes
   - **Purpose**: Separates presentation from business logic
   - **Implementation**: Controllers interact with services and update the model for view rendering

### model/ Module

1. **Entity Pattern** (Domain Model)
   - **Files**: `User.java`, `Role.java`, `Product.java`, `Category.java`
   - **Purpose**: Represents domain concepts as classes with attributes and behaviors
   - **Implementation**: JPA entities with annotations

2. **Composite Pattern**
   - **Files**: `User.java` and `Role.java`
   - **Purpose**: Represents the many-to-many relationship between users and roles
   - **Implementation**: Users contain collections of roles, creating a hierarchical structure

3. **Builder Pattern** (indirectly via Lombok)
   - **Files**: Classes annotated with `@Data`
   - **Purpose**: Simplifies object construction through generated builder methods
   - **Implementation**: Lombok `@Data` generates setters for building objects

### dto/ Module

1. **Data Transfer Object Pattern**
   - **Files**: `ProductDTO.java` (inferred from `AdminController.java`)
   - **Purpose**: Transfers data between processes, reducing method calls
   - **Implementation**: Plain Java objects with properties matching the data needed for specific operations

2. **Adapter Pattern**
   - **Files**: DTO transformations in service or controller classes
   - **Purpose**: Converts between domain models and DTOs
   - **Implementation**: Methods that map entity properties to/from DTOs

### repository/ Module

1. **Repository Pattern**
   - **Files**: All interfaces extending `JpaRepository` (`UserRepository.java`, `ProductRepository.java`, etc.)
   - **Purpose**: Abstracts data access logic, providing collection-like interface to domain objects
   - **Implementation**: Spring Data JPA repositories

2. **Factory Method Pattern**
   - **Files**: Repository interfaces
   - **Purpose**: Creates repository implementations at runtime
   - **Implementation**: Spring Data JPA dynamically generates implementations of repository interfaces

3. **Proxy Pattern**
   - **Files**: Generated repository implementations
   - **Purpose**: Provides additional functionality (transactions, caching) around repository operations
   - **Implementation**: Spring uses proxies for repositories to add transaction management

### service/ Module

1. **Facade Pattern**
   - **Files**: Service classes (`ProductService.java`, `CategoryService.java`, etc.)
   - **Purpose**: Provides a simplified interface to complex subsystems
   - **Implementation**: Service classes that orchestrate interactions between repositories and other components

2. **Singleton Pattern**
   - **Files**: All `@Service` classes
   - **Purpose**: Ensures a single instance of service objects
   - **Implementation**: Spring IoC container manages service instances as singletons by default

3. **Template Method Pattern**
   - **Files**: `CustomUserDetailService.java`
   - **Purpose**: Defines skeleton of operations, deferring some steps to subclasses
   - **Implementation**: Implements `UserDetailsService` with a template method for loading users

### global/ Module

1. **Singleton Pattern**
   - **Files**: `GlobalData.java`
   - **Purpose**: Maintains global application state (shopping cart)
   - **Implementation**: Static fields provide a single point of access for cart data across the application

2. **Registry Pattern**
   - **Files**: `GlobalData.java`
   - **Purpose**: Serves as a centralized storage for shopping cart data
   - **Implementation**: Static collection maintains application-wide shopping cart state

### templates/ (resources) Module

1. **Template Method Pattern**
   - **Files**: All Thymeleaf templates (`shop.html`, `cart.html`, etc.)
   - **Purpose**: Defines the skeleton of page rendering with specific sections customized per view
   - **Implementation**: Base layouts with fragment inclusions for customized sections

2. **Composite Pattern**
   - **Files**: Thymeleaf templates with nested fragments
   - **Purpose**: Composes UI elements into hierarchical structures
   - **Implementation**: Templates composed of smaller reusable fragments

3. **Decorator Pattern**
   - **Files**: CSS styling in templates
   - **Purpose**: Adds visual behaviors to UI elements
   - **Implementation**: CSS classes applied to HTML elements to enhance appearance

### static/ (resources) Module

No classic GoF design patterns are directly applicable to static resources (CSS, JavaScript, images). These files support the presentation layer but don't exhibit object-oriented design patterns themselves.

### application.properties

While not implementing a GoF design pattern, this file follows the **External Configuration** pattern, allowing runtime behavior to be configured without code changes.

### src/test/ Module

1. **Test Fixture Pattern**
   - **Files**: `OopprojectApplicationTests.java` and other test classes
   - **Purpose**: Sets up known good state before test execution
   - **Implementation**: `@BeforeEach` or similar methods establishing test preconditions

2. **Mock Object Pattern**
   - **Files**: Test classes using mock frameworks
   - **Purpose**: Simulates complex dependencies for isolated testing
   - **Implementation**: Using frameworks like Mockito to create test doubles

3. **Test Data Builder**
   - **Files**: Test helper methods creating test data
   - **Purpose**: Creates complex test objects with minimal code
   - **Implementation**: Builder methods for constructing test entities

## Module Structure

### Core Module

The Core Module contains the domain model and core business logic:

- **Domain Entities**: JPA annotated entity classes
  - **Location**: `src/main/java/com/example/oopproject/model/`
  - **Design Patterns**: Factory Method (through JPA)
  - **Key Classes**: 
    - `User.java`: Represents authenticated users
    - `Product.java`: Represents products for sale
    - `Category.java`: Represents product categories
    - `Role.java`: Represents user roles for authorization

- **Service Layer**: Contains business logic implementation
  - **Location**: `src/main/java/com/example/oopproject/service/`
  - **Design Patterns**: Facade, Singleton (through Spring)
  - **Key Classes**: 
    - `ProductService.java`: Manages product-related operations
    - `CategoryService.java`: Manages category-related operations
    - `CustomUserDetailService.java`: Manages user authentication

### UI Module

The UI Module handles all user interface components:

- **View Controllers**: Spring MVC Controllers
  - **Location**: `src/main/java/com/example/oopproject/controller/`
  - **Design Patterns**: Front Controller (via Spring MVC)
  - **Key Classes**: 
    - `AdminController.java`: Manages admin operations
    - `HomeController.java`: Manages main site navigation
    - `LoginController.java`: Handles user authentication
    - `CartController.java`: Manages shopping cart operations

- **UI Templates**: Thymeleaf templates
  - **Location**: `src/main/resources/templates/`
  - **Design Patterns**: Template Method
  - **Key Files**: 
    - `index.html`: Main landing page
    - `adminHome.html`: Admin dashboard
    - `products.html`: Product management
    - `categories.html`: Category management
    - `checkout.html`: Checkout process

### Data Access Module

The Data Access Module manages data persistence:

- **Repositories**: Spring Data JPA repositories
  - **Location**: `src/main/java/com/example/oopproject/repository/`
  - **Design Patterns**: Repository, Factory Method (via Spring Data)
  - **Key Classes**: 
    - `UserRepository.java`: Manages user data access
    - `ProductRepository.java`: Manages product data access
    - `CategoryRepository.java`: Manages category data access
    - `RoleRepository.java`: Manages role data access

- **Data Transfer Objects**: Transfer data between layers
  - **Location**: `src/main/java/com/example/oopproject/dto/`
  - **Design Patterns**: Data Transfer Object
  - **Key Classes**: 
    - `ProductDTO.java`: Transfers product data between layers

### Service Module

The Service Module provides utilities and cross-cutting concerns:

- **Security**: Authentication and authorization
  - **Location**: `src/main/java/com/example/oopproject/configuration/`
  - **Design Patterns**: Strategy, Chain of Responsibility
  - **Key Classes**: 
    - `SecurityConfig.java`: Configures security rules
    - `GoogleOAuth2SuccessHandler.java`: Handles OAuth authentication

- **Global Data**: Application-wide state
  - **Location**: `src/main/java/com/example/oopproject/global/`
  - **Design Patterns**: Singleton
  - **Key Classes**: 
    - `GlobalData.java`: Stores global state like shopping cart

## Class Diagrams

### Core Domain Model

```
+----------------+     +----------------+     +----------------+
|     User       |     |     Role       |     |    Product     |
+----------------+     +----------------+     +----------------+
| - id: Integer  |     | - id: Integer  |     | - id: Long     |
| - firstName    |<>-->| - name         |     | - name         |
| - lastName     |     |                |     | - price        |
| - email        |     +----------------+     | - weight       |
| - password     |                            | - description  |
+----------------+                            | - imageName    |
                                              +-------+--------+
                                                      |
                                                      |
                                              +-------v--------+
                                              |   Category     |
                                              +----------------+
                                              | - id: int      |
                                              | - name: String |
                                              +----------------+
```

### Service Layer

```
+----------------+     +----------------+     +----------------+
| ProductService |     | CategoryService|     | UserDetailSvc  |
+----------------+     +----------------+     +----------------+
| - productRepo  |     | - categoryRepo |     | - userRepo     |
| + getAllProduct|     | + getAllCat    |     | + loadUserBy   |
| + addProduct   |     | + addCategory  |     |   Username     |
| + removeProduct|     | + removeCategory|    |                |
+----------------+     +----------------+     +----------------+
```

### Controller Layer

```
+----------------+     +----------------+     +----------------+     +----------------+
| AdminController|     | HomeController |     |LoginController|     | CartController |
+----------------+     +----------------+     +----------------+     +----------------+
| - productSvc   |     | - categorySvc  |     | - encoder      |     | - productSvc   |
| - categorySvc  |     | - productSvc   |     | - userRepo     |     | + addToCart    |
| + adminHome()  |     | + home()       |     | + login()      |     | + removeItem   |
| + getCat()     |     | + shop()       |     | + register()   |     | + checkout     |
| + products()   |     | + viewProduct()|     |                |     |                |
+----------------+     +----------------+     +----------------+     +----------------+
```

## Sequence Diagrams

### User Authentication Flow

```
┌──────┐        ┌───────────┐           ┌───────────────┐        ┌────────────┐
│Client│        │LoginScreen│           │LoginController│        │UserRepo    │
└──┬───┘        └─────┬─────┘           └──────┬────────┘        └──────┬─────┘
   │  Login Request   │                        │                        │
   │─────────────────>│                        │                        │
   │                  │     loginPost()        │                        │
   │                  │───────────────────────>│                        │
   │                  │                        │     findUserByEmail()  │
   │                  │                        │───────────────────────>│
   │                  │                        │     User Object        │
   │                  │                        │<───────────────────────│
   │                  │                        │                        │
   │                  │                        │  Validate Password     │
   │                  │                        │──────────────┐         │
   │                  │                        │<─────────────┘         │
   │                  │    Auth Result         │                        │
   │                  │<───────────────────────│                        │
   │  Redirect to Home│                        │                        │
   │<─────────────────│                        │                        │
┌──┴───┐        ┌─────┴─────┐           ┌──────┴────────┐        ┌──────┴─────┐
│Client│        │LoginScreen│           │LoginController│        │UserRepo    │
└──────┘        └───────────┘           └───────────────┘        └────────────┘
```

### Shopping Cart Process

```
┌──────┐          ┌───────────┐          ┌─────────────┐          ┌────────────┐
│Client│          │ProductPage│          │CartController│         │GlobalData  │
└──┬───┘          └─────┬─────┘          └──────┬──────┘          └──────┬─────┘
   │  Add to Cart      │                        │                        │
   │─────────────────>│                        │                        │
   │                  │     addToCart()         │                        │
   │                  │───────────────────────>│                        │
   │                  │                        │     add(product)        │
   │                  │                        │───────────────────────>│
   │                  │                        │     Confirmation       │
   │                  │                        │<───────────────────────│
   │                  │    Redirect            │                        │
   │                  │<───────────────────────│                        │
   │  Shop Page       │                        │                        │
   │<─────────────────│                        │                        │
┌──┴───┐          ┌─────┴─────┐          ┌──────┴──────┐          ┌──────┴─────┐
│Client│          │ProductPage│          │CartController│         │GlobalData  │
└──────┘          └───────────┘          └─────────────┘          └────────────┘
```

## Data Flow

1. **Product Management Flow**:
   - Admin input → AdminController → ProductService → ProductRepository → Database
   - Implementation: AdminController methods call ProductService methods which use ProductRepository for database operations

2. **Shopping Flow**:
   - User browses products → Select product → Add to cart → Checkout
   - Implementation: HomeController shows products, CartController manages cart operations using GlobalData

3. **Authentication Flow**:
   - User credentials → LoginController → CustomUserDetailService → UserRepository
   - Implementation: Uses Spring Security filter chain with custom UserDetailsService implementation

## Error Handling Strategy

The application implements a multi-layered error handling approach:

1. **Presentation Layer**: 
   - User-friendly error messages and input validation
   - Implementation: Thymeleaf templates with validation messages
   - Location: `src/main/resources/templates/error/`

2. **Business Logic Layer**: 
   - Domain exceptions with semantic meaning
   - Implementation: Service layer exceptions
   - Location: Controller exception handlers

3. **Data Access Layer**: 
   - Technical exceptions and retry mechanisms
   - Implementation: Repository exception handling
   - Location: Service layer try-catch blocks

4. **Global Exception Handling**: 
   - Centralized error handling through Spring's @ExceptionHandler
   - Implementation: ErrorController
   - Location: `src/main/java/com/example/oopproject/controller/ErrorController.java`

## Testing Approach

The project employs a comprehensive testing strategy:

1. **Unit Testing**: 
   - Tests individual components in isolation
   - Location: `src/test/java/com/example/oopproject/`
   - Example: `OopprojectApplicationTests.java`

2. **Integration Testing**: 
   - Tests interactions between components
   - Implementation: Spring's testing framework
   - Focus: Repository and Service layer interaction

3. **End-to-End Testing**: 
   - Tests the system as a whole
   - Implementation: Browser automation tests
   - Focus: Critical user flows like checkout process

## Future Enhancements

Planned architectural improvements:

1. **Payment Integration**:
   - Integration with payment gateways like Stripe or PayPal
   - Implementation: New service layer for payment processing
   - Design patterns: Strategy pattern for different payment methods

2. **Enhanced User Profiles**:
   - More detailed user profile management
   - Implementation: Extended User model and dedicated profile service
   - Design patterns: Builder pattern for complex profile objects

3. **Order Management System**:
   - Comprehensive order tracking and management
   - Implementation: New Order domain model and service layer
   - Design patterns: State pattern for order status management
