//STEP 1. Import required packages
import java.sql.*;
import java.util.Scanner;
public class Modify_credit_card {
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
    System.out.print("Please enter userID : ");
    String custId= keyboard.next();
    System.out.print("Enter Name: ");
    String custname = keyboard.next();
    System.out.print("Enter expiration month: ");
    String exp_month = keyboard.next();
    System.out.print("Enter expiration year: ");
    String exp_year = keyboard.next();
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
      String sql = "SELECT * FROM custlive NATURAL JOIN address WHERE CUSTOMERID='"+custId+"'";
      ResultSet rs = stmt.executeQuery(sql);
      rs.next();
      do{
         //Retrieve by column name
         int custID=rs.getInt("CUSTOMERID");
         int addressID  = rs.getInt("addressid");
         String streetname = rs.getString("street_name");
         int streetnum= rs.getInt("street_number");
         int aptno = rs.getInt("APT_NUMBER");
         String city = rs.getString("CITY");
         String state = rs.getString("STATE");
         int zip = rs.getInt("ZIP");

         //Display values
         System.out.print("Customer ID: " + custID);
         System.out.print(", Address ID: " + addressID);
         System.out.print(", Street Name: " + streetname);
         System.out.print(", Street Number: " + streetnum);
         System.out.print(", Apartement Number: " + aptno);
         System.out.print(", City: " + city);
         System.out.print(", State: " + state);
         System.out.println(", Zip Code: " + zip);
      }while(rs.next());

    System.out.print("Enter Address ID: ");
    String addID = keyboard.next();
    keyboard.close();
     sql = "UPDATE Credit_card " +
                   "SET CUSTOMERID="+custId+",CUSTOMER_NAME='"+custname+"',EXPIRATION_MONTH="+exp_month+ 
        ", EXPIRATION_YEAR="+exp_year+",ADDRESSID="+addID+
        "WHERE CREDITNUMBER="+creditnum;
      stmt.executeUpdate(sql);

      // Now you can extract all the records
      // to see the updated records
      sql = "SELECT * FROM Credit_card WHERE CUSTOMERID='"+custId+"'";
      rs = stmt.executeQuery(sql);

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
}//end Modify_credit_card