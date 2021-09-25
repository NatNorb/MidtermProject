
INSERT INTO address (address_id, city, number, post_code, street) VALUES
(1, "Shire", "8", "12-345", "Shire"),
(2, "Gondor", "45", "456-789", "Mordor St"),
(3, "Rohan", "12/100", "159-753", "Castle");

INSERT INTO account_holder (acc_holder_id, date_of_birth, mailing_address, name, address_id) VALUES
(1, "1975-09-21", "bilbo@baggins.com", "Bilbo", 1),
(2, "2000-12-31", "frodo@baggins.com", "Frodo", 1),
(3, "1979-02-09", "aragorn@king.com", "Aragorn", 2),
(4, "1985-10-04", "eowina@rohan.com", "Eowina", 3);

INSERT INTO account (acc_type, id, penalty_fee, balance, currency, creation_date, primary_owner, secondary_owner, secret_key, status, minimum_balance, monthly_maintenance_fee, credit_limit, interest_rate, acc_holder_id) VALUES
("checking", 1, 40, 12000, "USD", "2021-09-14", "Bilbo", "Frodo", "ABC", "ACTIVE", 250, 12, null, null, 1),
("saving", 2, 40, 50000, "USD","2020-04-16", "Aragorn", null, "BCD", "ACTIVE", 1000, null, null, 0.0025, 3),
("credit_card", 3, 40, 1000,"USD","2019-02-09", "Eowina", null, "CDE", "ACTIVE", null , null, 100, 0.2, 4),
("credit_card", 4, 40, 1000,"USD","2021-08-14", "Aragorn", null, "CDE", "ACTIVE", null , null, 100, 0.15, 3),
("saving", 5, 40, 1000, "USD","2018-06-06", "Bilbo", null, "BCD", "ACTIVE", 1000, null, null, 0.25, 1);

INSERT INTO third_party (id, hashed_key, name) VALUES
(1, "1XYZ", "Legolas"),
(2, "2XYZ", "Gimli");

INSERT INTO user (username, password) VALUES
("account-holder", "$2a$10$MSzkrmfd5ZTipY0XkuCbAejBC9g74MAg2wrkeu8/m1wQGXDihaX3e"), --123456
("third-party", "$2a$15$NAYChDNT4kWKytFJLqNm0.vN9oNv0l9wa1acKQkDd7uSlgI6GpyIO"), --456789
("admin", "$2a$15$LNyWbikF3gn83ZnBBG1L8OxG9BXKe5smU2I5FF0quoJD9qtXIRBCm"); --789123

INSERT INTO role (name, user_id) VALUES
("ACCOUNT-HOLDER", 1),
("ADMIN", 3),
("THIRD-PARTY", 2);

INSERT INTO transaction (acc_holder_id, acc_id,foreign_acc_holder_id,foreign_acc_id,internal_op,operations,timestamp,value) VALUES
(4, 3, 3, 2, 1, "WITHDRAWAL", "2021-09-18 21:02:04", -417.15),
(4, 3, 3, 2, 1, "DEPOSIT", "2021-09-18 21:02:04", 338.66),
(4, 3, 1, 1, 1, "DEPOSIT", "2021-09-18 22:04:47", 133.52),
(3, 2, 1, 1, 1, "WITHDRAWAL", "2021-09-19 07:45:15", -560.05),
(3, 4, 3, 2, 1, "WITHDRAWAL", "2021-09-19 07:56:07", -205.24),
(3, 2, 4, 3, 1, "DEPOSIT", "2021-09-19 10:14:01", 720.77),
(1, 1, 1, 5, 1, "WITHDRAWAL", "2021-09-19 10:54:31", -125.8),
(4, 3, 3, 4, 1, "WITHDRAWAL", "2021-09-19 14:54:10", -817.58),
(4, 3, "2XYZ", null, 0, "WITHDRAWAL", "2021-09-19 15:29:44", -570.16),
(1, 5, 3, 2, 1, "WITHDRAWAL", "2021-09-19 17:50:27", -789.11),
(1, 1, 4, 3, 1, "DEPOSIT", "2021-09-20 05:06:47", 711.52),
(1, 5, 1, 1, 1, "WITHDRAWAL", "2021-09-20 07:38:58", -661.27),
(3, 4, 1, 5, 1, "WITHDRAWAL", "2021-09-20 08:02:32", -157.2),
(1, 1, 3, 4, 1, "WITHDRAWAL", "2021-09-20 11:27:32", -447.12),
(4, 3, 1, 5, 1, "WITHDRAWAL", "2021-09-20 15:12:51", -16.58),
(3, 2, 3, 4, 1, "WITHDRAWAL", "2021-09-20 18:00:42", -990.9),
(4, 3, "2XYZ", null, 0, "WITHDRAWAL", "2021-09-21 09:38:10", -501.34),
(1, 5, 4, 3, 1, "DEPOSIT", "2021-09-21 10:51:09", 830.12),
(3, 4, 4, 3, 1, "DEPOSIT", "2021-09-21 12:42:46", 961.5),
(3, 2, 1, 5, 1, "WITHDRAWAL", "2021-09-21 22:14:22", -903.36),
(1, 1, "1XYZ", null, 0, "WITHDRAWAL", "2021-09-22 00:30:27", -980.49),
(1, 5, 3, 2, 1, "WITHDRAWAL", "2021-09-22 04:52:22", -863.35),
(3, 4, "1XYZ", null, 0, "WITHDRAWAL", "2021-09-22 05:12:37", -69.2),
(1, 1, 4, 3, 1, "DEPOSIT", "2021-09-22 07:19:57", 518.18),
(4, 3, 3, 4, 1, "DEPOSIT", "2021-09-22 09:07:32", 536.33),
(3, 2, 4, 3, 1, "DEPOSIT", "2021-09-22 15:45:06", 203.17),
(3, 4, 1, 1, 1, "WITHDRAWAL", "2021-09-23 07:53:50", -877.77);

