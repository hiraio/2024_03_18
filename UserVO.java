package edu.java.side;

public class UserVO {

	private String userId;
	private String userPw;
	private String userName;
	private long usertime;
	
	public UserVO(String userId, String userPw, String userName, long usertime) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.usertime = usertime;
	}
	public UserVO(String userId,String userPw) {
		this.userId = userId;
		this.userPw = userPw;
	}
	
	public UserVO(long usertime,String userId) {
		this.usertime = usertime;
		this.userId = userId;
		
	}
	public UserVO(long usertime) {
		this.usertime = usertime;
		
	}
	public UserVO(String userId, String userName,long usertime) {
		this.userId = userId;
		this.userName = userName;
		this.usertime = usertime;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getUsertime() {
		return usertime;
	}

	public void setUsertime(long usertime) {
		this.usertime = usertime;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userName=" + userName + ", usertime=" + usertime + "]";
	}
	
}
		