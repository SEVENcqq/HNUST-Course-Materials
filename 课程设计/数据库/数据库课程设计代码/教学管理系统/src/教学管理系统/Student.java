package ��ѧ����ϵͳ;
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


public class Student {
		private Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
		String userMySql="root"; //
		String passwordMySql="123456";//
		//Vector<Object> rowData,columnNames;
		Vector<Object> columnNames;
		static JTable table2=null;
		//�������ݿ���Ҫ��ȫ�ֱ���
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		
		JTextField snotext = null;
		JTextField snametext = null;
		JTextField sclasstext  = null;
		
	    JTextField upnotext = null;
	    JTextField ssextext  = null;	   
	    JTextField upclasstext  = null;
		
		JFrame SeaStu = null;
		JFrame AddStu = null;
		JFrame UpdateStu = null;
		
		public Student(){
			columnNames=new Vector<Object>();
			//��������
			columnNames.add("ѧ��");
			columnNames.add("����");
			columnNames.add("�༶");		
			//rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ
			
			try {
				String userMySql="root"; 
				String passwordMySql="123456";
				//��������
				Class.forName("com.mysql.jdbc.Driver");
				//�õ�����
				ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
						+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
				
				ps=ct.prepareStatement("select * from student");
				
				rs=ps.executeQuery();
				
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
			table2 = new JTable(rowData,columnNames){
	            
				private static final long serialVersionUID = -4333506764666173598L;

				public boolean isCellEditable(int row, int column)
	            {return false;}//��������༭
	        };
	        table2.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//ֻ����ѡ��һ��
	        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
	        tcr.setHorizontalAlignment(JLabel.CENTER);// �����Ͼ�����һ��
	        table2.setDefaultRenderer(Object.class, tcr);
		}
		public void refreshStudent() {
			// TODO �Զ����ɵķ������
			new Student();
			SystemUI.scrollPane2.setViewportView(Student.table2);
		}

		public void searchStudent() {
			// TODO �Զ����ɵķ������
			final Vector<Vector<Object>> rowData0 = new Vector<Vector<Object>>();
	        SystemUI.unclickable();
	        SeaStu = new JFrame("����ѧ����Ϣ");
	        SeaStu.setSize(500, 200);
	       // SeaStu.setLocation(600, 300);
	        SeaStu.setLocationRelativeTo(null);
	        JPanel seastu = new JPanel();
	        JLabel sno = new JLabel("������ѧ��");
	        snotext  = new JTextField();
	        JButton ok = new JButton("ȷ��");
	        JButton reset = new JButton("����");
	         
	        seastu.setLayout(null);
	         
	        sno.setBounds(5,30,70,20);
	        snotext.setBounds(80,30,120,20);
	        ok.setBounds(50,70,60,20);
	        reset.setBounds(130,70,60,20);
	         
	        seastu.add(sno);
	        seastu.add(snotext);
	        seastu.add(ok);
	        seastu.add(reset);
	         
	        SeaStu.add(seastu);
	        SeaStu.setVisible(true);

	        SeaStu.addWindowListener(new WindowAdapter(){
	           @Override
	           public void windowClosing(WindowEvent e) {
	            SystemUI.clickable();
	           }
	          });
	        ok.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent arg0) {
	                columnNames=new Vector<Object>();
	                //��������
	                columnNames.add("ѧ��");
	                columnNames.add("����");
	               
	                columnNames.add("�༶");
	                String str = snotext.getText();
	                //rowData = new Vector<Object>();
	                int count=0;
	                //rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ
	                try {
	                    //��������
	                    Class.forName("com.mysql.jdbc.Driver");
	                    //�õ�����
	                    ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
	    						+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
	                    if(!snotext.getText().trim().equals("")) {
	                    	
	                    ps=ct.prepareStatement("select * from student where studentid='"+str+"'");
	                    
	                    rs=ps.executeQuery();
	                    }
	                    if(snotext.getText().trim().equals("")) {
	                    	ps=ct.prepareStatement("select * from student ");
	                    	JOptionPane.showMessageDialog(null, "�������ѯ��Ϣ��");
	                        rs=ps.executeQuery();	
	                    }
	                    while(rs.next()){
	                        //rowData���Դ�Ŷ���
	                        Vector<Object> hang=new Vector<Object>();
	                        hang.add(rs.getInt(1));
	                        hang.add(rs.getString(2));
	                        hang.add(rs.getString(3));
	                       
	                        //���뵽rowData
	                        rowData0.add(hang);//
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
	                    table2 = new JTable(rowData0,columnNames){
	                       
							private static final long serialVersionUID = -8131400305374145108L;

							public boolean isCellEditable(int row, int column)
	                        {return false;}//��������༭
	                    };
	                    table2.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//ֻ����ѡ��һ��
	                    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
	                    tcr.setHorizontalAlignment(JLabel.CENTER);// �����Ͼ�����һ��
	                    table2.setDefaultRenderer(Object.class, tcr);
	                    SystemUI.scrollPane2.setViewportView(table2);
	                    SeaStu.dispose();
	                    SystemUI.clickable();}
	                if(count==0) {
	                    JOptionPane.showMessageDialog(null, "���޴��ˣ�");
	                }
	            }   
	        });
	        reset.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent arg0) {
	                snotext.setText("");
	            }
	        });
		}

		public void deleteStudent() {
			// TODO �Զ����ɵķ������
			int row = table2.getSelectedRow();
	    	if ( row >= 0 ) {
	    		String string[] = new String[3];
	    		string[0] = String.valueOf(table2.getValueAt(row, 0));
	    		string[1] = String.valueOf(table2.getValueAt(row, 1));
	    		try {
	    			//��������
	    			Class.forName("com.mysql.jdbc.Driver");
	    			//�õ�����
	    			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
	    					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
	    			ps=ct.prepareStatement("delete from student where studentid='"+string[0]+"' and studentname='"+string[1]+"'");
	    			ps.executeUpdate();
	    			JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
	    			refreshStudent();
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

		public void addStudent() {
			// TODO �Զ����ɵķ������
			SystemUI.unclickable();
	        AddStu = new JFrame("���ѧ����Ϣ");//
	        AddStu.setSize(500, 300);
	        //AddStu.setLocation(600, 300);
	        AddStu.setLocationRelativeTo(null);
	        JPanel addStu = new JPanel();
	        JLabel sno = new JLabel("ѧ��");
	        JLabel sname = new JLabel("����");
	       
	        JLabel sclass = new JLabel("�༶");
	        snotext  = new JTextField();//
	        snametext = new JTextField();//
	        sclasstext  = new JTextField();//
	        JButton ok = new JButton("ȷ��");
	        JButton reset = new JButton("����");
	         
	        addStu.setLayout(null);
	         
	        sno.setBounds(5,5,70,20);
	        snotext.setBounds(80,5,120,20);
	        sname.setBounds(5,30,70,20);
	        snametext.setBounds(80,30,120,20);
	       
	        sclass.setBounds(5,60,70,20);
	        sclasstext.setBounds(80,60,120,20);
	        ok.setBounds(50,190,60,20);
	        reset.setBounds(130,190,60,20);
	         
	        addStu.add(sno);
	        addStu.add(snotext);
	        addStu.add(sname);
	        addStu.add(snametext);
	        
	        addStu.add(sclass);
	        addStu.add(sclasstext);
	        addStu.add(ok);
	        addStu.add(reset);
	         
	        AddStu.add(addStu);
	        AddStu.setVisible(true);
	         
	        AddStu.addWindowListener(new WindowAdapter(){
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
	     
	             		String name = snametext.getText();
	             		
	             		String sclass = sclasstext.getText();
	             		//String no = snotext.getText();
	             		 int no = Integer.valueOf(snotext.getText()).intValue();
	             		 
	         			//��������
	         			Class.forName("com.mysql.jdbc.Driver");
	         			//�õ�����
	         			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
	        					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
	         			ps=ct.prepareStatement("insert into student values('"+no+"','"+name+"','"+sclass+"')");
	         			ps.executeUpdate();
	         			JOptionPane.showMessageDialog(null, "��ӳɹ���");
	         			AddStu.dispose();
	         			SystemUI.clickable();
	         			refreshStudent();
	         			
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
	                 
	                snotext.setText("");
	                snametext.setText("");
	               
	                sclasstext.setText("");
	            }
	        });
		}
//�޸�ѧ����Ϣ��
		public void updateStudent() {
			// TODO �Զ����ɵķ������
		    SystemUI.unclickable();
		    UpdateStu = new JFrame("�޸�ѧ����Ϣ");//
		    UpdateStu.setSize(500, 300);
		    //UpdateStu.setLocation(600, 300);
		    UpdateStu.setLocationRelativeTo(null);
		    JPanel updatestu = new JPanel();
		    JLabel upno = new JLabel("Ҫ�޸ĵ�ѧ��");
		    JLabel ssex = new JLabel("Ҫ�ĳɵ�����");
		    
		    JLabel upclass = new JLabel("Ҫ�ĳɵİ༶");
		    upnotext = new JTextField();//
		    ssextext  = new JTextField();//
		   
		    upclasstext  = new JTextField();//
		    JButton ok = new JButton("ȷ��");
		    JButton reset = new JButton("����");
		     
		    updatestu.setLayout(null);
		    
		    upno.setBounds(5,5,100,20);
		    upnotext.setBounds(110,5,120,20); 
		    ssex.setBounds(5,30,100,20);
		    ssextext.setBounds(110,30,120,20);
		   
		    upclass.setBounds(5,60,100,20);
		    upclasstext.setBounds(110,60,120,20);
		    ok.setBounds(50,170,60,20);
		    reset.setBounds(120,170,60,20);
		    
		    updatestu.add(upno);
		    updatestu.add(upnotext); 
		    updatestu.add(ssex);
		    updatestu.add(ssextext);
		  
		    updatestu.add(upclass);
		    updatestu.add(upclasstext);
		    updatestu.add(ok);
		    updatestu.add(reset);
		     
		    UpdateStu.add(updatestu);
		    UpdateStu.setVisible(true);
		     
		    UpdateStu.addWindowListener(new WindowAdapter(){
		       @Override
		       public void windowClosing(WindowEvent e) {
		        SystemUI.clickable();
		       }
		      });
		     ok.addActionListener(new ActionListener(){
		         public void actionPerformed(ActionEvent arg0) {
		            try {
		                String sclass = upclasstext.getText();
		                String sname=ssextext.getText();
		               // String yno=upnotext.getText();
		                int yno = Integer.valueOf(upnotext.getText()).intValue();
		                 
		                //��������
		                Class.forName("com.mysql.jdbc.Driver");
		                //�õ�����
		                ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
		            			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
		                ps=ct.prepareStatement("update student set  class ='"+sclass+"',studentname='"+sname+"' where studentid='"+yno+"'");
		                ps.executeUpdate();
		                JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
		                UpdateStu.dispose();
		                SystemUI.clickable();
		                refreshStudent();
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
		           
		            upclasstext.setText("");
		        }
		    });
		}

}


