
import java.sql.*;
import java.util.Scanner;
public class CheckStaff {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	static boolean found = false;
	//  Database credentials
	static final String USER = "aalsabbagh";
	static final String PASS = "misho1995";
	
	public static boolean StaffLoginMain() {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Please enter StaffID : ");
		int staffId= keyboard.nextInt();
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
			//STEP 4: Execute a query
			System.out.println("Updating records in the table...");
			System.out.println(staffId);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select STAFFID from STAFF_MEMBER");
			while( rs.next()){
				int v =rs.getInt("STAFFID");
				if(staffId==v){
					CheckStaff.found =true;
					break;
					}
				}
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
				}catch(Exception e){
					//Handle errors for Class.forName
					e.printStackTrace();
					}finally{
						//finally block used to close resources
						try{
							if(stmt!=null)
								conn.close();
							}catch(SQLException se){
								
							}// do nothing
						try{
							if(conn!=null)
								conn.close();
							}catch(SQLException se){
								se.printStackTrace();
								}//end finally try
						}//end try
		System.out.println("Goodbye!");
		return found;
		// return found;
}//end main
}//end Staff_Login