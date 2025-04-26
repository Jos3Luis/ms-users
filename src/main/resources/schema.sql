CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),
    created TIMESTAMP,
    modified TIMESTAMP,
    last_login TIMESTAMP,
    token VARCHAR(200),
    is_active BOOLEAN
);
CREATE TABLE phones (
    id UUID PRIMARY KEY,
    number VARCHAR(20),
    citycode VARCHAR(10),
    countrycode VARCHAR(10),
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);