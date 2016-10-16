import java.sql.*;

public class Driver {
	private static Connection conn;
	private static Statement stmt;

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
		
		try {
			// Step 2. Create an Oracle JDBC Connection. 
			conn = DriverManager.getConnection(sourceURL,"neld9968", "EPxu5Cz0");  

			// Step 3. Creating a JDBC Statement object
			stmt = conn.createStatement();
		} catch(SQLException e) {
			System.out.println ("Exception message: " + e.getMessage());
			System.out.println ("Exception occurred in connecting to DB.");
		}

		//Load command line args
		if (args.length > 0) {
		    try {
		    	option = Integer.parseInt(args[0]);
		    	
		    	if(option == 1) {
		    		if(args.length == 4) {
			    		pid = Integer.parseInt(args[1]);
				    	pname = args[2];
				    	age = Integer.parseInt(args[3]);
			    		insertPerformer1(pid, pname, age);	
		    		} else {
		    			System.out.println("Please Enter PID , PNAME and Age");
		    			System.exit(1);
		    		}
		    	}
		    	else if (option == 2) {
		    		if(args.length == 5) {
			    		pid = Integer.parseInt(args[1]);
				    	pname = args[2];
				    	age = Integer.parseInt(args[3]);
			    		did = Integer.parseInt(args[3]);
			    		insertPerformer2(pid , pname , age, did);
		    		} else {
		    			System.out.println("Please Enter PID , PNAME,Age and DID ");
		    			System.exit(1);
		    		}
		    	}	        
		    	else if( option == 3 ) printPerformers();
		    	else if(option == 4) System.exit(0);
		    	
		    } catch (NumberFormatException e) {
		        System.err.println("Arguments 1, 2, 4, and 5 must be integers.");
		        System.exit(1);
		    }
		    try {
				conn.close();
			} catch(SQLException e){
				System.err.println("Exception occurred while closing Connection.");
			}	
		} else {
			System.out.println("No arguments specified.");
			System.exit(0);
		}

	}
	
	public static boolean insertPerformer1(int pid, String pname, int age){
		
		try {
			CallableStatement cStmt = conn.prepareCall("{? = call FUNCTION1(?, ?, ?)}");
			cStmt.registerOutParameter(1, Types.INTEGER);
			cStmt.setInt(2, pid);
			cStmt.setString(3, pname);
			cStmt.setInt(4, age);
			cStmt.execute();
			System.out.println("Performer inserted 1");

		} catch (SQLException e){
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
	public static void printPerformers(){
		try {
			ResultSet result = stmt.executeQuery("select * from performer");
			System.out.println("PID Name Experience Age");
			while(result.next()) {
				System.out.println(result.getString(1)
						+ " " + result.getString(2)
						+ " " + result.getString(3)
						+ " " + result.getString(4)); 
			}
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} 
	}

	public static void insertPerformer2(int pid, String pname , int age , int did)
	{
		try {
			CallableStatement cStmt = conn.prepareCall("{? = call FUNCTION2(?, ?, ?, ?)}");
			cStmt.registerOutParameter(1, Types.INTEGER);
			cStmt.setInt(2, pid);
			cStmt.setString(3, pname);
			cStmt.setInt(4, age);
			cStmt.setInt(5, did);
			cStmt.execute();
			System.out.println("Performer inserted 2");
			
			
			
		} catch (SQLException e) {
			System.err.println("SQL EXPECPTION ON INSERT2: " + e.getMessage());
		}
	}


}
