package guest_Book_pack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;

class LoginFrame extends JFrame{
	private JLabel id,pw;
	private JTextField idText;
	private JPasswordField passText;
	private JButton loginB,signUp;
	private ActionListener logA = new loginAction();
	String ID="",PASS="",userID="";
	private Container content; 
	private BufferedReader loginFin = null;
	private BufferedReader idFin = null;
	private int exist=-1;
	private LoginFrame temp;
	
	LoginFrame()
	{
		setTitle("Guest Book Login");
		setSize(400,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		content = getContentPane();
		content.setBackground(Color.DARK_GRAY);
		content.setLayout(null);
		
		id = new JLabel("아이디 : ");
		id.setLocation(50,150);
		id.setSize(100,40);
		id.setFont(new Font("Serif",Font.BOLD,11));
		id.setForeground(Color.WHITE);
		id.setFont(id.getFont().deriveFont(15.0f));
		add(id);
		
		pw = new JLabel("비밀번호 : ");
		pw.setLocation(50,200);
		pw.setSize(100,40);
		pw.setFont(new Font("Serif",Font.BOLD,11));
		pw.setForeground(Color.WHITE);
		pw.setFont(pw.getFont().deriveFont(15.0f));
		add(pw);
		
		idText = new JTextField();
		idText.setLocation(150,150);
		idText.setSize(200,40);
		idText.setFont(new Font("Serif",Font.BOLD,11));
		idText.setFont(idText.getFont().deriveFont(15.0f));
		add(idText);
		
		passText = new JPasswordField();
		passText.setLocation(150,200);
		passText.setSize(200,40);
		passText.setFont(new Font("Serif",Font.BOLD,11));
		passText.setFont(passText.getFont().deriveFont(15.0f));
		add(passText);
		
		
		
		loginB = new JButton("로그인");
		loginB.setLocation(125,350);
		loginB.setSize(150,40);
		loginB.setFont(loginB.getFont().deriveFont(16.0f));
		loginB.setForeground(Color.DARK_GRAY);
		loginB.setBackground(Color.LIGHT_GRAY);
		loginB.addActionListener(logA);
		add(loginB);
		
		signUp = new JButton("회원가입");
		signUp.setLocation(125,425);
		signUp.setSize(150,40);
		signUp.setFont(signUp.getFont().deriveFont(16.0f));
		signUp.setForeground(Color.DARK_GRAY);
		signUp.setBackground(Color.LIGHT_GRAY);
		signUp.addActionListener(logA);
		add(signUp);
		temp = this;
		
		setVisible(true);
		
	}
	
	class loginAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == loginB) {
				try {
					idFin = new BufferedReader( new FileReader("./Saves/login/idKey.txt"));
				
				
					while((ID = idFin.readLine())!=null) {
						if(ID.equals(String.valueOf(idText.getText())))
						{
							exist = 0;
							break;
						}
					}
				
				idFin.close();
				if(exist==0)
				{
					
					exist=-1;
					loginFin = new BufferedReader(new FileReader("./Saves/login/login.txt"));
					while((userID = loginFin.readLine())!=null) {
						if(userID.startsWith(ID)) {
							exist = 0;
							break;
						}
					}
					if(exist==0)
					{
						String [] PASS = userID.split(" ");
						if(PASS[1].equals(String.valueOf(passText.getText())))
						{
							postFrame post = new postFrame(userID);
							temp.dispose();
						}
						else {
							JOptionPane.showMessageDialog(content,"비밀번호가 다릅니다.","Password warning",JOptionPane.WARNING_MESSAGE);
							exist=-1;
						}
					}

				}
				else {
					JOptionPane.showMessageDialog(content,"아이디가 존재하지 않습니다.","ID warning",JOptionPane.WARNING_MESSAGE);
				}
			}
				
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else if(e.getSource()==signUp) {
				signUpFrame sign= new signUpFrame();
			
			}
		}
	}
}


class signUpFrame extends JFrame{
	private JLabel id,password,pass_Check, name, nickName,e_mail,number,minus1,minus2,e_symbol; 
	private JTextField idText, nameText,nickNameText,emailText,numberText1,numberText2,numberText3;
	private JPasswordField passText,pass_CheckText;
	private Container content;
	private String email[] = {"naver.com", "jbnu.ac.kr","gmail.com","daum.net"};
	private JComboBox<String> e_choice;
	private JButton accept,cancle,overCheck;
	private signAction sA1 = new signAction();
	private signUpFrame temp;
	private boolean over = true;
	private BufferedWriter loginFout = null;
	private BufferedReader idFin = null;
	
