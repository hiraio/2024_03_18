package edu.java.side;

public class SeatVO {
	private int pcNumber;
	private String userId;
	private String userName;
	private long userTime;
	
	public SeatVO(int pcNumber, String userId, String userName,long userTime) {
	
		this.pcNumber = pcNumber;
		this.userId = userId;
		this.userName = userName;
		
		
	}

	public SeatVO(String userId, long userTime) {
		this.userId = userId;
		this.userTime = userTime;
	}

	public int getPcNumber() {
		return pcNumber;
	}

	public void setPcNumber(int pcNumber) {
		this.pcNumber = pcNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public long getUserTime() {
		return userTime;
	}

	public void setUserTime(long userTime) {
		this.userTime = userTime;
	}

	@Override
	public String toString() {
		return "SeatVO [pcNumber=" + pcNumber + ", userId=" + userId + ", userName=" + userName + ", userTime=" + userTime + "]";
	}
		
	
	
} // end PC_SeatVO
