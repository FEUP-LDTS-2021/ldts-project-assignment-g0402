INSERT INTO Monster (Name, destructible, life, sprite, speed)
VALUES ("Gabriel Coelho", 1, 1, "{0,0,1,0}, {1,0,1,1}, {1,1,0,1}, {0,1,0,0}", 1),
       ("Matias Vaz", 1, 3, "0,0,2,0 .. 2,0,2,2 .. 2,2,0,1 .. 0,2,0,0", 1);

INSERT INTO Wave (MNTID, level, xInitial, yInitial, xOffset, yOffset, lineSize, columnSize)
VALUES (1, 1, 3, 3, 4, 4, 12, 6);