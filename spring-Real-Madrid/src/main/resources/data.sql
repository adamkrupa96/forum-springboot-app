INSERT INTO user (imie, nazwisko, email, haslo) VALUES ('Admin', 'Adam', 'admin@mail.com', '$2a$10$hv.qM./dL3IBtiIQpFK44O3B.LQIYdLQLK8/kTLoGnSFanTFQhvGi');
INSERT INTO user (imie, nazwisko, email, haslo) VALUES ('User', 'Adam', 'user@mail.com', '$2a$10$1tCQL2gMFTB9mFs4ZE8DsOf5mX2A03FLqvv1xIK403/583luwzw8u');

INSERT INTO role (nazwa) VALUES ('ROLE_ADMIN');
INSERT INTO role (nazwa) VALUES ('ROLE_MANAGER');
INSERT INTO role (nazwa) VALUES ('ROLE_USER');

INSERT INTO user_roles (user_user_id, roles_role_id) VALUES (1, 1);
INSERT INTO user_roles (user_user_id, roles_role_id) VALUES (1, 2);
INSERT INTO user_roles (user_user_id, roles_role_id) VALUES (2, 3);

INSERT INTO article (zawartosc_tekstowa, utworzony, tytul, user_id, zrodlo) VALUES ('Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego, Opis artykulu pierwszego', '2018-01-01 23:37:52', 'Tytul pierwszego newsa', '1', 'www.realmadrid.com');
INSERT INTO article (zawartosc_tekstowa, utworzony, tytul, user_id, zrodlo) VALUES ('Opis artykulu drugiego', '2018-01-01 23:37:52', 'Tytul drugiego newsa', '1', 'www.realmadrid.com');
INSERT INTO article (zawartosc_tekstowa, utworzony, tytul, user_id, zrodlo) VALUES ('Opis artykulu trzeciego', '2018-01-01 23:37:52', 'Tytul trzeciego newsa', '1', 'www.realmadrid.com');

