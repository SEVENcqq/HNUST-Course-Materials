package 教学管理系统;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;


public class SelectCourse {
	String userMySql="root"; //
	String passwordMySql="123456";//
	private Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
	//Vector<Object> rowData,columnNames;
	Vector<Object> columnNames;
    //定义数据库需要的全局变量
    PreparedStatement ps=null;
    Connection ct=null;
    ResultSet rs=null;
    
    JTextField sesnotext  = null;      
    JTextField secnotext = null;
    JTextField cnotext  = null;
    JTextField snotext  = null;
    
    JFrame Addse = null;
    JFrame SeaSelCou = null;
    
    static JTable table5=null;
    public SelectCourse(){
            columnNames=new Vector<Object>();
            //设置列名
            columnNames.add("学号");
            columnNames.add("姓名");
            columnNames.add("课程号");
            columnNames.add("课程名");     
           // rowData = new Vector<Object>();
            //rowData可以存放多行,开始从数据库里取
            
            try {
            	String userMySql="root"; //
            	String passwordMySql="123456";//
                    //加载驱动
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("加载成功！");
                    //得到连接
                    ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
        					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
                    
                    ps=ct.prepareStatement("select * from selectcourse");
                    
                    rs=ps.executeQuery();
                    
                    while(rs.next()){
                            //rowData可以存放多行
                            Vector<Object> hang=new Vector<Object>();
                            hang.add(rs.getString(1));
                            hang.add(rs.getString(2));
                            hang.add(rs.getString(3));
                            hang.add(rs.getString(4));
                            //加入到rowData
                            rowData.add(hang);
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally{
                    
                            try {
                                    if(rs!=null){
                                    rs.close();
                                    }
                                    if(ps!=null){
                                            ps.close();
                                    }
                                    if(ct!=null){
                                            ct.close();
                                    }
                            } catch (SQLException e) {
                                    e.printStackTrace();
                            }
            }
            table5= new JTable(rowData,columnNames){
    
				private static final long serialVersionUID = -8736911822989769111L;

		public boolean isCellEditable(int row, int column)
        {return false;}//表格不允许被编辑
    };
    table5.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//只允许选中一行
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
    tcr.setHorizontalAlignment(JLabel.CENTER);// 这句和上句作用一样
    table5.setDefaultRenderer(Object.class, tcr);
    }
    public void addSelCou() {
            // TODO 自动生成的方法存根
    SystemUI.unclickable();
    Addse = new JFrame("添加选课信息");//
    Addse.setSize(500, 300);
    Addse.setLocationRelativeTo(null);
    JPanel addse = new JPanel();
    JLabel sesno = new JLabel("学号");        
    JLabel secno = new JLabel("课程号");
    sesnotext  = new JTextField();//      
    secnotext = new JTextField();//

    
    JButton ok = new JButton("确定");
    JButton reset = new JButton("重置");
     
    addse.setLayout(null);
    sesno.setBounds(5,5,70,20);
    sesnotext.setBounds(80,5,120,20);
    secno.setBounds(5,30,70,20);
    secnotext.setBounds(80,30,120,20);
    ok.setBounds(50,70,60,20);
    reset.setBounds(130,70,60,20);
     
    addse.add(sesno);
    addse.add(sesnotext);
    addse.add(secno);
    addse.add(secnotext);      
    addse.add(ok);
    addse.add(reset);

    Addse.add(addse);
    Addse.setVisible(true);
    Addse.addWindowListener(new WindowAdapter(){
        @Override
        public void windowClosing(WindowEvent e) {
             SystemUI.clickable();
        }
       });
      ok.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent arg0) {       	  
             try {
                 String sno = sesnotext.getText();
                 String cno = secnotext.getText();
            		 //加载驱动
                 Class.forName("com.mysql.jdbc.Driver");
                 //得到连接
                 ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
     					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
                 if(((sno.equals(Login.NO)&&Login.level==1)||Login.level==3)&&getStatus(cno)!=5){//限制增添课程范围
                     ps=ct.prepareStatement("insert into selectcourse values('"+sno+"',(select studentname from student where studentid='"+sno+"'),'"+cno+"',(select coursename from course where courseid='"+cno+"'))");
                     ps.executeUpdate();
                     JOptionPane.showMessageDialog(null, "添加成功！");
                     Addse.dispose();
                     SystemUI.clickable();
                     refreshSelCou();
            	 }
                 else if(getStatus(cno)==5){
                     JOptionPane.showMessageDialog(null, "课程已删除！");
                     SystemUI.unclickable();
                 }
                 else{
                	 JOptionPane.showMessageDialog(null, "添加失败！");
                     SystemUI.unclickable();
                 }     
             } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "添加失败！");
                    SystemUI.unclickable();
               } finally{
                     try {
                         if(rs!=null){
                                     rs.close();
                         }
                         if(ps!=null){
                                 ps.close();
                         }
                         if(ct!=null){
                                 ct.close();
                                     }
                     } catch (SQLException e) {
                             e.printStackTrace();
                     }
             }
          }   
      });
    reset.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent arg0) {
            sesnotext.setText("");
            secnotext.setText("");
        }
    });
    }

    public void deleteSelCou() {
    	// TODO 自动生成的方法存根
    	int row = table5.getSelectedRow();
        if ( row >= 0 ) {
        	String string[] = new String[4];
            string[0] = String.valueOf(table5.getValueAt(row, 0));
            string[1] = String.valueOf(table5.getValueAt(row, 1));
            string[2] = String.valueOf(table5.getValueAt(row, 2));
            string[3] = String.valueOf(table5.getValueAt(row, 3));
            try {
                    //加载驱动
                    Class.forName("com.mysql.jdbc.Driver");
                    //得到连接
                    ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
        					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
                    if(string[0].equals(Login.NO)&&Login.level==1||Login.level==3){//限制删除选课范围
                        ps=ct.prepareStatement("delete from selectcourse where studentid='"+string[0]+"' and studentname='"+string[1]+"' and courseid='"+string[2]+"' and coursename='"+string[3]+"'");
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "删除成功！");
                        refreshSelCou();
                        row=-1;
                    }
                    else{
                    	JOptionPane.showMessageDialog(null, "删除失败！");
                    }
            } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "删除失败！");
            } finally{
                            try {
                                    if(rs!=null){
                                    rs.close();
                                    }
                                    if(ps!=null){
                                            ps.close();
                                    }
                                    if(ct!=null){
                                            ct.close();
                                    }
                            } catch (SQLException e) {
                                    e.printStackTrace();
                            }
            }
        }
        else {
        	JOptionPane.showMessageDialog(null, "请选中要删除的行！");
        }
    }
    public void searchSelCou() {
    	// TODO 自动生成的方法存根
    	final Vector<Vector<Object>> rowData0 = new Vector<Vector<Object>>();
        SystemUI.unclickable();
        SeaSelCou = new JFrame("查找课程信息");//
        SeaSelCou.setSize(500, 300);
        SeaSelCou.setLocationRelativeTo(null);
        JPanel seaselcou = new JPanel();
        JLabel cno = new JLabel("请输入课程号");
        cnotext  = new JTextField();//
        JLabel sno = new JLabel("请输入学号");
        snotext  = new JTextField();//
        JButton ok = new JButton("确定");
        JButton reset = new JButton("重置");
         
        seaselcou.setLayout(null);
         
        cno.setBounds(5,5,80,20);
        cnotext.setBounds(90,5,120,20);
        sno.setBounds(5,30,80,20);
        snotext.setBounds(90,30,120,20);
        ok.setBounds(50,70,60,20);
        reset.setBounds(130,70,60,20);
         
        seaselcou.add(cno);
        seaselcou.add(cnotext);
        seaselcou.add(sno);
        seaselcou.add(snotext);
        seaselcou.add(ok);
        seaselcou.add(reset);
         
        SeaSelCou.add(seaselcou);
        SeaSelCou.setVisible(true);

        SeaSelCou.addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent e) {
            SystemUI.clickable();
           }
          });
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                columnNames=new Vector<Object>();
                //设置列名
                columnNames.add("学号");
                columnNames.add("姓名");
                columnNames.add("课程号");
                columnNames.add("课程名");
                String str = cnotext.getText();
                String str1 = snotext.getText();
               // rowData = new Vector<Object>();
                int count=0;
                //rowData可以存放多行,开始从数据库里取
                try {
                    //加载驱动
                    Class.forName("com.mysql.jdbc.Driver");
                    //得到连接
                    ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit","root","123456");
                    if(str1.equals(Login.NO)&&Login.level==1||Login.level==3){//设置查询范围
                      if(snotext.getText().trim().equals("")&&!cnotext.getText().trim().equals("")){
                           ps=ct.prepareStatement("select * from selectcourse where courseid='"+str+"'");
                           rs=ps.executeQuery();
                      }
                      if(cnotext.getText().trim().equals("")&&!snotext.getText().trim().equals("")){
                           ps=ct.prepareStatement("select * from selectcourse where studentid='"+str1+"'");
                           rs=ps.executeQuery();
                      }
                      if(!cnotext.getText().trim().equals("")&&!snotext.getText().trim().equals("")){
                           ps=ct.prepareStatement("select * from selectcourse where studentid='"+str1+"' and courseid='"+str+"'");
                           rs=ps.executeQuery();
                      }
                      if(cnotext.getText().trim().equals("")&&snotext.getText().trim().equals("")){
                           ps=ct.prepareStatement("select * from selectcourse");
                           rs=ps.executeQuery();
                           JOptionPane.showMessageDialog(null, "请输入查询信息！");
                      }
                       while(rs.next()){
                           //rowData可以存放多行
                           Vector<Object> hang=new Vector<Object>();
                           hang.add(rs.getString(1));
                           hang.add(rs.getString(2));
                           hang.add(rs.getString(3));
                           hang.add(rs.getString(4));
                           //加入到rowData
                           rowData0.add(hang);
                           count++;
                      }
                    }
                    else{
                    	JOptionPane.showMessageDialog(null, "查询失败！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "查询失败！");
                } finally{
                        try {
                            if(rs!=null){
                            rs.close();
                            }
                            if(ps!=null){
                                ps.close();
                            }
                            if(ct!=null){
                                ct.close();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                }
                if(count!=0) {
                    table5 = new JTable(rowData0,columnNames){                       
						private static final long serialVersionUID = -3692229347549819226L;

						public boolean isCellEditable(int row, int column)
                        {return false;}//表格不允许被编辑
                    };
                    table5.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//只允许选中一行
                    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
                    tcr.setHorizontalAlignment(JLabel.CENTER);// 这句和上句作用一样
                    table5.setDefaultRenderer(Object.class, tcr);
                    SystemUI.scrollPane5.setViewportView(table5);
                    SeaSelCou.dispose();
                    SystemUI.clickable();}
                if(count==0&&((str1.equals(Login.NO)&&Login.level==1)||Login.level==3)) {
                    JOptionPane.showMessageDialog(null, "没有查询到相关信息！");
                }
            }   
        });
        reset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cnotext.setText("");
                snotext.setText("");
            }
        });    
    }

    public void refreshSelCou() {
    	// TODO 自动生成的方法存根
    	new SelectCourse();
        SystemUI.scrollPane5.setViewportView(SelectCourse.table5);
    }
    
    public int getStatus(String cno) {
    	 int status=3;  	 
    	 {
    		try {//课程存在但不可选,课程已删除
                   //加载驱动
                   Class.forName("com.mysql.jdbc.Driver");
                   //得到连接
                   ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit","root","123456");
                   ps=ct.prepareStatement("select leixing from course where courseid='"+cno+"'");
                   rs=ps.executeQuery();                
                   if(rs.next()){
              		 status = rs.getInt(1);
              	   }
             } catch (Exception e) {
                 e.printStackTrace();
                 SystemUI.unclickable();
             } 
    	 }
    	return status;  	
    }

}



