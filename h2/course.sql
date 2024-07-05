drop table course if exists;
create table course(
course_id int primary key,
course_name varchar(100) not null,
);

insert into course values(1,'システム開発コース');
insert into course values(2,'AIシステム・データサイエンスコース');
