You will need to create a local MySQL (because the key type is identity) database with the following code to run the webapp successfully:


CREATE TABLE vault (
    vault_number INT PRIMARY KEY,
    location VARCHAR(255),
    overseer_name VARCHAR(255),
    population INT,
    status VARCHAR(255),
    radiation_level DOUBLE
);

CREATE TABLE vault_dweller (
    dweller_id INT AUTO_INCREMENT PRIMARY KEY,
    vault_number INT,
    name VARCHAR(255),
    gender VARCHAR(255),
    status VARCHAR(255),
    join_date DATE,
    last_seen DATETIME,
    radiation_level DECIMAL(38,2),
    strength INT,
    intelligence INT,
    password_hash VARCHAR(255),
    security_clearance VARCHAR(255),
    username VARCHAR(255),
    FOREIGN KEY (vault_number) REFERENCES vault(vault_number)
);

CREATE TABLE tickets (
    ticket_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    status ENUM('OPEN','AWAITING_RESPONSE','RESOLVED'),
    submitter_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (submitter_id) REFERENCES vault_dweller(dweller_id)
);

CREATE TABLE ticket_messages (
    message_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT,
    sender_id INT,
    ticket_id BIGINT,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES vault_dweller(dweller_id),
    FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id)
);

CREATE TABLE radio_station (
    station_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    frequency DECIMAL(38,2),
    location VARCHAR(255),
    is_online TINYINT
);

CREATE TABLE radio_message (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    station_id INT,
    sender VARCHAR(255),
    content LONGTEXT,
    timestamp DATETIME,
    is_emergency TINYINT,
    FOREIGN KEY (station_id) REFERENCES radio_station(station_id)
);

CREATE TABLE location (
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    danger_level INT,
    description LONGTEXT,
    x_coord INT,
    y_coord INT
);

CREATE TABLE inventory_item (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    dweller_id INT,
    name VARCHAR(255),
    item_type VARCHAR(255),
    quantity INT,
    condition_percentage INT,
    weight DECIMAL(38,2),
    FOREIGN KEY (dweller_id) REFERENCES vault_dweller(dweller_id)
);

CREATE TABLE dweller_location (
    dweller_id INT,
    location_id INT,
    update_time DATETIME,
    PRIMARY KEY (dweller_id, location_id),
    FOREIGN KEY (dweller_id) REFERENCES vault_dweller(dweller_id),
    FOREIGN KEY (location_id) REFERENCES location(location_id)
);