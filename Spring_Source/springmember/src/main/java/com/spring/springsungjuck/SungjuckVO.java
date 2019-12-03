package com.spring.springsungjuck;
/*
create table ssungjuck(
	id varchar2(15),
	hakbun varchar2(10),
	name varchar2(15),
	kor number(3),
	eng number(3),
	math number(3),
	tot number(3),
	avg number(5,2),
	grade varchar2(5),
	primary key(id)
	);
*/
public class SungjuckVO {
	private String id;
	private String hakbun;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private double avg;
	private String grade;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHakbun() {
		return hakbun;
	}
	public void setHakbun(String hakbun) {
		this.hakbun = hakbun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getTot() {
		return tot;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public void process() {
		tot = kor + eng + math;
		avg = tot/3.;
		switch((int)avg/10) {
		case 10:
		case 9:
			grade="수";
			break;
		case 8:
			grade="우";
			break;
		case 7:
			grade="미";
			break;
		case 6:
			grade="양";
			break;
		default:
			grade="가";
			break;
		}
	}
	
}
