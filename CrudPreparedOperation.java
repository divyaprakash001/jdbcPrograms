package com.divya.entities;

import java.sql.*;
import java.util.Scanner;

import com.divya.utilities.JdbcUtil;

public class CrudPreparedOperation {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		Scanner sc = null;
		Integer id = 0;
		Integer marks = 0;
		String name = null;
		name = "'" + name + "'";
		Integer rowAffected;
		Integer ch;
		Integer lh;

//		all types of query
		String selectQuery = "select * from student where sid = ? ";
		String insertQuery = "insert into student (`sid`,`sname`,`marks`) values(?,?,?) ";
		String updateNameQuery = "update student set sname = ? where sid = ? ";
		String updateMarksQuery = "update student set marks = ? where sid = ? ";
		String deleteQuery = "delete from student where sid = ? ";
		String selectAllQuery = "select * from student";

		try {
			System.out.println("Welcome to Divya's CRUD Application Prepared");
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null) {

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
					case 1:
						pstmt = connection.prepareStatement(selectQuery);
						if (pstmt != null) {
							System.out.print("enter your id to search :: ");
							id = sc.nextInt();
							pstmt.setInt(1, id);
							resultSet = pstmt.executeQuery();
							if (resultSet != null) {
								if (resultSet.next()) {
									System.out.println("ID\tNAME\tMARKS");
									id = resultSet.getInt("sid");
									name = resultSet.getString(2);
									marks = resultSet.getInt("marks");
									System.out.println(id + "\t" + name + "\t" + marks);
								} else {
									System.out.println("sorry, data not found with id " + id);
								}
							}
						}
						break;
					case 2:
						pstmt = connection.prepareStatement(insertQuery);
						if (pstmt != null) {
							System.out.print("enter id here  :: ");
							Integer sid = sc.nextInt();
							System.out.print("enter name here  :: ");
							String sname = sc.next();
							System.out.print("enter marks here  :: ");
							marks = sc.nextInt();
							pstmt.setInt(1, sid);
							pstmt.setString(2, sname);
							pstmt.setInt(3, marks);
							rowAffected = pstmt.executeUpdate();
							System.out.println("row affected " + rowAffected);

						}
						break;
					case 3: // update the data

						do {
							System.out.println("what you want to update ");
							System.out.println("1. Name");
							System.out.println("2. Marks");
							System.out.println("3. Go back to Main menu");
							System.out.println("choose one option : ");
							lh = sc.nextInt();
							switch (lh) {
							case 1: // update name
								pstmt = connection.prepareStatement(updateNameQuery);
								if (pstmt != null) {
									System.out.println("enter id which you want to update update the name ");
									id = sc.nextInt();
									System.out.println("enter your new name ");
									name = sc.next();
									pstmt.setString(1, name);
									pstmt.setInt(2, id);
									rowAffected = pstmt.executeUpdate();
									if (rowAffected == 0) {
										System.out.println("sorry data not found");
									} else {
										System.out.println(rowAffected + " name updated sucessfully");
									}

								}
								break;
							case 2: // update marks
								pstmt = connection.prepareStatement(updateMarksQuery);
								if (pstmt != null) {
									System.out.println("enter id to update the data ");
									id = sc.nextInt();
									System.out.println("enter your new marks ");
									marks = sc.nextInt();
									pstmt.setInt(1, marks);
									pstmt.setInt(2, id);
									rowAffected = pstmt.executeUpdate();
									System.out.println(rowAffected + " marks updated sucessfully");
								}
								break;
							case 3: // exit
								System.out.println("Successfully back to main method");
								break;
							default:
								System.out.println("please choose correct option ");
								break;
							}

						} while (lh != 3);
						break;
					case 4: // delete the data
						pstmt = connection.prepareStatement(deleteQuery);
						if (pstmt != null) {
							System.out.print("enter id to delete  :: ");
							Integer sid = sc.nextInt();
							pstmt.setInt(1, sid);
							rowAffected = pstmt.executeUpdate();
							if (rowAffected == 0) {
								System.out.println("sorry data not found");
							} else {
								System.out.println(rowAffected + " data deleted successfully");
							}
						}

						break;
					case 5: // show all query
						pstmt = connection.prepareStatement(selectAllQuery);
						if (pstmt != null) {
							resultSet = pstmt.executeQuery();
							if (resultSet != null) {
								System.out.println("ID\tNAME\tMARKS");
								while (resultSet.next()) {
									id = resultSet.getInt(1);
									name = resultSet.getString(2);
									marks = resultSet.getInt("marks");
									System.out.println(id + "\t" + name + "\t" + marks);
								}
							}

						}

						break;
					case 6:
						System.out.println("Thank you for using my application");
						System.exit(0);
						break;
					default:
						System.out.println("please choose correct option");
						break;
					}
				} while (ch != 6);

			}

		} catch (

		SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closing the resources
			JdbcUtil.closeConnection(resultSet, pstmt, connection);
			if (sc != null) {
				sc.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}

		}

	}

}
