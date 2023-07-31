package com.digit.crsApp.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.crsApp.CRSApp;

public class Professor 
{   
	int pid;
	String pname;
	int exp;
	public Professor(int pid, String pname, int exp) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.exp = exp;
	}
	public Professor() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}
	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}
	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * @return the exp
	 */
	public int getExp() {
		return exp;
	}
	/**
	 * @param exp the exp to set
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	
	
}
