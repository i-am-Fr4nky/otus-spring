DROP TABLE IF EXISTS books_genre;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS author;

CREATE TABLE author (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(250),
    surname VARCHAR(250) NOT NULL,
    middle_name VARCHAR(250)
);

CREATE TABLE genre (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE book (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    author_id INT NOT NULL,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(2000),

    FOREIGN KEY (author_id) references author(id)
);

CREATE TABLE books_genre (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    book_id INT NOT NULL,
    genre_id INT NOT NULL,

    FOREIGN KEY (book_id) references book(id),
    FOREIGN KEY (genre_id) references genre(id)
);