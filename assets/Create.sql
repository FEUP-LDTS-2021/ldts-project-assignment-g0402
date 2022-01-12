PRAGMA
    foreign_keys = on;
.mode column
.headers on
.nullvalue NULL
BEGIN TRANSACTION;
-- Table: Monster
DROP TABLE IF EXISTS Wave;
DROP TABLE IF EXISTS Monster;

CREATE TABLE Monster
(
    MNTID        INTEGER PRIMARY KEY,
    Name         VARCHAR(25)  NOT NULL,
    destructible INTEGER      NOT NULL CHECK (destructible == 0 or destructible == 1),
    life         INTEGER      NOT NULL CHECK (life > 0),
    sprite       VARCHAR(100) NOT NULL,
    speed        INTEGER      NOT NULL CHECK (speed > 0)
);
-- Table: Wave
PRAGMA foreign_keys = OFF;

DROP TABLE IF EXISTS Wave;
PRAGMA
    foreign_keys = on;
CREATE TABLE Wave
(
    WAVID      INTEGER PRIMARY KEY,
    MNTID      INTEGER REFERENCES Monster (MNTID) NOT NULL,
    level      INTEGER                            NOT NULL CHECK (level > 0),
    xInitial   INTEGER                            NOT NULL CHECK (xInitial > 0),
    yInitial   INTEGER                            NOT NULL CHECK (yInitial > 0),
    xOffset    INTEGER                            NOT NULL CHECK (xOffset > 0),
    yOffset    INTEGER                            NOT NULL CHECK (yOffset > 0),
    lineSize   INTEGER                            NOT NULL CHECK (lineSize > 0),
    columnSize INTEGER                            NOT NULL CHECK (columnSize > 0)
);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;