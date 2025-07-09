package ��ѧ����ϵͳ;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import java.util.Date;


public class Teacher extends JFrame{
	Vector<Object> columnNames;
	
	private Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
	static JTable table1=null;
	//�������ݿ���Ҫ��ȫ�ֱ���
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;//�������ݿ��ѯ�Ľ�������resultset�Ķ���rs��
	
	JTextField teachernametext = null;
	JTextField teacherdepartmenttext  = null;
	JTextField teacheridtext  =  null;
	
	JFrame AddTea = null;
	JFrame UpdateTea = null;
	JFrame SeaTea = null;
	
	JTextField upidtext = null;//
    JTextField upnametext = null;//
    JTextField updepartmenttext  = null;//
    JTextField tnotext  = null;
    JPanel jP1, jP2,jP3,jP4,jP5,jP6,jPBottom= null;
	
	public Teacher(){
		columnNames=new Vector<Object>();
		//��������
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("����");
		//rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ    
		try {	
		String userMySql="root"; //zhangsan
		String passwordMySql="123456";//12345678
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//�õ����ӣ�����ȡ���Ӷ���ct
			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
			
			ps=ct.prepareStatement("select * from teacher");//��mysql���Ͷ�̬��sql���
			
			rs=ps.executeQuery();//ʹ��select���ʱ��executeQueryִ�У�executeupdate����ִ��insert��update��delete��execute���ڷ��ض�����������䡣
			
			while(rs.next()){
				//rowData���Դ�Ŷ���
				Vector<Object> hang=new Vector<Object>();
				hang.add(rs.getInt(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				//���뵽rowData
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
		table1=new JTable(rowData,columnNames) {
			private static final long serialVersionUID=-3229560868878458304L;
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
        table1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//ֻ����ѡ��һ��
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
        tcr.setHorizontalAlignment(JLabel.CENTER);// �����Ͼ�����һ��
        table1.setDefaultRenderer(Object.class, tcr);
	}
	
	//��ӽ�ʦ����Ϣ��
	public void addTeacher() {
		// TODO �Զ����ɵķ������
		SystemUI.unclickable();
		//setIconImage(Toolkit.getDefaultToolkit().getImage("image\\У��.jpg"));
        AddTea = new JFrame("��ӽ�ʦ��Ϣ");//
        AddTea.setSize(500, 300);//250,270
        AddTea.setLocationRelativeTo(null);
        //AddTea.setLocation(600, 300);
        JPanel addtea = new JPanel();
        JLabel teacherid = new JLabel("����");
        JLabel teachername = new JLabel("����");
        JLabel teacherdepartment = new JLabel("����");
        teacheridtext  = new JTextField();//
        //JTextField teachernametext = null;
        teachernametext = new JTextField();//
        teacherdepartmenttext  = new JTextField();//
        JButton ok = new JButton("ȷ��");
        JButton reset = new JButton("����");
      
        addtea.setLayout(null);
        teacherid.setBounds(5,5,70,20);
        teacheridtext.setBounds(80,5,120,20);
        teachername.setBounds(5,30,70,20);
        teachernametext.setBounds(80,30,120,20);
        teacherdepartment.setBounds(5,60,70,20);
        teacherdepartmenttext.setBounds(80,60,120,20);
        
        ok.setBounds(50,190,60,20);
        reset.setBounds(130,190,60,20);
        addtea.add(teacherid);
        addtea.add(teacheridtext);
        addtea.add(teachername);
        addtea.add(teachernametext);
        addtea.add(teacherdepartment);
        addtea.add(teacherdepartmenttext);
        addtea.add(ok);
        addtea.add(reset);
         
        AddTea.add(addtea);
        AddTea.setVisible(true);
        
       AddTea.addWindowListener(new WindowAdapter(){
    	   @Override
    	  /* public void windowlconified(WindowEvent e) {
    		   setIconImage(Toolkit.getDefaultToolkit().getImage("image\\У��.jpg"));
       	   }*/
    	   public void windowClosing(WindowEvent e) {
    		SystemUI.clickable();
    	   }
    	  });
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
            	try {	
            	String userMySql="root"; 
            	String passwordMySql="123456";
            		
            		String  name = teachernametext.getText();
            		String department = teacherdepartmenttext.getText();
            		 int id = Integer.valueOf(teacheridtext.getText()).intValue();
            		
        			//��������
        			Class.forName("com.mysql.jdbc.Driver");
        			//�õ�����
        			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
        					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
					ps=ct.prepareStatement("insert into teacher values('"+id+"','"+name+"','"+department+"')");
        			ps.executeUpdate();
        			JOptionPane.showMessageDialog(null, "��ӳɹ���");
        			AddTea.dispose();
        			SystemUI.clickable();
        			addTeacher();
        			
            	} catch (Exception e) {
        			e.printStackTrace();
        			JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
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
                 
                teacheridtext.setText("");
                teachernametext.setText("");
                teacherdepartmenttext.setText("");
   
            }
        });
	}
//�޸Ľ�ʦ����Ϣ��
	public void updateTeacher() {
		// TODO �Զ����ɵķ������
		SystemUI.unclickable();
	    UpdateTea = new JFrame("�޸Ľ�ʦ��Ϣ");//
	    UpdateTea.setSize(500, 300);
	   // UpdateTea.setLocation(600, 300);
	    UpdateTea.setLocationRelativeTo(null);
	    JPanel updatetea = new JPanel();
	    JLabel upid = new JLabel("Ҫ�޸ĵĹ���");
	    JLabel upname = new JLabel("Ҫ�ĳɵ�����");
	    JLabel updepartment = new JLabel("Ҫ�ĳɵĲ���");
	    upidtext = new JTextField();//
	    upnametext = new JTextField();//
	    updepartmenttext  = new JTextField();//
	    JButton ok = new JButton("ȷ��");
	    JButton reset = new JButton("����");
	     
	    updatetea.setLayout(null);
	    
	    upid.setBounds(5,30,100,20);
	    upidtext.setBounds(110,30,120,20);
	    upname.setBounds(5,60,100,20);
	    upnametext.setBounds(110,60,120,20);
	    updepartment.setBounds(5,90,100,20);
	    updepartmenttext.setBounds(110,90,120,20);
	    ok.setBounds(50,180,60,20);
	    reset.setBounds(120,180,60,20);
	    
	    updatetea.add(upid);
	    updatetea.add(upidtext); 
	    updatetea.add(upname);
	    updatetea.add(upnametext);
	    updatetea.add(updepartment);
	    updatetea.add(updepartmenttext);
	    updatetea.add(ok);
	    updatetea.add(reset);
	     
	    UpdateTea.add(updatetea);
	    UpdateTea.setVisible(true);
	    
	   	UpdateTea.addWindowListener(new WindowAdapter(){
		   @Override
		   public void windowClosing(WindowEvent e) {
			SystemUI.clickable();
		   }
		  });
	    ok.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent arg0) {
	        	try {	String userMySql="root"; 
	        	String passwordMySql="123456";
	        		//String id = upidtext.getText();
	        		String name = upnametext.getText();
	        		String department = updepartmenttext.getText();
	        		int id = Integer.valueOf(upidtext.getText()).intValue();
	        		
	    			//��������
	    			Class.forName("com.mysql.jdbc.Driver");
	    			//�õ�����
	    			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
					ps=ct.prepareStatement("update teacher set teachername ='"+name+"',teacherdepartment ='"+department+"' where teacherid='"+id+"'");
	    			ps.executeUpdate();
	    			JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
	    			UpdateTea.dispose();
	    			SystemUI.clickable();
	    			addTeacher();
	        	} catch (Exception e) {
	    			e.printStackTrace();
	    			JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
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
	            upidtext.setText(""); 
	            upnametext.setText("");
	            updepartmenttext.setText("");
	          
	        }
	    });
	}
//ɾ����ʦ��Ϣ
	public void deleteTeacher() {
		// TODO �Զ����ɵķ������
		int row = table1.getSelectedRow();
    	if ( row >= 0 ) {
    		String string[] = new String[5];
    		string[0] = String.valueOf(table1.getValueAt(row, 0));
    		string[1] = String.valueOf(table1.getValueAt(row, 1));
    		try {	String userMySql="root"; 
    		String passwordMySql="123456";
    			//��������
    			Class.forName("com.mysql.jdbc.Driver");
    			//�õ�����
    			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
    			ps=ct.prepareStatement("delete from teacher where teacherid='"+string[0]+"' and teachername='"+string[1]+"'");
    			ps.executeUpdate();
    			JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
    			refreshTeacher();
    			row=-1;
        	} catch (Exception e) {
    			e.printStackTrace();
    			JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
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
    		JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�����У�");
    	}
	}


	public void searchTeacher() {
		// TODO �Զ����ɵķ������
		final Vector<Vector<Object>> rowData0 = new Vector<Vector<Object>>();
		SystemUI.unclickable();
		SeaTea = new JFrame("���ҽ�ʦ��Ϣ");//
		SeaTea.setSize(500, 200);//250,100
		//SeaTea.setLocation(600, 300);
		SeaTea.setLocationRelativeTo(null);
		
		JPanel seatea = new JPanel();
		JLabel tno = new JLabel("�����빤��");
		tnotext  = new JTextField();//
		JButton ok = new JButton("ȷ��");
		JButton reset = new JButton("����");
		 
		seatea.setLayout(null);
		 
		tno.setBounds(5,5,70,20);
		tnotext.setBounds(80,5,120,20);
		ok.setBounds(50,30,60,20);
		reset.setBounds(130,30,60,20);
		 
		seatea.add(tno);
		seatea.add(tnotext);
		seatea.add(ok);
		seatea.add(reset);
		 
		SeaTea.add(seatea);
		SeaTea.setVisible(true);

		SeaTea.addWindowListener(new WindowAdapter(){
		   @Override
		   public void windowClosing(WindowEvent e) {
		    SystemUI.clickable();
		   }
		  });
		ok.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent arg0) {
		    	columnNames=new Vector<Object>();
				//��������
				columnNames.add("����");
				columnNames.add("����");
				columnNames.add("����");
				String str = tnotext.getText();

				int count=0;
				
				try {	String userMySql="root"; 
				String passwordMySql="123456";
					//��������
					Class.forName("com.mysql.jdbc.Driver");
					//�õ�����
					ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
					if(!tnotext.getText().trim().equals("")) {
					ps=ct.prepareStatement("select * from teacher where teacherid='"+str+"'");
					
					rs=ps.executeQuery();
					}
					if(tnotext.getText().trim().equals("")) {
						ps=ct.prepareStatement("select * from teacher ");
						rs=ps.executeQuery();
						JOptionPane.showMessageDialog(null, "�������ѯ��Ϣ��");
					}
					while(rs.next()){
						//rowData���Դ�Ŷ���
						Vector<Object> hang=new Vector<Object>();
						hang.add(rs.getInt(1));
						hang.add(rs.getString(2));
						hang.add(rs.getString(3));
						
						//���뵽rowData
						rowData0.add(hang);
						count++;
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
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
		        	table1 = new JTable(rowData0,columnNames){
			            
						private static final long serialVersionUID = -1545099432791772807L;

						public boolean isCellEditable(int row, int column)
			            {return false;}//��������༭
			        };
			        table1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//ֻ����ѡ��һ��
			        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
			        tcr.setHorizontalAlignment(JLabel.CENTER);// �����Ͼ�����һ��
			        table1.setDefaultRenderer(Object.class, tcr);
		        	SystemUI.scrollPane1.setViewportView(table1);
		        	SeaTea.dispose();
		        	SystemUI.clickable();}
		        if(count==0) { 
		        	JOptionPane.showMessageDialog(null, "���޴��ˣ�");
		        }
		    }   
		});
	}
	public void refreshTeacher() {
		// TODO �Զ����ɵķ������
		new Teacher();
		SystemUI.scrollPane1.setViewportView(Teacher.table1);
	}
}


