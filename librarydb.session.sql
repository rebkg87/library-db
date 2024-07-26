CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    isbn BIGINT(13) NOT NULL
);

INSERT INTO books (title, description, isbn) VALUES
    ('Veronica decide morir', 'Veronika es una joven normal. Es guapa, le va bien en el amor y tiene trabajo. Su vida transcurre sin sobresaltos. Pero no es feliz. Por eso, una mañana, Veronika decide acabar con su vida.', 9788408074786);

SELECT * FROM books;

INSERT INTO books (title, description, isbn)
VALUES 
    ('Cumbres Borrascosas', 'Una trágica historia de pasiones encontradas en la Inglaterra más conservadora. Catherine, la hija de una familia distinguida, y Heatcliff, un campesino adoptado.',
    '9788491819431'),
    ('La sombra del viento', 'Un día de 1945 padre e hijo dan con un lugar oculto de la ciudad: el Cementerio de los Libros Olvidados. Daniel  encuentra un libro maldito que cambiará su vida.',
    '9788408163350'),
    ('La divina comedia', 'Una trágica historia de pasiones encontradas en la Inglaterra más conservadora. Catherine, la hija de una familia distinguida, y Heatcliff, un campesino adoptado.',
    '9788491819431'),
    ('Viaje al centro de la tierra', 'Una trágica historia de pasiones encontradas en la Inglaterra más conservadora. Catherine, la hija de una familia distinguida, y Heatcliff, un campesino adoptado.',
    '9788491819431'),
    ('Orgullo y prejuicio', 'Una trágica historia de pasiones encontradas en la Inglaterra más conservadora. Catherine, la hija de una familia distinguida, y Heatcliff, un campesino adoptado.',
    '9788491819431'),
    ('Deja de ser Tú', 'Una trágica historia de pasiones encontradas en la Inglaterra más conservadora. Catherine, la hija de una familia distinguida, y Heatcliff, un campesino adoptado.',
    '9788491819431'),
    ('Origen', 'Una trágica historia de pasiones encontradas en la Inglaterra más conservadora. Catherine, la hija de una familia distinguida, y Heatcliff, un campesino adoptado.',
    '9788491819431'),
    ('La biología del presente', 'Una trágica historia de pasiones encontradas en la Inglaterra más conservadora. Catherine, la hija de una familia distinguida, y Heatcliff, un campesino adoptado.',
    '9788491819431'),
    ('Pídeme lo que quieras', 'Una trágica historia de pasiones encontradas en la Inglaterra más conservadora. Catherine, la hija de una familia distinguida, y Heatcliff, un campesino adoptado.',
    '9788491819431'),
    ('El clan de la loba', 'Una trágica historia de pasiones encontradas en la Inglaterra más conservadora. Catherine, la hija de una familia distinguida, y Heatcliff, un campesino adoptado.',
    '9788491819431'),
    ('Las siete llaves', 'Una trágica historia de pasiones encontradas en la Inglaterra más conservadora. Catherine, la hija de una familia distinguida, y Heatcliff, un campesino adoptado.',
    '9788491819431');

INSERT INTO books (title, description, isbn)
VALUES (
    
    'title:character varying',
    'description:character varying',
    'isbn:bigint'
  );    SELECT * FROM books;
    
CREATE TABLE IF NOT EXISTS authors (
    id SERIAL PRIMARY KEY,
    author VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS genres (
    id SERIAL PRIMARY KEY,
    genre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS authors_books (
    id_book INT NOT NULL,
    id_author INT NOT NULL,
    PRIMARY KEY (id_book, id_author),
    FOREIGN KEY (id_book) REFERENCES books(id),
    FOREIGN KEY (id_author) REFERENCES authors(id)
);
CREATE TABLE IF NOT EXISTS genres_books (
    id_book INT NOT NULL,
    id_genre INT NOT NULL,
    PRIMARY KEY (id_book, id_genre),
    FOREIGN KEY (id_book) REFERENCES books(id),
    FOREIGN KEY (id_genre) REFERENCES genres(id)
);

-- - A hacer la búsqueda ya sea por título o por autor, se mostrarán todos los campos del libro/s.

SELECT b.title, b.description, a.author
FROM books AS b
inner JOIN authors_books AS ab ON (b.id = ab.id_book)
inner JOIN authors AS a ON (a.id = ab.id_author)
WHERE a.author LIKE "author"


SELECT b.id, b.title, b.description, a.author
from books AS b
inner JOIN authors_books AS ab ON (b.id = ab.id_book)
inner JOIN authors AS a ON (a.id = ab.id_author)
WHERE b.title LIKE "title"


SELECT b.title, b.description, a.author
from books AS b
inner JOIN authors_books AS ab ON (b.id = ab.id_book)
inner JOIN authors AS a ON (a.id = ab.id_author)
WHERE b.id = 10


Select b.title, b.description, a.author
from books AS b
inner JOIN authors_books AS ab ON (b.id = ab.id_book)
inner JOIN authors AS a ON (a.id = ab.id_author)
WHERE a.id = 2

-- - A hacer la búsqueda por género literario, se mostrarán todos los campos del libro/s excepto la descripción.