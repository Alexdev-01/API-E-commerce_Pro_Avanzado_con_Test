-- Insertar categorías solo si no existen
INSERT INTO categorias (id, nombre, descripcion)
SELECT 1, 'Informática', 'Ordenadores, portátiles y accesorios'
WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE id = 1);

INSERT INTO categorias (id, nombre, descripcion)
SELECT 2, 'Telefonía', 'Móviles, tablets y accesorios'
WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE id = 2);

INSERT INTO categorias (id, nombre, descripcion)
SELECT 3, 'Electrodomésticos', 'Electrodomésticos del hogar'
WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE id = 3);

INSERT INTO categorias (id, nombre, descripcion)
SELECT 4, 'Televisión', 'Televisores y accesorios audiovisuales'
WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE id = 4);

INSERT INTO categorias (id, nombre, descripcion)
SELECT 5, 'Videojuegos', 'Consolas, juegos y accesorios'
WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE id = 5);
