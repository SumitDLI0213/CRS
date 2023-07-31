package com.digit.crsApp.service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

import com.crsApp.CRSApp;
import com.mysql.cj.xdevapi.Result;

public class AdminServices 
{
	Scanner sc = new Scanner(System.in);
	private PreparedStatement pstmt;
	private ResultSet resultset;
	private PreparedStatement psmt;
	//private ResultSet resultset;
	public void menu() throws Exception 
	{
		// TODO Auto-generated method stub
		System.out.println("Select Option:");
		System.out.println("1. Add course\n" + "2. Add Student\n" + "3. Add Professor\n"+"4. Exit");
		int n = sc.nextInt();
		switch (n) {
		case 1: {
			addCourse();
			break;
		}
		case 2: {
			addstudent();
			break;
		}
		case 3: {
			addProfessor();
			break;
		}
		case 4: {
			System.out.println("Exiting Admin Services");
			CRSApp.main_menu();
		}
		default: {
			System.out.println("wrong input Please enter from options");
		}
		}
	}

	public void addCourse() {
		try {
			String sql = "insert into course values(?,?,?,?)";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Course ID :- ");
			pstmt.setInt(1, sc.nextInt());
			System.out.println("Course Name :- ");
			pstmt.setString(2, sc.next());
			System.out.println("Course Fees :- ");
			pstmt.setInt(3, sc.nextInt());
			System.out.println("Course Duration in Months :- ");
			pstmt.setInt(4, sc.nextInt());
			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Course Added ");
				menu();
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("ID already Exists Please Select Different Id");
			addCourse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addstudent() {
		try {
			String cred="insert into users values(?,?,?)";
			pstmt = CRSApp.con.prepareStatement(cred);
			System.out.println("Please make Your Student User Name and Password :- ");
			System.out.println("Enter Student User name:- ");
			pstmt.setString(1, sc.next());
			System.out.println("Enter Student Password:- ");
			pstmt.setString(2, sc.next());
			//System.out.println("Enter which type It is :- ");
			pstmt.setString(3, "Student");
			int y=pstmt.executeUpdate();
			if (y > 0) {
				System.out.println("Student Credential  Added :");
				//menu();
			}
			else {
				System.out.println("Wrong Credential Please Re-enter");
				addProfessor();
			}
			String sql = "insert into student (Sid,sname,email,coursetaken) values(?,?,?,?);";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter Student ID :- ");
			pstmt.setInt(1, sc.nextInt());
			System.out.println("Enter Student Name :- ");
			pstmt.setString(2, sc.next());
			System.out.println("Enter your email:- ");
			pstmt.setString(3, sc.next());
			String course = "select * from course";
			psmt = CRSApp.con.prepareStatement(course);
			resultset = psmt.executeQuery();
			while(resultset.next()==true)
     	    {   
     	    	System.out.println("--------------------------------------");
     	    	System.out.println("Course Id is "+resultset.getInt(1));
     	    	System.out.println("Course's Name is "+resultset.getString(2));
     	    	System.out.println("--------------------------------------");
     	    }
			System.out.println("Enter Course name You want to take from Above Courses");
			pstmt.setString(4, sc.next());
		    int x = pstmt.executeUpdate();
			if (x > 0) 
			{
				System.out.println("Student Added :");
				menu();
			}
			
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("ID already Exists Please Select Different Id");
			addCourse();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addProfessor() {
		try {
			String cred="insert into users values(?,?,?)";
			pstmt = CRSApp.con.prepareStatement(cred);
			System.out.println("Please make Your Professor User Name and Password :- ");
			System.out.println("Enter Professor User name:- ");
			pstmt.setString(1, sc.next());
			System.out.println("Enter Professor Password:- ");
			pstmt.setString(2, sc.next());
			//System.out.println("Enter which type It is :- ");
			pstmt.setString(3, "Professor");
			int y=pstmt.executeUpdate();
			if (y > 0) {
				System.out.println("Professor Credential  Added :");
				//menu();
			}
			else {
				System.out.println("Wrong Credential Please Re-enter");
				addProfessor();
			}
			String sql = "insert into professor values(?,?,?)";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter Professor ID :- ");
			pstmt.setInt(1, sc.nextInt());
			System.out.println("Enter Professor Name :- ");
			pstmt.setString(2, sc.next());
			System.out.println("Enter Professor Experience :- ");
			pstmt.setInt(3, sc.nextInt());
            
			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Professor Added :");
				menu();
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("ID already Exists Please Select Different Id");
			addCourse();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
