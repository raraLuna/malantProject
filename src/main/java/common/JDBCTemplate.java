package common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemplate {
	
//	// 1. 직접 값을 지정한 경우
//	public static Connection getConnection() {
//		Connection conn = null;
//		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##testweb", "testweb");
//			conn.setAutoCommit(false);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return conn;
//	}
	
	// 2. driver.properties 파일에서 값을 읽어와서 적용하는 경우
	// static 메소드 내에서는 this 사용을 못하므로, 별도의 파일 읽기용 내부클래스 작성해서 사용하도록 함
	// static 메소드 내에서 사용해야 하므로, static class 로 작성함
//	private static class ReadProperties {
//		private Properties prop;
//		
//		public ReadProperties() {
//			prop = new Properties();
//			
//			try {
//				prop.load(new InputStreamReader(this.getClass().getResourceAsStream("driver.properties")));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		public Properties getProp() {
//			return prop;
//		}
//	}
	// **********************************************************************************
	
//	public static Connection getConnection() {
//		Connection conn = null;
//		
//		try {
//			Properties prop = new ReadProperties().getProp();
//			
//			Class.forName(prop.getProperty("driver"));
//			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("passwd"));
//			conn.setAutoCommit(false);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return conn;
//	}
	
	// 3. 톰캣이 제공하는 DBCP(DataBase Connection Pool)를 이용
	// content directory : 프로젝트/src/main/webapp
	// webapp/META-INF/context.xml 파일에 설정 처리함
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			// context.xml에 설정된 <Resource> 엘리먼트의 값들을 이용해서
			// 톰캣의 DBCP를 통해서 Connection을 얻어옴
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/oraDB"); // tomcat의 DB 소스를 가리킴
			
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace(); // 에러메시지 출력 꼭 넣으세요. 에러 잡으려면 필요함
		}
		
		return conn;
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
