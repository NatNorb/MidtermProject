
INSERT INTO address (address_id, city, number, post_code, street) VALUES
(1, "Shire", "8", "12-345", "Shire"),
(2, "Gondor", "45", "456-789", "Mordor St"),
(3, "Rivendell", "12/100", "159-753", "Elven Rd");

INSERT INTO account_holder (acc_holder_id, date_of_birth, mailing_address, name, address_id) VALUES
(1, "1975-09-21", "bilbo@baggins.com", "Bilbo", 1),
(2, "2000-12-31", "frodo@baggins.com", "Frodo", 1),
(3, "1979-02-09", "aragorn@king.com", "Aragorn", 2),
(4, "1985-10-04", "arwen@elven.com", "Arwen", 3);

INSERT INTO account (acc_type, id, penalty_fee, balance, currency, creation_date, primary_owner, secondary_owner, secret_key, status, minimum_balance, monthly_maintenance_fee, credit_limit, interest_rate, acc_holder_id) VALUES
("checking", 1, 40, 12000, "USD", "2021-09-14", "Bilbo", "Frodo", "ABC", "ACTIVE", 250, 12, null, null, 1),
("saving", 2, 40, 50000, "USD","2020-04-16", "Aragorn", null, "BCD", "ACTIVE", 1000, null, null, 0.0025, 3),
("credit_card", 3, 40, 1000,"USD","2019-02-09", "Arwen", null, "CDE", "ACTIVE", null , null, 100, 0.2, 4),
("credit_card", 4, 40, 1000,"USD","2021-08-14", "Aragorn", null, "CDE", "ACTIVE", null , null, 100, 0.15, 3),
("saving", 5, 40, 1000, "USD","2018-06-06", "Bilbo", null, "BCD", "ACTIVE", 1000, null, null, 0.25, 1);

INSERT INTO third_party (id, hashed_key, name) VALUES
(1, "1XYZ", "Legolas"),
(2, "2XYZ", "Gimli");
