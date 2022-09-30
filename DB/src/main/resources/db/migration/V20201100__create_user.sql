create table if not exists drift.user
(
    id    int         null,
    name  varchar(20) null,
    age   int         null,
    email int         null
    );

INSERT INTO drift.user (id, name, age, email) VALUES (1, '11', 1, 12);

