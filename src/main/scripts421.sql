/*UPDATE student
SET age = 20
WHERE age < 16 OR age IS NULL;*/

-- 1. Возраст не может быть меньше 16 лет
ALTER TABLE public.student
    ADD CONSTRAINT chk_student_age CHECK (age >= 16);

-- 2. Имена студентов должны быть уникальными и не null
ALTER TABLE public.student
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE public.student
    ADD CONSTRAINT uniq_student_name UNIQUE (name);

-- 3. Установка возраста по умолчанию 20 лет, если не указан
ALTER TABLE public.student
    ALTER COLUMN age SET DEFAULT 20;

-- 4. Пара "название факультета" - "цвет факультета" должна быть уникальной
ALTER TABLE faculty
    ADD CONSTRAINT uniq_faculty_name_color UNIQUE (name, color);


-- Сначала создаем таблицу cars, так как people будет ссылаться на нее
CREATE TABLE cars (
                      car_id SERIAL PRIMARY KEY,
                      brand VARCHAR(100) NOT NULL,
                      model VARCHAR(100) NOT NULL,
                      price DECIMAL(12, 2) NOT NULL
);

-- Создаем таблицу people с внешним ключом на cars
CREATE TABLE people (
                        person_id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        age INTEGER NOT NULL,
                        has_driver_license BOOLEAN NOT NULL,
                        car_id INTEGER REFERENCES cars(car_id)
);