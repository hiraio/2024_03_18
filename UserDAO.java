package edu.java.side;

import java.util.ArrayList;

public interface UserDAO {

//	
//	UserDAO기능 - 내 남은 시간 확인, 아이디 비밀번호 만들기, pc 키고 끄기 해야지
//	1. 사용자 정보 등록
	public abstract int insertUser(UserVO vo);
	
//	2. 사용자 정보 선택 - 회원가입된 아이디인지 확인
	public abstract int select(String userID); 

//	3. 사용자 정보 가져오기 - 아이디,이름, 남은시간
	public abstract UserVO selectInfo(String userId);
	
//	4. 사용자 시간 수정(추가) 충전시
	public abstract int updateTime(Long usertime, String userId);

	//  4.2 사용자 남은 시간 저장
	
	public abstract int SaveTime(Long usertime, String userId);
	
//	5. 사용자 남은 시간 출력
	public abstract UserVO selectTime(String userId); // 실시간으로 계속 남은 시간이 출력되는 것
	
// 6. 사용자 아이디 비밀번호가 회원가입 되어있는지 확인 왜? 자리에 로그인 할 때 확인해야하니까	
	public abstract int checkuser(String userId, String userPw);
	
// 7. 아이디 중복 확인
	public abstract int isDuplicate(String userId);
// 8. 관리자인지 아닌지 체크
	public abstract int checkAdmin();
// 9 회원 정보 전체 검색
	public abstract ArrayList<UserVO>select();	

	
}

