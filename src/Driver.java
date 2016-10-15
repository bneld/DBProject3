import java.sql.*;
import java.util.Scanner;


public class Driver {

	public static void main(String[] args) {
		// Step 1. Loading a database driver 
		String sourceURL = "jdbc:oracle:thin:@//oracle.cs.ou.edu:1521/pdborcl.cs.ou.edu";
		try 
			{
			Class.forName("oracle.jdbc.OracleDriver"); 
			}
		catch(Exception x)
			{
			System.out.println( "Unable to load the driver class!" );
			}
		//Prompt User for Action 
		System.out.println("Please Enter Option");
		Scanner inputScanner = new Scanner(System.in); 
		int input = inputScanner.nextInt(); 
		// Step 2. Creating an Oracle JDBC Connection.  The following example assumes // that the login name is smith1234 and the password is johnsmith  
		try
			{
			Connection conn = DriverManager.getConnection(sourceURL,"jabr5892", "JExh5Hd5");  
			System.out.println("Success!");
			// Step 3. Creating a JDBC Statement object
			Statement stmt = conn.createStatement();
			
			//Here is where we execute statements 
			if(input == 1)
				System.out.println("User Selected First Option");
				
			
			
			conn.close();}
		
		catch(Exception e)
			{
			System.out.println (e.getMessage());
			System.out.println ("Exception occurred in executing the statement");
			}
		
	
		
		
		
	}

}
