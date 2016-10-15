import java.sql.*;
import java.util.Scanner;

public class Driver {
	private static Connection conn;
	private static Statement stmt;

	public static void main(String[] args) {
		// Step 1. Loading a database driver 
		String sourceURL = "jdbc:oracle:thin:@//oracle.cs.ou.edu:1521/pdborcl.cs.ou.edu";
		try {
			Class.forName("oracle.jdbc.OracleDriver"); 
		} catch(Exception x) {
			System.out.println( "Unable to load the driver class!" );
			System.exit(1);
		}
		
		//Prompt User for Action 
		System.out.println("Please Enter Option");
		Scanner inputScanner = new Scanner(System.in); 
		int input = inputScanner.nextInt(); 
		
		// Step 2. Create an Oracle JDBC Connection. 
		try {
			conn = DriverManager.getConnection(sourceURL,"jabr5892", "JExh5Hd5");  
			System.out.println("Success!");
			// Step 3. Creating a JDBC Statement object
			stmt = conn.createStatement();
			
			//Here is where we execute statements 
			if(input == 1) {
				insertPerformer1();
			}
			
			conn.close();
		} catch(Exception e) {
			System.out.println (e.getMessage());
			System.out.println ("Exception occurred in executing the statement");
		}
		inputScanner.close();
		
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
