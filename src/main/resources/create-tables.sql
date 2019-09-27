drop table if exists movie;
drop table if exists filmmaker;

create table filmmaker(
    id int not null auto_increment primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    country varchar(30) not null,
    date_of_birth date not null    
);

create table movie(
      id int not null auto_increment primary key,
      name varchar(255) not null,
      release_date date not null,
      duration int,
      filmmaker_id int,
      constraint filmmaker_id_fk
          foreign key (filmmaker_id)
              references filmmaker(id) on delete cascade
);