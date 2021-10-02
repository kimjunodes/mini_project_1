ALTER SESSION SET NLS_DATE_FORMAT = 'yy/mm/dd hh24:mi';
   
create table movie (
    m_id number not NULL, 
    m_name varchar(100) not Null,
    Time date not null,
    hall number not null,
    seat number);
    
insert into movie values (
    2,
    'No Time To Die',
    '21/09/30 19:00',
    3,
    0
    );
    
create table movie_Table (
    m_name varchar(100) not Null, 
    d_name varchar(20) not Null,
    O_Time date not null,
    age number,
    actor varchar(20),
    story varchar2(200));
    
insert into movie_table values (
    'No Time To Die',
    'Cary Fukunaga',
    '21/09/29',12,
    'Daniel Craig',
    'story~~' 
);
   
    drop table members;
create table members (
    m_id varchar(15) not Null, 
    m_pw varchar(15) not Null,
    p_number varchar(30),
    m_address varchar2(100)
);    
insert into members values(
    '0',
    '0000',
    '154-481-1824',
    'Ansan'
    );
    
    
