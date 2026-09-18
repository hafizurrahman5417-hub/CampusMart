# 🛒 CampusMart

### A Student-Focused Campus Marketplace

CampusMart is a campus-focused marketplace application designed for students to **buy, sell, rent, and manage products within their campus community**.

The project is developed as a Java-based web application using **Spring Boot, MySQL, HTML, CSS, and JavaScript**.

---

## 🎓 Project Overview

Students often need books, calculators, electronics, hostel essentials, accessories, and other useful items during their college life.

CampusMart provides a simple platform where students can:

- 🛍️ Browse campus products
- 🔎 Search for products
- 📂 Filter products by category
- 💰 Buy products
- 🏷️ List products for sale
- 🔄 Rent campus-use items
- 🛒 Add products to cart
- 📦 Place orders
- 📋 View previous orders
- 👤 Manage user accounts

The main goal is to create a **student-oriented marketplace rather than a general e-commerce platform**.

---

# ✨ Features

## 🏠 Marketplace

- Student-focused marketplace homepage
- Product listing cards
- Product categories
- Product search
- Buy and Rent filters
- Product details page

## 🛒 Shopping Cart

- Add products to cart
- View cart items
- View total price
- Remove items from cart
- Pickup location selection
- Place an order

## 📦 Orders

- Place orders from the shopping cart
- Automatic order creation
- Order ID generation
- Order date and time
- Pickup location
- Order status
- Order total
- View previous orders

## 👤 User Management

- User registration
- User login
- User session handling
- User-specific cart
- User-specific orders

## 🏷️ Product Management

Products can contain:

- Product name
- Description
- Category
- Price
- Rent price
- Listing type
- Condition
- Stock
- Location
- Product image
- Seller information

---

# 🛠️ Technology Stack

## Backend

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Maven**
- **BCrypt**

## Frontend

- **HTML5**
- **CSS3**
- **JavaScript**
- **Fetch API**
- **LocalStorage**

## Development Tools

- Antigravity IDE
- MySQL Workbench
- Git
- GitHub

---

# 🏗️ Project Architecture

```text
CampusMart
│
├── Backend
│   │
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── campusmart
│   │       │           │
│   │       │           ├── controller
│   │       │           ├── model
│   │       │           ├── repository
│   │       │           ├── service
│   │       │           │
│   │       │           └── CampusMartApplication.java
│   │       │
│   │       └── resources
│   │           └── application.properties
│   │
│   └── pom.xml
│
└── Frontend
    │
    ├── css
    │   └── style.css
    │
    ├── js
    │   ├── admin.js
    │   ├── app.js
    │   ├── auth.js
    │   ├── cart.js
    │   └── products.js
    │
    ├── images
    │
    ├── index.html
    ├── login.html
    ├── register.html
    ├── product.html
    ├── cart.html
    ├── order.html
    ├── sell.html
    ├── profile.html
    └── admin.html
