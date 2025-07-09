package ��ѧ����ϵͳ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.lang.String;
	
public class Login extends JFrame implements ActionListener {
private static final long serialVersionUID = 5252772035964963789L;
private JPanel pan = new JPanel();
private JLabel accountlab = new JLabel("�˺�");
private JLabel passlab = new JLabel("����");
private JTextField accounttext = new JTextField();
private JPasswordField passtext = new JPasswordField();
public JButton denglu = new JButton("��¼");
public JButton chongzhi = new JButton("����");
private JLabel title;
private JLabel background;
//ImageIcon image;
public static int level;//��̬������Ȩ�޵ȼ�������չʾ��ͬ�����Ӱ�飬����SystemUI����
public static String NO;//��̬���������������û�����ɾ�Ĳ�ķ�Χ
public Login(){
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/У��.jpg"));
		super.setTitle("���ϿƼ���ѧ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		super.setSize(600,500);
		pan.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pan);
		pan.setLayout(null);
		this.setLocationRelativeTo(null);
		
		accountlab.setForeground(Color.white);
		accountlab.setFont(new Font("����", Font.BOLD, 22));
		accountlab.setBounds(160,170,60,35);
		pan.add(accountlab);
		
		passlab.setForeground(Color.white);
		passlab.setFont(new Font("����", Font.BOLD, 22));
		passlab.setBounds(160,230,60,35);
		pan.add(passlab);
		
		accounttext.setFont(new Font("Times New Roman", Font.BOLD, 22));
		accounttext.setBounds(230,170,160,35);
		pan.add(accounttext);
		accounttext.setColumns(10);
		
		passtext.setFont(new Font("Times New Roman", Font.BOLD, 22));
		passtext.setBounds(230,230,160,35);
		pan.add(passtext);
		passtext.setColumns(10);
		
		JRadioButton stu = new JRadioButton("ѧ��");
		stu.setForeground(Color.black);
		stu.setFont(new Font("����", Font.BOLD, 17));
		stu.setBounds(170, 110, 65, 40);
		stu.setContentAreaFilled(false);
		pan.add(stu);
		
		JRadioButton tea = new JRadioButton("��ʦ");
		tea.setForeground(Color.black);
		tea.setFont(new Font("����", Font.BOLD, 17));
		tea.setBounds(240, 110, 65, 40);
		tea.setContentAreaFilled(false);
		pan.add(tea);
		
		JRadioButton staff = new JRadioButton("������Ա");
		staff.setForeground(Color.black);
		staff.setFont(new Font("����", Font.BOLD, 17));
		staff.setBounds(310, 110, 100, 40);
		staff.setContentAreaFilled(false);//��ť͸����
		pan.add(staff);
		
		denglu.setForeground(Color.black);
		denglu.setFont(new Font("����", Font.BOLD, 20));
		denglu.setBounds(160,300,90,35);//227, 355, 127, 49
		//denglu.setContentAreaFilled(false);
		pan.add(denglu);
		
		chongzhi.setForeground(Color.black);
		chongzhi.setFont(new Font("����", Font.BOLD, 20));
		chongzhi.setBounds(310,300,90,35);
		//chongzhi.setContentAreaFilled(false);
		pan.add(chongzhi);
				
		title = new JLabel("�� ѧ �� �� ϵ ͳ");
		title.setForeground(Color.black);
		title.setFont(new Font("����", Font.BOLD, 33));
		title.setBounds(140, 50, 350, 40);
		pan.add(title);
		
		background = new JLabel(new ImageIcon("image/ѧУ.jpg"));
		//background.setForeground(Color.black);
		background.setBounds(0, 0, 600, 500);
		pan.add(background);
		
		denglu.addActionListener(this);
	    chongzhi.addActionListener(this);
		super.setVisible(true);
	}
    passtext.addKeyListener(new KeyAdapter() {
    	public  void keyPressed(KeyEvent e2) {
    		 if(e2.getKeyChar()==KeyEvent.VK_ENTER) {//���������enter��
    			 denglu.doClick();//�����¼��ť
    			 } } 
    });
}


public static void main(String []args){
     
    new Login();
    
}

    public void actionPerformed(ActionEvent arg0) {
    	if(arg0.getSource()==denglu){
            denglu();
        }else if (arg0.getSource()==chongzhi){
            chongzhi();
        }
    }
    //��¼��ť���¼�������
    public void denglu(){
    Jdbc d = new Jdbc();
     String useraccount = accounttext.getText();
     char[] password = passtext.getPassword();
      if(d.compare(useraccount, password)){
    	  JOptionPane.showMessageDialog(null, "��½�ɹ���");
    	  level = d.L;
    	  NO = useraccount;//�˺���ƥ�䣬����������ɾ�ķ�Χ
    	  dispose();
          new SystemUI();
      }
    }
    //���ð�ť��������¼�������
    public void chongzhi() {
    	accounttext.setText("");
    	passtext.setText("");
    }
}
class Jdbc {
	Connection con = null;
	Statement statement = null;
	ResultSet res = null;
	int L;
	String driver = "com.mysql.jdbc.Driver";
	String account = "root";
	String passwd = "123456";
	String url  = "jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
			+account+"&password="+passwd + "&useUnicode=true&characterEncoding=gbk";
	 
	public Jdbc(){
	    try{
	    	Class.forName(driver);
		   Connection con = DriverManager.getConnection(url,account,passwd);//�����ݿ⽨�����ӣ�
		   statement = con.createStatement();
		    }catch(ClassNotFoundException e){
		        System.out.println("�Բ����Ҳ������Driver");
		        e.printStackTrace();
		    }catch(SQLException e){
		        e.printStackTrace();
		    }catch(Exception e){
		        e.printStackTrace();
		    }
	    
	}
	//�Ա��û����������ǲ�ƥ��
	public boolean compare(String useraccount,char[] password){
		String pwd = String.valueOf(password);
	    boolean m = false;
	    String sql = "select userpass,level from userpass where useraccount='"+useraccount+"'";
	    try{
	        res = statement.executeQuery(sql);
	        if(res.next()){
	            String pa = res.getString(1);
	            System.out.println(pa+" " +pwd);
	            if(pa.equals(pwd)){
	                m = true;
	                L = res.getInt(2);
	            }else {
	                JOptionPane.showMessageDialog(null, "�������");
	            }
	        }else {
	            JOptionPane.showMessageDialog(null, "�˺Ų����ڣ�");
	        } 
	    }catch(SQLException e){
	        e.printStackTrace();
	    }
	    return m;
	}

}

