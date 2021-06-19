USE evrosetka;

DROP TABLE IF EXISTS evrosetka_users;

CREATE TABLE evrosetka_users(
user_id INT NOT NULL PRIMARY KEY auto_increment,
user_surname VARCHAR(20) NOT NULL,
user_name VARCHAR(20) NOT NULL,
user_login VARCHAR (20) NOT NULL,
user_password VARCHAR (20) NOT NULL,
user_gender VARCHAR(15) NOT NULL
);

SET NAMES utf8;

INSERT INTO evrosetka_users (user_surname, user_name, user_login, user_password, user_gender) VALUES ('Казаченко','Александр','kae00','barsik1976','мужчина'),
('Казаченко','Евгений','santmus','7893480Qw','мужчина');

SELECT user_login, user_password FROM evrosetka_users;

