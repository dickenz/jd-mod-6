INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES
    ('John Doe', '1990-05-15', 'Trainee', 800),
    ('Jane Smith', '1985-08-22', 'Junior', 1200),
    ('Michael Johnson', '1982-02-10', 'Middle', 3000),
    ('Emily Davis', '1978-11-05', 'Senior', 6000),
    ('David Brown', '1992-07-30', 'Middle', 2500),
    ('Jennifer Wilson', '1987-04-12', 'Junior', 1100),
    ('Christopher Anderson', '1995-09-18', 'Trainee', 900),
    ('Jessica Taylor', '1989-01-25', 'Middle', 2800),
    ('Daniel Martinez', '1984-06-08', 'Senior', 5500),
    ('Olivia Thomas', '1991-03-02', 'Junior', 1300);


INSERT INTO client (NAME) VALUES
    ('ABC Company'),
    ('XYZ Corporation'),
    ('123 Industries'),
    ('Best Solutions Inc.'),
    ('Global Tech');


INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES
    (1, '2023-01-01', '2023-04-30'),
    (2, '2022-11-15', '2023-03-15'),
    (4, '2023-02-01', '2024-01-31'),
    (4, '2022-09-01', '2023-06-30'),
    (5, '2023-05-01', '2023-06-30'),
    (1, '2023-03-15', '2024-02-29'),
    (2, '2022-12-01', '2023-06-30'),
    (5, '2023-01-01', '2023-05-31'),
    (4, '2023-02-15', '2023-08-15'),
    (5, '2022-10-01', '2023-01-31');


INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 4),
    (2, 5),
    (3, 6),
    (3, 7),
    (4, 8),
    (4, 9),
    (5, 10),
    (6, 1),
    (6, 2),
    (6, 4),
    (7, 6),
    (7, 8),
    (7, 10),
    (8, 3),
    (8, 5),
    (9, 1),
    (9, 6),
    (9, 9),
    (10, 2),
    (10, 4),
    (10, 8);