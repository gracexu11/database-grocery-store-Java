import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
 public class Place_Order {
       // JDBC driver name and database URL
     static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
     static final String DB_URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
      
     //  Database credentials
     static final String user = "aalsabbagh";
     static final String pass = "misho1995";
      
   public static void Place_OrderMain(){
     
     Connection conn = null;
     Statement stmt = null;
        try{
    
    
           //STEP 2: Register JDBC driver
            
         Class.forName("oracle.jdbc.driver.OracleDriver");
    
           //STEP 3: Open a connection
           System.out.println("Connecting to a selected database");
           conn = DriverManager.getConnection(DB_URL, user, pass);
           System.out.println("Connected database successfully");
            
         //STEP 4:  display for available the product
           System.out.println("if you do not want continue shopping , just enter quit or anything else to continue, Then press enter:");
           stmt = conn.createStatement();
           Scanner sc = new Scanner(System.in);
           String a =sc.next();

           while(true){
            if(a.equals("quit")){//this just keeps it running and checking for quits
           break;
            }
           String sql1 ="Select * from stock NATURAL JOIN warehouse NATURAl JOIN  product";
           ResultSet rs=stmt.executeQuery(sql1);
           ArrayList<Integer> list2 = new ArrayList<Integer>();
           int x= 1;
           while(rs.next()){// this will show all products we have in stock,
            int v = rs.getInt("productid");
            String w= rs.getString("product_name");
            System.out.println(x+"."+w);
            x++;
            list2.add(v);
           }
           System.out.println("please enter your 6 digit ID or 0 to quit");
           int custId=sc.nextInt();
           if (custId==0){
           System.exit(0);
           }
           System.out.println("Please enter the state of delivery");
           String state = sc.next();
           System.out.println("Please enter the number of product you want to add  ");
           int input1= sc.nextInt();
           int num = input1 -1 ;
          String sql2= "select QUANTITY from STOCK NATURAL JOIN warehouse NATURAL JOIN address WHERE productid="+list2.get(num)+
            "AND state='"+state+"'";
          ResultSet rs1 = stmt.executeQuery(sql2);
          rs1.next();
          int v= rs1.getInt("QUANTITY");
          System.out.println("the available quantity is " +v);
      System.out.println("Please enter the  product quantity you want");
      int quant= sc.nextInt();
      while(quant>v){
        System.out.println("Sorry, we can't supply such demand. Please enter a smaller quantity");
        quant= sc.nextInt();
      }
      String sql3 ="INSERT INTO SHOPPINGCART VALUES ("+list2.get(num)+","+quant+","+custId+") ";
      ResultSet rs2 =stmt.executeQuery(sql3);
      rs2.next();      
      String sql5 ="SELECT price from price  WHERE productid="+list2.get(num)+
        "and state='"+state+"'";
      ResultSet rs3=stmt.executeQuery(sql5);
        rs3.next();
        int v1 =rs3.getInt("price");
        System.out.println("calculating cost");
        Double a1= Double.valueOf(quant)*Double.valueOf(v1);
        System.out.println(" cost "+a1+ "$");
        System.out.println("if you would like to place order: enter 1, to keep shopping enter 2, to quit enter 3");
        int submit = sc.nextInt();
        if (submit==1){
             String sql8 = "SELECT * FROM Credit_card WHERE CUSTOMERID='"+custId+"'";
             rs = stmt.executeQuery(sql8);
             int cnt=1;
      while(rs.next()){
         //Retrieve by column name
         long creditNumbe  = rs.getLong("CREDITNUMBER");
         String custName = rs.getString("CUSTOMER_NAME");
         int xp_month = rs.getInt("EXPIRATION_MONTH");
         int xp_year = rs.getInt("EXPIRATION_YEAR");
         //Display values
         System.out.print(cnt+". Creditcard Number: " + creditNumbe);
         System.out.print(", NAME: " + custName);
         System.out.print(", MONTH: " + xp_month);
         System.out.println(", YEAR: " + xp_year);
         cnt++;
      }//end while for showing credit cards
      System.out.println("Please enter the number of the prefered payment method");
      int paymntm= sc.nextInt();
      System.out.println("payment method"+ paymntm+" confirmed, Thank you for your business");
      String sql4="UPDATE STOCK SET quantity=quantity- '"+quant+"' WHERE productid="+list2.get(num);
      System.out.println("updated quantity");
      stmt.executeUpdate(sql4);
      String sql7="UPDATE CUSTOMER SET balance=balance+"+a1;
      System.out.println("updated balance, order placed");
      String sql21 = "DELETE FROM SHOPPINGCART WHERE productid="+list2.get(num)+ "AND CUSTOMERID="+custId;
      stmt.executeUpdate(sql7);
      stmt.executeUpdate(sql21);
        }//end if statement for submission
        else if (submit==3){
          System.exit(0);}
           }}catch(SQLException se){
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
   }
  }
 }