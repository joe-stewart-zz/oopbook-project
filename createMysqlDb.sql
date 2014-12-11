drop database if exists university;
create database university;
use university;

create table Course (code varchar(10) not null,
title varchar(30),
numCredits int,
maxStudents int,
primary key (code));

create table Student (studentID bigint not null,
name varchar(30),
address varchar(30),
phone varchar(30),
email varchar(30),
primary key (studentID));

create table UGStudent (studentID bigint not null,
major varchar(30),
minor varchar(30),
primary key (studentID));

create table PGStudent (studentID bigint not null,
thesisTitle varchar(30),
supervisor varchar(30),
primary key (studentID));

create table Registration (studentID bigint not null,
courseCode varchar(30) not null,
primary key (studentID, courseCode));

#insert into Course values ('comp101', 'computer programming 1', 4, 20);
