import java.sql.*;
import java.util.Scanner;

public class Driver {
	private static Connection conn;
	private static Statement stmt;
	private static Scanner inputScanner;

	public static void main(String[] args) {
		// Step 1. Loading a database driver 
		String sourceURL = "jdbc:oracle:thin:@//oracle.cs.ou.edu:1521/pdborcl.cs.ou.edu";
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

			    		insertPerformer2(pid, pname , age, did );
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
		
		int lower = (age - 10 >= 0) ? age - 10 : 0;
		int upper = age + 10;
		int counter = 0;
		int totalYears = 0;
		int years_of_experience;
		String experienceSql = "select years_of_experience from performer where age between "
				+ lower + "and " + upper;
		
		//find years of experience of performers <= 10 years younger or older
		try {
			ResultSet result = stmt.executeQuery(experienceSql);
			while(result.next()) {
				System.out.println(result.getString(1)); 
				try {
					totalYears += Integer.parseInt(result.getString(1));
					counter++;
				} catch(NumberFormatException e) {
					System.err.println("Uh oh, that wasn't a number!");
			        System.exit(1); 
				}
			}
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.exit(1);
		} 
		
		if(counter == 0){
				years_of_experience = (age - 18 >= 0) ? age - 18 : 0;
		} else {
			years_of_experience = (int) totalYears / counter;
		}	
		System.out.println("years of experience: " + years_of_experience);
		
		String sqlInsert = "insert into performer values(" 
				+ pid 
				+ ", '"
				+ pname
				+ "', "
				+ years_of_experience
				+ ", "
				+ age
				+ ")";
		
		try {
			stmt.executeUpdate(sqlInsert);
			ResultSet result = stmt.executeQuery("select * from performer");
			while(result.next()) {
				System.out.println("PID Name Experience Age");
				System.out.println(result.getString(1)
						+ " " + result.getString(2)
						+ " " + result.getString(3)
						+ " " + result.getString(4)); 
			}
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} 
		return true;
	}

	
	public static void insertPerformer2(int pid, String name, int age , int did) 
	{ 
		System.out.println(pid +" " + name +" " + age +" "+ did);
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR in statement");
		} 
		
		//find average age of  all performers who have acted in a movie that was directed by a director with a given did
	
		//find all these performers 
		
		
	}
}
