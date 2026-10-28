CREATE DATABASE IF NOT EXISTS wishlist
    CHARACTER SET utf8mb4;

USE wishlist;

DROP TABLE IF EXISTS wish;

CREATE TABLE IF NOT EXISTS wish
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(250),
    price INT,
    link VARCHAR(500),
    image VARCHAR(500),
    priority BOOLEAN
);

DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);


DROP TABLE IF EXISTS list;

CREATE TABLE IF NOT EXISTS list
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    user_ID INT, FOREIGN KEY(user_id)
        REFERENCES user (id)
);



DROP TABLE IF EXISTS WishListBridge;

CREATE TABLE IF NOT EXISTS WishListBridge
(
    list_ID INT NOT NULL,
    wish_ID INT NOT NULL,
    PRIMARY KEY (list_ID, wish_ID),
    FOREIGN KEY (list_ID)
        REFERENCES list (id),
    FOREIGN KEY (wish_ID)
        REFERENCES wish (id)   
);


