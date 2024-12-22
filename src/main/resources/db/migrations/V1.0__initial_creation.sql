CREATE TABLE author (
    id            BIGINT PRIMARY KEY auto_increment NOT NULL,
    first_name    VARCHAR(50),
    last_name     VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    year_of_birth INT,
    address       VARCHAR(50)
);

CREATE TABLE book (
    id BIGINT PRIMARY KEY auto_increment NOT NULL,
    author_id BIGINT NOT NULL,
    title VARCHAR(400) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(id)
);


INSERT INTO author(first_name, last_name, date_of_birth, year_of_birth, address) VALUES ( 'George', 'Orwell', '1903-06-25', 1903, null);
INSERT INTO author(first_name, last_name, date_of_birth, year_of_birth, address) VALUES ( 'Paulo', 'Coelho', '1947-08-24', 1947, null);

INSERT INTO book(author_id, title) VALUES (1, '1984');
INSERT INTO book(author_id, title) VALUES (1, 'Animal Farm');
INSERT INTO book(author_id, title) VALUES (2, 'O Alquimista');
INSERT INTO book(author_id, title) VALUES (2, 'Brida');