CREATE TABLE user
(
  id bigint NOT NULL AUTO_INCREMENT,
  email   VARCHAR(255) NULL,
  usuario VARCHAR(255) NULL,
  senha VARCHAR(255) NULL,
  PRIMARY KEY(id)
);



insert into user(email, usuario, senha) value("samuel@gmail.com", "samuel", "123");