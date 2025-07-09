package 教学管理系统;
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


	public class Course {
		private Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
		String userMySql="root"; //
		String passwordMySql="123456"; //
		Vector<Object> columnNames;
		//定义数据库需要的全局变量
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		static JTable table3=null;
		
        JTextField cnotext  = null;
        JTextField cnametext = null;
        JTextField cpnotext  = null;
        JTextField credittext  = null;
        JTextField cremarktext  = null;
        JTextField tnametext  = null;//
        JTextField periodtext  = null;//
        JTextField placetext  = null;//
        JTextField timetext  = null;//
        JTextField ycnotext = null;
        
        JFrame AddCou = null;
        JFrame UpdateCou = null;
        JFrame SeaCou = null;
		
        //public static int status;
        
		public Course(){
			//setIconImage(Toolkit.getDefaultToolkit().getImage("image\\校徽.jpg"));
			columnNames=new Vector<Object>();
			//设置列名
			columnNames.add("课程号");
			columnNames.add("课程名");
			columnNames.add("先修课课程号");
			columnNames.add("教师名");//
			columnNames.add("学分");
			columnNames.add("学时");//
			columnNames.add("地点");//
			columnNames.add("时间");	//
			columnNames.add("状态");	

			//rowData = new Vector<Object>();
			//rowData可以存放多行,开始从数据库里取
			
			try {
				String userMySql="root"; 
            	String passwordMySql="123456";
				//加载驱动
				Class.forName("com.mysql.jdbc.Driver");
				//得到连接
				ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
        					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
				
				ps=ct.prepareStatement("select * from course");
				
				rs=ps.executeQuery();
				
				while(rs.next()){
					//rowData可以存放多行
					Vector<Object> hang=new Vector<Object>();
					hang.add(rs.getString(1));
					hang.add(rs.getString(2));
					hang.add(rs.getString(3));
					hang.add(rs.getString(4));
					hang.add(rs.getInt(5));
					hang.add(rs.getInt(6));
					hang.add(rs.getString(7));
					hang.add(rs.getString(8));
					hang.add(rs.getString(9));
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
			table3 = new JTable(rowData,columnNames){
				private static final long serialVersionUID = 6843971351064905400L;

				public boolean isCellEditable(int row, int column)
	            {return false;}//表格不允许被编辑
	        };
	        table3.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//只允许选中一行
	        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
	        tcr.setHorizontalAlignment(JLabel.CENTER);// 这句和上句作用一样
	        table3.setDefaultRenderer(Object.class, tcr);
		}
		public void addCourse() {           //增加课程
			// TODO 自动生成的方法存根
			SystemUI.unclickable();
	        AddCou = new JFrame("添加课程信息");//
	        AddCou.setSize(500, 400);//250,250
	       // AddCou.setLocation(600, 300);
	        AddCou.setLocationRelativeTo(null);
	        JPanel addCou = new JPanel();
	        JLabel cno = new JLabel("课程号");
	        JLabel cname = new JLabel("课程名");
	        JLabel cpno = new JLabel("先修课");
	        JLabel credit = new JLabel("学分");
	        JLabel cremark = new JLabel("状态");
	        JLabel tname = new JLabel("教师名");//
	        JLabel period = new JLabel("学时");//
	        JLabel place = new JLabel("地点");//
	        JLabel time = new JLabel("时间");//
	        
	        cnotext  = new JTextField();//
	        cnametext = new JTextField();//
	        cpnotext  = new JTextField();//
	        tnametext = new JTextField();//
	        credittext  = new JTextField();//
	        periodtext  = new JTextField();//
	        placetext  = new JTextField();//
	        timetext  = new JTextField();//
	        cremarktext  = new JTextField();//
	        
	        JButton ok = new JButton("确定");
	        JButton reset = new JButton("重置");
	         
	        addCou.setLayout(null);
	         
	        cno.setBounds(5,5,70,20);
	        cnotext.setBounds(80,5,120,20);
	        cname.setBounds(5,30,70,20);
	        cnametext.setBounds(80,30,120,20);
	        cpno.setBounds(5,60,70,20);
	        cpnotext.setBounds(80,60,120,20);
	        tname.setBounds(5,90,70,20);
	        tnametext.setBounds(80,90,120,20);
	        credit.setBounds(5,120,70,20);
	        credittext.setBounds(80,120,120,20);//
	        period.setBounds(5,150,70,20);
	        periodtext.setBounds(80,150,120,20);
	        place.setBounds(5,180,70,20);
	        placetext.setBounds(80,180,120,20);
	        time.setBounds(5,210,70,20);
	        timetext.setBounds(80,210,120,20);
	        cremark.setBounds(5,240,70,20);
	        cremarktext.setBounds(80,240,120,20);
	        ok.setBounds(50,270,60,20);
	        reset.setBounds(130,270,60,20);
	         
	        addCou.add(cno);
	        addCou.add(cnotext);
	        addCou.add(cname);
	        addCou.add(cnametext);
	        addCou.add(cpno);
	        addCou.add(cpnotext);
	        addCou.add(tname);
	        addCou.add(tnametext);
	        addCou.add(credit);
	        addCou.add(credittext);
	        addCou.add(period);
	        addCou.add(periodtext);
	        addCou.add(place);
	        addCou.add(placetext);
	        addCou.add(time);
	        addCou.add(timetext);
	        addCou.add(cremark);
	        addCou.add(cremarktext);
            addCou.add(ok);
	        addCou.add(reset);
	         
	        AddCou.add(addCou);
	        AddCou.setVisible(true);
	         
			AddCou.addWindowListener(new WindowAdapter(){
		     	   @Override
		     	   public void windowClosing(WindowEvent e) {
		     		SystemUI.clickable();
		     	   }
		     	  });
		         ok.addActionListener(new ActionListener(){
		             public void actionPerformed(ActionEvent arg0) {
		             	try {
		             		String userMySql="root"; 
		                	String passwordMySql="123456";
		             		String no = cnotext.getText();
		             		String  name = cnametext.getText();
		             		String pno = cpnotext.getText();
		             		String tn = tnametext.getText();
		             		String pl = placetext.getText();
		             		String ti = timetext.getText();
		             		String remark = cremarktext.getText();
		             		int credit = Integer.valueOf(credittext.getText()).intValue();
		             		int period = Integer.valueOf(periodtext.getText()).intValue();
		         			//加载驱动
		         			Class.forName("com.mysql.jdbc.Driver");
		         			//得到连接
		         			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
		        					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
		         			String sql;
		         			sql = "insert into course values('"+no+"','"+name+"','"+pno+"','"+tn+"',"+credit+","+period+",'"+pl+"','"+ti+"','"+remark+"')";
		         			ps=ct.prepareStatement(sql);
		         			ps.executeUpdate();
		         			JOptionPane.showMessageDialog(null, "添加成功！");
		         			AddCou.dispose();
		         			SystemUI.clickable();
		         			refreshCourse();
		         		
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
	                 
	                cnotext.setText("");
	                cnametext.setText("");
	                cpnotext.setText("");
	                tnametext.setText("");
	                credittext.setText("");
	                periodtext.setText("");
	                placetext.setText("");
	                timetext.setText("");
	                cremarktext.setText("");
	                	                
	            }
	        });
		}

		public void updateCourse() {
			// TODO 自动生成的方法存根
		    SystemUI.unclickable();
		    UpdateCou = new JFrame("修改课程信息");//
		    UpdateCou.setSize(500, 400);
		    //UpdateCou.setLocation(600, 300);
		    UpdateCou.setLocationRelativeTo(null);
		    JPanel updatecou = new JPanel();
		    JLabel ycno = new JLabel("要修改的课程号");
		    JLabel cpno = new JLabel("要改成的先修课");
		    JLabel tname = new JLabel("要改成的教师名");
		    JLabel credit = new JLabel("要改成的学分");
		    JLabel period = new JLabel("要改成的学时");
		    JLabel place = new JLabel("要改成的地点");
		    JLabel time = new JLabel("要改成的时间");
		    JLabel cremark = new JLabel("要改成的状态");
	    
		    
		    ycnotext = new JTextField();//
		    cpnotext  = new JTextField();
		    tnametext  = new JTextField();
		    credittext  = new JTextField();
		    periodtext  = new JTextField();
		    placetext  = new JTextField();
		    timetext  = new JTextField();
		    cremarktext  = new JTextField();
		    
		    JButton ok = new JButton("确定");
		    JButton reset = new JButton("重置");
		     
		    updatecou.setLayout(null);
		    ycno.setBounds(5,5,100,20);
		    ycnotext.setBounds(110,5,120,20);
		    cpno.setBounds(5,30,100,20);
		    cpnotext.setBounds(110,30,120,20);
		    tname.setBounds(5,60,100,20);
		    tnametext.setBounds(110,60,120,20);
		    credit.setBounds(5,90,100,20);
		    credittext.setBounds(110,90,120,20);
		    period.setBounds(5,120,100,20);
		    periodtext.setBounds(110,120,120,20);
		    place.setBounds(5,150,100,20);
		    placetext.setBounds(110,150,120,20);
		    time.setBounds(5,180,100,20);
		    timetext.setBounds(110,180,120,20);
		    cremark.setBounds(5,210,100,20);
		    cremarktext.setBounds(110,210,120,20);
		    ok.setBounds(50,250,60,20);
		    reset.setBounds(120,250,60,20);
		    
		    updatecou.add(ycno);
		    updatecou.add(ycnotext); 
		    updatecou.add(cpno);
		    updatecou.add(cpnotext);
		    updatecou.add(tname);
		    updatecou.add(tnametext);
		    updatecou.add(credit);
		    updatecou.add(credittext);
		    updatecou.add(period);
		    updatecou.add(periodtext);
		    updatecou.add(place);
		    updatecou.add(placetext);
		    updatecou.add(time);
		    updatecou.add(timetext);
		    updatecou.add(cremark);
		    updatecou.add(cremarktext);
			    
		    updatecou.add(ok);
		    updatecou.add(reset);
		     
		    UpdateCou.add(updatecou);
		    UpdateCou.setVisible(true);
		     
		    UpdateCou.addWindowListener(new WindowAdapter(){
		           @Override
		           public void windowClosing(WindowEvent e) {
		            SystemUI.clickable();
		           }
		          });
		         ok.addActionListener(new ActionListener(){
		             public void actionPerformed(ActionEvent arg0) {
		                try {
		                    String yno = ycnotext.getText();
		                    String pno = cpnotext.getText();
		                    String tn = tnametext.getText();
		                    String pl = placetext.getText();
		             		String ti = timetext.getText();
		                    String remark = cremarktext.getText();
		             		int credit = Integer.valueOf(credittext.getText()).intValue();
		             		int period = Integer.valueOf(periodtext.getText()).intValue();
		                    //加载驱动
		                    Class.forName("com.mysql.jdbc.Driver");
		                    //得到连接
		                    ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
		                			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
		                    String sql;	            
		                    sql = "update course set beforecourse ='"+pno+"', teachername ='"+tn+"', xuefen ='"+credit+"', xueshi ='"+period+"', didian ='"+pl+"', shijian ='"+ti+"', leixing ='"+remark+"' where courseid='"+yno+"'";
		                    ps=ct.prepareStatement(sql);
		                    ps.executeUpdate();
		                    JOptionPane.showMessageDialog(null, "修改成功！");
		                    UpdateCou.dispose();
		                    SystemUI.clickable();
		                    refreshCourse();
		                } catch (Exception e) {
		                    e.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "修改失败！");
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

		            ycnotext.setText("");
	                cpnotext.setText("");
	                tnametext.setText("");
	                credittext.setText("");
	                periodtext.setText("");
	                placetext.setText("");
	                timetext.setText("");
	                cremarktext.setText("");
		        }
		    });
		}

		public void deleteCourse() {
			// TODO 自动生成的方法存根
			int row = table3.getSelectedRow();
	    	if ( row >= 0 ) {
	    		String string[] = new String[3];
	    		string[0] = String.valueOf(table3.getValueAt(row, 0));
	    		string[1] = String.valueOf(table3.getValueAt(row, 1));
	    		try {
	    			//加载驱动
	    			Class.forName("com.mysql.jdbc.Driver");
	    			//得到连接
	    			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
	    					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
	    			ps=ct.prepareStatement("delete from course where courseid='"+string[0]+"' and coursename='"+string[1]+"'");
	    			ps.executeUpdate();
	    			JOptionPane.showMessageDialog(null, "删除成功！");
	    			refreshCourse();
	    			row=-1;
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

		public void searchCourse() {
			// TODO 自动生成的方法存根
			final Vector<Vector<Object>> rowData0 = new Vector<Vector<Object>>();
	        SystemUI.unclickable();
	        SeaCou = new JFrame("查找课程信息");//
	        SeaCou.setSize(500, 200);
	        SeaCou.setLocationRelativeTo(null);
	        JPanel seacou = new JPanel();
	        JLabel cno = new JLabel("请输入课程号");
	        cnotext  = new JTextField();//
	        JButton ok = new JButton("确定");
	        JButton reset = new JButton("重置");
	         
	        seacou.setLayout(null);
	         
	        cno.setBounds(5,5,80,20);
	        cnotext.setBounds(90,5,120,20);
	        ok.setBounds(50,60,60,20);
	        reset.setBounds(130,60,60,20);
	         
	        seacou.add(cno);
	        seacou.add(cnotext);
	        seacou.add(ok);
	        seacou.add(reset);
	         
	        SeaCou.add(seacou);
	        SeaCou.setVisible(true);

	        SeaCou.addWindowListener(new WindowAdapter(){
	           @Override
	           public void windowClosing(WindowEvent e) {
	            SystemUI.clickable();
	           }
	          });
	        ok.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent arg0) {
	                columnNames=new Vector<Object>();
	                //设置列名
	                columnNames.add("课程号");
	                columnNames.add("课程名");
	                columnNames.add("先修课课程号");
	                columnNames.add("教师名");
	                columnNames.add("学分");
	                columnNames.add("学时");
	                columnNames.add("地点");
	                columnNames.add("时间");
	                columnNames.add("状态");
	                String str = cnotext.getText();
	                //rowData = new Vector<Object>();
	                int count=0;
	                //rowData可以存放多行,开始从数据库里取
	                try {
	                    //加载驱动
	                    Class.forName("com.mysql.jdbc.Driver");
	                    //得到连接
	                    ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
	                			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
	                    if(!cnotext.getText().trim().equals("")) {
	                    ps=ct.prepareStatement("select * from course where courseid='"+str+"'");
	                    
	                    rs=ps.executeQuery();
	                    }
	                    else if(cnotext.getText().trim().equals("")) {
	                    	ps=ct.prepareStatement("select * from course ");
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
	                        hang.add(rs.getInt(5));
	                        hang.add(rs.getInt(6));
	                        hang.add(rs.getString(7));
	                        hang.add(rs.getString(8));
	                        hang.add(rs.getString(9));
	                        //加入到rowData
	                        rowData0.add(hang);
	                        count++;
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
	                    table3 = new JTable(rowData0,columnNames){
	                     
							private static final long serialVersionUID = -8656035973692724899L;

							public boolean isCellEditable(int row, int column)
	                        {return false;}//表格不允许被编辑
	                    };
	                    table3.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//只允许选中一行
	                    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
	                    tcr.setHorizontalAlignment(JLabel.CENTER);// 这句和上句作用一样
	                    table3.setDefaultRenderer(Object.class, tcr);
	                    SystemUI.scrollPane3.setViewportView(table3);
	                    SeaCou.dispose();
	                    SystemUI.clickable();}
	                if(count==0) {
	                    JOptionPane.showMessageDialog(null, "没有查询到相关消息！");
	                }
	            }   
	        });
	        reset.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent arg0) {
	                cnotext.setText("");
	            }
	        });
		}

		public void refreshCourse() {
			// TODO 自动生成的方法存根
			new Course();
			SystemUI.scrollPane3.setViewportView(Course.table3);
		}

	}



