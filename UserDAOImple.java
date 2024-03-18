package edu.java.side;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import oracle.jdbc.OracleDriver;

public class UserDAOImple implements UserDAO, OracleUserQuery {

	// 싱글톤 디자인 패턴 적용 시작

	// 1. private static 자기 자신 타입의 변수 선언
	private static UserDAOImple instance = null;

	// 2. private 생성자
	UserDAOImple() {
	}

	// 3. public static 메소드 - 인스턴스를 리턴하는 메소드 구현
	public static UserDAOImple getInstance() {
		if (instance == null) {
			instance = new UserDAOImple();
		}
		return instance;
	}

	@Override
	public int insertUser(UserVO vo) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		// PreparedStatement : 매개변수를 갖고 있는 SQL 문장을 활용하기 위한 클래스
		// Statement와 상속관계

		try {
			// 2. Oracle JDBC 드라이버를 메모리에 로드
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			// 3. DB와 Connection(연결)을 맺음
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			// 4. Connection 객체를 사용하여 PreparedStatement 객체를 생성
			pstmt = conn.prepareStatement(SQL_INSERT);

			// 5. SQL 문장 완성 - SQL_INSERT 쿼리의 ?를 채워주는 코드

			if (!vo.getUserId().isEmpty() && vo.getUserId() != null) {
				pstmt.setString(1, vo.getUserId());
			} else {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
			}

			if (!vo.getUserPw().isEmpty() && vo.getUserPw() != null) {
				pstmt.setString(2, vo.getUserPw());
			} else {

				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요", "에러", JOptionPane.ERROR_MESSAGE);
			}

			if (!vo.getUserName().isEmpty() && vo.getUserName() != null) {
				pstmt.setString(3, vo.getUserName());
				pstmt.setLong(4, vo.getUsertime());
				result = pstmt.executeUpdate();

			} else {

				JOptionPane.showMessageDialog(null, "이름을 입력하세요", "에러", JOptionPane.ERROR_MESSAGE);
			}

			// SQL 쿼리의 ? 순서와 parameterIndex의 값은 동일하게 지정
			// 예시) ?가 첫 번째이면 parameterIndex = 1

			// setInt() : DB의 Number 타입
			// setString() : DB의 varchar, varchar2 타입
			// setFloat() : DB의 Float 타입
			// setDate() : DB의 Date 타입

			// 6. SQL 문장 실행(DB 서버로 SQL 전송)

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
	public int select(String userId) {

		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_SELECT_ID);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			result = pstmt.executeUpdate();
			
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
	public int updateTime(Long usertime, String userId) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		// PreparedStatement : 매개변수를 갖고 있는 SQL 문장을 활용하기 위한 클래스
		// Statement와 상속관계

		try {
			// 2. Oracle JDBC 드라이버를 메모리에 로드
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			// 3. DB와 Connection(연결)을 맺음
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			System.out.println("시간" + usertime + " 아이디: " +userId);
			// 4. Connection 객체를 사용하여 PreparedStatement 객체를 생성
			pstmt = conn.prepareStatement(SQL_UPDATE_TIME_CHARGE);

			// 5. SQL 문장 완성 - SQL_INSERT 쿼리의 ?를 채워주는 코드

			pstmt.setLong(1, usertime);
			pstmt.setString(2, userId);

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
	public int SaveTime(Long usertime, String userId) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		// PreparedStatement : 매개변수를 갖고 있는 SQL 문장을 활용하기 위한 클래스
		// Statement와 상속관계

		try {
			// 2. Oracle JDBC 드라이버를 메모리에 로드
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			// 3. DB와 Connection(연결)을 맺음
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			System.out.println("시간" + usertime + " 아이디: " +userId);
			// 4. Connection 객체를 사용하여 PreparedStatement 객체를 생성
			pstmt = conn.prepareStatement(SQL_UPDATE_TIME_SAVE);

			// 5. SQL 문장 완성 - SQL_INSERT 쿼리의 ?를 채워주는 코드

			pstmt.setLong(1, usertime);
			pstmt.setString(2, userId);

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
	public UserVO selectTime(String userId) {
		UserVO uservo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_SELECT_TIME);
			pstmt.setString(1, userId);
			

			rs = pstmt.executeQuery();
			while (rs.next()) {
				long time = rs.getLong(1);
				uservo = new UserVO(time,userId);

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

		return uservo;
	}

	@Override
	public int checkuser(String userId, String userPw) {
		int result = 0;

		
		Connection conn = null;
		PreparedStatement pstmt = null;
		

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_CHECK_USER);

			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);

			result = pstmt.executeUpdate();
			System.out.println(result + "행이 존재합니다.");

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
	public int isDuplicate(String userId) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_CHECK_ID);

			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next() == true) {
				result =  1;
			} else {
				result =  0;
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
		return result;
		
	}

	@Override
	public UserVO selectInfo(String userId) {
		UserVO vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_SELECT_USERINFO);
			
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 레코드가 존재할 때까지
				userId = rs.getString(1); // USER_ID 컬럼
				String name = rs.getString(2); // USER_NAME 컬럼
				long time = rs.getLong(3); // USER_TIME 컬럼
				
				vo = new UserVO(userId, name, time);
			}
			System.out.println(vo);
			

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
	public int checkAdmin() {
		
	int result = 0;

		
		Connection conn = null;
		PreparedStatement pstmt = null;
		

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_SELECT_ADMIN);

			result = pstmt.executeUpdate();
			System.out.println(result + "행이 존재합니다.");

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
	public ArrayList<UserVO> select() {
	
		  ArrayList<UserVO> list = new ArrayList<>();
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         // 2. Oracle JDBC 드라이버를 메모리에 로드
	         DriverManager.registerDriver(new OracleDriver());
	         System.out.println("드라이버 로드 성공");
	         
	         // 3. DB와 Connection(연결)을 맺음
	         conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         System.out.println("DB 연결 성공");

	         // 4. SQL 문장 작성
	         // SELECT * FROM EX_CONTACT ORDER BY CONTACT_ID;
	         
	         // 5. Connection 객체를 사용하여 Statement 객체를 생성
	         pstmt = conn.prepareStatement(SQL_SELECT);
	         
	         // 6. SQL 문장 실행(DB 서버로 SQL 전송)
	         rs = pstmt.executeQuery();
	   
	         // 7. DB 서버가 보낸 결과 확인/처리
	         while(rs.next()) { // 레코드가 존재할 때까지
	            String id = rs.getString(COL_USER_ID);
	            String pw = rs.getString(COL_USER_PW);
	            String name = rs.getString(COL_USER_NAME);
	            int time = rs.getInt(COL_USER_TIME);
	            
	            
	          UserVO vo = new UserVO(id,pw,name,time);
	            list.add(vo);
	         }
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
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
	      return list;
	   }

	}
