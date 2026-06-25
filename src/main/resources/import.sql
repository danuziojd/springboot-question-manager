-- Tematicas
INSERT INTO tematicas (nombre) VALUES ('Matematicas');
INSERT INTO tematicas (nombre) VALUES ('Historia');
INSERT INTO tematicas (nombre) VALUES ('Ciencias');
INSERT INTO tematicas (nombre) VALUES ('Lengua');
INSERT INTO tematicas (nombre) VALUES ('Ingles');

-- Preguntas (asumiendo que las tematicas se insertan en orden con IDs 1-5)
INSERT INTO preguntas (enunciado, dificultad, tematica_id) VALUES ('?Cuanto es 2+2?', 1, 1);
INSERT INTO preguntas (enunciado, dificultad, tematica_id) VALUES ('?Quien descubrio America?', 2, 2);
INSERT INTO preguntas (enunciado, dificultad, tematica_id) VALUES ('?Cual es el planeta mas grande del sistema solar?', 2, 3);
INSERT INTO preguntas (enunciado, dificultad, tematica_id) VALUES ('?Que es un sustantivo?', 1, 4);
INSERT INTO preguntas (enunciado, dificultad, tematica_id) VALUES ('?Como se dice "hello" en espanol?', 1, 5);
INSERT INTO preguntas (enunciado, dificultad, tematica_id) VALUES ('?Cual es la raiz cuadrada de 144?', 3, 1);
INSERT INTO preguntas (enunciado, dificultad, tematica_id) VALUES ('?En que anyo empezo la Segunda Guerra Mundial?', 3, 2);
INSERT INTO preguntas (enunciado, dificultad, tematica_id) VALUES ('?Cual es el simbolo quimico del agua?', 1, 3);
INSERT INTO preguntas (enunciado, dificultad, tematica_id) VALUES ('?Que es un verbo?', 2, 4);
INSERT INTO preguntas (enunciado, dificultad, tematica_id) VALUES ('?Como se dice "good morning" en espanol?', 1, 5);