	signUpFrame(){
		setTitle("SignUp is Free!");
		setSize(400,600);
		setLayout(null);
		Dimension frameSize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - frameSize.width) /2 -450, (screenSize.height - frameSize.height) /2-100);
		
		content = getContentPane();
		content.setBackground(Color.DARK_GRAY);

		id = new JLabel("아이디 : ");
		id.setLocation(20,50);
		id.setSize(150,30);
		id.setFont(id.getFont().deriveFont(16.0f));
		id.setForeground(Color.WHITE);
		add(id);
		
		overCheck = new JButton("중복체크");
		overCheck.setLocation(280,50);
		overCheck.setSize(90,30);
		overCheck.setFont(new Font("Serif",Font.BOLD,11));
		overCheck.setFont(overCheck.getFont().deriveFont(13.0f));
		overCheck.setForeground(Color.DARK_GRAY);
		overCheck.setBackground(Color.LIGHT_GRAY);
		overCheck.addActionListener(sA1);
		add(overCheck);
		
		password = new JLabel("비밀번호 : ");
		password.setLocation(20,100);
		password.setSize(150,30);
		password.setFont(password.getFont().deriveFont(16.0f));
		password.setForeground(Color.WHITE);
		add(password);
		
		pass_Check = new JLabel("비번 확인 : ");
		pass_Check.setLocation(20,150);
		pass_Check.setSize(150,30);
		pass_Check.setFont(pass_Check.getFont().deriveFont(16.0f));
		pass_Check.setForeground(Color.WHITE);
		add(pass_Check);
		
		name = new JLabel("이름 : ");
		name.setLocation(20,200);
		name.setSize(150,30);
		name.setFont(name.getFont().deriveFont(16.0f));
		name.setForeground(Color.WHITE);
		add(name);
		
		nickName = new JLabel("별명 : ");
		nickName.setLocation(20,250);
		nickName.setSize(150,30);
		nickName.setFont(nickName.getFont().deriveFont(16.0f));
		nickName.setForeground(Color.WHITE);
		add(nickName);
		
		number = new JLabel("전화 번호 : ");
		minus1 = new JLabel("-");
		minus1.setLocation(190,300);
		minus1.setSize(150,30);
		minus1.setFont(number.getFont().deriveFont(16.0f));
		minus1.setForeground(Color.WHITE);
		minus2 = new JLabel("-");
		minus2.setLocation(275, 300);
		minus2.setSize(150,30);
		minus2.setFont(number.getFont().deriveFont(16.0f));
		minus2.setForeground(Color.WHITE);
		number.setLocation(20,300);
		number.setSize(150,30);
		number.setFont(number.getFont().deriveFont(16.0f));
		number.setForeground(Color.WHITE);
		add(number);
		add(minus1);
		add(minus2);
		
		e_mail = new JLabel("이메일 : ");
		e_symbol = new JLabel("@");
		e_mail.setLocation(20,350);
		e_mail.setSize(150,30);
		e_mail.setFont(e_mail.getFont().deriveFont(16.0f));
		e_mail.setForeground(Color.WHITE);
		e_symbol.setLocation(220,350);
		e_symbol.setSize(150,30);
		e_symbol.setFont(e_symbol.getFont().deriveFont(16.0f));
		e_symbol.setForeground(Color.WHITE);
		add(e_mail);
		add(e_symbol);
		
		idText=new JTextField();
		idText.setBounds(120,50,150,30);
		idText.setFont(new Font("Serif",Font.BOLD,11));
		idText.setFont(idText.getFont().deriveFont(15.0f));
		add(idText);
		
		passText=new JPasswordField();
		passText.setBounds(120,100,250,30);
		passText.setFont(new Font("Serif",Font.BOLD,11));
		passText.setFont(passText.getFont().deriveFont(15.0f));
		add(passText);
		
		pass_CheckText=new JPasswordField();
		pass_CheckText.setBounds(120,150,250,30);
		pass_CheckText.setFont(new Font("Serif",Font.BOLD,11));
		pass_CheckText.setFont(pass_CheckText.getFont().deriveFont(15.0f));
		add(pass_CheckText);
		
		nameText=new JTextField();
		nameText.setBounds(120,200,250,30); 
		nameText.setFont(new Font("Serif",Font.BOLD,11));
		nameText.setFont(nameText.getFont().deriveFont(15.0f));
		add(nameText);
		
		nickNameText=new JTextField();
		nickNameText.setBounds(120,250,250,30); 
		nickNameText.setFont(new Font("Serif",Font.BOLD,11));
		nickNameText.setFont(nickNameText.getFont().deriveFont(15.0f));
		add(nickNameText);
		
		numberText1=new JTextField();
		numberText1.setBounds(120,300,60,30); 
		numberText1.setFont(new Font("Serif",Font.BOLD,11));
		numberText1.setFont(numberText1.getFont().deriveFont(15.0f));
		add(numberText1);
		numberText2=new JTextField();
		numberText2.setBounds(205,300,60,30); 
		numberText2.setFont(new Font("Serif",Font.BOLD,11));
		numberText2.setFont(numberText2.getFont().deriveFont(15.0f));
		add(numberText2);
		numberText3=new JTextField();
		numberText3.setBounds(290,300,60,30); 
		numberText3.setFont(new Font("Serif",Font.BOLD,11));
		numberText3.setFont(numberText3.getFont().deriveFont(15.0f));
		add(numberText3);
		
		emailText=new JTextField();
		emailText.setBounds(120,350,90,30); 
		emailText.setFont(new Font("Serif",Font.BOLD,11));
		emailText.setFont(emailText.getFont().deriveFont(15.0f));
		add(emailText);
		
		e_choice = new JComboBox<String>(email);
		e_choice.setBounds(250,350,120,30);
		add(e_choice);
		
		accept = new JButton("확  인");
		accept.setLocation(50,425);
		accept.setSize(130,40);
		accept.setFont(new Font("Serif",Font.BOLD,11));
		accept.setFont(accept.getFont().deriveFont(17.0f));
		accept.setForeground(Color.DARK_GRAY);
		accept.setBackground(Color.LIGHT_GRAY);
		accept.addActionListener(sA1);
		add(accept);
		
		cancle = new JButton("취  소");
		cancle.setLocation(220,425);
		cancle.setSize(130,40);
		cancle.setFont(new Font("Serif",Font.BOLD,11));
		cancle.setFont(cancle.getFont().deriveFont(17.0f));
		cancle.setForeground(Color.DARK_GRAY);
		cancle.setBackground(Color.LIGHT_GRAY);
		cancle.addActionListener(sA1);
		add(cancle);
		
		temp = this;
		
		setVisible(true);
		
		
	}
	
	class signAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == accept) {
				
				if(over == true)
				{
					JOptionPane.showMessageDialog(content,"중복 검사를 완료해주세요.","Overlapping please",JOptionPane.WARNING_MESSAGE);
				}
				else if(!String.valueOf(passText.getText()).equals(String.valueOf(pass_CheckText.getText())))
				{
					JOptionPane.showMessageDialog(content,"비밀번호와 비밀번호 확인이 일치하지 않습니다.","Password error",JOptionPane.WARNING_MESSAGE);
				}
				else if(String.valueOf(nameText.getText()).equals(""))
				{
					JOptionPane.showMessageDialog(content,"이름을 입력해주세요.","Name error",JOptionPane.WARNING_MESSAGE);
				}
				else if(String.valueOf(nickNameText.getText()).equals(""))
				{
					JOptionPane.showMessageDialog(content,"닉네임을 입력해주세요.","Nickname error",JOptionPane.WARNING_MESSAGE);
				}
				else if(String.valueOf(numberText1.getText()).equals("")||String.valueOf(numberText3.getText()).equals("")||String.valueOf(numberText2.getText()).equals(""))
				{
					JOptionPane.showMessageDialog(content,"번호를 입력해주세요.","Number error",JOptionPane.WARNING_MESSAGE);
				}
				else if(String.valueOf(emailText.getText()).equals(""))
				{
					JOptionPane.showMessageDialog(content,"이메일을 입력해주세요.","E-mail error",JOptionPane.WARNING_MESSAGE);
				}
				else {
					try {
						writeSignUp(loginFout);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					temp.dispose();
				}
			}
			else if(e.getSource()==cancle) {
				temp.dispose();
				
			}
			else if(e.getSource()==overCheck) {
				try {
				if(checkOver(idFin,String.valueOf(idText.getText()))==-1)
				{
					JOptionPane.showMessageDialog(content,"아이디가 중복 됩니다.","Overlapping error",JOptionPane.WARNING_MESSAGE);
					over = true;
				}
				else
				{
					JOptionPane.showMessageDialog(content,"아이디가 사용 가능합니다.","Not Overlapping",JOptionPane.INFORMATION_MESSAGE);
					over = false;
				}
				}
				catch(IOException e1){
					JOptionPane.showMessageDialog(content,"파일 오류","File error",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	int checkOver(BufferedReader idFin,String overId) throws IOException {
		String id=null;
		idFin =new BufferedReader( new FileReader("./Saves/login/idKey.txt"));
		while((id = idFin.readLine())!=null) {
			
			if(overId.equals(id))
			{
				idFin.close();
				return -1;
			}
		}
		
		idFin.close();
		return 0;
	}
	
	void writeSignUp(BufferedWriter loginFout) throws IOException
	{
		
		loginFout =new BufferedWriter( new FileWriter("./Saves/login/login.txt",true));
		PrintWriter loginFoutP = new PrintWriter(loginFout,true);
		
		Person signP = new Person();
		String num1,num2,num3,saves="";
		signP.id = String.valueOf(idText.getText());
		signP.pass = String.valueOf(passText.getText());
		signP.name = String.valueOf(nameText.getText());
		signP.nickName=String.valueOf(nickNameText.getText());
		num1 = String.valueOf(numberText1.getText());
		num2 = String.valueOf(numberText2.getText());
		num3 = String.valueOf(numberText3.getText());
			
		signP.number=signP.number.concat(num1);
		signP.number=signP.number.concat("-");
		signP.number=signP.number.concat(num2);
		signP.number=signP.number.concat("-");
		signP.number=signP.number.concat(num3);
		
		
		signP.e_mail = String.valueOf(emailText.getText());
		signP.e_mail =signP.e_mail.concat("@");
		signP.e_mail =signP.e_mail.concat(e_choice.getSelectedItem().toString());
		
		saves = saves.concat(signP.id);
		saves = saves.concat(" ");
		saves = saves.concat(signP.pass);
		saves = saves.concat(" ");
		saves = saves.concat(signP.name);
		saves = saves.concat(" ");
		saves = saves.concat(signP.nickName);
		saves = saves.concat(" ");
		saves = saves.concat(signP.number);
		saves = saves.concat(" ");
		saves = saves.concat(signP.e_mail);

		loginFoutP.write(saves+"\n");
		idKeyWrite(signP.id);
		
		loginFoutP.close();
	}
	
	void idKeyWrite(String id) throws IOException{
		BufferedWriter idFout =new BufferedWriter( new FileWriter("./Saves/login/idKey.txt",true));
		PrintWriter idFoutP = new PrintWriter(idFout,true);
		idFoutP.write(id+"\n");
		idFoutP.close();
	}

}


class Person{
	String id;
	String pass;
	String name;
	String nickName;
	String e_mail;
	String number="";
	
	int postNum=0;
	int commentNum=0;
	int RRN;
	
	
}

class postFrame extends JFrame{
	private Person user;
	private Container content; 
	private File todayPost;
	
	postFrame(String str){
		user = new Person();
		user = insertPerson(str);
		setTitle("방명록");
		setSize(1000,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		content = getContentPane();
		content.setBackground(Color.DARK_GRAY);
		content.setLayout(null);
		setVisible(true);
		
		
		try {
			todayPost = new File("score.txt");
			todayPost.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	Person insertPerson(String str)
	{
		String [] strTemp = str.split(" ");
		Person user = new Person();
		user.id = strTemp[0];
		user.pass = strTemp[1];
		user.name = strTemp[2];
		user.nickName = strTemp[3];
		user.number = strTemp[4];
		user.e_mail = strTemp[5];
	
	
		return user;
	}
	
}

public class guestBook{
	
	
	public static void main(String argv[])
	{
		
		LoginFrame lo = new LoginFrame();
	}
	
	
}
