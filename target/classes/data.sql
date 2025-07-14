INSERT INTO USUARIO (name, email, password, token, isactived) VALUES ('Ana', 'ana@gmail.com', 'Contrasena5', random_uuid(), true);
INSERT INTO TELEFONO (number, citycode, contrycode, id_usuario) VALUES ('8855224', '32', '56', SELECT ID FROM USUARIO WHERE email = 'ana@gmail.com' LIMIT 1);
