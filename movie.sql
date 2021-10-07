Alter table ticket add payment varchar(10);
Alter table ticket add pay varchar(10) default 'NO' not null;

Alter table movie_table add price number;

update movie_table set price = 7000
where m_name = 'WALL-E';

update movie_table set price = 8000
where m_name = 'SAVING PRIVATE RYAN';

update movie_table set price = 9000
where m_name = 'Titanic';

update ticket set payment = 'cash'
where m_id = 1;

update ticket set payment = 'card'
where m_id = 2;

update ticket set pay = 'YES'
where payment = 'card';
commit;