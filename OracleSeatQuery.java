package edu.java.side;

public interface OracleSeatQuery {
	
	public static final String URL = 
	         "jdbc:oracle:thin:@192.168.0.141:1521:xe"; // 접속할 오라클 DB 경로
	   public static final String USER = "scott";
	   public static final String PASSWORD = "tiger";
	   
	   public static final String TABLE_NAME1 = "SEAT_INFO";
	   public static final String TABLE_NAME2 = "USER_INFO"; 
	   public static final String COL_PC_NUMBER = "PC_NUMBER";
	   public static final String COL_USER_ID = "USER_ID";
	   public static final String COL_USER_NAME = "USER_NAME";
	
	   public static final String COL_REMAIN_TIME = "REMAIN_TIME";
	   public static final String COL_USER_TIME = "USER_TIME";
	   
	   
		   
	   //	   1. pc 번호별로 PC 사용 on off 하는 기능
	   public static final String SQL_UPDATE_SEAT = 
		         "UPDATE " + TABLE_NAME1 + " SET " + 
		               COL_USER_ID + " = ? , " +
		               COL_USER_NAME + " = ? , " +
		               COL_REMAIN_TIME + " = ? " + 
		               "WHERE " + COL_PC_NUMBER + " = ? ";
	   
	   
	   //	   2. 사용자 아이디를 입력받아서 사용자 남은 시간을 추가하는 기능
	   
	   public static final String SQL_UPDATE_TIME = 
		         "UPDATE " + TABLE_NAME2 + " SET " + 
		                COL_USER_TIME + " = USER_TIME " +
		               "WHERE " + COL_USER_ID + " = ?";

	   
	   //	   3. pc 번호별로 사용자 id와 남은 시간을 출력해주는 기능
	   
	   // SELECT * FROM EX_CONTACT WHERE COL_USER_ID = ?;
	   public static final String SQL_SELECT_PC_NUMBER =
	         "SELECT USER_ID, REMAIN_TIME FROM " + TABLE_NAME1 + 
	         " WHERE " + COL_PC_NUMBER + " = ?";
	   
	   // 4. SEAT_INFO에 로그인 사용자 정보 INSERT 하기

	   // SELECT * FROM EX_CONTACT WHERE COL_USER_ID = ?;
	   public static final String SQL_SELECT_USER =
	         "SELECT * FROM " + TABLE_NAME1 + 
	         " WHERE " + COL_USER_ID + " = ?";
	   
	   // 5. SEAT_INFO에 있는 값 삭제하기
	   
	   
	    
}
