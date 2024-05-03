CREATE TABLE IF NOT EXISTS Sell (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255),
    customer_email VARCHAR(255),
    buying_price INT,
    selling_price INT
);



INSERT INTO Sell (product_name, customer_email, buying_price, selling_price)
VALUES ('Product 1', 'customer1@example.com', 100, 150);
--       ('Product 2', 'customer2@example.com', 120, 180),
--       ('Product 3', 'customer3@example.com', 80, 130);