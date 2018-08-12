package hu.bme.ftsrg.modes3.rabbitmq.test.first;

import java.sql.*;

public class Save2SQL {
	 // JDBC driver name and database URL

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://192.168.43.7/iot_db";



	//  Database credentials
    static final String USER = "gateway";

	static final String PASS = "new_password";


	public Save2SQL() {
		 Connection conn = null;

	      Statement stmt = null;
	      try {
	    	  Class.forName("com.mysql.jdbc.Driver");
	    	  conn = DriverManager.getConnection(DB_URL,USER,PASS);
	    	  stmt = conn.createStatement();
	    	  String sql;
	          sql = "insert into trainsensordata(AccX,AccY,AccZ,MicroTimestamp,SensorName) values("+String(input.AccX)+","+String(input.AccY)+","+String(input.AccZ)+","+String(input.MicroTimestamp)+",'train_test_1')";
	          ResultSet rs = stmt.executeQuery(sql);
	          rs.close();
	          stmt.close();
	          conn.close();
	        }catch(SQLException se){
	            //Handle errors for JDBC
	            System.out.println("there is an error with sql");
	        } catch (Exception e) {
	        	System.out.println("there is an error with accdata");
	            return;
	        }finally{
		        try{
	
		            if(stmt!=null)
		            stmt.close();
	
		        }catch(SQLException se2){
	
		        }
	        try{
	            if(conn!=null)
	            conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }
	}

}
