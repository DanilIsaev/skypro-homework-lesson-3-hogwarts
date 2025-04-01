

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