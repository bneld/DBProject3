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
			
			//Prompt User for Action 
			//Load command line args
			if (args.length > 0) {
			    try {
			        option = Integer.parseInt(args[0]);
			    } catch (NumberFormatException e) {
			        System.err.println("Argument" + args[0] + " must be an integer.");
			        System.exit(1);
			    }
			} else {
				System.out.println("No arguments specified.");
				option = 4;
			}
			
			inputScanner = new Scanner(System.in); 
			System.out.println("Please Enter Option");
			option = inputScanner.nextInt(); 
			
			//Here is where we execute statements 
			switch(option) {
				case 1:
					insertPerformer1();
					break;
				case 4:
					System.exit(0);
					break;
			}
			
			conn.close();
			inputScanner.close();
		} catch(Exception e) {
			System.out.println (e.getMessage());
			System.out.println ("Exception occurred in executing the statement");
		}		
	}
	
	//public static boolean insertPerformer1(int pid, String pname, int years_of_experience, int age){
	public static boolean insertPerformer1(){

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
