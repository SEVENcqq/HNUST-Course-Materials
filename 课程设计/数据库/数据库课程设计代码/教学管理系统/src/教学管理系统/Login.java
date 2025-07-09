package 教学管理系统;

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
private JLabel accountlab = new JLabel("账号");
private JLabel passlab = new JLabel("密码");
private JTextField accounttext = new JTextField();
private JPasswordField passtext = new JPasswordField();
public JButton denglu = new JButton("登录");
public JButton chongzhi = new JButton("重置");
private JLabel title;
private JLabel background;
//ImageIcon image;
public static int level;//静态变量，权限等级，用于展示不同的连接板块，便于SystemUI调用
public static String NO;//静态变量，用于限制用户的增删改查的范围
public Login(){
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/校徽.jpg"));
		super.setTitle("湖南科技大学");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		super.setSize(600,500);
		pan.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pan);
		pan.setLayout(null);
		this.setLocationRelativeTo(null);
		
		accountlab.setForeground(Color.white);
		accountlab.setFont(new Font("楷体", Font.BOLD, 22));
		accountlab.setBounds(160,170,60,35);
		pan.add(accountlab);
		
		passlab.setForeground(Color.white);
		passlab.setFont(new Font("楷体", Font.BOLD, 22));
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
		
		JRadioButton stu = new JRadioButton("学生");
		stu.setForeground(Color.black);
		stu.setFont(new Font("楷体", Font.BOLD, 17));
		stu.setBounds(170, 110, 65, 40);
		stu.setContentAreaFilled(false);
		pan.add(stu);
		
		JRadioButton tea = new JRadioButton("教师");
		tea.setForeground(Color.black);
		tea.setFont(new Font("楷体", Font.BOLD, 17));
		tea.setBounds(240, 110, 65, 40);
		tea.setContentAreaFilled(false);
		pan.add(tea);
		
		JRadioButton staff = new JRadioButton("教务人员");
		staff.setForeground(Color.black);
		staff.setFont(new Font("楷体", Font.BOLD, 17));
		staff.setBounds(310, 110, 100, 40);
		staff.setContentAreaFilled(false);//按钮透明化
		pan.add(staff);
		
		denglu.setForeground(Color.black);
		denglu.setFont(new Font("楷体", Font.BOLD, 20));
		denglu.setBounds(160,300,90,35);//227, 355, 127, 49
		//denglu.setContentAreaFilled(false);
		pan.add(denglu);
		
		chongzhi.setForeground(Color.black);
		chongzhi.setFont(new Font("楷体", Font.BOLD, 20));
		chongzhi.setBounds(310,300,90,35);
		//chongzhi.setContentAreaFilled(false);
		pan.add(chongzhi);
				
		title = new JLabel("教 学 管 理 系 统");
		title.setForeground(Color.black);
		title.setFont(new Font("楷体", Font.BOLD, 33));
		title.setBounds(140, 50, 350, 40);
		pan.add(title);
		
		background = new JLabel(new ImageIcon("image/学校.jpg"));
		//background.setForeground(Color.black);
		background.setBounds(0, 0, 600, 500);
		pan.add(background);
		
		denglu.addActionListener(this);
	    chongzhi.addActionListener(this);
		super.setVisible(true);
	}
    passtext.addKeyListener(new KeyAdapter() {
    	public  void keyPressed(KeyEvent e2) {
    		 if(e2.getKeyChar()==KeyEvent.VK_ENTER) {//如果密码是enter键
    			 denglu.doClick();//点击登录按钮
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
    //登录按钮的事件处理函数
    public void denglu(){
    Jdbc d = new Jdbc();
     String useraccount = accounttext.getText();
     char[] password = passtext.getPassword();
      if(d.compare(useraccount, password)){
    	  JOptionPane.showMessageDialog(null, "登陆成功！");
    	  level = d.L;
    	  NO = useraccount;//账号相匹配，便于限制增删改范围
    	  dispose();
          new SystemUI();
      }
    }
    //重置按钮触发后的事件处理函数
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
		   Connection con = DriverManager.getConnection(url,account,passwd);//与数据库建立连接；
		   statement = con.createStatement();
		    }catch(ClassNotFoundException e){
		        System.out.println("对不起，找不到这个Driver");
		        e.printStackTrace();
		    }catch(SQLException e){
		        e.printStackTrace();
		    }catch(Exception e){
		        e.printStackTrace();
		    }
	    
	}
	//对比用户名和密码是不匹配
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
	                JOptionPane.showMessageDialog(null, "密码错误！");
	            }
	        }else {
	            JOptionPane.showMessageDialog(null, "账号不存在！");
	        } 
	    }catch(SQLException e){
	        e.printStackTrace();
	    }
	    return m;
	}

}

