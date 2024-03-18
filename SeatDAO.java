package edu.java.side;

public interface SeatDAO {
	
	//	2. 사용자 아이디를 입력받아서 사용자 남은 시간을 추가하는 기능
	public abstract int UserTime(String userId);
	
	//	3. pc 번호별로 사용자 id와 남은 시간을 출력해주는 기능 , 
	public abstract SeatVO AllTime(int n);
	
	// 4. 종료를 누르면 1번 좌석 정보를 없애는 매소드
	public abstract int DeleteSeat(int n);
	
	// 5. check메소드가 참이면, 로그인완료(USER_INFO 를 SEAT_INFO로 옮김), PC 넘버 값을 입력받아서 사용시작
	public abstract int loginSeat(UserVO vo,int n);
	
} // end SeatDAO
