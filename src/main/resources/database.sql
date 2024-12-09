-- Create the database
CREATE DATABASE IF NOT EXISTS inventory_system;

-- Use the database
USE inventory_system;

-- Create a table for users (admin and regular users)
CREATE TABLE IF NOT EXISTS Users (
                                     user_id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'user') NOT NULL
    );

-- Insert default users with plain text passwords
-- Note: In a real application, always hash and salt passwords
INSERT INTO Users (username, password, role) VALUES
                                                 ('admin', 'admin', 'admin'),
                                                 ('user', 'user', 'user');

-- Create a table for inventory items
CREATE TABLE IF NOT EXISTS Inventory (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT NOT NULL,
    ready_to_ship_quantity INT NOT NULL,
    date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Create a table for customers
CREATE TABLE IF NOT EXISTS Customers (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
    );

-- Create a table for sales
CREATE TABLE IF NOT EXISTS Sales (
                                     sale_id INT AUTO_INCREMENT PRIMARY KEY,
                                     customer_id INT,
                                     item_id INT,
                                     quantity_sold INT NOT NULL,
                                     total_price DECIMAL(10, 2) NOT NULL,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES Customers(id),
    FOREIGN KEY (item_id) REFERENCES Inventory(id)
    );

-- Create a table for orders
CREATE TABLE IF NOT EXISTS Orders (
                                      order_id INT AUTO_INCREMENT PRIMARY KEY,
                                      customer_id INT,
                                      item_id INT,
                                      quantity_ordered INT NOT NULL,
                                      order_status ENUM('Pending', 'Shipped', 'Delivered', 'Cancelled') DEFAULT 'Pending',
    tracking_number VARCHAR(50),
    shipping_address TEXT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES Customers(id),
    FOREIGN KEY (item_id) REFERENCES Inventory(id)
    );

-- Create a table for settings
CREATE TABLE IF NOT EXISTS Settings (
                                        setting_id INT AUTO_INCREMENT PRIMARY KEY,
                                        user_id INT,
                                        language VARCHAR(20) DEFAULT 'English',
    currency ENUM('USD', 'Euro', 'HUF') DEFAULT 'USD',
    theme ENUM('Light', 'Dark') DEFAULT 'Light',
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
    );
