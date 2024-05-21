CREATE TABLE author (
    id VARCHAR(40) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birthdate DATE,
    nationality VARCHAR(3)
);

INSERT INTO author VALUES ('c68b5904-4979-451e-8e65-d8c48314ce14', 'Jane Austen', '1975-12-16', 'CAN');