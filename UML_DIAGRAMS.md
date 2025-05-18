# UML Diagrams for OOP_PROJECT E-Commerce System

This document contains various UML diagrams to illustrate the architecture, classes, and interactions in the OOP_PROJECT e-commerce system.

## Table of Contents
- [Domain Model Class Diagram](#domain-model-class-diagram)
- [MVC Architecture Diagram](#mvc-architecture-diagram)
- [Service and Repository Layer](#service-and-repository-layer)
- [Package Structure](#package-structure)
- [Authentication Flow](#authentication-flow)
- [Shopping Cart Flow](#shopping-cart-flow)
- [Admin Management Flow](#admin-management-flow)

## Domain Model Class Diagram

```plantuml
@startuml "Domain Model"
skinparam classAttributeIconSize 0
hide circle

package "com.example.oopproject.model" {
    class User {
        -Integer id
        -String firstName
        -String lastName
        -String email
        -String password
        +User(User user)
        +User()
    }
    
    class Role {
        -Integer id
        -String name
    }
    
    class Product {
        -Long id
        -String name
        -Double price
        -Double weight
        -String description
        -String imageName
    }
    
    class Category {
        -int id
        -String name
    }
    
    class CustomUserDetail {
        -User user
        +CustomUserDetail(User user)
        +getAuthorities(): Collection<? extends GrantedAuthority>
        +getPassword(): String
        +getUsername(): String
        +isAccountNonExpired(): boolean
        +isAccountNonLocked(): boolean
        +isCredentialsNonExpired(): boolean
        +isEnabled(): boolean
    }
}

User "1" *-- "many" Role : has >
Product "*" --> "1" Category : belongs to >
CustomUserDetail --> "1" User : wraps >

@enduml
```

## MVC Architecture Diagram

```plantuml
@startuml "MVC Architecture"
skinparam packageStyle rectangle
skinparam componentStyle uml2

package "View Layer" {
    [Thymeleaf Templates] as View
}

package "Controller Layer" {
    [HomeController] as HC
    [AdminController] as AC
    [CartController] as CC
    [LoginController] as LC
    [ErrorController] as EC
}

package "Service Layer" {
    [ProductService] as PS
    [CategoryService] as CS
    [CustomUserDetailService] as UDS
}

package "Repository Layer" {
    [ProductRepository] as PR
    [CategoryRepository] as CR
    [UserRepository] as UR
    [RoleRepository] as RR
}

package "Model Layer" {
    [User] as U
    [Product] as P
    [Category] as C
    [Role] as R
}

database "H2 Database" as DB

View <--> HC
View <--> AC
View <--> CC
View <--> LC
View <--> EC

HC --> PS
HC --> CS
AC --> PS
AC --> CS
CC --> PS
LC --> UDS

PS --> PR
CS --> CR
UDS --> UR
UDS --> RR

PR --> P
CR --> C
UR --> U
RR --> R

PR --> DB
CR --> DB
UR --> DB
RR --> DB

@enduml
```

## Service and Repository Layer

```plantuml
@startuml "Service and Repository"
skinparam classAttributeIconSize 0
skinparam packageStyle rectangle

package "com.example.oopproject.service" {
    class ProductService {
        -ProductRepository productRepository
        -CategoryService categoryService
        +getAllProduct(): List<Product>
        +addProduct(product: Product): void
        +removeProductById(id: Long): void
        +getProductById(id: Long): Optional<Product>
        +getAllProductByCategoryId(id: int): List<Product>
        +updateProduct(product: Product): void
    }
    
    class CategoryService {
        -CategoryRepository categoryRepository
        +getAllCategory(): List<Category>
        +addCategory(category: Category): void
        +removeCategoryById(id: int): void
        +getCategoryById(id: int): Optional<Category>
        +updateCategory(category: Category): void
    }
    
    class CustomUserDetailService implements UserDetailsService {
        -UserRepository userRepository
        +loadUserByUsername(username: String): UserDetails
    }
}

package "com.example.oopproject.repository" {
    interface ProductRepository extends JpaRepository<Product, Long> {
        +findAllByCategory_Id(id: int): List<Product>
    }
    
    interface CategoryRepository extends JpaRepository<Category, Integer> {
    }
    
    interface UserRepository extends JpaRepository<User, Integer> {
        +findUserByEmail(email: String): Optional<User>
    }
    
    interface RoleRepository extends JpaRepository<Role, Integer> {
    }
}

ProductService --> ProductRepository : uses >
ProductService --> CategoryService : uses >
CategoryService --> CategoryRepository : uses >
CustomUserDetailService --> UserRepository : uses >

note right of CustomUserDetailService: "Implements Spring Security\nUserDetailsService"
note right of ProductRepository: "Spring Data JPA manages\nimplementation"

@enduml
```

## Package Structure

```plantuml
@startuml "Package Structure"
skinparam packageStyle rectangle
skinparam componentStyle uml2

package "com.example.oopproject" {
    [OopprojectApplication] as Main
    
    package "configuration" {
        [SecurityConfig]
        [GoogleOAuth2SuccessHandler]
        [PasswordEncoderConfig]
    }
    
    package "controller" {
        [AdminController]
        [CartController]
        [ErrorController]
        [HomeController]
        [LoginController]
    }
    
    package "dto" {
        [ProductDTO]
    }
    
    package "global" {
        [GlobalData]
    }
    
    package "model" {
        [Category]
        [CustomUserDetail]
        [Product]
        [Role]
        [User]
    }
    
    package "repository" {
        [CategoryRepository]
        [ProductRepository]
        [RoleRepository]
        [UserRepository]
    }
    
    package "service" {
        [CategoryService]
        [CustomUserDetailService]
        [ProductService]
    }
}

Main --> configuration
Main --> controller
Main --> dto
Main --> global
Main --> model
Main --> repository
Main --> service

controller --> service
controller --> dto
controller --> global
service --> repository
repository --> model
service --> model
configuration --> service

@enduml
```

## Authentication Flow

```plantuml
@startuml "Authentication Flow"
actor Client
participant LoginScreen
participant LoginController
participant CustomUserDetailService
participant UserRepository
database Database

Client -> LoginScreen: Enter credentials
LoginScreen -> LoginController: POST /login
activate LoginController
LoginController -> CustomUserDetailService: loadUserByUsername(email)
activate CustomUserDetailService
CustomUserDetailService -> UserRepository: findUserByEmail(email)
activate UserRepository
UserRepository -> Database: Query user by email
Database --> UserRepository: Return user data
UserRepository --> CustomUserDetailService: Return User object
deactivate UserRepository
CustomUserDetailService -> CustomUserDetailService: Create UserDetails
CustomUserDetailService --> LoginController: Return UserDetails
deactivate CustomUserDetailService
LoginController -> LoginController: Validate password
alt Credentials valid
    LoginController --> LoginScreen: Authentication success
    LoginScreen --> Client: Redirect to home page
else Credentials invalid
    LoginController --> LoginScreen: Authentication failure
    LoginScreen --> Client: Display error message
end
deactivate LoginController

@enduml
```

## Shopping Cart Flow

```plantuml
@startuml "Shopping Cart Flow"
actor Customer
participant ShopPage
participant ProductDetailsPage
participant CartController
participant ProductService
participant GlobalData

Customer -> ShopPage: Browse products
ShopPage -> ProductDetailsPage: View product details
Customer -> ProductDetailsPage: Click "Add to Cart"
ProductDetailsPage -> CartController: /addToCart/{id}
activate CartController

CartController -> ProductService: getProductById(id)
activate ProductService
ProductService --> CartController: Return Product
deactivate ProductService

CartController -> GlobalData: addToCart(product)
activate GlobalData
GlobalData -> GlobalData: Update cart items
GlobalData --> CartController: Confirm addition
deactivate GlobalData

CartController --> Customer: Redirect to shop page

Customer -> CartController: View cart (/cart)
CartController -> GlobalData: Get cart items
GlobalData --> CartController: Return cart items
CartController --> Customer: Display cart page

Customer -> CartController: Proceed to checkout (/checkout)
CartController --> Customer: Show checkout form

Customer -> CartController: Submit order details (/orderPlaced)
CartController -> GlobalData: Clear cart
CartController --> Customer: Show order confirmation

@enduml
```

## Admin Management Flow

```plantuml
@startuml "Admin Management Flow"
actor Admin
participant AdminDashboard
participant ProductsPage
participant CategoriesPage
participant AdminController
participant ProductService
participant CategoryService
database Database

Admin -> AdminDashboard: Access admin panel
AdminDashboard --> Admin: Show admin options

group Product Management
    Admin -> ProductsPage: View all products
    ProductsPage -> AdminController: /admin/products
    AdminController -> ProductService: getAllProduct()
    ProductService -> Database: Query all products
    Database --> ProductService: Return products
    ProductService --> AdminController: Return product list
    AdminController --> ProductsPage: Display products
    ProductsPage --> Admin: Show product list

    Admin -> ProductsPage: Add new product
    ProductsPage -> AdminController: /admin/products/add (GET)
    AdminController -> CategoryService: getAllCategory()
    CategoryService --> AdminController: Return categories
    AdminController --> ProductsPage: Show add product form
    ProductsPage --> Admin: Display product form

    Admin -> ProductsPage: Submit product data
    ProductsPage -> AdminController: /admin/products/add (POST)
    AdminController -> ProductService: addProduct(product)
    ProductService -> Database: Save product
    Database --> ProductService: Confirm save
    ProductService --> AdminController: Product added
    AdminController --> ProductsPage: Redirect to products
    ProductsPage --> Admin: Show updated product list
end

group Category Management
    Admin -> CategoriesPage: View all categories
    CategoriesPage -> AdminController: /admin/categories
    AdminController -> CategoryService: getAllCategory()
    CategoryService -> Database: Query all categories
    Database --> CategoryService: Return categories
    CategoryService --> AdminController: Return category list
    AdminController --> CategoriesPage: Display categories
    CategoriesPage --> Admin: Show category list

    Admin -> CategoriesPage: Add new category
    CategoriesPage -> AdminController: /admin/categories/add (GET)
    AdminController --> CategoriesPage: Show add category form
    CategoriesPage --> Admin: Display category form

    Admin -> CategoriesPage: Submit category data
    CategoriesPage -> AdminController: /admin/categories/add (POST)
    AdminController -> CategoryService: addCategory(category)
    CategoryService -> Database: Save category
    Database --> CategoryService: Confirm save
    CategoryService --> AdminController: Category added
    AdminController --> CategoriesPage: Redirect to categories
    CategoriesPage --> Admin: Show updated category list
end

@enduml
```

## Design Patterns Implementation

```plantuml
@startuml "Design Patterns"
skinparam packageStyle rectangle
skinparam componentStyle uml2

package "Creational Patterns" {
    [Singleton\n(Spring Components)] as Singleton
    [Factory Method\n(Repository Creation)] as Factory
    [Builder\n(ProductDTO)] as Builder
}

package "Structural Patterns" {
    [Adapter\n(CustomUserDetail)] as Adapter
    [Facade\n(Service Layer)] as Facade
    [Composite\n(UI Templates)] as Composite
}

package "Behavioral Patterns" {
    [Observer\n(Event Listeners)] as Observer
    [Strategy\n(Authentication)] as Strategy
    [Command\n(Controller Actions)] as Command
}

package "Spring Components" {
    [UserService] -up-> Singleton
    [ProductRepository] -up-> Factory
    [ProductDTO] -up-> Builder
}

package "Security" {
    [CustomUserDetail] -up-> Adapter
    [SecurityConfig] -up-> Strategy
}

package "UI Layer" {
    [Thymeleaf Templates] -up-> Composite
    [Controller Methods] -up-> Command
}

package "Business Logic" {
    [Service Classes] -up-> Facade
    [Event System] -up-> Observer
}

@enduml
```

## Complete System Architecture

```plantuml
@startuml "System Architecture"
!include <archimate/archimate>
!include <archimate/Archimate>

title E-Commerce System - Architecture Overview

' Define the layers
Business_Layer(business, "Business Layer") {
    Business_Service(user_management, "User Management")
    Business_Service(product_catalog, "Product Catalog")
    Business_Service(shopping_cart, "Shopping Cart")
    Business_Service(checkout, "Checkout Process")
    Business_Service(admin_panel, "Admin Panel")
}

Application_Layer(application, "Application Layer") {
    Application_Component(controllers, "Controllers")
    Application_Component(services, "Services")
    Application_Component(repositories, "Repositories")
    Application_Component(security, "Security")
    Application_Component(global_data, "Global Data")
}

Technology_Layer(technology, "Technology Layer") {
    Technology_Service(spring_boot, "Spring Boot")
    Technology_Service(thymeleaf, "Thymeleaf")
    Technology_Service(h2_database, "H2 Database")
    Technology_Service(spring_security, "Spring Security")
}

' Relationships between layers
Rel_Flow_Up(technology, application, "Supports")
Rel_Flow_Up(application, business, "Realizes")

' Internal relationships in Business Layer
Rel_Flow(product_catalog, shopping_cart, "Products added to")
Rel_Flow(shopping_cart, checkout, "Proceeds to")
Rel_Flow(user_management, shopping_cart, "Associates with")
Rel_Flow(user_management, checkout, "Provides details for")
Rel_Flow(admin_panel, product_catalog, "Manages")

' Internal relationships in Application Layer
Rel_Flow(controllers, services, "Uses")
Rel_Flow(services, repositories, "Uses")
Rel_Flow(controllers, security, "Protected by")
Rel_Flow(controllers, global_data, "Uses")

' Internal relationships in Technology Layer
Rel_Flow(spring_boot, thymeleaf, "Integrates")
Rel_Flow(spring_boot, h2_database, "Uses")
Rel_Flow(spring_boot, spring_security, "Incorporates")

@enduml
```

## Database Schema

```plantuml
@startuml "Database Schema"
!define table(x) class x << (T,#FFAAAA) >>
!define primary_key(x) <b>x</b>
!define foreign_key(x) <u>x</u>

hide circle
hide methods

table(users) {
    primary_key(id) : INTEGER
    firstName : VARCHAR
    lastName : VARCHAR
    email : VARCHAR
    password : VARCHAR
}

table(roles) {
    primary_key(id) : INTEGER
    name : VARCHAR
}

table(user_role) {
    primary_key(user_id) : INTEGER
    primary_key(role_id) : INTEGER
}

table(categories) {
    primary_key(category_id) : INTEGER
    name : VARCHAR
}

table(products) {
    primary_key(id) : BIGINT
    name : VARCHAR
    foreign_key(category_id) : INTEGER
    price : DOUBLE
    weight : DOUBLE
    description : VARCHAR(255)
    imageName : VARCHAR
}

users "1" -- "many" user_role : has >
roles "1" -- "many" user_role : assigned to >
categories "1" -- "many" products : contains >

@enduml
```

## Design Patterns Relationships

```plantuml
@startuml "Design Patterns Relationships"
skinparam componentStyle uml2
skinparam packageStyle rectangle

package "Spring Framework" {
    [IoC Container] as IoC
    [MVC Framework] as MVC
    [Data Access Layer] as DAL
    [Security Framework] as SEC
}

package "Design Patterns" {
    package "Creational" {
        [Singleton] as S
        [Factory Method] as FM
        [Builder] as B
    }
    
    package "Structural" {
        [Adapter] as A
        [Facade] as F
        [Composite] as C
    }
    
    package "Behavioral" {
        [Observer] as O
        [Strategy] as ST
        [Command] as CMD
        [Template Method] as TM
    }
}

IoC --> S : implements
IoC --> FM : implements
MVC --> CMD : implements
MVC --> C : implements
MVC --> TM : implements
DAL --> FM : implements
DAL --> F : implements
SEC --> ST : implements
SEC --> A : implements
SEC --> O : implements

@enduml
```
