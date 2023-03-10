CREATE SCHEMA IF NOT EXISTS WeatherViewer;

CREATE TABLE IF NOT EXISTS WeatherViewer.users (
    ID       SERIAL PRIMARY KEY,
    Email    VARCHAR(256) UNIQUE NOT NULL,
    Password VARCHAR(256)        NOT NULL
);

CREATE TABLE IF NOT EXISTS WeatherViewer.sessions (
    ID        VARCHAR(256) PRIMARY KEY,
    UserID    BIGINT REFERENCES WeatherViewer.users (ID),
    ExpiresAt TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS WeatherViewer.locations (
    ID        SERIAL PRIMARY KEY,
    City      VARCHAR(256) NOT NULL,
    Country   VARCHAR(256) NOT NULL,
    UserID    BIGINT REFERENCES WeatherViewer.users (ID),
    Latitude  DECIMAL      NOT NULL,
    Longitude DECIMAL      NOT NULL
);
