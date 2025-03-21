SELECT *
FROM student
WHERE age > 10
  AND age < 21;

SELECT name
FROM student;

SELECT *
FROM student
WHERE name LIKE '%a%';

SELECT *
FROM student
WHERE age < id;

SELECT *
FROM student
ORDER BY age asc;