package edu.java.side;

public interface OracleUserQuery {

	public static final String URL = 
	         "jdbc:oracle:thin:@192.168.0.141:1521:xe"; // 접속할 오라클 DB 경로
	   public static final String USER = "scott";
	   public static final String PASSWORD = "tiger";
	   
	   public static final String TABLE_NAME_USER = "USER_INFO";
	   public static final String COL_USER_ID = "USER_ID";
	   public static final String COL_USER_PW = "USER_PW";
	   public static final String COL_USER_NAME = "USER_NAME";
	   public static final String COL_USER_TIME = "USER_TIME";
	
	// 사용자 정보 등록
	  
	// 데이터 등록
	   // INSERT INTO EX_CONTACT
	   // VALUES (?, ?, ?, ?, ?);
	   public static final String SQL_INSERT = "INSERT INTO " + TABLE_NAME_USER 
	                        + " VALUES (?, ?, ?, ?)";
	   
	// 사용자 정보 찾기 - 회원가입된 아이디 인지 확인
	   
	   // SELECT * FROM EX_CONTACT WHERE COL_USER_ID = ?;
	   public static final String SQL_SELECT_ID =
	         "SELECT " + COL_USER_ID + " FROM " + TABLE_NAME_USER + 
	         " WHERE " + COL_USER_ID + " = ?";
	   // 관리자인지 아닌지 확인
	   public static final String SQL_SELECT_ADMIN =
		         "SELECT " + COL_USER_ID + " FROM " + TABLE_NAME_USER + 
		         " WHERE " + COL_USER_ID + " = admin " +"AND " + COL_USER_PW + " = admin";
	   
	   
	   // - 회원 정보 전체 검색
	   // SELECT * FROM T_MEMBER;
	   public static final String SQL_SELECT = 
	         "SELECT * FROM " + TABLE_NAME_USER;

	   
	   
	 // 사용자 아이디중복 확인
	   public static final String SQL_CHECK_ID =
			   "SELECT * FROM " + TABLE_NAME_USER + 
			   " WHERE " + COL_USER_ID + " = ? ";
	   
	// 사용자 모든 정보 가져오기   
	 public static final String SQL_SELECT_USERINFO =
			 "SELECT " +  COL_USER_ID + ", " + COL_USER_NAME  + ", " + COL_USER_TIME + " FROM " + TABLE_NAME_USER +
			 " WHERE " +  COL_USER_ID + " = ? ";
			 
			
	   
	// 사용자 정보 수정
	   // 데이터 수정
	   // UPDATE EX_CONTACT
	   // SET NAME = ?, PHONE = ?, EMAIL = ?
	   // WHERE CONTACT_ID = ?
	   public static final String SQL_UPDATE = 
	         "UPDATE " + TABLE_NAME_USER + " SET " + 
	               COL_USER_ID + " = ? , " +
	               COL_USER_PW + " = ? , " +
	               COL_USER_NAME + " = COL_USER_NAME , " +
	               COL_USER_TIME + " = COL_USER_TIME " +
	               "WHERE " + COL_USER_NAME + " = ?";
	   
	// 사용자 정보 삭제

	   // 데이터 삭제
	   // DELETE EX_CONTACT WHERE CONTACT_ID = ?
	   public static final String SQL_DELETE = 
	         "DELETE " + TABLE_NAME_USER + " WHERE " + COL_USER_NAME + " = ?";


	   // 사용자 시간 수정 (추가)
	   
	   public static final String SQL_UPDATE_TIME_CHARGE = 
			   "UPDATE " + TABLE_NAME_USER + " SET " +
					   COL_USER_TIME + " = " + COL_USER_TIME + " + ? " +
					   "WHERE " + COL_USER_ID + " = ?";
	   
	   public static final String SQL_UPDATE_TIME_SAVE = 
			   "UPDATE " + TABLE_NAME_USER + " SET " +
					   COL_USER_TIME + " = ? " +
					   "WHERE " + COL_USER_ID + " = ?";
	   
	   
					   
	   
	   // 사용자 남은 시간 출력, 저장
	   public static final String SQL_CHECK_USER = 
			   "SELECT " + COL_USER_ID + ", " + COL_USER_TIME + " FROM " +TABLE_NAME_USER + 
			   " WHERE " + COL_USER_ID + " = ? " + " AND " + COL_USER_PW + " = ? ";
	   
	   // 사용자 시간 불러오기
	   public static final String SQL_SELECT_TIME =
			   "SELECT " + COL_USER_TIME + " FROM " + TABLE_NAME_USER +
			   " WHERE " + COL_USER_ID + " = ? ";
	   
	   
} 