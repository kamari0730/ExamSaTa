drop table student if exists;
create table student(
student_id int auto_increment primary key,
student_name varchar(100) not null,
course_id int not null
);

insert into student values(null,'大原 太郎',1);
insert into student values(null,'大原 花子',1);
insert into student values(null,'大原 達治',2);
insert into student values(null,'大原 由美',2);
insert into student values(null,'大原 四郎',1);
insert into student values(null,'大原 五郎',2);
insert into student values(null,'大原 良子',2);
insert into student values(null,'大原 正子',1);
