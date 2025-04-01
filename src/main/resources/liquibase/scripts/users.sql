-- liquibase formatted sql

-- changeset jrembo:1
CREATE TABLE users (
                       id SERIAL,
                       email TEXT
)

-- changeset sconnor:1
ALTER TABLE users ADD name TEXT;

-- changeset disaev:2
DROP TABLE users;

-- changeset disaev:1
