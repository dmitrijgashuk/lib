CREATE TABLE security_user
(
    id  bigserial constraint user_pk
            primary key,
    login varchar(255) not null,
    password   varchar(255) not null,
    role varchar(25) not null default 'USER',
    status varchar(25) default 'ACTIVE'
);

INSERT INTO security_user VALUES (1,'Casper', '$2a$12$ftEY/4Adibw1cjBm/BbdLu3CjlZ/oDRDv9gRrEsNoMBjU7XoWCL4i','ADMIN','ACTIVE');
INSERT INTO security_user VALUES (2,'User', '$2a$12$/Vb3OBD7XPFNBpfqGZlLDuX59rO5qAOZ7rpxGKdds.n9KX9UXCXd.','USER','BANNED');