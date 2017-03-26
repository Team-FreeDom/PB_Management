package com.base.po;

public class StatisticData {
    private int typenum;
    private int namenum;
    private int collegenum;
    private int majornum;
    private int teachernum;
    private int classnum;
    private int expensenum;
    private int personnum;
    public StatisticData() {
   	super();
       }

    public StatisticData(int typenum, int namenum, int collegenum,
	    int majornum, int teachernum, int classnum, int expensenum,
	    int personnum) {
	super();
	this.typenum = typenum;
	this.namenum = namenum;
	this.collegenum = collegenum;
	this.majornum = majornum;
	this.teachernum = teachernum;
	this.classnum = classnum;
	this.expensenum = expensenum;
	this.personnum = personnum;
    }

    public int getTypenum() {
	return typenum;
    }

    public void setTypenum(int typenum) {
	this.typenum = typenum;
    }

    public int getNamenum() {
	return namenum;
    }

    public void setNamenum(int namenum) {
	this.namenum = namenum;
    }

    public int getCollegenum() {
	return collegenum;
    }

    public void setCollegenum(int collegenum) {
	this.collegenum = collegenum;
    }

    public int getMajornum() {
	return majornum;
    }

    public void setMajornum(int majornum) {
	this.majornum = majornum;
    }

    public int getTeachernum() {
	return teachernum;
    }

    public void setTeachernum(int teachernum) {
	this.teachernum = teachernum;
    }

    public int getClassnum() {
	return classnum;
    }

    public void setClassnum(int classnum) {
	this.classnum = classnum;
    }

    public int getExpensenum() {
	return expensenum;
    }

    public void setExpensenum(int expensenum) {
	this.expensenum = expensenum;
    }

    public int getPersonnum() {
	return personnum;
    }

    public void setPersonnum(int personnum) {
	this.personnum = personnum;
    }

}
