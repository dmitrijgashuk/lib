CREATE TABLE books
(
    id         bigserial
        constraint books_pk
            primary key,
    title      varchar(255) not null,
    student_id bigint,
    constraint book_student_id_fk foreign key (student_id)
        references students (id) on delete set null
);