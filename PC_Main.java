package edu.java.side;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;


public class PC_Main {

	public static UserDAO userdao = null;
	public static SeatDAO seatdao = null;
	public static SeatVO vo;
	
	private JLabel lblresult, lblAreaID,lblAreaTime;

	JFrame frame;
	private JTextField txtUserID;
	private JTextField txtTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PC_Main window = new PC_Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		seatdao = SeatDAOImple.getInstance();
		userdao = UserDAOImple.getInstance();

	}

	/**
	 * Create the application.
	 */
	public PC_Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 522, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnassign = new JButton("회원가입");
		btnassign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnassign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Assign Abutton = new Assign();
				Abutton.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnassign.setBounds(73, 327, 97, 23);
		frame.getContentPane().add(btnassign);

		JLabel lblTop = new JLabel("환영합니다. JUN PC방입니다");
		lblTop.setFont(new Font("궁서체", Font.PLAIN, 22));
		lblTop.setBounds(99, 21, 353, 39);
		frame.getContentPane().add(lblTop);

		JLabel lblSearchId = new JLabel("아이디 검색");
		lblSearchId.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblSearchId.setBounds(41, 80, 129, 59);
		frame.getContentPane().add(lblSearchId);

		txtUserID = new JTextField();
		txtUserID.setBounds(177, 95, 199, 39);
		frame.getContentPane().add(txtUserID);
		txtUserID.setColumns(10);

		JButton btnSearch = new JButton("검색");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectID();
			}

		});
		btnSearch.setFont(new Font("굴림", Font.PLAIN, 20));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(388, 93, 97, 38);
		frame.getContentPane().add(btnSearch);

		JButton btnSeatView = new JButton("좌석 보기");
		btnSeatView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 

				Seat Sbutton = new Seat();
				Sbutton.setVisible(true); // SEAT UI가 보임
				frame.setVisible(false); // PC_MAIN UI가 사라짐
			}

		});
	
		btnSeatView.setBounds(217, 327, 103, 23);
		frame.getContentPane().add(btnSeatView);

		JLabel lblTimecharge = new JLabel("시간 충전");
		lblTimecharge.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblTimecharge.setBounds(41, 194, 129, 59);
		frame.getContentPane().add(lblTimecharge);

		txtTime = new JTextField();
		txtTime.setColumns(10);
		txtTime.setBounds(150, 209, 84, 39);
		frame.getContentPane().add(txtTime);

		JButton btnCharge = new JButton("충전");
		btnCharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 입력된 시간값이 사용자 ID DB에 저장
				timecharge();
			}
		});
		btnCharge.setFont(new Font("굴림", Font.PLAIN, 20));
		btnCharge.setBounds(246, 207, 97, 38);
		frame.getContentPane().add(btnCharge);

		lblresult = new JLabel();
		lblresult.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblresult.setBounds(51, 258, 256, 23);
		frame.getContentPane().add(lblresult);

		lblAreaID = new JLabel();
		lblAreaID.setFont(new Font("굴림", Font.PLAIN, 14));
		lblAreaID.setBounds(177, 144, 199, 23);
		frame.getContentPane().add(lblAreaID);
		
		lblAreaTime = new JLabel();
		lblAreaTime.setFont(new Font("굴림", Font.PLAIN, 14));
		lblAreaTime.setBounds(177, 176, 199, 23);
		frame.getContentPane().add(lblAreaTime);

	}

	private void timecharge() {
		// 아이디를 입력한 경우
		if (txtTime.getText() != null && !txtTime.getText().isEmpty() && txtUserID.getText() != null
				&& !txtUserID.getText().isEmpty()) {
			try {
			long usertime = Long.parseLong(txtTime.getText());
			
			String userId = txtUserID.getText();
		
			// 입력한 아이디에 시간을 충전한다
			int result = userdao.updateTime(usertime, userId); 
			
			if (result == 1) {
				lblresult.setText(" 충전이 완료 되었습니다 ");
			} else {
				lblresult.setText("다시 시도하세요");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "숫자를 입력하세요!", "에러", JOptionPane.ERROR_MESSAGE);
			
		}
	} 
	}

	private void selectID() {

		String userId = txtUserID.getText();
		int result = userdao.select(userId);

		if (result == 1) {
			//회원가입된 아이디인지 확인
			UserVO vo = userdao.selectTime(userId);
			
			lblAreaID.setText(vo.getUserId() + "님 안녕하세요");
			lblAreaTime.setText("잔여 시간은 " + vo.getUsertime() + "분 입니다");
			
		} else {
			lblAreaID.setText("회원가입을 진행해주세요");
		}

	}
}