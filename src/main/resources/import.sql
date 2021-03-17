INSERT INTO USERS (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, PHONENUMBER, ACTIVATED) VALUES ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', '099999999', 1);
INSERT INTO USERS (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, PHONENUMBER, ACTIVATED) VALUES ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', '099999999', 1);
INSERT INTO USERS (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, PHONENUMBER, ACTIVATED) VALUES ('disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', '099999999', 0);

INSERT INTO AUTHORITY (NAME) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (NAME) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (NAME) VALUES ('ROLE_SPECIAL_USER');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_USER');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (2, 'ROLE_ADMIN');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (3, 'ROLE_USER');

INSERT INTO BUSINESSTYPE (TYPE, NAME, DESCRIPTION) VALUES ('Za djecu', 'Psihološko savjetovanje', 'opis1');
INSERT INTO BUSINESSTYPE (TYPE, NAME, DESCRIPTION) VALUES ('Za odrasle ', 'Psihološko savjetovanje', 'opis2');
INSERT INTO BUSINESSTYPE (TYPE, NAME, DESCRIPTION) VALUES ('Za odrasle', 'usluga', 'opis3');

INSERT INTO TERMIN (TERMIN_START, TERMIN_END, DESCRIPTION) VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL 1 HOUR, 'Termin');

INSERT INTO REVIEW (GRADE, REVIEWER_ID, TARGET_ID, DESCRIPTION) VALUES (5, 2, 3, 'Dobar doktor');

