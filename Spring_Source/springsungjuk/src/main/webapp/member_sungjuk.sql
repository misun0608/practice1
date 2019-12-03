create table smember (
  id varchar2(15),
  password varchar2(10),
  name varchar2(15),
  age number,
  gender varchar2(5),
  email varchar2(30),
  primary key(id)
);

create table sungjuk (
  id varchar2(15),
  hakbun varchar2(10),
  irum varchar2(15),
  kor number(3),
  eng number(3),
  math number(3),
  tot number(3),
  avg number(5,2),
  grade varchar2(5),
  primary key(id)
);