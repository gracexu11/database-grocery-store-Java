
import java.sql.*;
import java.util.Scanner;
public class CheckProduct {
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	static boolean found = false;
	//  Database credentials
	static final String USER = "aalsabbagh";
	static final String PASS = "misho1995";
	
	public static boolean ProductIDStateCheckMain() {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Please enter Product ID : ");
		int prodId= keyboard.nextInt();
		System.out.print("Please enter State : ");
		String state= keyboard.next();
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
			System.out.println(prodId);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select PRODUCTID, STATE from PRICE");
			while( rs.next()){
				int v =rs.getInt("PRODUCTID");
				String x = rs.getString("STATE");
				if(prodId==v && state.equals(x)){
					CheckProduct.found = true;
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
		}//end main
	}//end Customer_Login
