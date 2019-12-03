package com.spring.springmember;
/*
create table smember(
	    id varchar2(15),
	    password number(10),
	    name varchar2(15),
	    age number(3),
	    gender VARCHAR2(4),
	    email varchar2(20),
	    primary key(id)
	    );
*/


public class MemberVO {
	String id;
	String password;
	String name;
	int age;
	String gender;
	String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
