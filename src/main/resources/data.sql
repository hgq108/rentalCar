DROP TABLE IF EXISTS car;
-- DROP TABLE IF EXISTS reserve;

CREATE TABLE car (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  model VARCHAR(250) NOT NULL,
  is_reserved BOOLEAN default FALSE,
  customer_id  INT,
  start_time DATE,
  end_time DATE
);

INSERT INTO car (model, is_reserved, customer_id, start_time, end_time) VALUES
  ('Toyota Camry', false, null, null, null),
  ('Toyota Camry', false, null, null, null),
  ('BMW 650', false, null, null, null),
  ('BMW 650', true, 1001, '2022-01-02', '2022-02-05');


-- CREATE TABLE reserve (
--     id INT AUTO_INCREMENT  PRIMARY KEY,
--     car_id INT NOT NULL,
--     customer_id INT NOT NULL,
--     start_time DATE NOT NULL,
--     end_time DATE NOT NULL
-- );
--
-- INSERT INTO reserve (car_id, customer_id, start_time, end_time) VALUES
--     (4, 1001, '2022-01-02', '2022-02-05')