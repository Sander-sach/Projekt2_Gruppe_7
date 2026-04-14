USE wishlistdb;

CREATE TABLE IF NOT EXISTS user (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    email varchar(255) DEFAULT NULL,
    password varchar(255) DEFAULT NULL,
    PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS wishlist (
    id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint NOT NULL,
    name varchar(255) DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id));

CREATE TABLE IF NOT EXISTS wish (
    id bigint NOT NULL AUTO_INCREMENT,
    wishlist_id bigint NOT NULL,
    item_name varchar(255) DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    price double DEFAULT NULL,
    item_url varchar(500) DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (wishlist_id) REFERENCES wishlist (id));

