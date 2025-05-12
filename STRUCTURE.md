# OOP_PROJECT - Project Structure

```
OOP_PROJECT/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── oopproject/
│   │   │               ├── OopprojectApplication.java
│   │   │               ├── configuration/
│   │   │               │   ├── GoogleOAuth2SuccessHandler.java
│   │   │               │   ├── PasswordEncoderConfig.java
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── controller/
│   │   │               │   ├── AdminController.java
│   │   │               │   ├── CartController.java
│   │   │               │   ├── ErrorController.java
│   │   │               │   ├── HomeController.java
│   │   │               │   └── LoginController.java
│   │   │               ├── dto/
│   │   │               │   └── ProductDTO.java
│   │   │               ├── global/
│   │   │               │   └── GlobalData.java
│   │   │               ├── model/
│   │   │               │   ├── Category.java
│   │   │               │   ├── CustomUserDetail.java
│   │   │               │   ├── Product.java
│   │   │               │   ├── Role.java
│   │   │               │   └── User.java
│   │   │               ├── repository/
│   │   │               │   ├── CategoryRepository.java
│   │   │               │   ├── ProductRepository.java
│   │   │               │   ├── RoleRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               └── service/
│   │   │                   ├── CategoryService.java
│   │   │                   ├── CustomUserDetailService.java
│   │   │                   └── ProductService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── data.txt
│   │       ├── static/
│   │       │   ├── images/
│   │       │   │   ├── background.jpg
│   │       │   │   └── logo.PNG
│   │       │   └── productImages/
│   │       │       ├── background.jpg
│   │       │       ├── download (1).jpeg
│   │       │       ├── download (2).jpeg
│   │       │       ├── download.jpeg
│   │       │       ├── images (1).jpeg
│   │       │       ├── images (2).jpeg
│   │       │       ├── IMG-20240424-WA0016.jpg
│   │       │       ├── logo.png
│   │       │       └── ROSES.jpg
│   │       └── templates/
│   │           ├── adminHome.html
│   │           ├── cart.html
│   │           ├── categories.html
│   │           ├── categoriesAdd.html
│   │           ├── checkout.html
│   │           ├── error/
│   │           │   ├── 403.html
│   │           │   ├── 404.html
│   │           │   └── 500.html
│   │           ├── index.html
│   │           ├── login.html
│   │           ├── orderPlaced.html
│   │           ├── products.html
│   │           ├── productsAdd.html
│   │           ├── register.html
│   │           ├── shop.html
│   │           └── viewProduct.html
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── oopproject/
│                       └── OopprojectApplicationTests.java
├── target/
│   ├── classes/
│   │   ├── application.properties
│   │   ├── com/
│   │   │   └── example/
│   │   │       └── oopproject/
│   │   │           ├── OopprojectApplication.class
│   │   │           ├── configuration/
│   │   │           │   ├── GoogleOAuth2SuccessHandler.class
│   │   │           │   ├── PasswordEncoderConfig.class
│   │   │           │   └── SecurityConfig.class
│   │   │           ├── controller/
│   │   │           │   ├── AdminController.class
│   │   │           │   ├── CartController.class
│   │   │           │   ├── ErrorController.class
│   │   │           │   ├── HomeController.class
│   │   │           │   └── LoginController.class
│   │   │           ├── dto/
│   │   │           │   └── ProductDTO.class
│   │   │           ├── global/
│   │   │           │   └── GlobalData.class
│   │   │           ├── model/
│   │   │           │   ├── Category.class
│   │   │           │   ├── CustomUserDetail.class
│   │   │           │   ├── Product.class
│   │   │           │   ├── Role.class
│   │   │           │   └── User.class
│   │   │           ├── repository/
│   │   │           │   ├── CategoryRepository.class
│   │   │           │   ├── ProductRepository.class
│   │   │           │   ├── RoleRepository.class
│   │   │           │   └── UserRepository.class
│   │   │           └── service/
│   │   │               ├── CategoryService.class
│   │   │               ├── CustomUserDetailService.class
│   │   │               └── ProductService.class
│   │   ├── data.txt
│   │   ├── static/
│   │   │   ├── images/
│   │   │   │   ├── background.jpg
│   │   │   │   └── logo.PNG
│   │   │   └── productImages/
│   │   │       ├── background.jpg
│   │   │       ├── download (1).jpeg
│   │   │       ├── download (2).jpeg
│   │   │       ├── download.jpeg
│   │   │       ├── images (1).jpeg
│   │   │       ├── images (2).jpeg
│   │   │       ├── IMG-20240424-WA0016.jpg
│   │   │       ├── logo.png
│   │   │       └── ROSES.jpg
│   │   └── templates/
│   │       ├── adminHome.html
│   │       ├── cart.html
│   │       ├── categories.html
│   │       ├── categoriesAdd.html
│   │       ├── checkout.html
│   │       ├── error/
│   │       │   ├── 403.html
│   │       │   ├── 404.html
│   │       │   └── 500.html
│   │       ├── index.html
│   │       ├── login.html
│   │       ├── orderPlaced.html
│   │       ├── products.html
│   │       ├── productsAdd.html
│   │       ├── register.html
│   │       ├── shop.html
│   │       └── viewProduct.html
│   ├── generated-sources/
│   │   └── annotations/
│   ├── generated-test-sources/
│   │   └── test-annotations/
│   ├── maven-status/
│   │   └── maven-compiler-plugin/
│   │       ├── compile/
│   │       │   └── default-compile/
│   │       │       ├── createdFiles.lst
│   │       │       └── inputFiles.lst
│   │       └── testCompile/
│   │           └── default-testCompile/
│   │               ├── createdFiles.lst
│   │               └── inputFiles.lst
│   ├── surefire-reports/
│   │   ├── com.example.oopproject.OopprojectApplicationTests.txt
│   │   └── TEST-com.example.oopproject.OopprojectApplicationTests.xml
│   └── test-classes/
│       └── com/
│           └── example/
│               └── oopproject/
│                   └── OopprojectApplicationTests.class
├── .git/
├── .gitignore
├── .mvn/
│   └── wrapper/
│       ├── maven-wrapper.jar
│       └── maven-wrapper.properties
├── .vscode/
│   └── settings.json
├── db.mv.db
├── db.trace.db
├── DESIGN.md
├── LICENSE
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── STRUCTURE.md
```

## Key Components

### Java Source Files

- **Controllers**: Handle HTTP requests and routing
- **Models**: Domain objects representing business entities
- **Repositories**: Data access interfaces
- **Services**: Business logic implementations
- **Configuration**: Application configuration classes
- **DTOs**: Data Transfer Objects for transferring data between layers
- **Global**: Application-wide utilities and state

### Resources

- **Templates**: Thymeleaf HTML templates for view rendering
- **Static Resources**: Images, CSS, JavaScript files
- **Configuration Files**: Properties files for application settings

### Build and Project Files

- **pom.xml**: Maven project configuration
- **DESIGN.md**: Design documentation
- **README.md**: Project overview and documentation
- **.gitignore**: Git ignore configuration
- **mvnw/mvnw.cmd**: Maven wrapper scripts
