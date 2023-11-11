drop schema if exists test cascade;
create schema test;

set schema 'test';

drop table if exists students;
create table students
(
    id bigserial
        constraint students_pk primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null
);

CREATE TABLE books
(
    id bigserial
        constraint books_pk
        primary key,
    title      varchar(255) not null,
    student_id bigint,
    constraint book_student_id_fk foreign key (student_id)
        references students (id) on delete set null
);