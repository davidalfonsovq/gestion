INSERT INTO USUARIO (name, email, password, token, isactived) VALUES ('Ana', 'ana@mail.com', 'contrasena', random_uuid(), true);
INSERT INTO TELEFONO (number, citycode, contrycode, id_usuario) VALUES ('8855224', '32', '56', SELECT ID FROM USUARIO WHERE email = 'ana@mail.com' LIMIT 1);
