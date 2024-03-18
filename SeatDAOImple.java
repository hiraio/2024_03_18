package edu.java.side;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import oracle.jdbc.OracleDriver;

public class SeatDAOImple implements SeatDAO, OracleSeatQuery {


	// 1. private static 자기 자신 타입의 변수 선언
	private static SeatDAOImple instance = null;
	
	private SeatDAOImple() {
	}

	
	// 3. public static 메소드 - 인스턴스를 리턴하는 메소드 구현
	public static SeatDAOImple getInstance() {
		if (instance == null) {
			instance = new SeatDAOImple();
		}
		return instance;
	}
	

	@Override
	public int UserTime(String userId) {
		int result = 0;
		
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      // PreparedStatement : 매개변수를 갖고 있는 SQL 문장을 활용하기 위한 클래스
	      //                  Statement와 상속관계
	      
	      try {
	         // 2. Oracle JDBC 드라이버를 메모리에 로드
	         DriverManager.registerDriver(new OracleDriver());
	         System.out.println("드라이버 로드 성공");
	         
	         // 3. DB와 Connection(연결)을 맺음
	         conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         System.out.println("DB 연결 성공");
	         
	         // 4. Connection 객체를 사용하여 PreparedStatement 객체를 생성
	         pstmt = conn.prepareStatement(SQL_UPDATE_TIME);
	         
	         // 5. SQL 문장 완성 - SQL_INSERT 쿼리의 ?를 채워주는 코드
	        
	     	 pstmt.setString(1, userId);
	     	     
	     	 System.out.println("아이디까지 들어감");
	         
	         // SQL 쿼리의 ? 순서와 parameterIndex의 값은 동일하게 지정
	         // 예시) ?가 첫 번째이면 parameterIndex = 1
	         
	         // setInt() : DB의 Number 타입
	         // setString() : DB의 varchar, varchar2 타입
	         // setFloat() : DB의 Float 타입
	         // setDate() : DB의 Date 타입
	         
	         // 6. SQL 문장 실행(DB 서버로 SQL 전송)
	         result = pstmt.executeUpdate();
	         
	         // 7. DB 서버가 보낸 결과 확인/처리
	         System.out.println(result + "행이 수정됐습니다.");
	         
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            pstmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		
		
		return result;
	}

	@Override
	public SeatVO AllTime(int n) {
		SeatVO vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_UPDATE_TIME);
		
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 레코드가 존재할 때까지
				String userid  = rs.getString(1); // USER_ID 컬럼	
				
				 // PC_ISUSED 컬럼
				int remainTime = rs.getInt(4); // PC_REMAINTIME 컬럼

				vo = new SeatVO(userid,remainTime);
				System.out.println("vo 전송 성공");

				
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vo;
	}
		





	@Override
	public int loginSeat(UserVO vo,int n) {
			int result = 0;
			
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      // PreparedStatement : 매개변수를 갖고 있는 SQL 문장을 활용하기 위한 클래스
	      //                  Statement와 상속관계
	      
	      try {
	         // 2. Oracle JDBC 드라이버를 메모리에 로드
	         DriverManager.registerDriver(new OracleDriver());
	         System.out.println("드라이버 로드 성공");
	         
	         // 3. DB와 Connection(연결)을 맺음
	         conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         System.out.println("DB 연결 성공");
	         
	         // 4. Connection 객체를 사용하여 PreparedStatement 객체를 생성
	         pstmt = conn.prepareStatement(SQL_UPDATE_SEAT);
	         
	         pstmt.setString(1, vo.getUserId());
	         pstmt.setString(2, vo.getUserName());
	         pstmt.setLong(3, vo.getUsertime());
	         pstmt.setInt(4, n);	
	    //   
	         
	         
	         // 5. SQL 문장 완성 - SQL_INSERT 쿼리의 ?를 채워주는 코드
	      
	         
	         // 6. SQL 문장 실행(DB 서버로 SQL 전송)
	         result = pstmt.executeUpdate();
	         
	         // 7. DB 서버가 보낸 결과 확인/처리
	         System.out.println(result + "행이 삽입됐습니다.");
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            pstmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		
		
		return result;
	}




	@Override
	public int DeleteSeat(int n) {
		int result = 0;
		
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      // PreparedStatement : 매개변수를 갖고 있는 SQL 문장을 활용하기 위한 클래스
	      //                  Statement와 상속관계
	      
	      try {
	         // 2. Oracle JDBC 드라이버를 메모리에 로드
	         DriverManager.registerDriver(new OracleDriver());
	         System.out.println("드라이버 로드 성공");
	         
	         // 3. DB와 Connection(연결)을 맺음
	         conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         System.out.println("DB 연결 성공");
	         
	         // 4. Connection 객체를 사용하여 PreparedStatement 객체를 생성
	         pstmt = conn.prepareStatement(SQL_UPDATE_SEAT);
	         
	         pstmt.setString(1, null);
	         pstmt.setString(2, null);
	         pstmt.setString(3, null);
	         pstmt.setInt(4, n);	
	    //   
	         
	         
	         // 5. SQL 문장 완성 - SQL_INSERT 쿼리의 ?를 채워주는 코드
	      
	         
	         // 6. SQL 문장 실행(DB 서버로 SQL 전송)
	         result = pstmt.executeUpdate();
	         
	         // 7. DB 서버가 보낸 결과 확인/처리
	         System.out.println(result + "행이 삽입됐습니다.");
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            pstmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }

		
		
		return result;
	}




}
