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
import java.util.Date;


public class CourseState {
	Vector<Object> columnNames;
	
	private Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
	static JTable table6=null;
	//�������ݿ���Ҫ��ȫ�ֱ���
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;//�������ݿ��ѯ�Ľ�������resultset�Ķ���rs��
	
	public CourseState(){
		columnNames=new Vector<Object>();
		//��������
		columnNames.add("״̬����");
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
			
			ps=ct.prepareStatement("select * from coursestate");//��mysql���Ͷ�̬��sql���
			
			rs=ps.executeQuery();//ʹ��select���ʱ��executeQueryִ�У�executeupdate����ִ��insert��update��delete��execute���ڷ��ض�����������䡣
			
			while(rs.next()){
				//rowData���Դ�Ŷ���
				Vector<Object> hang=new Vector<Object>();
				hang.add(rs.getInt(1));
				hang.add(rs.getString(2));
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
		table6=new JTable(rowData,columnNames) {
			private static final long serialVersionUID=-3229560868878458304L;
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
        table6.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//ֻ����ѡ��һ��
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
        tcr.setHorizontalAlignment(JLabel.CENTER);// �����Ͼ�����һ��
        table6.setDefaultRenderer(Object.class, tcr);
	}
	

	public void refreshCourseState() {
		// TODO �Զ����ɵķ������
		new CourseState();
		SystemUI.scrollPane6.setViewportView(CourseState.table6);
	}
}



