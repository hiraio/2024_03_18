package edu.java.side;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JLabel;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Seat extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public static UserDAO userdao;
	public static SeatDAO seatdao;
	private JPanel contentPane;
	private JTextArea textPC1;
	private JLabel lblPC1;
	private JTextArea textPC2;
	private Thread timerThread1;
	private Thread timerThread2;
	private Thread timerThread3;
	private Thread timerThread4;
	private Thread timerThread5;
	private Thread timerThread6;
	
	private JTextArea textPC3;
	private JLabel lblPC3;
	private JButton btnPC3ON;
	private JButton btnPC3Off;
	private JTextArea textPC4;
	private JLabel lblPC4;
	private JButton btnPC4ON;
	private JButton btnPC4Off;
	private JTextArea textPC5;
	private JLabel lblPC5;
	private JButton btnPC5ON;
	private JButton btnPC5Off;
	private JTextArea textPC6;
	private JButton btnPC6ON;
	private JButton btnPC6Off;
	private JLabel lblPC6;

	private JLabel lblPC2;
	private JButton btnPC2Off, btnPC1Off;
	private JButton btnPC1ON;
	private JButton btnPC2ON;
	private long time = 0;
	private String userid = null;
	private boolean start01 = true;
	private boolean stop01= false;
	private boolean start02 = true;
	private boolean stop02= false;
	private boolean start03 = true;
	private boolean stop03= false;
	private boolean start04 = true;
	private boolean stop04= false;
	private boolean start05 = true;
	private boolean stop05= false;
	private boolean start06 = true;
	private boolean stop06= false;
	/**
	 * Create the frame.
	 */
	public Seat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnGoMain = new JButton("메인화면");
		btnGoMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PC_Main window = new PC_Main();
				window.frame.setVisible(true);
				setVisible(false);
			}
		});

		textPC1 = new JTextArea();
		timer01.start();

		textPC1.setBackground(Color.YELLOW);
		textPC1.setBounds(30, 16, 117, 78);
		contentPane.add(textPC1);

		lblPC1 = new JLabel("1번");
		lblPC1.setBackground(Color.GRAY);
		lblPC1.setBounds(76, 104, 25, 15);
		contentPane.add(lblPC1);

		btnPC1ON = new JButton("시작");
		btnPC1ON.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 시작 버튼을 누르면 timer01 이 멈춤(계속 출력되던 글자 STOP), PC1번으로 로그인을 시도, button = true -> 종료
				// 버튼을 활성화함
				if (start01 == true) {
					timer01.stop();
					loginPC1();
					stop01 = true;
				} else {
					JOptionPane.showMessageDialog(null, "이미 사용중인 PC입니다.", "에러", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
		btnPC1ON.setForeground(new Color(0, 0, 0));

		btnPC1ON.setBackground(Color.LIGHT_GRAY);
		btnPC1ON.setBounds(159, 27, 57, 23);
		contentPane.add(btnPC1ON);

		btnPC1Off = new JButton("종료");
		btnPC1Off.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 활성화 된 종료버튼
				if (stop01 == true) {
					start01 = true;
					userdao = UserDAOImple.getInstance();
					seatdao = SeatDAOImple.getInstance();

					// 종료버튼을 누른 시점에 사용자의 시간을 DB에 저장함
					userdao.SaveTime(time, userid);
					
					System.out.println(time + userid);
					
					System.out.println("PC를 종료하고, 남은 시간 DB에 저장완료");
					String pcNumber = Character.toString(lblPC1.getText().charAt(0));
					int n = Integer.parseInt(pcNumber);

					// 1번 SEATINFO에 저장되어있던 데이터 삭제
					seatdao.DeleteSeat(n);
					// 타이머 쓰레드 종료
					timerThread1.interrupt();
				} else { // 종료버튼이 활성화 되지않았으면 에러 메세지 호출,
					JOptionPane.showMessageDialog(null, "PC를 시작해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});

		btnPC1Off.setBackground(Color.LIGHT_GRAY);
		btnPC1Off.setBounds(159, 60, 57, 23);
		contentPane.add(btnPC1Off);

		textPC2 = new JTextArea();
		timer02.start();

		textPC2.setBackground(Color.YELLOW);
		textPC2.setBounds(30, 129, 117, 78);
		contentPane.add(textPC2);

		lblPC2 = new JLabel("2번");
		lblPC2.setBackground(Color.GRAY);
		lblPC2.setBounds(76, 219, 57, 15);
		contentPane.add(lblPC2);

		btnPC2ON = new JButton("시작");
		btnPC2ON.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer02.stop();
				loginPC2();
				stop02 = true;
			}
		});
		btnPC2ON.setBackground(Color.LIGHT_GRAY);
		btnPC2ON.setBounds(159, 145, 57, 23);
		contentPane.add(btnPC2ON);

		btnPC2Off = new JButton("종료");
		btnPC2Off.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (stop02 == true) {
					start02 = true;
					userdao = UserDAOImple.getInstance();
					seatdao = SeatDAOImple.getInstance();
					userdao.SaveTime(time, userid);
					String pcNumber = Character.toString(lblPC2.getText().charAt(0));
					int n = Integer.parseInt(pcNumber);
					seatdao.DeleteSeat(n);
					timerThread2.interrupt();
				} else {
					JOptionPane.showMessageDialog(null, "PC를 시작해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}

			}
		});
		btnPC2Off.setBackground(Color.LIGHT_GRAY);
		btnPC2Off.setBounds(159, 178, 57, 23);
		contentPane.add(btnPC2Off);

		btnGoMain.setBounds(30, 429, 117, 35);
		contentPane.add(btnGoMain);

		textPC3 = new JTextArea();
		timer03.start();

		textPC3.setBackground(Color.YELLOW);
		textPC3.setBounds(279, 16, 117, 78);
		contentPane.add(textPC3);

		lblPC3 = new JLabel("3번");
		lblPC3.setBackground(Color.GRAY);
		lblPC3.setBounds(328, 104, 25, 15);
		contentPane.add(lblPC3);

		btnPC3ON = new JButton("시작");
		btnPC3ON.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer03.stop();
				loginPC3();
				stop03 = true;
			}
		});
		btnPC3ON.setForeground(Color.BLACK);
		btnPC3ON.setBackground(Color.LIGHT_GRAY);
		btnPC3ON.setBounds(408, 27, 57, 23);
		contentPane.add(btnPC3ON);

		btnPC3Off = new JButton("종료");
		btnPC3Off.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (stop03 == true) {
					start03 = true;
					userdao = UserDAOImple.getInstance();
					seatdao = SeatDAOImple.getInstance();
					userdao.SaveTime(time, userid);
					String pcNumber = Character.toString(lblPC3.getText().charAt(0));
					int n = Integer.parseInt(pcNumber);
					seatdao.DeleteSeat(n);
					timerThread3.interrupt();
				} else {
					JOptionPane.showMessageDialog(null, "PC를 시작해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}

			}
		});

		btnPC3Off.setBackground(Color.LIGHT_GRAY);
		btnPC3Off.setBounds(408, 60, 57, 23);
		contentPane.add(btnPC3Off);

		textPC4 = new JTextArea();
		timer04.start();

		textPC4.setBackground(Color.YELLOW);
		textPC4.setBounds(279, 129, 117, 78);
		contentPane.add(textPC4);

		lblPC4 = new JLabel("4번");
		lblPC4.setBackground(Color.GRAY);
		lblPC4.setBounds(328, 219, 25, 15);
		contentPane.add(lblPC4);

		btnPC4ON = new JButton("시작");
		btnPC4ON.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer04.stop();
				loginPC4();
				stop04 = true;

			}
		});
		btnPC4ON.setBackground(Color.LIGHT_GRAY);
		btnPC4ON.setBounds(408, 145, 57, 23);
		contentPane.add(btnPC4ON);

		btnPC4Off = new JButton("종료");
		btnPC4Off.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (stop04 == true) {
					start04 = true;
					userdao = UserDAOImple.getInstance();
					seatdao = SeatDAOImple.getInstance();
					userdao.SaveTime(time, userid);
					String pcNumber = Character.toString(lblPC4.getText().charAt(0));
					int n = Integer.parseInt(pcNumber);
					seatdao.DeleteSeat(n);
					timerThread4.interrupt();
				} else {
					JOptionPane.showMessageDialog(null, "PC를 시작해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}

			}
		});
		btnPC4Off.setBackground(Color.LIGHT_GRAY);
		btnPC4Off.setBounds(408, 178, 57, 23);
		contentPane.add(btnPC4Off);

		textPC5 = new JTextArea();
		timer05.start();

		textPC5.setBackground(Color.YELLOW);
		textPC5.setBounds(30, 244, 117, 78);
		contentPane.add(textPC5);

		lblPC5 = new JLabel("5번");
		lblPC5.setBackground(Color.GRAY);
		lblPC5.setBounds(76, 332, 57, 15);
		contentPane.add(lblPC5);

		btnPC5ON = new JButton("시작");
		btnPC5ON.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer05.stop();
				loginPC5();
				stop05 = true;
			}
		});
		btnPC5ON.setBackground(Color.LIGHT_GRAY);
		btnPC5ON.setBounds(159, 255, 57, 23);
		contentPane.add(btnPC5ON);

		btnPC5Off = new JButton("종료");
		btnPC5Off.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (stop05 == true) {
					start05 = true;
					userdao = UserDAOImple.getInstance();
					seatdao = SeatDAOImple.getInstance();
					userdao.SaveTime(time, userid);
					String pcNumber = Character.toString(lblPC5.getText().charAt(0));
					int n = Integer.parseInt(pcNumber);
					seatdao.DeleteSeat(n);
					timerThread5.interrupt();
				} else {
					JOptionPane.showMessageDialog(null, "PC를 시작해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}

			}
		});
		btnPC5Off.setBackground(Color.LIGHT_GRAY);
		btnPC5Off.setBounds(159, 299, 57, 23);
		contentPane.add(btnPC5Off);

		textPC6 = new JTextArea();
		timer06.start();

		textPC6.setBackground(Color.YELLOW);
		textPC6.setBounds(279, 244, 117, 78);
		contentPane.add(textPC6);

		btnPC6ON = new JButton("시작");
		btnPC6ON.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer06.stop();
				loginPC6();
				stop06 = true;
			}
		});
		btnPC6ON.setBackground(Color.LIGHT_GRAY);
		btnPC6ON.setBounds(408, 255, 57, 23);
		contentPane.add(btnPC6ON);

		btnPC6Off = new JButton("종료");
		btnPC6Off.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (stop06 == true) {
					start06 = true;
					userdao = UserDAOImple.getInstance();
					seatdao = SeatDAOImple.getInstance();
					userdao.SaveTime(time, userid);
					String pcNumber = Character.toString(lblPC6.getText().charAt(0));
					int n = Integer.parseInt(pcNumber);
					seatdao.DeleteSeat(n);
					timerThread6.interrupt();
				} else {
					JOptionPane.showMessageDialog(null, "PC를 시작해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}

			}
		});
		btnPC6Off.setBackground(Color.LIGHT_GRAY);
		btnPC6Off.setBounds(408, 299, 57, 23);
		contentPane.add(btnPC6Off);

		lblPC6 = new JLabel("6번");
		lblPC6.setBackground(Color.GRAY);
		lblPC6.setBounds(317, 332, 57, 15);
		contentPane.add(lblPC6);

	}

	public class TimerTaskPC1 implements Runnable {

		private long usertime;
		private String userId;

		TimerTaskPC1(long usertime, String userId) {
			this.usertime = usertime;
			this.userId = userId;
		}

		public void run() {
			try {
				long startTime = System.currentTimeMillis();

				while (!Thread.currentThread().isInterrupted()) {

					long elapsedTime = System.currentTimeMillis() - startTime;
					long remainTime = usertime - elapsedTime;

					if (remainTime <= 0) {
						break;
					}

					long seconds = remainTime / 1000;
					long minutes = seconds / 60;
					seconds %= 60;

					String data = String.format("%02d분%02d초", minutes, seconds);

					textPC1.setText("UserID :" + userId + "\n" + " 남은 시간 : " + data);
					time = minutes;
					userid = userId;
					Thread.sleep(1000); // 1초
				}

			} catch (InterruptedException e) {

			} finally {
				textPC1.setText("안녕히 가세요");
				btnPC1ON.setEnabled(true);

				timer01.setRepeats(false);
				timer01.start();
			}

		}
	}

	private void viewPC1(String userId, long time) {
		System.out.println("아이디 : " + userId);
		seatdao = SeatDAOImple.getInstance();

		long userTime = time * 60 * 1000;

		// 타이머 쓰레드 생성
		timerThread1 = new Thread(new TimerTaskPC1(userTime, userId));

		timerThread1.start();

	}

	public class TimerTaskPC2 implements Runnable {

		private long usertime;
		private String userId;

		TimerTaskPC2(long usertime, String userId) {
			this.usertime = usertime;
			this.userId = userId;
		}

		public void run() {
			try {
				long startTime = System.currentTimeMillis();

				while (!Thread.currentThread().isInterrupted()) {

					long elapsedTime = System.currentTimeMillis() - startTime;
					long remainTime = usertime - elapsedTime;

					if (remainTime <= 0) {
						break;
					}

					long seconds = remainTime / 1000;
					long minutes = seconds / 60;
					seconds %= 60;

					String data = String.format("%02d분%02d초", minutes, seconds);

					textPC2.setText("UserID :" + userId + "\n" + " 남은 시간 : " + data);
					if (start02 == true) {
						time = minutes;
						userid = userId;
					}
					Thread.sleep(1000); // 1초
				}

			} catch (InterruptedException e) {

			} finally {
				textPC2.setText("안녕히 가세요");
				btnPC2ON.setEnabled(true);

				timer02.setRepeats(false);
				timer02.start();
			}

		}
	}

	private void viewPC2(String userId, long time) {
		System.out.println("아이디 : " + userId);
		seatdao = SeatDAOImple.getInstance();

		long userTime = time * 60 * 1000;

		// 타이머 쓰레드 생성
		timerThread2 = new Thread(new TimerTaskPC2(userTime, userId));

		timerThread2.start();

	}

	public class TimerTaskPC3 implements Runnable {

		private long usertime;
		private String userId;

		TimerTaskPC3(long usertime, String userId) {
			this.usertime = usertime;
			this.userId = userId;
		}

		public void run() {
			try {
				long startTime = System.currentTimeMillis();

				while (!Thread.currentThread().isInterrupted()) {

					long elapsedTime = System.currentTimeMillis() - startTime;
					long remainTime = usertime - elapsedTime;

					if (remainTime <= 0) {
						break;
					}

					long seconds = remainTime / 1000;
					long minutes = seconds / 60;
					seconds %= 60;

					String data = String.format("%02d분%02d초", minutes, seconds);

					textPC3.setText("UserID :" + userId + "\n" + " 남은 시간 : " + data);
					if (start03 == true) {
						time = minutes;
						userid = userId;
					}
					Thread.sleep(1000); // 1초
				}

			} catch (InterruptedException e) {

			} finally {
				textPC3.setText("안녕히 가세요");
				btnPC3ON.setEnabled(true);

				timer03.setRepeats(false);
				timer03.start();
			}

		}
	}

	private void viewPC3(String userId, long time) {
		System.out.println("아이디 : " + userId);
		seatdao = SeatDAOImple.getInstance();

		long userTime = time * 60 * 1000;

		// 타이머 쓰레드 생성
		timerThread3 = new Thread(new TimerTaskPC3(userTime, userId));

		timerThread3.start();

	}

	public class TimerTaskPC4 implements Runnable {

		private long usertime;
		private String userId;

		TimerTaskPC4(long usertime, String userId) {
			this.usertime = usertime;
			this.userId = userId;
		}

		public void run() {
			try {
				long startTime = System.currentTimeMillis();

				while (!Thread.currentThread().isInterrupted()) {

					long elapsedTime = System.currentTimeMillis() - startTime;
					long remainTime = usertime - elapsedTime;

					if (remainTime <= 0) {
						break;
					}

					long seconds = remainTime / 1000;
					long minutes = seconds / 60;
					seconds %= 60;

					String data = String.format("%02d분%02d초", minutes, seconds);

					textPC4.setText("UserID :" + userId + "\n" + " 남은 시간 : " + data);
					if (start04 == true) {
						time = minutes;
						userid = userId;
					}
					Thread.sleep(1000); // 1초
				}

			} catch (InterruptedException e) {

			} finally {
				textPC4.setText("안녕히 가세요");
				btnPC4ON.setEnabled(true);

				timer04.setRepeats(false);
				timer04.start();
			}

		}
	}

	private void viewPC4(String userId, long time) {
		System.out.println("아이디 : " + userId);
		seatdao = SeatDAOImple.getInstance();

		long userTime = time * 60 * 1000;

		// 타이머 쓰레드 생성
		timerThread4 = new Thread(new TimerTaskPC4(userTime, userId));

		timerThread4.start();

	}

	public class TimerTaskPC5 implements Runnable {

		private long usertime;
		private String userId;

		TimerTaskPC5(long usertime, String userId) {
			this.usertime = usertime;
			this.userId = userId;
		}

		public void run() {
			try {
				long startTime = System.currentTimeMillis();

				while (!Thread.currentThread().isInterrupted()) {

					long elapsedTime = System.currentTimeMillis() - startTime;
					long remainTime = usertime - elapsedTime;

					if (remainTime <= 0) {
						break;
					}

					long seconds = remainTime / 1000;
					long minutes = seconds / 60;
					seconds %= 60;

					String data = String.format("%02d분%02d초", minutes, seconds);

					textPC5.setText("UserID :" + userId + "\n" + " 남은 시간 : " + data);
					if (start05 == true) {
						time = minutes;
						userid = userId;
					}
					Thread.sleep(1000); // 1초
				}

			} catch (InterruptedException e) {

			} finally {
				textPC5.setText("안녕히 가세요");
				btnPC5ON.setEnabled(true);

				timer05.setRepeats(false);
				timer05.start();
			}

		}
	}

	private void viewPC5(String userId, long time) {
		System.out.println("아이디 : " + userId);
		seatdao = SeatDAOImple.getInstance();

		long userTime = time * 60 * 1000;

		// 타이머 쓰레드 생성
		timerThread5 = new Thread(new TimerTaskPC5(userTime, userId));

		timerThread5.start();

	}

	public class TimerTaskPC6 implements Runnable {

		private long usertime;
		private String userId;

		TimerTaskPC6(long usertime, String userId) {
			this.usertime = usertime;
			this.userId = userId;
		}

		public void run() {
			try {
				long startTime = System.currentTimeMillis();

				while (!Thread.currentThread().isInterrupted()) {

					long elapsedTime = System.currentTimeMillis() - startTime;
					long remainTime = usertime - elapsedTime;

					if (remainTime <= 0) {
						break;
					}

					long seconds = remainTime / 1000;
					long minutes = seconds / 60;
					seconds %= 60;

					String data = String.format("%02d분%02d초", minutes, seconds);

					textPC6.setText("UserID :" + userId + "\n" + " 남은 시간 : " + data);
					if (start06 == true) {
						time = minutes;
						userid = userId;
					}
					Thread.sleep(1000); // 1초
				}

			} catch (InterruptedException e) {

			} finally {
				textPC6.setText("안녕히 가세요");
				btnPC6ON.setEnabled(true);

				timer06.setRepeats(false);
				timer06.start();
			}

		}
	}

	private void viewPC6(String userId, long time) {
		System.out.println("아이디 : " + userId);
		seatdao = SeatDAOImple.getInstance();

		long userTime = time * 60 * 1000;

		// 타이머 쓰레드 생성
		timerThread6 = new Thread(new TimerTaskPC6(userTime, userId));

		timerThread6.start();

	}

	private void loginPC1() { // 시작 버튼을 누르면 실행되는 함수.

		userdao = UserDAOImple.getInstance();
		// 입력값을 userid로 받아옴
		String userid = JOptionPane.showInputDialog(null, "아이디를 입력하세요:", "로그인", JOptionPane.QUESTION_MESSAGE);

		System.out.println("pc 1번에 입력받은 Id :" + userid);

		// 사용자가 취소를 누르거나 아이디를 입력하지 않은 경우, 에러 메시지를 띄움
		if (userid == null || userid.isEmpty()) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);

			// 다시 시작 버튼을 활성화함
			btnPC1ON.setEnabled(true);
			return;
		}

		// 비밀번호 입력 대화상자 표시
		String userpw = JOptionPane.showInputDialog(null, "비밀번호를 입력하세요:", "로그인", JOptionPane.QUESTION_MESSAGE);
		System.out.println("pc 1번에 입력받은 PW :" + userpw);

		// 사용자가 취소를 누른 경우
		if (userpw == null) {
			JOptionPane.showMessageDialog(null, "로그인이 취소되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 다시 시작버튼을 활성화
			btnPC1ON.setEnabled(true);
			return;
		}

		// 입력된 아이디와 비밀번호 출력

		// 입력된 아이디와 비밀번호가 회원가입이 되어있는지 checkuser

		int result = userdao.checkuser(userid, userpw);

		if (result == 1) {
			seatdao = SeatDAOImple.getInstance();

			JOptionPane.showMessageDialog(null, "환영합니다 : " + userid + "님 ", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);

			System.out.println("회원입니다");

			long usertime = userdao.selectTime(userid).getUsertime();
			if (usertime != 0) { // 로그인한 회원이 PC충전이 되어있는지 selectTime를 써서 DB에 저장된 시간을 불러움
				System.out.println("시간이 남아있음");
				UserVO vo = userdao.selectInfo(userid);

				// 몇 번 자리에 로그인을 시도했는지, n에 PC자리를 넣어준다
				String pcNumber = Character.toString(lblPC1.getText().charAt(0));
				int n = Integer.parseInt(pcNumber);

				System.out.println(n + "번 자리에서 PC 사용 시작");
				seatdao.loginSeat(vo, n); // 회원 아이디,비번을 이용해서 자리에 로그인. , SEAT_INFO에 회원정보를 넣는다

				System.out.println("자리에 연결완료");
				start01 = false;

				// 유저 아이디, 시간을 화면에 출력
				viewPC1(userid, usertime);

			} else {
				JOptionPane.showMessageDialog(null, "잔여시간이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				// 시작 버튼 활성화
				btnPC1ON.setEnabled(true);
			}
		} else {
			JOptionPane.showMessageDialog(null, "회원 정보가 일치하지 않습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 시작 버튼 활성화
			btnPC1ON.setEnabled(true);
		}
	}

	private void loginPC2() { // 시작 버튼을 누르면 실행되는 함수.

		userdao = UserDAOImple.getInstance();
		// 입력값을 userid로 받아옴
		String userid = JOptionPane.showInputDialog(null, "아이디를 입력하세요:", "로그인", JOptionPane.QUESTION_MESSAGE);

		System.out.println("pc 2번에 입력받은 Id :" + userid);

		// 사용자가 취소를 누르거나 아이디를 입력하지 않은 경우, 에러 메시지를 띄움
		if (userid == null || userid.isEmpty()) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);

			// 다시 시작 버튼을 활성화함
			btnPC2ON.setEnabled(true);
			return;
		}

		// 비밀번호 입력 대화상자 표시
		String userpw = JOptionPane.showInputDialog(null, "비밀번호를 입력하세요:", "로그인", JOptionPane.QUESTION_MESSAGE);
		System.out.println("pc 2번에 입력받은 PW :" + userpw);

		// 사용자가 취소를 누른 경우
		if (userpw == null) {
			JOptionPane.showMessageDialog(null, "로그인이 취소되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 다시 시작버튼을 활성화
			btnPC2ON.setEnabled(true);
			return;
		}

		// 입력된 아이디와 비밀번호 출력

		// 입력된 아이디와 비밀번호가 회원가입이 되어있는지 checkuser

		int result = userdao.checkuser(userid, userpw);

		if (result == 1) {
			seatdao = SeatDAOImple.getInstance();

			JOptionPane.showMessageDialog(null, "환영합니다 : " + userid + "님 ", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);

			System.out.println("회원입니다");

			long usertime = userdao.selectTime(userid).getUsertime();
			if (usertime != 0) { // 로그인한 회원이 PC충전이 되어있는지 selectTime를 써서 DB에 저장된 시간을 불러움
				System.out.println("시간이 남아있음");
				UserVO vo = userdao.selectInfo(userid);

				// 몇 번 자리에 로그인을 시도했는지, n에 PC자리를 넣어준다
				String pcNumber = Character.toString(lblPC2.getText().charAt(0));
				int n = Integer.parseInt(pcNumber);

				System.out.println(n + "번 자리에서 PC 사용 시작");
				seatdao.loginSeat(vo, n); // 회원 아이디,비번을 이용해서 자리에 로그인. , SEAT_INFO에 회원정보를 넣는다

				System.out.println("자리에 연결완료");
				start02 = false;

				// 유저 아이디, 시간을 화면에 출력
				viewPC2(userid, usertime);

			} else {
				JOptionPane.showMessageDialog(null, "잔여시간이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				// 시작 버튼 활성화
				btnPC2ON.setEnabled(true);
			}
		} else {
			JOptionPane.showMessageDialog(null, "회원 정보가 일치하지 않습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 시작 버튼 활성화
			btnPC2ON.setEnabled(true);
		}
	}

	private void loginPC3() { // 시작 버튼을 누르면 실행되는 함수.

		userdao = UserDAOImple.getInstance();
		// 입력값을 userid로 받아옴
		String userid = JOptionPane.showInputDialog(null, "아이디를 입력하세요:", "로그인", JOptionPane.QUESTION_MESSAGE);

		System.out.println("pc 3번에 입력받은 Id :" + userid);

		// 사용자가 취소를 누르거나 아이디를 입력하지 않은 경우, 에러 메시지를 띄움
		if (userid == null || userid.isEmpty()) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);

			// 다시 시작 버튼을 활성화함
			btnPC3ON.setEnabled(true);
			return;
		}

		// 비밀번호 입력 대화상자 표시
		String userpw = JOptionPane.showInputDialog(null, "비밀번호를 입력하세요:", "로그인", JOptionPane.QUESTION_MESSAGE);
		System.out.println("pc 3번에 입력받은 PW :" + userpw);

		// 사용자가 취소를 누른 경우
		if (userpw == null) {
			JOptionPane.showMessageDialog(null, "로그인이 취소되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 다시 시작버튼을 활성화
			btnPC3ON.setEnabled(true);
			return;
		}

		// 입력된 아이디와 비밀번호 출력

		// 입력된 아이디와 비밀번호가 회원가입이 되어있는지 checkuser

		int result = userdao.checkuser(userid, userpw);

		if (result == 1) {
			seatdao = SeatDAOImple.getInstance();

			JOptionPane.showMessageDialog(null, "환영합니다 : " + userid + "님 ", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);

			System.out.println("회원입니다");

			long usertime = userdao.selectTime(userid).getUsertime();
			if (usertime != 0) { // 로그인한 회원이 PC충전이 되어있는지 selectTime를 써서 DB에 저장된 시간을 불러움
				System.out.println("시간이 남아있음");
				UserVO vo = userdao.selectInfo(userid);

				// 몇 번 자리에 로그인을 시도했는지, n에 PC자리를 넣어준다
				String pcNumber = Character.toString(lblPC3.getText().charAt(0));
				int n = Integer.parseInt(pcNumber);

				System.out.println(n + "번 자리에서 PC 사용 시작");
				seatdao.loginSeat(vo, n); // 회원 아이디,비번을 이용해서 자리에 로그인. , SEAT_INFO에 회원정보를 넣는다

				System.out.println("자리에 연결완료");
				start03 = false;


				// 유저 아이디, 시간을 화면에 출력
				viewPC3(userid, usertime);

			} else {
				JOptionPane.showMessageDialog(null, "잔여시간이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				// 시작 버튼 활성화
				btnPC3ON.setEnabled(true);
			}
		} else {
			JOptionPane.showMessageDialog(null, "회원 정보가 일치하지 않습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 시작 버튼 활성화
			btnPC3ON.setEnabled(true);
		}
	}

	private void loginPC4() { // 시작 버튼을 누르면 실행되는 함수.

		userdao = UserDAOImple.getInstance();
		// 입력값을 userid로 받아옴
		String userid = JOptionPane.showInputDialog(null, "아이디를 입력하세요:", "로그인", JOptionPane.QUESTION_MESSAGE);

		System.out.println("pc 4번에 입력받은 Id :" + userid);

		// 사용자가 취소를 누르거나 아이디를 입력하지 않은 경우, 에러 메시지를 띄움
		if (userid == null || userid.isEmpty()) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);

			// 다시 시작 버튼을 활성화함
			btnPC4ON.setEnabled(true);
			return;
		}

		// 비밀번호 입력 대화상자 표시
		String userpw = JOptionPane.showInputDialog(null, "비밀번호를 입력하세요:", "로그인", JOptionPane.QUESTION_MESSAGE);
		System.out.println("pc 4번에 입력받은 PW :" + userpw);

		// 사용자가 취소를 누른 경우
		if (userpw == null) {
			JOptionPane.showMessageDialog(null, "로그인이 취소되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 다시 시작버튼을 활성화
			btnPC4ON.setEnabled(true);
			return;
		}

		// 입력된 아이디와 비밀번호 출력

		// 입력된 아이디와 비밀번호가 회원가입이 되어있는지 checkuser

		int result = userdao.checkuser(userid, userpw);

		if (result == 1) {
			seatdao = SeatDAOImple.getInstance();

			JOptionPane.showMessageDialog(null, "환영합니다 : " + userid + "님 ", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);

			System.out.println("회원입니다");

			long usertime = userdao.selectTime(userid).getUsertime();
			if (usertime != 0) { // 로그인한 회원이 PC충전이 되어있는지 selectTime를 써서 DB에 저장된 시간을 불러움
				System.out.println("시간이 남아있음");
				UserVO vo = userdao.selectInfo(userid);

				// 몇 번 자리에 로그인을 시도했는지, n에 PC자리를 넣어준다
				String pcNumber = Character.toString(lblPC4.getText().charAt(0));
				int n = Integer.parseInt(pcNumber);

				System.out.println(n + "번 자리에서 PC 사용 시작");
				seatdao.loginSeat(vo, n); // 회원 아이디,비번을 이용해서 자리에 로그인. , SEAT_INFO에 회원정보를 넣는다

				System.out.println("자리에 연결완료");
				start05 = false;


				// 유저 아이디, 시간을 화면에 출력
				viewPC4(userid, usertime);

			} else {
				JOptionPane.showMessageDialog(null, "잔여시간이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				// 시작 버튼 활성화
				btnPC4ON.setEnabled(true);
			}
		} else {
			JOptionPane.showMessageDialog(null, "회원 정보가 일치하지 않습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 시작 버튼 활성화
			btnPC4ON.setEnabled(true);
		}
	}

	private void loginPC5() { // 시작 버튼을 누르면 실행되는 함수.

		userdao = UserDAOImple.getInstance();
		// 입력값을 userid로 받아옴
		String userid = JOptionPane.showInputDialog(null, "아이디를 입력하세요:", "로그인", JOptionPane.QUESTION_MESSAGE);

		System.out.println("pc 5번에 입력받은 Id :" + userid);

		// 사용자가 취소를 누르거나 아이디를 입력하지 않은 경우, 에러 메시지를 띄움
		if (userid == null || userid.isEmpty()) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);

			// 다시 시작 버튼을 활성화함
			btnPC5ON.setEnabled(true);
			return;
		}

		// 비밀번호 입력 대화상자 표시
		String userpw = JOptionPane.showInputDialog(null, "비밀번호를 입력하세요:", "로그인", JOptionPane.QUESTION_MESSAGE);
		System.out.println("pc 5번에 입력받은 PW :" + userpw);

		// 사용자가 취소를 누른 경우
		if (userpw == null) {
			JOptionPane.showMessageDialog(null, "로그인이 취소되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 다시 시작버튼을 활성화
			btnPC5ON.setEnabled(true);
			return;
		}

		// 입력된 아이디와 비밀번호 출력

		// 입력된 아이디와 비밀번호가 회원가입이 되어있는지 checkuser

		int result = userdao.checkuser(userid, userpw);

		if (result == 1) {
			seatdao = SeatDAOImple.getInstance();

			JOptionPane.showMessageDialog(null, "환영합니다 : " + userid + "님 ", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);

			System.out.println("회원입니다");

			long usertime = userdao.selectTime(userid).getUsertime();
			if (usertime != 0) { // 로그인한 회원이 PC충전이 되어있는지 selectTime를 써서 DB에 저장된 시간을 불러움
				System.out.println("시간이 남아있음");
				UserVO vo = userdao.selectInfo(userid);

				// 몇 번 자리에 로그인을 시도했는지, n에 PC자리를 넣어준다
				String pcNumber = Character.toString(lblPC5.getText().charAt(0));
				int n = Integer.parseInt(pcNumber);

				System.out.println(n + "번 자리에서 PC 사용 시작");
				seatdao.loginSeat(vo, n); // 회원 아이디,비번을 이용해서 자리에 로그인. , SEAT_INFO에 회원정보를 넣는다

				System.out.println("자리에 연결완료");
				start06 = false;


				// 유저 아이디, 시간을 화면에 출력
				viewPC5(userid, usertime);

			} else {
				JOptionPane.showMessageDialog(null, "잔여시간이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				// 시작 버튼 활성화
				btnPC5ON.setEnabled(true);
			}
		} else {
			JOptionPane.showMessageDialog(null, "회원 정보가 일치하지 않습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 시작 버튼 활성화
			btnPC5ON.setEnabled(true);
		}
	}

	private void loginPC6() { // 시작 버튼을 누르면 실행되는 함수.

		userdao = UserDAOImple.getInstance();
		// 입력값을 userid로 받아옴
		String userid = JOptionPane.showInputDialog(null, "아이디를 입력하세요:", "로그인", JOptionPane.QUESTION_MESSAGE);

		System.out.println("pc 6번에 입력받은 Id :" + userid);

		// 사용자가 취소를 누르거나 아이디를 입력하지 않은 경우, 에러 메시지를 띄움
		if (userid == null || userid.isEmpty()) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);

			// 다시 시작 버튼을 활성화함
			btnPC6ON.setEnabled(true);
			return;
		}

		// 비밀번호 입력 대화상자 표시
		String userpw = JOptionPane.showInputDialog(null, "비밀번호를 입력하세요:", "로그인", JOptionPane.QUESTION_MESSAGE);
		System.out.println("pc 6번에 입력받은 PW :" + userpw);

		// 사용자가 취소를 누른 경우
		if (userpw == null) {
			JOptionPane.showMessageDialog(null, "로그인이 취소되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 다시 시작버튼을 활성화
			btnPC6ON.setEnabled(true);
			return;
		}

		// 입력된 아이디와 비밀번호 출력

		// 입력된 아이디와 비밀번호가 회원가입이 되어있는지 checkuser

		int result = userdao.checkuser(userid, userpw);

		if (result == 1) {
			seatdao = SeatDAOImple.getInstance();

			JOptionPane.showMessageDialog(null, "환영합니다 : " + userid + "님 ", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);

			System.out.println("회원입니다");

			long usertime = userdao.selectTime(userid).getUsertime();
			if (usertime != 0) { // 로그인한 회원이 PC충전이 되어있는지 selectTime를 써서 DB에 저장된 시간을 불러움
				System.out.println("시간이 남아있음");
				UserVO vo = userdao.selectInfo(userid);

				// 몇 번 자리에 로그인을 시도했는지, n에 PC자리를 넣어준다
				String pcNumber = Character.toString(lblPC6.getText().charAt(0));
				int n = Integer.parseInt(pcNumber);

				System.out.println(n + "번 자리에서 PC 사용 시작");
				seatdao.loginSeat(vo, n); // 회원 아이디,비번을 이용해서 자리에 로그인. , SEAT_INFO에 회원정보를 넣는다

				System.out.println("자리에 연결완료");

				// 유저 아이디, 시간을 화면에 출력
				viewPC6(userid, usertime);

			} else {
				JOptionPane.showMessageDialog(null, "잔여시간이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				// 시작 버튼 활성화
				btnPC6ON.setEnabled(true);
			}
		} else {
			JOptionPane.showMessageDialog(null, "회원 정보가 일치하지 않습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 시작 버튼 활성화
			btnPC6ON.setEnabled(true);
		}
	}

	Timer timer01 = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			textPC1.setText("PC1번 :  사용가능 \n " + "시작 버튼을 눌러 \n 사용을 시작해주세요");

		}
	});

	Timer timer02 = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			textPC2.setText("PC2번 :  사용가능 \n " + "시작 버튼을 눌러 \n 사용을 시작해주세요");

		}
	});

	Timer timer03 = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			textPC3.setText("PC3번 :  사용가능 \n " + "시작 버튼을 눌러 \n 사용을 시작해주세요");

		}
	});

	Timer timer04 = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			textPC4.setText("PC4번 :  사용가능 \n " + "시작 버튼을 눌러 \n 사용을 시작해주세요");

		}
	});

	Timer timer05 = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			textPC5.setText("PC5번 :  사용가능 \n " + "시작 버튼을 눌러 \n 사용을 시작해주세요");

		}
	});

	Timer timer06 = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			textPC6.setText("PC6번 :  사용가능 \n " + "시작 버튼을 눌러 \n 사용을 시작해주세요");

		}
	});



} // end Seat