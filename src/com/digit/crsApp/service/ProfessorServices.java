package com.digit.crsApp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.crsApp.CRSApp;


public class ProfessorServices 
{   
	private static PreparedStatement pstmt;
	private static ResultSet res;
	public static String prof_user_name;
    public static String prof_password;
	Scanner sc= new Scanner(System.in);
	public static ResultSet resultset;
	public void prof_menu() throws Exception 
	   {
		   //grade student
		   //view prof detail
		   System.out.println("\nWelcome To Professor Menu ");
		   System.out.println("   Select your Choice ");
		   System.out.println("1. Grade Student\n"
		   			+ "2. View Professor Details\n"
		   		    + "3. Exit\n");
		   Scanner sc = new Scanner(System.in);
		   int n = sc.nextInt();
		   switch (n) 
			{
			case 1: 
			{
					//ProfessorServices pof = new ProfessorServices();
					Grade_Students();
			}
			case 2:
			{   
//				ProfessorServices pof =new ProfessorServices();
				Professor_details();
		    }
			case 3:
			{
				System.out.println("Exiting Professor menu");
				CRSApp.main_menu();
			}
			default:
				System.out.println("Enter Right Choice");
			
	     }
	  }
   public void Professor_details()
   {
	   try 
	   {
			String sql = "Select * from Professor where pname=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter Professor Name OR user Name");
			pstmt.setString(1,sc.next());
     	    resultset=pstmt.executeQuery();
     	    while(resultset.next()==true)
     	    {   
     	    	System.out.println("Professor Id is "+resultset.getInt(1));
     	    	System.out.println("Professor's Name is "+resultset.getString(2));
     	    	System.out.println("Professor's Experience is "+resultset.getInt(3));
     	    	System.out.println("--------------------------------------");
     	    }			
		} 
	   catch (Exception e) 
	   {
			e.printStackTrace();
	   }
   }
   public void Grade_Students()
   {
	   System.out.println("Welcome In Student Grading Module ");
	   try 
	   {
		    String display="Select * from Student where coursetaken=?";
			pstmt = CRSApp.con.prepareStatement(display);
		    System.out.println("Enter your course :");
		    pstmt.setString(1,sc.next());
			resultset= pstmt.executeQuery();
			 while(resultset.next()==true)
	     	    {   
	     	    	System.out.println("Student Id is "+resultset.getInt(1));
	     	    	System.out.println("Student's Name is "+resultset.getString(2));
	     	    	System.out.println("Student's Email ID is "+resultset.getString(3));
	     	    	System.out.println("--------------------------------------");
	     	    }	
		    System.out.println("Please select Student Id Displayed Above For Grading");
			String sql = "Select * from Student Where Sid=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter Student ID For Grading");
			pstmt.setInt(1,sc.nextInt());
     	    resultset=pstmt.executeQuery();
		   	String grade="update student set Grade=? where Sid=?";
			pstmt = CRSApp.con.prepareStatement(grade);
		   	System.out.println("Enter Grade :");
			pstmt.setString(1,sc.next());
			System.out.println("Enter Student Id of the Student");
			pstmt.setInt(2,sc.nextInt());
			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Graded Succesfully");
				System.out.println("Exiting to Professor Menu");
				prof_menu();
			}
		} 
	   catch (Exception e) 
	   {
			e.printStackTrace();
	   }
   }
   public static boolean prof_login() {
		try {
			// TODO Auto-generated method stub
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Professor user name:");
			prof_user_name=sc.next();
			System.out.println("Enter the Professor password:");
			prof_password=sc.next();
			String sql = "select * from users where User_name=? and Password=? and Usertype='Professor'";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setString(1, prof_user_name);
			pstmt.setString(2, prof_password);
			res = pstmt.executeQuery();
			if(res.next()==true) {
				return true;
			}
			else {
				System.out.println("Wrong Id Password");
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
