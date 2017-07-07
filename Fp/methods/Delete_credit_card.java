//STEP 1. Import required packages
import java.sql.*;
import java.util.Scanner;
public class Delete_credit_card {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
   static final String DB_URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";

   //  Database credentials
   static final String USER = "aalsabbagh";
   static final String PASS = "misho1995";
   
   public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Please enter a CreditCard number: ");
    String creditnum = keyboard.next();
    keyboard.close();
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
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql = "DELETE FROM Credit_card WHERE CREDITNUMBER ="+creditnum;
      stmt.executeUpdate(sql);
      System.out.println("Created statement...");
      // Now you can extract all the records
      // to see the remaining records
      sql = "SELECT * FROM Credit_card";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
         //Retrieve by column name
         long creditNumbe  = rs.getLong("CREDITNUMBER");
         int id = rs.getInt("CUSTOMERID");
         String custName = rs.getString("CUSTOMER_NAME");
         int xp_month = rs.getInt("EXPIRATION_MONTH");
         int xp_year = rs.getInt("EXPIRATION_YEAR");
         int addressID = rs.getInt("ADDRESSID");

         //Display values
         System.out.print("Creditcard Number: " + creditNumbe);
         System.out.print(", ID: " + id);
         System.out.print(", NAME: " + custName);
         System.out.print(", MONTH: " + xp_month);
         System.out.print(", YEAR: " + xp_year);
         System.out.println(", Address: " + addressID);
      }
      rs.close();
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
}//end main
}//end Delete_credit_card