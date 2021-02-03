INSERT INTO author (id, name, surname, middle_name) VALUES
    (1, 'Джордж', 'Оруэлл', NULL),
    (2, 'Рэй',  'Брэдбери', NULL),
    (3, 'Олдос', 'Хаксли', NULL);

INSERT INTO genre (id, name) VALUES
    (1, 'Роман'),
    (2, 'Повесть'),
    (3, 'Фантастика');

INSERT INTO book (id, author_id, name, description) VALUES
    (1, 1, '1984', 'Большой Брат по-прежнему не смыкает глаз...'),
    (2, 1, 'Скотный двор', 'Сатирическая новелла, рассказывающая о животных, изгнавших своих хозяев со своей фермы.'),
    (3, 2, '451 градус по Фаренгейту', 'Пожарные, которые разжигают пожары, книги, которые запрещено читать, и люди, которые уже почти перестали быть людьми…'),
    (4, 3, 'О дивный новый мир', 'На страницах романа описывается мир далёкого будущего.');

INSERT INTO books_genre (id, book_id, genre_id) VALUES
    (1, 1, 1),
    (2, 2, 2),
    (3, 2, 3),
    (4, 3, 1),
    (5, 3, 3),
    (6, 4, 1),
    (7, 4, 3);