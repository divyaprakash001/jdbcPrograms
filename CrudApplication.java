package com.divya.entities;

import java.sql.*;
import java.util.Scanner;

import com.divya.utilities.JdbcUtil;

public class CrudApplication {
	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner sc = null;
		int id = 0;
		int marks = 0;
		int rowAffected;
		int ch;
		int lh;

		try {
			System.out.println("Welcome to Divya's CRUD Application");
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null) {
				statement = connection.createStatement();
				if (statement != null) {

					do {
						sc = new Scanner(System.in);
						System.out.println("what you want to do");
						System.out.println("1. Search");
						System.out.println("2. Add");
						System.out.println("3. Update");
						System.out.println("4. Delete");
						System.out.println("5. Show All");
						System.out.println("6. Exit");
						System.out.print("Choose one option :: ");
						ch = sc.nextInt();
						switch (ch) {
						case 1: // search option
							System.out.println("Enter id to search ::");
							id = sc.nextInt();
							String selectQuery = "select sid,sname,marks from student where sid =" + id + "";
							resultSet = statement.executeQuery(selectQuery);
							if (resultSet != null) {
								System.out.println("ID\t\tNAME\t\tMARKS");
								if (resultSet.next()) {
									Integer sid = resultSet.getInt(1);
									String name = resultSet.getString(2);
									marks = resultSet.getInt(3);
									System.out.println(sid + "\t\t" + name + "\t\t" + marks);
								} else {
									System.out.println("sorry data not found with id " + id);
								}
							}
							break;

						case 2: // add option
							System.out.println("enter your id :: ");
							id = sc.nextInt();
							System.out.println("enter your name :: ");
							String name = sc.next();
							System.out.println("enter your marks :: ");
							marks = sc.nextInt();
							String insertQuery = String.format(
									"insert into student(`sid`,`sname`,`marks`) values(%d,'%s',%d)", id, name, marks);
							rowAffected = statement.executeUpdate(insertQuery);
							System.out.println(rowAffected + " one data added suucessfully");
							break; // -----------------------------------------------------

						case 3: // update option
							do {
								System.out.println("what you want to update ");
								System.out.println("1. Name");
								System.out.println("2. Marks");
								System.out.println("3. Go back to Main menu");
								System.out.println("choose one option : ");
								lh = sc.nextInt();
								switch (lh) {
								case 1: // update name
									System.out.println("enter id to update the data ");
									id = sc.nextInt();
									System.out.println("enter your new name ");
									name = sc.next();
									String updateName = String.format("update  student set sname = '%s' where sid= %d",
											name, id);
									rowAffected = statement.executeUpdate(updateName);
									if (rowAffected == 1) {
										System.out.println(rowAffected + " name updated sucessfully");
									} else {
										System.out.println("sorry, no data found with id " + id);
									}
									break;
								case 2: // update marks
									System.out.println("enter id to update the data ");
									id = sc.nextInt();
									System.out.println("enter your new marks ");
									marks = sc.nextInt();
									String updateMarks = String.format("update  student set marks = %d where sid= %d",
											marks, id);
									rowAffected = statement.executeUpdate(updateMarks);
									if (rowAffected == 1) {
										System.out.println(rowAffected + " marks updated sucessfully");
									} else {
										System.out.println("sorry no data found with id " + id);
									}
									break;
								case 3: // exit
									System.out.println("Successfully back to main method");
									break;
								default:
									System.out.println("please choose correct option ");
									break;
								}
							} while (lh != 3); // internal do-while end here in 3rd case

							break; // -----------------------------------------------------

						case 4:
							System.out.println("enter id to delete :: ");
							id = sc.nextInt();
							String deleteQuery = "delete from student where sid=" + id + "";
							rowAffected = statement.executeUpdate(deleteQuery);
							if (rowAffected == 1) {
								System.out.println(rowAffected + " data deleted successfully");
							} else {
								System.out.println("No data available for id " + id);
							}
							break; // -----------------------------------------------------

						case 5:
							String selectAllQuery = "select sid,sname,marks from student ";
							resultSet = statement.executeQuery(selectAllQuery);
							if (resultSet != null) {
								System.out.println("ID\tNAME\tMARKS");
								while (resultSet.next()) {
									Integer sid = resultSet.getInt("sid");
									name = resultSet.getString(2);
									marks = resultSet.getInt("marks");
									System.out.println(sid + "\t" + name + "\t" + marks);
								}
							}
							break; // -----------------------------------------------------

						case 6:
							System.out.println("Thanks For using my Application");
							System.exit(0);
							break; // -----------------------------------------------------

						default:
							System.out.println("Please choose the correct option");
							break; // -----------------------------------------------------
						} // switch case end here

					} while (ch != 6); // do-while loop end here
				} // statement end here
			} // connection end here

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closing the resources
			JdbcUtil.closeConnection(resultSet, statement, connection);
			if (sc != null) {
				sc.close();
			}
		}

	}

}
