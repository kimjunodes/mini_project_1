create table members (
    user_id varchar(20),
    user_pw varchar(20),
    user_pn varchar(30),
    user_addr varchar(100),
    ticket_id number,
    
    primary key (user_id)
);

create table ticket (
    ticket_id number,
    m_id number,
    user_id varchar(20),
    
    primary key (ticket_id)
);

alter table ticket add foreign key(user_id) references members(user_id);

create table movie(
    m_id number,
    m_name varchar(20),
    m_time date,
    hall number,
    
    primary key (m_id)
);

create table movie_table(
    m_name varchar(20),
    actor varchar(20),
    dir varchar(20),
    mt_time date,
    age number,
    story varchar(200),
    run_time number,
    
    primary key (m_name)
);

select * from movie_table;
commit;