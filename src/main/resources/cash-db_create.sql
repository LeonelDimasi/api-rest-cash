 drop table if exists loan CASCADE 

    
    drop table if exists user CASCADE 

    
    create table loan (
       id_loan bigint generated by default as identity,
        id_user bigint not null,
        primary key (id_loan)
    )

    
    create table user (
       id bigint generated by default as identity,
        email varchar(255) not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        primary key (id)
    )

    
    alter table loan 
       add constraint FKnx6y4sq2u7xecyn0yqiwm05br 
       foreign key (id_user) 
       references user