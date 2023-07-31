package com.digit.crsApp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.crsApp.CRSApp;

public class StudentServices {
	private static String stud_user_name;
	private static PreparedStatement pstmt;
	private static String stud_password;
	private static ResultSet res;
	private ResultSet resultset;

	Scanner sc = new Scanner(System.in);
	private ResultSet reslut;

	// view grades
	// score card
	// std details
	public void student_menu() throws Exception {
		System.out.println("\nWelcome To Student Menu ");
		System.out.println("   Select your Choice ");
		System.out.println("1. Student Details\n" + "2. View Grades\n" + "3. View Score Card\n" + "4. Exit\n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		switch (n) {
		case 1: {
			Student_details();
		}
		case 2: {
			show_grades();
		}
		case 3: {
			score_card();
		}
		case 4: {
			System.out.println("Exiting Student menu");
			CRSApp.main_menu();
		}
		default:
			System.out.println("Enter Right Choice");

		}
	}

	public void Student_details() {
		try {
			String sql = "Select * from student where sname=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter Student Name ");
			pstmt.setString(1, sc.next());
			resultset = pstmt.executeQuery();
			while (resultset.next() == true) {
				System.out.println("Student Id is " + resultset.getInt(1));
				System.out.println("Student's Name is " + resultset.getString(2));
				System.out.println("Student's Email Id is " + resultset.getString(3));
				System.out.println("Student's Course Taken is " + resultset.getString(5));
				System.out.println("--------------------------------------");
			}
			System.out.println("Enter 1 for Going Back anda 0 for Exit ");
			int n = Integer.parseInt(sc.next());
			if (n == 1) {
				System.out.println("Going Back to Main menu ");
				student_menu();
			} else if (n == 0) {
				System.out.println("Wrong input Existing ");
				CRSApp.main_menu();
			} else {
				System.out.println("Wrong input Existing ");
				CRSApp.main_menu();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void show_grades() {
		try {
			String sql = "Select * from student where sname=? and Grade=null";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter student Name");
			pstmt.setString(1, sc.next());
			// pstmt.setString(2,null);
			resultset = pstmt.executeQuery();
			if (resultset.next() == false) {
				System.out.println("Student not Graded yet Contact Your Professor");
				System.out.println("Exiting to Student Menu");
				student_menu();
			} else {
				String query = "select * from student where sname =?";
				pstmt = CRSApp.con.prepareStatement(query);
				System.out.println("Enter Student Name");
				pstmt.setString(1, sc.next());
				resultset = pstmt.executeQuery();
				while (resultset.next() == true) {
					System.out.println("Student's Grade is " + resultset.getString(4));
					System.out.println("--------------------------------------");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void score_card() {
		try {
			String sql = "Select * from student where sname=? and Grade='EMPTY'";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter student Name");
			pstmt.setString(1, sc.next());
			// pstmt.setString(2,null);
			reslut = pstmt.executeQuery();
			//reslut.getString("grade");
			//System.out.println(reslut.getString(4));
			if (reslut.next()) 
			{
				System.out.println("Student not Graded yet Contact Your Professor");
				System.out.println("Exiting to Student Menu");
				student_menu();
			} else {
				String query = "select * from student where sname =?";
				pstmt = CRSApp.con.prepareStatement(query);
				System.out.println("Enter Student Name");
				pstmt.setString(1, sc.next());
				resultset = pstmt.executeQuery();
				while (resultset.next() == true) {
					System.out.println("--------------------------------------");
					System.out.println("Student Id is " + resultset.getInt(1));
					System.out.println("Student's Name is " + resultset.getString(2));
					System.out.println("Student's Email ID is " + resultset.getString(3));
					System.out.println("Student's Course Taken  is " + resultset.getString(5));
					System.out.println("Student's Grade is " + resultset.getString(4));
					System.out.println("--------------------------------------");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean stud_login() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Student user name:");
			stud_user_name = sc.next();
			System.out.println("Enter the Student password:");
			stud_password = sc.next();
			String sql = "select * from users where User_name=? and Password=? and Usertype='Student'";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setString(1, stud_user_name);
			pstmt.setString(2, stud_password);
			res = pstmt.executeQuery();
			if (res.next() == true) {
				return true;
			} else {
				System.out.println("Wrong Id Password");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
