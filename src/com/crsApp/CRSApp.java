package com.crsApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import com.digit.crsApp.beans.Users;
import com.digit.crsApp.service.AdminServices;
import com.digit.crsApp.service.ProfessorServices;
import com.digit.crsApp.service.StudentServices;

public class CRSApp {
	public static Connection con;
	public static void main_menu() throws Exception
	{
		System.out.println("Welcome To Course Regitration System");
		System.out.println("Select the Type of User:");
		System.out.println("1. Admin\n"
				+ "2. Professor\n"
				+ "3. Student\n"
				+ "4. Exit\n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");
		String url = "jdbc:mysql://localhost:3306/crs";

		String user = "root";
		String pwd  = "cseibm";
		// Step:2
		con = DriverManager.getConnection(url, user, pwd);
		
		switch (n) 
		{
		case 1: {
			boolean b = Users.login();
			if(b==true) {
				System.out.println("Admin Login Success");
				AdminServices adsrv = new AdminServices();
				adsrv.menu();
			}
			else {
				     System.out.println("Incorret ID or Password");
			}
		}
		case 2:
		{
			boolean b = ProfessorServices.prof_login();
			if(b==true) {
				System.out.println("Professor Login Success");
				ProfessorServices adsrv = new ProfessorServices();
				adsrv.prof_menu();
			}
			else {
				     System.out.println("Incorret ID or Password");
			}
		}
		case 3:
		{
			boolean b = StudentServices.stud_login();
			if(b==true) 
			{
		    System.out.println("Student Login Success");
			StudentServices std =new StudentServices();
			std.student_menu();
			}
			else {
				System.out.println("Incorret ID or Password");
			}
		}
		case 4:
		{
			System.out.println("Thanks for Visiting Us");
			System.exit(0);
		}
		default:
		}
	}
	
	public static void main(String[] args) throws Exception 
	{
		main_menu();
	}
}