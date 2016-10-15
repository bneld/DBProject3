import java.sql.*;
import java.util.Scanner;

public class Driver {
	private static Connection conn;
	private static Statement stmt;
	private static Scanner inputScanner;

	public static void main(String[] args) {
		// Step 1. Loading a database driver 
		String sourceURL = "jdbc:oracle:thin:@//oracle.cs.ou.edu:1521/pdborcl.cs.ou.edu";
		int option;
		int pid;
		String pname;
		int age;
		int did;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver"); 
		} catch(Exception x) {
			System.out.println( "Unable to load the driver class!" );
			System.exit(1);
		}
		
		// Step 2. Create an Oracle JDBC Connection. 
		try {
			conn = DriverManager.getConnection(sourceURL,"neld9968", "EPxu5Cz0");  
			System.out.println("Success!");
			// Step 3. Creating a JDBC Statement object
			stmt = conn.createStatement();
			
			//Load command line args
			if (args.length > 0) {
			    try {
			    	pid = Integer.parseInt(args[0]);
			    	pname = args[1];
			    	age = Integer.parseInt(args[2]);
			    	
			    	if(args.length == 3){
			    		insertPerformer1(pid, pname, age);
			    	}
			    	else if (args.length >= 4){
			    		did = Integer.parseInt(args[3]);
			    		insertPerformer2();
			    	}
			        
			    } catch (NumberFormatException e) {
			        System.err.println("Arguments 1, 3, and 4 must be integers.");
			        System.exit(1);
			    }
			} else {
				System.out.println("No arguments specified.");
				System.exit(0);
			}
			
			conn.close();
			inputScanner.close();
		} catch(Exception e) {
			System.out.println (e.getMessage());
			System.out.println ("Exception occurred in executing the statement");
		}		
	}
	
	public static boolean insertPerformer1(int pid, String pname, int age){

//		String sqlInsert = "insert into performer values(" 
//				+ pid 
//				+ ", '"
//				+ pname
//				+ "', "
//				+ years_of_experience
//				+ ","
//				+ age
//				+ ")";
		
		try {
			//stmt.executeUpdate(sqlInsert);
			ResultSet result = stmt.executeQuery("select * from performer");
			System.out.println("PerformerID      Name ");
			while(result.next())
			{
				System.out.println(result.getString(1) + "      " + result.getString(2)); 
				
			}
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} 
		return true;
	}

}
