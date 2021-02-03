INSERT INTO author (id, name, surname, middle_name) VALUES
    (1, 'Author 1 name', 'Author 1 surname', 'Author 1 middle name'),
    (2, 'Author 2 name', 'Author 2 surname', 'Author 2 middle name');

INSERT INTO genre (id, name) VALUES
    (1, 'Genre 1'),
    (2, 'Genre 2');

INSERT INTO book (id, author_id, name, description)VALUES
    (1, 1, 'book 1 name withPrefixForLikeSearch', 'book 1 desc'),
    (2, 2, 'book 2 name withPrefixForLikeSearch', 'book 2 desc'),
    (3, 2, 'book 3 name', 'book 2 desc');

INSERT INTO books_genre (book_id, genre_id) VALUES
    (1, 1),
    (2, 2),
    (3, 2);