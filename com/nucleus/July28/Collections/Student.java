/**
 * 
 */
package com.nucleus.July28.Collections;

import java.util.Comparator;

/**
 * 
 */

class StudentComparator implements Comparator<Student> {
	@Override
	public int compare(Student student1, Student student2) {
		// TODO Auto-generated method stub
		return Integer.compare(student1.age, student2.age);
	}
}

public class Student implements Comparable<Student> {

	String name;
	String fName; // Father's Name
	String mName; // Mother's Name
	int age;

	public Student(String name, String fName, String mName, int age) {
		super();
		this.name = name;
		this.fName = fName;
		this.mName = mName;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * 
	 */
	public Student() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Student student) {
		// TODO Auto-generated method stub
		return this.name.compareTo(student.name);
	}

}
