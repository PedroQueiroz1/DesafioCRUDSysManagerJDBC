CREATE TABLE author (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE review (
    id INT NOT NULL AUTO_INCREMENT,
    stars INT NOT NULL,
    comment VARCHAR(255) NOT NULL,
    reviewer_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (reviewer_id) REFERENCES author(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE book (
    id INT NOT NULL,
    genre VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES product(id)
);

CREATE TABLE movie (
    id INT NOT NULL,
    duration INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES product(id)
);