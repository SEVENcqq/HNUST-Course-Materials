package ��ѧ����ϵͳ;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class Score {
	String userMySql="root"; //
	String passwordMySql="123456"; //
	private Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
	//Vector<Object> rowData;
	Vector<Object> columnNames;
	//�������ݿ���Ҫ��ȫ�ֱ���
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	static JTable table4=null;
	static JTable table6=null;
	static JTable table7=null;
	
    JTextField scsnotext  = null;      
    JTextField sccnotext = null;
    JTextField scoretext  = null;
    JTextField yscsnotext = null;
    JTextField ysccnotext = null;
    JTextField cnotext  = null;
    JTextField snotext  = null;

    JFrame Addsc = null;
    JFrame UpdateSC = null;
    JFrame SeaSC = null;
	
	public Score(){
		columnNames=new Vector<Object>();
		//��������
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�γ̺�");
		columnNames.add("�γ���");
		columnNames.add("�ɼ�");
			
		//rowData = new Vector<Object>();
		//rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ
		
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//�õ�����
			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
			ps=ct.prepareStatement("select * from score");
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				//rowData���Դ�Ŷ���
				Vector<Object> hang=new Vector<Object>();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getInt(5));
				
				
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
		table4 = new JTable(rowData,columnNames){
           
			private static final long serialVersionUID = 1525421110274312344L;

			public boolean isCellEditable(int row, int column)
            {return false;}//��������༭
        };
        table4.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//ֻ����ѡ��һ��
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
        tcr.setHorizontalAlignment(JLabel.CENTER);// �����Ͼ�����һ��
        table4.setDefaultRenderer(Object.class, tcr);
	}
	public void addScore() {
		// TODO �Զ����ɵķ������
		SystemUI.unclickable();
        Addsc = new JFrame("��ӳɼ���Ϣ");//
        Addsc.setSize(500, 300);
        Addsc.setLocationRelativeTo(null);
        JPanel addsc = new JPanel();
        JLabel scsno = new JLabel("ѧ��");        
        JLabel sccno = new JLabel("�γ̺�");
        JLabel score = new JLabel("�ɼ�");
        scsnotext  = new JTextField(); //     
        sccnotext = new JTextField();//
        scoretext  = new JTextField();//
        
        JButton ok = new JButton("ȷ��");
        JButton reset = new JButton("����");
         
        addsc.setLayout(null);
        scsno.setBounds(5,5,70,20);
        scsnotext.setBounds(80,5,120,20);
        sccno.setBounds(5,30,70,20);
        sccnotext.setBounds(80,30,120,20);
        score.setBounds(5,60,70,20);
        scoretext.setBounds(80,60,120,20);
        ok.setBounds(50,100,60,20);
        reset.setBounds(130,100,60,20);
         
        addsc.add(scsno);
        addsc.add(scsnotext);
        addsc.add(sccno);
        addsc.add(sccnotext);
        addsc.add(score);
        addsc.add(scoretext);        
        addsc.add(ok);
        addsc.add(reset);
 
        Addsc.add(addsc);
        Addsc.setVisible(true);
         
		Addsc.addWindowListener(new WindowAdapter(){
	     	   @Override
	     	   public void windowClosing(WindowEvent e) {
	     		SystemUI.clickable();
	     	   }
	     	  });
	         ok.addActionListener(new ActionListener(){
	             public void actionPerformed(ActionEvent arg0) {
	             	try {
	             		String sno = scsnotext.getText();
	             		String cno = sccnotext.getText();
	             		int score = Integer.valueOf(scoretext.getText()).intValue();
	             		int count=0;
	         			//��������
	         			Class.forName("com.mysql.jdbc.Driver");
	         			//�õ�����
	         			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
	        					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
	         			ps=ct.prepareStatement("select studentname from selectcourse where studentid='"+sno+"' and courseid='"+cno+"'");
	         			rs=ps.executeQuery();
	         			while(rs.next()){
	                        count++;
	                    }
	         			if(count==0) {
	         				JOptionPane.showMessageDialog(null, "����û��ѡ�˿γ̣�");
	         			}
	         			if(count!=0) {
	         			ps=ct.prepareStatement("insert into score values('"+sno+"',(select studentname from selectcourse where studentid='"+sno+"' and courseid='"+cno+"'),'"+cno+"',(select coursename from selectcourse where courseid='"+cno+"' and studentid='"+sno+"'),"+score+")");
	         			ps.executeUpdate();
	         			JOptionPane.showMessageDialog(null, "��ӳɹ���");
	         			Addsc.dispose();
	         			SystemUI.clickable();
	         			refreshScore();
	         			}
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
                scsnotext.setText("");
                sccnotext.setText("");
                scoretext.setText("");
            }
        });
	}

	public void updateScore() {
		// TODO �Զ����ɵķ������
		SystemUI.unclickable();
        UpdateSC = new JFrame("�޸ĳɼ���Ϣ");//
        UpdateSC.setSize(500, 300);
        UpdateSC.setLocationRelativeTo(null);
        JPanel updatesc = new JPanel();
        JLabel yscsno = new JLabel("Ҫ�޸ĵ�ѧ��");
        JLabel scsno = new JLabel("Ҫ�ĳɵ�ѧ��");
        JLabel ysccno = new JLabel("Ҫ�޸ĵĿγ̺�");        
        JLabel sccno = new JLabel("Ҫ�ĳɵĿγ̺�");
        JLabel score = new JLabel("�ɼ�");
        yscsnotext = new JTextField();//
        scsnotext  = new JTextField();//
        ysccnotext = new JTextField(); //     
        sccnotext = new JTextField();//
        scoretext  = new JTextField();//
        
        JButton ok = new JButton("ȷ��");
        JButton reset = new JButton("����");
         
        updatesc.setLayout(null);
        yscsno.setBounds(5,5,100,20);
        yscsnotext.setBounds(110,5,120,20);
        scsno.setBounds(5,30,100,20);
        scsnotext.setBounds(110,30,120,20);
        ysccno.setBounds(5,60,100,20);
        ysccnotext.setBounds(110,60,120,20);
        sccno.setBounds(5,90,100,20);
        sccnotext.setBounds(110,90,120,20);
        score.setBounds(5,120,100,20);
        scoretext.setBounds(110,120,120,20);
        ok.setBounds(50,160,60,20);
        reset.setBounds(120,160,60,20);
        
        updatesc.add(yscsno);
        updatesc.add(yscsnotext); 
        updatesc.add(scsno);
        updatesc.add(scsnotext);
        updatesc.add(ysccno);
        updatesc.add(ysccnotext);
        updatesc.add(sccno);
        updatesc.add(sccnotext);
        updatesc.add(score);
        updatesc.add(scoretext);        
        updatesc.add(ok);
        updatesc.add(reset);
 
        UpdateSC.add(updatesc);
        UpdateSC.setVisible(true);
         
        UpdateSC.addWindowListener(new WindowAdapter(){
               @Override
               public void windowClosing(WindowEvent e) {
                SystemUI.clickable();
               }
              });
             ok.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent arg0) {
                    try {
                        String ysno=yscsnotext.getText();
                        String ycno=ysccnotext.getText();
                        String sno = scsnotext.getText();
                        String cno = sccnotext.getText();
                        int score = Integer.valueOf(scoretext.getText()).intValue();
                        int count=0;
                        //��������
                        Class.forName("com.mysql.jdbc.Driver");
                        //�õ�����
                        ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit","root","123456");
                        ps=ct.prepareStatement("select studentname from selectcourse where studentid='"+sno+"' and courseid='"+cno+"'");
	         			rs=ps.executeQuery();
	         			while(rs.next()){
	                        count++;
	                    }
	         			if(count==0) {
	         				JOptionPane.showMessageDialog(null, "����û��ѡ�˿γ̣�");
	         			}
	         			if(count!=0) {
                        ps=ct.prepareStatement("update score set studentid ='"+sno+"', courseid ='"+cno+"',studentname=(select studentname from selectcourse where studentid='"+sno+"'and courseid ='"+cno+"'), coursename =(select coursename from selectcourse where courseid='"+cno+"'and studentid='"+sno+"'), score ="+score+" where studentid='"+ysno+"'and courseid='"+ycno+"'");
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
                        UpdateSC.dispose();
                        SystemUI.clickable();
                        refreshScore();
	         			}
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
                scsnotext.setText("");
                sccnotext.setText("");
                scoretext.setText("");
            }
        });
	}

	public void deleteScore() {
		// TODO �Զ����ɵķ������
		int row = table4.getSelectedRow();
    	if ( row >= 0 ) {
    		String string[] = new String[4];
    		string[0] = (String) table4.getValueAt(row, 0);
    		string[1] = (String) table4.getValueAt(row, 1);
    		string[2] = (String) table4.getValueAt(row, 2);
    		string[3] = (String) table4.getValueAt(row, 3);
    		try {
    			//��������
    			Class.forName("com.mysql.jdbc.Driver");
    			//�õ�����
    			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
    					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
    			ps=ct.prepareStatement("delete from score where studentid='"+string[0]+"' and studentname='"+string[1]+"' and courseid='"+string[2]+"' and coursename='"+string[3]+"'");
    			ps.executeUpdate();
    			JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
    			refreshScore();
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

	public void searchScore() {
		// TODO �Զ����ɵķ������
		final Vector<Vector<Object>> rowData0 = new Vector<Vector<Object>>();
        SystemUI.unclickable();
        SeaSC = new JFrame("���ҳɼ���Ϣ");//
        SeaSC.setSize(500, 200);
        SeaSC.setLocationRelativeTo(null);
        JPanel seasc = new JPanel();
        JLabel cno = new JLabel("������γ̺�");
        cnotext  = new JTextField();//
        JLabel sno = new JLabel("������ѧ��");
        snotext  = new JTextField();//
        JButton ok = new JButton("ȷ��");
        JButton reset = new JButton("����");
         
        seasc.setLayout(null);
         
        cno.setBounds(5,5,80,20);
        cnotext.setBounds(90,5,120,20);
        sno.setBounds(5,30,80,20);
        snotext.setBounds(90,30,120,20);
        ok.setBounds(50,70,60,20);
        reset.setBounds(130,70,60,20);
         
        seasc.add(cno);
        seasc.add(cnotext);
        seasc.add(sno);
        seasc.add(snotext);
        seasc.add(ok);
        seasc.add(reset);
         
        SeaSC.add(seasc);
        SeaSC.setVisible(true);

        SeaSC.addWindowListener(new WindowAdapter(){
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
                columnNames.add("�γ̺�");
                columnNames.add("�γ���");
                columnNames.add("�ɼ�");
                String str = cnotext.getText();
                String str1 = snotext.getText();
                //rowData = new Vector<Object>();
                int count=0;
                //rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ
                try {
                    //��������
                    Class.forName("com.mysql.jdbc.Driver");
                    //�õ�����
                    ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit","root","123456");
                    if((str1.equals(Login.NO)&&Login.level==1)||Login.level==3){//���Ʋ�ѯ�ɼ���Χ
                      if(snotext.getText().trim().equals("")&&!cnotext.getText().trim().equals("")){
                          ps=ct.prepareStatement("select * from score where courseid='"+str+"'");
                          rs=ps.executeQuery();
                      }
                      if(cnotext.getText().trim().equals("")&&!snotext.getText().trim().equals("")){
                          ps=ct.prepareStatement("select * from score where studentid='"+str1+"'");
                          rs=ps.executeQuery();
                      }
                      if(!cnotext.getText().trim().equals("")&&!snotext.getText().trim().equals("")){
                          ps=ct.prepareStatement("select * from score where studentid='"+str1+"' and courseid='"+str+"'");
                          rs=ps.executeQuery();
                      }
                      if(cnotext.getText().trim().equals("")&&snotext.getText().trim().equals("")){
                          ps=ct.prepareStatement("select * from score order by score desc ");
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
                          //���뵽rowData
                          rowData0.add(hang);
                          count++;
                      }
                    }
                    else{
                   	    JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
                        SystemUI.unclickable();
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
                    table4 = new JTable(rowData0,columnNames){
						private static final long serialVersionUID = -518524586936803727L;
						public boolean isCellEditable(int row, int column)
                        {return false;}//��������༭
                    };
                    table4.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//ֻ����ѡ��һ��
                    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
                    tcr.setHorizontalAlignment(JLabel.CENTER);// �����Ͼ�����һ��
                    table4.setDefaultRenderer(Object.class, tcr);
                    SystemUI.scrollPane4.setViewportView(table4);
                    SeaSC.dispose();
                    SystemUI.clickable();}
                if(count==0&&((str1.equals(Login.NO)&&Login.level==1)||Login.level==3)) {
                    JOptionPane.showMessageDialog(null, "û�в�ѯ�������Ϣ��");
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

	public void refreshScore() {
		// TODO �Զ����ɵķ������
		new Score();
		SystemUI.scrollPane4.setViewportView(Score.table4);
	}
	
	
	
	public void avgrank() {//
		// TODO �Զ����ɵķ������
		final Vector<Vector<Object>> rowData0 = new Vector<Vector<Object>>();
		JFrame jf = new JFrame("ƽ��������");//
		jf.setVisible(true);
		jf.setSize(500, 300);
		jf.setLocationRelativeTo(null);
		JScrollPane js=new JScrollPane();
		jf.add(js,BorderLayout.CENTER);
		columnNames=new Vector<Object>();
		//��������
		columnNames.add("����");
		columnNames.add("ѧ��");
		columnNames.add("ƽ����");//
		//rowData = new Vector<Object>();
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//�õ�����
			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
					+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
    			ps=ct.prepareStatement("select studentid,avg(score) avg_grade from score group by score.studentid having count(courseid)>=1 order by avg_grade desc");//
    			rs=ps.executeQuery();
    			int rank=0;
    			while(rs.next()){
    				//rowData���Դ�Ŷ���
    				rank++;
    				Vector<Object> hang=new Vector<Object>();
    				hang.add(rank);
    				hang.add(rs.getString(1));
    				hang.add(rs.getString(2));
    				//���뵽rowData
    				rowData0.add(hang);
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
		table7 = new JTable(rowData0,columnNames){
			private static final long serialVersionUID = 3701216243676371199L;
			public boolean isCellEditable(int row, int column)
            {return false;}//��������༭
        };
        table7.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//ֻ����ѡ��һ��
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
        tcr.setHorizontalAlignment(JLabel.CENTER);// �����Ͼ�����һ��
        table7.setDefaultRenderer(Object.class, tcr);
        js.setViewportView(table7);
	}
}



