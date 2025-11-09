CREATE DATABASE waste_collection9;

USE waste_collection9;

CREATE TABLE schedule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    area VARCHAR(100),
    collection_date DATE,
    vehicle_number VARCHAR(50),
    status VARCHAR(20)
);
select * from schedule;