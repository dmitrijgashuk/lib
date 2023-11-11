create table students
(
    id bigserial
        constraint students_pk primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null
);