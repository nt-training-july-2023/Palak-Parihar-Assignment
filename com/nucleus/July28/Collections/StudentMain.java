/**
 * 
 */
package com.nucleus.July28.Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 */
public class StudentMain {

	List<Student> studentList;
	
	
	public StudentMain(List<Student> studentList) {
		super();
		this.studentList = studentList;
	}
	
	public void addElement(Student student) {
		studentList.add(student);
	}
	
	public void printList(StudentMain studentMain) {
		Iterator<Student> iterator = studentMain.studentList.iterator();
		
		while(iterator.hasNext()) {
			
			Student student = iterator.next();
			System.out.println("Student's Name : " +student.getName() + ", Father's Name : " + student.getfName() + " Mother's Name" + student.getmName() +" , Student's Age : " + student.getAge());
		}
		
	}

	/**
	 * 
	 */
	public StudentMain() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentMain studentMain = new StudentMain(new ArrayList<Student>());
		
		studentMain.addElement(new Student("Joseph", "Mike", "Lily", 12));
		
		studentMain.addElement(new Student("Josephine", "Mike", "Lily", 10));
		
		studentMain.addElement(new Student("Casey", "Mike", "Lily", 9));
		
		studentMain.addElement(new Student("Bonnie", "Mike", "Lily", 13));
		
		studentMain.addElement(new Student("Luke", "Mike", "Lily", 15));
		
		System.out.println();
		System.out.println(" Sorting List on the basis of Student's Name : ");
		Collections.sort(studentMain.studentList);
		
		studentMain.printList(studentMain);
		
		
		System.out.println();
		System.out.println(" Sorting List on the basis of Student's Age : ");
		
		Collections.sort(studentMain.studentList, new StudentComparator());
		
		studentMain.printList(studentMain);

	}

}
