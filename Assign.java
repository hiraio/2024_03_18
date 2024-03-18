package edu.java.side;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Assign extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtPw;
	private JTextField txtName;
	private JLabel lblresult, lblresultID;

	public UserDAO userdao = null;
	public SeatDAO seatdao = null;

	/**
	 * Create the frame.
	 */
	public Assign() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUserID = new JLabel(" 아이디 입력");
		lblUserID.setBackground(Color.LIGHT_GRAY);
		lblUserID.setOpaque(true);
		lblUserID.setBounds(31, 42, 80, 33);
		contentPane.add(lblUserID);

		JLabel lblUserPW = new JLabel("비밀번호 입력");
		lblUserPW.setOpaque(true);
		lblUserPW.setBackground(Color.LIGHT_GRAY);
		lblUserPW.setBounds(31, 101, 80, 33);
		contentPane.add(lblUserPW);

		txtId = new JTextField();
		txtId.setBounds(136, 42, 192, 32);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtPw = new JTextField();
		txtPw.setColumns(10);
		txtPw.setBounds(136, 101, 192, 32);
		contentPane.add(txtPw);

		JButton btnInsert = new JButton("가입하기");
		btnInsert.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

			}
		});
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertUser();
			}

		});
		btnInsert.setBounds(31, 224, 118, 33);
		contentPane.add(btnInsert);

		JLabel lblUserName = new JLabel("이름 입력");
		lblUserName.setOpaque(true);
		lblUserName.setBackground(Color.LIGHT_GRAY);
		lblUserName.setBounds(31, 159, 80, 33);
		contentPane.add(lblUserName);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(136, 159, 192, 32);
		contentPane.add(txtName);

		lblresult = new JLabel();
		lblresult.setBounds(36, 312, 267, 15);
		contentPane.add(lblresult);

		lblresultID = new JLabel();
		lblresultID.setBounds(136, 76, 192, 15);
		contentPane.add(lblresultID);

		JButton btngoMain = new JButton("메인화면");
		btngoMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PC_Main window = new PC_Main();
				window.frame.setVisible(true);
				setVisible(false);

			}
		});
		btngoMain.setBounds(358, 294, 97, 23);
		contentPane.add(btngoMain);

		JButton btnCheckID = new JButton("아이디 중복확인");
		btnCheckID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCheckID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				isDuplicate();
			}
		});
		btnCheckID.setBounds(340, 47, 115, 23);
		contentPane.add(btnCheckID);

	}

	private void InsertUser() {

		userdao = UserDAOImple.getInstance();
		String userId = txtId.getText();
		String userPw = txtPw.getText();
		String userName = txtName.getText();

		if (userId == null || userId.isEmpty()) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
		} else {

			UserVO vo = new UserVO(userId, userPw, userName, 0);
			int check = userdao.isDuplicate(txtId.getText());
			if (check != 1) {
				int result = userdao.insertUser(vo);

				if (result == 1) {
					lblresult.setText("회원가입 성공입니다. 환영합니다");

				} else {
					lblresult.setText("회원가입을 다시 시도 하세요 ");
				}
			} else {
				lblresult.setText("회원가입을 다시 시도 하세요 ");
			}
		}
	}

	private void isDuplicate() {

		userdao = UserDAOImple.getInstance();
		String userid = txtId.getText();
		System.out.println(userid);
		int result = userdao.isDuplicate(userid);
		System.out.println(result);
		if (result == 1) {
			lblresultID.setText("아이디 중복");
		} else {
			lblresultID.setText("아이디 사용가능");
		}

	}

}
