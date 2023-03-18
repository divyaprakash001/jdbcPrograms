package com.divya.entities;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.divya.utilities.JdbcUtil;

public class CrudDateOperation {

	public static void main(String[] args) throws SQLException, ParseException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		Scanner sc = null;
		Integer id = 0;
		String eadd = null, egender = null, edob = null, edoj = null, edom = null;

		String name = null;
		Integer rowAffected;
		Integer ch;
		Integer lh;

		SimpleDateFormat sdfb = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfj = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDob = null;
		java.util.Date uDoj = null;
		java.util.Date uDom = null;
		long lb = 0;
		long lj = 0;
		long lm = 0;
		java.sql.Date sDob = null;
		java.sql.Date sDoj = null;
		java.sql.Date sDom = null;

//		all types of query
		String selectQuery = "select * from employee where eid = ? ";
		String insertQuery = "insert into employee (`eid`,`ename`,`eadd`,`egender`,`edob`,`edoj`,`edom`) values(?,?,?,?,?,?,?) ";
		String updateDob = "update employee set edob = ? where eid = ?";
		String updateDoj = "update employee set edoj = ? where eid = ? ";
		String updateDom = "update employee set edom = ? where eid = ? ";
		String deleteQuery = "delete from employee where eid = ? ";
		String selectAllQuery = "select * from employee";

		try {
			System.out.println("Welcome to Divya's Employee Data Application");
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null) {
//				pstmt4 = connection.prepareStatement(deleteQuery);
//				pstmt5 = connection.prepareStatement(selectAllQuery);

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
									System.out.println("ID\tNAME\tADDRESS\tGENDER\t\tDOB\t\tDOJ\t\tDOM");
									id = resultSet.getInt("eid");
									name = resultSet.getString(2);
									eadd = resultSet.getString(3);
									egender = resultSet.getString(4);
									sDob = resultSet.getDate(5);
									sDoj = resultSet.getDate(6);
									sDom = resultSet.getDate(7);
									sdfb = new SimpleDateFormat("dd-MM-yyyy");
									sdfj = new SimpleDateFormat("MM-dd-yyyy");
									sdfm = new SimpleDateFormat("yyyy-MM-dd");
									String sb = sdfb.format(sDob);
									String sj = sdfj.format(sDoj);
									String sm = sdfm.format(sDom);
									System.out.println(id + "\t" + name + "\t" + eadd + "\t" + egender + "\t\t" + sb
											+ "\t\t" + sj + "\t\t" + sm);
								} else {
									System.out.println("sorry, data not found with id " + id);
								}
							}
						}
						break;
					case 2: // insert
						pstmt = connection.prepareStatement(insertQuery);
						if (pstmt != null) {
							System.out.print("enter id here  :: ");
							Integer eid = sc.nextInt();
							System.out.print("enter name here  :: ");
							String ename = sc.next();
							System.out.print("enter address here  :: ");
							eadd = sc.next();
							System.out.print("enter gender here  :: ");
							egender = sc.next();
							System.out.print("enter date of birth(dd-mm-yyyy)  :: ");
							edob = sc.next();
							System.out.print("enter date of joining (mm-dd-yyyy)  :: ");
							edoj = sc.next();
							System.out.print("enter date of matriculation (yyyy-mm-dd)  :: ");
							edom = sc.next();
							uDob = sdfb.parse(edob);
							uDoj = sdfj.parse(edoj);
							uDom = sdfm.parse(edom);
							lb = uDob.getTime();
							lj = uDoj.getTime();
							lm = uDom.getTime();
							sDob = new java.sql.Date(lb);
							sDoj = new java.sql.Date(lj);
							sDom = new java.sql.Date(lm);

							pstmt.setInt(1, eid);
							pstmt.setString(2, ename);
							pstmt.setString(3, eadd);
							pstmt.setString(4, egender);
							pstmt.setDate(5, sDob);
							pstmt.setDate(6, sDoj);
							pstmt.setDate(7, sDom);
							rowAffected = pstmt.executeUpdate();
							System.out.println("row affected " + rowAffected);

						}
						break;
					case 3: // update the date

						do {
							System.out.println("what you want to update ");
							System.out.println("1. DOB");
							System.out.println("2. Doj");
							System.out.println("3. DOM");
							System.out.println("4. Go back to Main menu");
							System.out.println("choose one option : ");
							lh = sc.nextInt();
							switch (lh) {
							case 1: // update name
								pstmt = connection.prepareStatement(updateDob);
								if (pstmt != null) {
									System.out.println("enter id which you want to  update the dob ");
									id = sc.nextInt();
									System.out.println("enter your new dob ");
									edob = sc.next();
									uDob = sdfb.parse(edob);
									lb = uDob.getTime();
									sDob = new java.sql.Date(lb);
									pstmt.setDate(1, sDob);
									pstmt.setInt(2, id);
									rowAffected = pstmt.executeUpdate();
									if (rowAffected == 0) {
										System.out.println("sorry data not found");
									} else {
										System.out.println(rowAffected + " dob updated sucessfully");
									}

								}
								break;
							case 2: // update doj
								pstmt = connection.prepareStatement(updateDoj);
								if (pstmt != null) {
									System.out.println("enter id which you want to  update the doj ");
									id = sc.nextInt();
									System.out.println("enter your new doj ");
									edob = sc.next();
									uDoj = sdfj.parse(edoj);
									lj = uDoj.getTime();
									sDoj = new java.sql.Date(lj);
									pstmt.setDate(1, sDoj);
									pstmt.setInt(2, id);
									rowAffected = pstmt.executeUpdate();
									if (rowAffected == 0) {
										System.out.println("sorry data not found");
									} else {
										System.out.println(rowAffected + " doj updated sucessfully");
									}
								}
								break;
							case 3: // update dom
								pstmt = connection.prepareStatement(updateDom);
								if (pstmt != null) {
									System.out.println("enter id which you want to  update the dom ");
									id = sc.nextInt();
									System.out.println("enter your new dom ");
									edob = sc.next();
									uDom = sdfm.parse(edom);
									lm = uDom.getTime();
									sDom = new java.sql.Date(lm);
									pstmt.setDate(1, sDom);
									pstmt.setInt(2, id);
									rowAffected = pstmt.executeUpdate();
									if (rowAffected == 0) {
										System.out.println("sorry data not found");
									} else {
										System.out.println(rowAffected + " dom updated sucessfully");
									}
								}
								break;
							case 4: // exit
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
								System.out.println("ID\tNAME\tADDRESS\tGENDER\t\tDOB\t\tDOJ\t\tDOM");
								while (resultSet.next()) {
									id = resultSet.getInt("eid");
									name = resultSet.getString(2);
									eadd = resultSet.getString(3);
									egender = resultSet.getString(4);
									sDob = resultSet.getDate(5);
									sDoj = resultSet.getDate(6);
									sDom = resultSet.getDate(7);
									sdfb = new SimpleDateFormat("dd-MM-yyyy");
									sdfj = new SimpleDateFormat("MM-dd-yyyy");
									sdfm = new SimpleDateFormat("yyyy-MM-dd");
									String sb = sdfb.format(sDob);
									String sj = sdfj.format(sDoj);
									String sm = sdfm.format(sDom);
									System.out.println(id + "\t" + name + "\t" + eadd + "\t" + egender + "\t\t" + sb
											+ "\t\t" + sj + "\t\t" + sm);
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

		}

	}

}
