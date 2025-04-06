CREATE TABLE IF NOT EXISTS stocks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    stock_symbol VARCHAR(10) UNIQUE NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO stocks (stock_symbol, price) VALUES ('AAPL', 150.00), ('GOOGL', 2800.00), ('AMZN', 3400.00), ('MSFT', 299.00), ('TSLA', 700.00);

SELECT * FROM stocks;

ALTER TABLE stocks
ADD COLUMN currency VARCHAR(3) NOT NULL DEFAULT 'USD';