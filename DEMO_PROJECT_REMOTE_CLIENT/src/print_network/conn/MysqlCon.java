package print_network.conn;
import java.sql.*;

public class MysqlCon {
        public MysqlCon() {
        }
	public static void printDebugMessage(String message) {
		System.out.println(message);
	}

	public static void printDebugMessage(Exception e) {
		e.getStackTrace();
		System.out.println(e.getMessage());
	}
        public static void main(String args[]){  
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/remote_viewer","root","");  
//here sonoo is database name, root is username and password  
Statement stmt=con.createStatement();  
ResultSet rs=stmt.executeQuery("select * from tbl_user");  
while(rs.next())  
System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
con.close();  
}catch(Exception e){ System.out.println(e);}  
} 
}
