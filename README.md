# Online Shopping Web App

## Overview

The **Online Shopping Web App** is a Java-based web application designed to provide a scalable and efficient platform for users to browse, search, and purchase products online. Developed using the **Spring Framework**, this application offers a secure and responsive user experience while allowing for easy expansion and future enhancements.

## Features

- User authentication and authorization with **Spring Security**.
- OAuth2 integration for secure login via **Google OAuth**.
- In-memory **H2 Database** for development and testing.
- **Spring Data JPA** for easy database interaction.
- Responsive front-end using **Bootstrap** and **Thymeleaf** for server-side rendering.
- Administrative panel for managing categories and products.
- User-specific shopping cart functionality.

## Technologies Used

- **Backend**: Java (Spring Framework)
- **Frontend**: HTML, CSS, Thymeleaf, Bootstrap
- **Database**: H2 (in-memory database)
- **Authentication**: Spring Security, Google OAuth2
- **Persistence**: Spring Data JPA
- **Other**: Lombok, OAuth2

## Installation

### Prerequisites

- **JDK 8** or higher
- **Maven** (for dependency management and building the project)

### Steps

1. **Clone the repository**:

    ```bash
    git clone <repository-url>
    cd <project-directory>
    ```

2. **Build the application**:

    If you are using **Maven**, run:

    ```bash
    mvn clean install
    ```

3. **Run the application**:

    You can run the application using:

    ```bash
    mvn spring-boot:run
    ```

    The application should now be running locally on [http://localhost:8080](http://localhost:8080).

## Project Structure

### Models

- **Category**: Represents the product categories.
- **CustomUserDetails**: Custom user authentication details.
- **Product**: Represents individual products.
- **Role**: Defines user roles (admin, customer).
- **User**: Represents users of the application.

### Repositories

- **CategoryRepository**: Manages category-related data.
- **ProductRepository**: Handles product CRUD operations.
- **RoleRepository**: Manages user roles.
- **UserRepository**: Handles user-related operations.

### Services

- **CategoryService**: Contains business logic for categories.
- **CustomUserDetailsService**: Implements user authentication.
- **ProductService**: Manages business logic for products.

### Controllers

- **AdminController**: Manages the administrative functions like product and category management.
- **UserController**: Handles user operations such as browsing products.
- **HomeController**: Controls the homepage display.
- **CartController**: Manages shopping cart operations.
- **ErrorController**: Handles error pages.

### Configuration Files

- **Security Config**: Configures Spring Security for authentication.
- **Google OAuth Config**: Configures Google OAuth integration.
- **Password Encoder**: Handles password encoding for authentication.

## Future Development

- **Payment Integration**: In the future, a payment system (such as Stripe) can be integrated to allow users to make secure payments online. Currently, the frontend supports necessary forms, but the Stripe API could not be used due to business verification restrictions.

## Teamwork

The project was carried out collaboratively, with tasks divided among team members to ensure efficient development. The work was distributed across the **Frontend**, **Backend**, and **Database** to minimize individual workload and maximize productivity.

## Contribution

We welcome contributions to improve and expand the project. If you'd like to contribute:

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Open a pull request for review.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

- **Spring Framework** for making Java-based web development easier.
- **Bootstrap** for providing a responsive front-end framework.
- **Google OAuth** for secure authentication.
- **H2 Database** for lightweight and fast in-memory database development.
