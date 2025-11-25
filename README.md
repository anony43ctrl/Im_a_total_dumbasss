# BuyBuddy: Online Retail Shopping Application

A web-based application that allows users to browse products, add them to the cart, place orders, and track deliveries. It includes user registration, product management, order processing, and payment integration.

---

## 1. Purpose
The application allows users to:
- Browse products
- Add products to a shopping cart
- Place orders
- Track order delivery

It supports:
- User registration/login
- Product management
- Order processing
- Payment functionality

---

## 1.2 Definitions
- **User:** Any individual who uses the system (Customer or Admin)
- **Admin:** A privileged user who can manage products and orders
- **Cart:** Temporary storage of selected items before purchase
- **Order:** Final purchase request including payment details

---

## 2. User Classes and Characteristics

### Customer
- Can browse, search, buy products, and track orders.

### Admin
- Can add/remove products, manage orders and users.

---

## 3. Functional Requirements

| Service | Description |
|---------|-------------|
| **User Registration/Login** | Users sign up using email and log in securely with password encryption |
| **Product Browsing** | Customers can view product categories and listings with images and prices |
| **Search & Filter Products** | Find specific items using search bar and filters (price, category, rating) |
| **Product Details Page** | Displays detailed information including product specs, reviews, ratings |
| **Add to Cart** | Users can add products to their shopping cart |
| **Cart Management** | Users can update quantities or remove items from the cart |
| **Order Checkout** | Users enter shipping information and select payment method |
| **Payment** | Payments via Credit/Debit |
| **Order Management** | Admins view, process, ship, or cancel orders. Customers can track orders |
| **User Profile Management** | Users can view/update personal info, addresses, and password |
| **Product Management** | Admins can add/edit/delete products including images and prices |
| **Inventory Management** | Tracks product stock levels and restock alerts |
| **Notifications** | Users receive email/SMS notifications for registration, orders, shipping |

---

## 4. System Design Overview

- **Backend:** Java 17, Spring Boot  
- **Frontend:** React.js  
- **Database:** MySQL  

---

## Frontend Setup
```bash
cd frontend
npm install
npm start
