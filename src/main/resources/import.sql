-- Usuario de prueba (username: admin, password: admin123)
INSERT INTO usuarios (id, username, password, role) VALUES (1, 'admin', '$2b$10$7XrFriPKdlrKFUqgos7Qe.x8sf8mh3gn1ZB.NAkQLgEONkAzxvpRK', 'ADMIN');

-- Tematicas
INSERT INTO tematicas (id, nombre) VALUES (1, 'Matematicas');
INSERT INTO tematicas (id, nombre) VALUES (2, 'Historia');
INSERT INTO tematicas (id, nombre) VALUES (3, 'Ciencias');
INSERT INTO tematicas (id, nombre) VALUES (4, 'Lengua');
INSERT INTO tematicas (id, nombre) VALUES (5, 'Ingles');

-- Preguntas (JOINED inheritance requires inserts in both parent and child tables)
INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (1, '¿Cuanto es 2+2?', 1, 1);
INSERT INTO preguntas_verdadeiro_falso (id, respuesta_correcta) VALUES (1, true);

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (2, '¿Quien descubrio America?', 2, 2);
INSERT INTO preguntas_seleccion_unica (id, opciones, respuesta_correcta) VALUES (2, 'Colon,Magallanes,Cortes,Pizarro', 'Colon');

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (3, '¿Cual es el planeta mas grande del sistema solar?', 2, 3);
INSERT INTO preguntas_seleccion_unica (id, opciones, respuesta_correcta) VALUES (3, 'Marte,Jupiter,Saturno,Neptuno', 'Jupiter');

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (4, '¿Que es un sustantivo?', 1, 4);
INSERT INTO preguntas_verdadeiro_falso (id, respuesta_correcta) VALUES (4, true);

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (5, '¿Como se dice "hello" en espanol?', 1, 5);
INSERT INTO preguntas_seleccion_unica (id, opciones, respuesta_correcta) VALUES (5, 'Hola,Adios,Gracias,Por favor', 'Hola');

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (6, '¿Cual es la raiz cuadrada de 144?', 3, 1);
INSERT INTO preguntas_seleccion_unica (id, opciones, respuesta_correcta) VALUES (6, '10,11,12,13', '12');

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (7, '¿En que anyo empezo la Segunda Guerra Mundial?', 3, 2);
INSERT INTO preguntas_seleccion_unica (id, opciones, respuesta_correcta) VALUES (7, '1914,1939,1945,1918', '1939');

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (8, '¿Cual es el simbolo quimico del agua?', 1, 3);
INSERT INTO preguntas_seleccion_unica (id, opciones, respuesta_correcta) VALUES (8, 'H2O,CO2,NaCl,O2', 'H2O');

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (9, '¿Que es un verbo?', 2, 4);
INSERT INTO preguntas_verdadeiro_falso (id, respuesta_correcta) VALUES (9, true);

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (10, '¿Como se dice "good morning" en espanol?', 1, 5);
INSERT INTO preguntas_seleccion_multiple (id, opciones, respuestas_correctas) VALUES (10, 'Buenos dias,Buenas tardes,Buenas noches,Hola', 'Buenos dias');

-- Más preguntas para probar paginación (total 15)
INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (11, '¿2+2=4?', 1, 1);
INSERT INTO preguntas_verdadeiro_falso (id, respuesta_correcta) VALUES (11, true);

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (12, '¿Cual fue la primera revolucion industrial?', 3, 2);
INSERT INTO preguntas_seleccion_unica (id, opciones, respuesta_correcta) VALUES (12, '1780,1850,1900,1760', '1760');

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (13, '¿La fotosintesis produce oxigeno?', 1, 3);
INSERT INTO preguntas_verdadeiro_falso (id, respuesta_correcta) VALUES (13, true);

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (14, '¿Selecciona los articulos determinados?', 2, 4);
INSERT INTO preguntas_seleccion_multiple (id, opciones, respuestas_correctas) VALUES (14, 'el,la,un,una,los,las', 'el,la,los,las');

INSERT INTO preguntas (id, enunciado, dificultad, tematica_id) VALUES (15, '¿Colores primarios?', 2, 5);
INSERT INTO preguntas_seleccion_multiple (id, opciones, respuestas_correctas) VALUES (15, 'Rojo,Azul,Verde,Amarillo', 'Rojo,Azul,Amarillo');
