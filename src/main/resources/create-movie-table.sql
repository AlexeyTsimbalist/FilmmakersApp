create table movie(
    id int not null auto_increment primary key,
    name varchar(255) not null,
    year date not null,
    duration int,
    filmmaker_id int,
    constraint filmmaker_id_fk
    foreign key (filmmaker_id)
    references filmmaker(id) on delete cascade
) ENGINE=InnoDB;