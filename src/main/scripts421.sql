/*UPDATE student
SET age = 20
WHERE age < 16 OR age IS NULL;*/

--1
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
CREATE TABLE cars
(
    car_id SERIAL PRIMARY KEY,
    brand  VARCHAR(100)   NOT NULL,
    model  VARCHAR(100)   NOT NULL,
    price  DECIMAL(12, 2) NOT NULL
);

--2
-- Создаем таблицу people с внешним ключом на cars
CREATE TABLE people
(
    person_id          SERIAL PRIMARY KEY,
    name               VARCHAR(100) NOT NULL,
    age                INTEGER      NOT NULL,
    has_driver_license BOOLEAN      NOT NULL,
    car_id             INTEGER REFERENCES cars (car_id)
);

-- 3
--Получить всех студентов с названиями факультетов
SELECT s.name AS student_name,
       s.age  AS student_age,
       f.name AS faculty_name
FROM student as s
         JOIN
     faculty f ON s.faculty_id = f.id;

--JOIN-запрос, чтобы получить только тех студентов, у которых есть аватарки
SELECT s.name      AS student_name,
       s.age       AS student_age,
       f.name      AS faculty_name,
       a.file_path AS avatar_path, -- дополнительно можно вывести путь к аватарке
       a.file_size AS avatar_size  -- и размер файла
FROM student s
         JOIN
     faculty f ON s.faculty_id = f.id
         JOIN
     avatar a ON s.id = a.student_id -- связь по student_id
WHERE a.data IS NOT NULL