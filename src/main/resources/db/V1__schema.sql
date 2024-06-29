CREATE TABLE filter (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         filter_sequence INT,
                         title VARCHAR(255) NOT NULL
);

CREATE TABLE criteria (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           filter_id BIGINT,
                           type VARCHAR(255) NOT NULL,
                           condition VARCHAR(255) NOT NULL,
                           criterion_value VARCHAR(255) NOT NULL,
                           FOREIGN KEY (filter_id) REFERENCES filter(id) ON DELETE CASCADE
);

CREATE INDEX idx_criteria_filter_id ON criteria(filter_id);