create table filmmaker(
    id long not null auto_increment primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    country varchar(30) not null,
    date_of_birth date not null    
) engine=InnoDB;
