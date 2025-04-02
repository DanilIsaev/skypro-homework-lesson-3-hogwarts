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
CREATE INDEX index_for_searching_by_student_name ON student(name)

-- changeset disaev:3
CREATE INDEX  index_for_searching_by_faculty_name ON faculty (name);
CREATE INDEX  index_for_searching_by_faculty_color ON faculty (color);
