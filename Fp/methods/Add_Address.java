//STEP 1. Import required packages
import java.sql.*;
import java.util.Scanner;
public class Add_Address {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
   static final String DB_URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";

   //  Database credentials
   static final String USER = "aalsabbagh";
   static final String PASS = "misho1995";
   
   public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Please enter userID : ");
    String custId= keyboard.next();
    System.out.print("Please enter Street Name : ");
    String streetName= keyboard.next();
    System.out.print("Please enter Street Number ");
    String streetNum = keyboard.next();
    System.out.print("Please enter Apt Number: ");
    String aptNum = keyboard.next();
    System.out.print("Enter city: ");
    String city = keyboard.next();
    System.out.print("Enter state: ");
    String state = keyboard.next();
    System.out.print("Enter zip: ");
    String zip = keyboard.next();
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
      System.out.println("Inserting records into the table...");
      System.out.println(streetName+","+streetNum+","+aptNum+","+city+","+state+","+zip);
      stmt = conn.createStatement();
      String sql="SELECT addID_seq.nextval FROM dual";
      ResultSet rs = stmt.executeQuery(sql);
      rs.next();
      int addID = rs.getInt("NEXTVAL");
      sql = "INSERT INTO Address VALUES("+addID+",'"+streetName+"','"+streetNum+"',"+aptNum+",'"+city+"','"+state+"',"+zip+")";
      stmt.executeUpdate(sql);
      sql= "INSERT INTO CUSTLIVE VALUES("+custId+","+addID+")";
      stmt.executeUpdate(sql);
      System.out.println("Inserted records into the table...");

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
}//end Add_Address