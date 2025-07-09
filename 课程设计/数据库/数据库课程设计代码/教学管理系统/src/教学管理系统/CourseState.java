package 教学管理系统;

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
	//定义数据库需要的全局变量
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;//将对数据库查询的结果保存进resultset的对象rs中
	
	public CourseState(){
		columnNames=new Vector<Object>();
		//设置列名
		columnNames.add("状态代码");
		columnNames.add("描述");
		//rowData可以存放多行,开始从数据库里取    
		try {	
		String userMySql="root"; //zhangsan
		String passwordMySql="123456";//12345678
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//得到连接，并获取连接对象ct
			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoxueguanlixit?user="
			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT");
			
			ps=ct.prepareStatement("select * from coursestate");//向mysql发送动态的sql语句
			
			rs=ps.executeQuery();//使用select语句时用executeQuery执行，executeupdate用于执行insert，update，delete，execute用于返回多个结果集的语句。
			
			while(rs.next()){
				//rowData可以存放多行
				Vector<Object> hang=new Vector<Object>();
				hang.add(rs.getInt(1));
				hang.add(rs.getString(2));
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
		table6=new JTable(rowData,columnNames) {
			private static final long serialVersionUID=-3229560868878458304L;
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
        table6.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//只允许选中一行
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
        tcr.setHorizontalAlignment(JLabel.CENTER);// 这句和上句作用一样
        table6.setDefaultRenderer(Object.class, tcr);
	}
	

	public void refreshCourseState() {
		// TODO 自动生成的方法存根
		new CourseState();
		SystemUI.scrollPane6.setViewportView(CourseState.table6);
	}
}



