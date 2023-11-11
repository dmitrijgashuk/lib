set schema 'test';

insert into test.books (id, title, student_id)
VALUES (1, 'War and Peace', null),
       (2, 'Everybody lies', null);

insert into test.students(id, first_name, last_name)
VALUES (1, 'Oleg', 'Moroz'),
       (2, 'Igor', 'Peklin'),
       (3, 'Sergey', 'Graboviy'),
       (4, 'Mazim', 'Skochok');
