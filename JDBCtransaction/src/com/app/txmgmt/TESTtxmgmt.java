package com.app.txmgmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class TESTtxmgmt {
	public static void main(String[] args) throws SQLException {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement psmt = null;
		boolean rs;
		String name = null;
		int number = 0;
		double amount = 0;

		boolean flag = false;
		String driverclassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password = "root";

		sc = new Scanner(System.in);

		System.out.println("enter number");
		number = sc.nextInt();
		sc.nextLine();
		System.out.println("enter name");
		name = sc.nextLine();

		System.out.println("enter amount");
		amount = sc.nextDouble();
		// con=Class.forName(driverclassName);
		con = DriverManager.getConnection(url, username, password);

		try {
			if (con != null) {
				psmt = (PreparedStatement) con.prepareStatement("INSERT INTO person VALUES (?,?,?)");
				psmt.setInt(1, number);
		 		psmt.setString(2, name);
				psmt.setDouble(3, amount);
				psmt.execute();
				flag = true;
			}

			if (flag == false) {

				System.out.println("commited not happend");
				con.setAutoCommit(false);
				System.out.println("roll back data");
				con.rollback();
			} else {
				con.setAutoCommit(true);
				System.out.println("data commited");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
