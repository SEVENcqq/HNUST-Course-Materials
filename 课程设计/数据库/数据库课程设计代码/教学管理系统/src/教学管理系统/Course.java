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


	public class Course {
		private Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
		String userMySql="root"; //
		String passwordMySql="123456"; //
		Vector<Object> columnNames;
		//�������ݿ���Ҫ��ȫ�ֱ���
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
			//setIconImage(Toolkit.getDefaultToolkit().getImage("image\\У��.jpg"));
			columnNames=new Vector<Object>();
			//��������
			columnNames.add("�γ̺�");
			columnNames.add("�γ���");
			columnNames.add("���޿ογ̺�");
			columnNames.add("��ʦ��");//
			columnNames.add("ѧ��");
			columnNames.add("ѧʱ");//
			columnNames.add("�ص�");//
			columnNames.add("ʱ��");	//
			columnNames.add("״̬");	

			//rowData = new Vector<Object>();
			//rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ
			
			try {
				String userMySql="root"; 
            	String passwordMySql="123456";
				//��������
				Class.forName("com.mysql.jdbc.Driver");
				//�õ�����
				ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
        					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
				
				ps=ct.prepareStatement("select * from course");
				
				rs=ps.executeQuery();
				
				while(rs.next()){
					//rowData���Դ�Ŷ���
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
			table3 = new JTable(rowData,columnNames){
				private static final long serialVersionUID = 6843971351064905400L;

				public boolean isCellEditable(int row, int column)
	            {return false;}//��������༭
	        };
	        table3.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//ֻ����ѡ��һ��
	        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
	        tcr.setHorizontalAlignment(JLabel.CENTER);// �����Ͼ�����һ��
	        table3.setDefaultRenderer(Object.class, tcr);
		}
		public void addCourse() {           //���ӿγ�
			// TODO �Զ����ɵķ������
			SystemUI.unclickable();
	        AddCou = new JFrame("��ӿγ���Ϣ");//
	        AddCou.setSize(500, 400);//250,250
	       // AddCou.setLocation(600, 300);
	        AddCou.setLocationRelativeTo(null);
	        JPanel addCou = new JPanel();
	        JLabel cno = new JLabel("�γ̺�");
	        JLabel cname = new JLabel("�γ���");
	        JLabel cpno = new JLabel("���޿�");
	        JLabel credit = new JLabel("ѧ��");
	        JLabel cremark = new JLabel("״̬");
	        JLabel tname = new JLabel("��ʦ��");//
	        JLabel period = new JLabel("ѧʱ");//
	        JLabel place = new JLabel("�ص�");//
	        JLabel time = new JLabel("ʱ��");//
	        
	        cnotext  = new JTextField();//
	        cnametext = new JTextField();//
	        cpnotext  = new JTextField();//
	        tnametext = new JTextField();//
	        credittext  = new JTextField();//
	        periodtext  = new JTextField();//
	        placetext  = new JTextField();//
	        timetext  = new JTextField();//
	        cremarktext  = new JTextField();//
	        
	        JButton ok = new JButton("ȷ��");
	        JButton reset = new JButton("����");
	         
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
		         			//��������
		         			Class.forName("com.mysql.jdbc.Driver");
		         			//�õ�����
		         			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
		        					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
		         			String sql;
		         			sql = "insert into course values('"+no+"','"+name+"','"+pno+"','"+tn+"',"+credit+","+period+",'"+pl+"','"+ti+"','"+remark+"')";
		         			ps=ct.prepareStatement(sql);
		         			ps.executeUpdate();
		         			JOptionPane.showMessageDialog(null, "��ӳɹ���");
		         			AddCou.dispose();
		         			SystemUI.clickable();
		         			refreshCourse();
		         		
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
			// TODO �Զ����ɵķ������
		    SystemUI.unclickable();
		    UpdateCou = new JFrame("�޸Ŀγ���Ϣ");//
		    UpdateCou.setSize(500, 400);
		    //UpdateCou.setLocation(600, 300);
		    UpdateCou.setLocationRelativeTo(null);
		    JPanel updatecou = new JPanel();
		    JLabel ycno = new JLabel("Ҫ�޸ĵĿγ̺�");
		    JLabel cpno = new JLabel("Ҫ�ĳɵ����޿�");
		    JLabel tname = new JLabel("Ҫ�ĳɵĽ�ʦ��");
		    JLabel credit = new JLabel("Ҫ�ĳɵ�ѧ��");
		    JLabel period = new JLabel("Ҫ�ĳɵ�ѧʱ");
		    JLabel place = new JLabel("Ҫ�ĳɵĵص�");
		    JLabel time = new JLabel("Ҫ�ĳɵ�ʱ��");
		    JLabel cremark = new JLabel("Ҫ�ĳɵ�״̬");
	    
		    
		    ycnotext = new JTextField();//
		    cpnotext  = new JTextField();
		    tnametext  = new JTextField();
		    credittext  = new JTextField();
		    periodtext  = new JTextField();
		    placetext  = new JTextField();
		    timetext  = new JTextField();
		    cremarktext  = new JTextField();
		    
		    JButton ok = new JButton("ȷ��");
		    JButton reset = new JButton("����");
		     
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
		                    //��������
		                    Class.forName("com.mysql.jdbc.Driver");
		                    //�õ�����
		                    ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
		                			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
		                    String sql;	            
		                    sql = "update course set beforecourse ='"+pno+"', teachername ='"+tn+"', xuefen ='"+credit+"', xueshi ='"+period+"', didian ='"+pl+"', shijian ='"+ti+"', leixing ='"+remark+"' where courseid='"+yno+"'";
		                    ps=ct.prepareStatement(sql);
		                    ps.executeUpdate();
		                    JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
		                    UpdateCou.dispose();
		                    SystemUI.clickable();
		                    refreshCourse();
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
			// TODO �Զ����ɵķ������
			int row = table3.getSelectedRow();
	    	if ( row >= 0 ) {
	    		String string[] = new String[3];
	    		string[0] = String.valueOf(table3.getValueAt(row, 0));
	    		string[1] = String.valueOf(table3.getValueAt(row, 1));
	    		try {
	    			//��������
	    			Class.forName("com.mysql.jdbc.Driver");
	    			//�õ�����
	    			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
	    					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
	    			ps=ct.prepareStatement("delete from course where courseid='"+string[0]+"' and coursename='"+string[1]+"'");
	    			ps.executeUpdate();
	    			JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
	    			refreshCourse();
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

		public void searchCourse() {
			// TODO �Զ����ɵķ������
			final Vector<Vector<Object>> rowData0 = new Vector<Vector<Object>>();
	        SystemUI.unclickable();
	        SeaCou = new JFrame("���ҿγ���Ϣ");//
	        SeaCou.setSize(500, 200);
	        SeaCou.setLocationRelativeTo(null);
	        JPanel seacou = new JPanel();
	        JLabel cno = new JLabel("������γ̺�");
	        cnotext  = new JTextField();//
	        JButton ok = new JButton("ȷ��");
	        JButton reset = new JButton("����");
	         
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
	                //��������
	                columnNames.add("�γ̺�");
	                columnNames.add("�γ���");
	                columnNames.add("���޿ογ̺�");
	                columnNames.add("��ʦ��");
	                columnNames.add("ѧ��");
	                columnNames.add("ѧʱ");
	                columnNames.add("�ص�");
	                columnNames.add("ʱ��");
	                columnNames.add("״̬");
	                String str = cnotext.getText();
	                //rowData = new Vector<Object>();
	                int count=0;
	                //rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ
	                try {
	                    //��������
	                    Class.forName("com.mysql.jdbc.Driver");
	                    //�õ�����
	                    ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
	                			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
	                    if(!cnotext.getText().trim().equals("")) {
	                    ps=ct.prepareStatement("select * from course where courseid='"+str+"'");
	                    
	                    rs=ps.executeQuery();
	                    }
	                    else if(cnotext.getText().trim().equals("")) {
	                    	ps=ct.prepareStatement("select * from course ");
	                        rs=ps.executeQuery();
	                        JOptionPane.showMessageDialog(null, "�������ѯ��Ϣ��");
	                    }
	                    while(rs.next()){
	                        //rowData���Դ�Ŷ���
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
	                    table3 = new JTable(rowData0,columnNames){
	                     
							private static final long serialVersionUID = -8656035973692724899L;

							public boolean isCellEditable(int row, int column)
	                        {return false;}//��������༭
	                    };
	                    table3.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//ֻ����ѡ��һ��
	                    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
	                    tcr.setHorizontalAlignment(JLabel.CENTER);// �����Ͼ�����һ��
	                    table3.setDefaultRenderer(Object.class, tcr);
	                    SystemUI.scrollPane3.setViewportView(table3);
	                    SeaCou.dispose();
	                    SystemUI.clickable();}
	                if(count==0) {
	                    JOptionPane.showMessageDialog(null, "û�в�ѯ�������Ϣ��");
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
			// TODO �Զ����ɵķ������
			new Course();
			SystemUI.scrollPane3.setViewportView(Course.table3);
		}

	}



