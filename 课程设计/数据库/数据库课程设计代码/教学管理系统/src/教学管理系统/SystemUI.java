package 教学管理系统;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;



public class SystemUI extends JFrame{
	
	private static final long serialVersionUID = 4197017698513729527L;

	Teacher t = new Teacher();
	Course c = new Course();
	Student st = new Student();
	Score sc = new Score();
	SelectCourse se = new SelectCourse();
    CourseState cs = new CourseState();//
	static JTabbedPane tabbedPane;
	static JToolBar toolBar_1;
	static JToolBar toolBar_2;
	static JToolBar toolBar_3;
	static JToolBar toolBar_4;
	static JToolBar toolBar_5;
	static JButton AddTeacher;
	static JButton UpdateTeacher;
	static JButton DeleteTeacher;
	static JButton SearchTeacher;
	static JButton RefreshTeacher;
	static JButton AddStudent;
	static JButton UpdateStudent;
	static JButton DeleteStudent;
	static JButton SearchStudent;
	static JButton RefreshStudent;
	static JButton AddCourse;
	static JButton UpdateCourse;
	static JButton DeleteCourse;
	static JButton SearchCourse;
	static JButton RefreshCourse;
	static JButton AddSelCou;
	static JButton DeleteSelCou;
	static JButton SearchSelCou;
	static JButton RefreshSelCou;
	static JButton AddScore;
	static JButton UpdateScore;
	static JButton DeleteScore;
	static JButton SearchScore;
	static JButton RefreshScore;
	static JButton Statistical;
	static JButton AvgRank;
	static JScrollPane scrollPane1;
	static JScrollPane scrollPane2;
	static JScrollPane scrollPane3;
	static JScrollPane scrollPane4;
	static JScrollPane scrollPane5;

    static JToolBar toolBar_6;//
	static JButton RefreshCourseState;//
	static JScrollPane scrollPane6;

	public SystemUI() 
    {
        setIconImage(Toolkit.getDefaultToolkit().getImage("image\\校徽.jpg"));
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(565, 500);//565,500
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("欢迎来到教务系统");
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        JPanel TeacherInfo = new JPanel();
        JPanel StudentInfo = new JPanel();
        JPanel CourseInfo = new JPanel();
        JPanel ScoreInfo = new JPanel();
        JPanel SelectCourseInfo = new JPanel();
        JPanel CourseStateInfo = new JPanel();
        
        TeacherInfo.setLayout(new BorderLayout(0, 0));
        toolBar_1 = new JToolBar();
        TeacherInfo.add(toolBar_1, BorderLayout.NORTH);     	
             
        AddTeacher = new JButton("添加教师信息");
        AddTeacher.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		t.addTeacher();
        	}
        });
        
        UpdateTeacher = new JButton("修改教师信息");
        UpdateTeacher.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		t.updateTeacher();
        	}
        });
        	
         DeleteTeacher = new JButton("删除教师信息");
        DeleteTeacher.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		t.deleteTeacher();
        	}
        });
     
        SearchTeacher = new JButton("搜索教师信息");
        SearchTeacher.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		t.searchTeacher();
        	}
        });
        
        RefreshTeacher = new JButton("刷新");
        RefreshTeacher.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

        		t.refreshTeacher();
        	}
        });
        
        scrollPane1 = new JScrollPane();
        TeacherInfo.add(scrollPane1, BorderLayout.CENTER);
        scrollPane1.setViewportView(Teacher.table1); 
                                       
        StudentInfo.setLayout(new BorderLayout(0, 0));
        toolBar_2 = new JToolBar();
        StudentInfo.add(toolBar_2, BorderLayout.NORTH); 
        
        AddStudent = new JButton("添加学生信息");
        AddStudent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		st.addStudent();
        	}
        });
        
        UpdateStudent = new JButton("修改学生信息");
        UpdateStudent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		st.updateStudent();
        	}
        });
        
        DeleteStudent = new JButton("删除学生信息");
        DeleteStudent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		st.deleteStudent();
        	}
        });
        
        SearchStudent = new JButton("搜索学生信息");
        SearchStudent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		st.searchStudent();
        	}
        });
        
        RefreshStudent = new JButton("刷新");
        RefreshStudent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		st.refreshStudent();
        	}
        });
        
        scrollPane2 = new JScrollPane();
        StudentInfo.add(scrollPane2, BorderLayout.CENTER);
        
        scrollPane2.setViewportView(Student.table2);
        
        CourseInfo.setLayout(new BorderLayout(0, 0));
        toolBar_3 = new JToolBar();
        CourseInfo.add(toolBar_3, BorderLayout.NORTH);
        
        AddCourse = new JButton("添加课程信息");
        AddCourse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		c.addCourse();
        	}
        });
        
        UpdateCourse = new JButton("修改课程信息");
        UpdateCourse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		c.updateCourse();
        	}
        });
        
        DeleteCourse = new JButton("删除课程信息");
        DeleteCourse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		c.deleteCourse();
        	}
        });
        
        SearchCourse = new JButton("搜索课程信息");
        SearchCourse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		c.searchCourse();
        	}
        });
        
        RefreshCourse = new JButton("刷新");
        RefreshCourse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		c.refreshCourse();
        	}
        });
        
        scrollPane3 = new JScrollPane();
        CourseInfo.add(scrollPane3, BorderLayout.CENTER);
             
        scrollPane3.setViewportView(Course.table3);
        
        SelectCourseInfo.setLayout(new BorderLayout(0, 0));
        toolBar_5 = new JToolBar();
        SelectCourseInfo.add(toolBar_5, BorderLayout.NORTH);
        
        AddSelCou = new JButton("添加选课信息");
        AddSelCou.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		se.addSelCou();
        	}
        });       
        
        DeleteSelCou = new JButton("删除选课信息");
        DeleteSelCou.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		se.deleteSelCou();
        	}
        });
        
        SearchSelCou = new JButton("搜索选课信息");
        SearchSelCou.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		se.searchSelCou();
        	}
        });
        
        RefreshSelCou = new JButton("刷新");
        RefreshSelCou.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

        		se.refreshSelCou();
        	}
        });
        
        scrollPane5 = new JScrollPane();
        SelectCourseInfo.add(scrollPane5, BorderLayout.CENTER);
        
        scrollPane5.setViewportView(SelectCourse.table5);
       
        ScoreInfo.setLayout(new BorderLayout(0, 0));
        toolBar_4 = new JToolBar();
        ScoreInfo.add(toolBar_4, BorderLayout.NORTH);
        
        AddScore = new JButton("添加成绩信息");
        AddScore.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		sc.addScore();
        	}
        });
        
        UpdateScore = new JButton("修改成绩信息");
        UpdateScore.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		sc.updateScore();
        	}
        });
        
        DeleteScore = new JButton("删除成绩信息");
        DeleteScore.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		sc.deleteScore();
        	}
        });
        
        SearchScore = new JButton("搜索成绩信息");
        SearchScore.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		sc.searchScore();
        	};
        });
        
        Statistical = new JButton("统计分析");
        Statistical.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
      
        		//sc.statistical();
        	}
        });
        
        AvgRank = new JButton("平均分排名");
        AvgRank.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		sc.avgrank();
        	}
        });
        
        RefreshScore = new JButton("刷新");
        RefreshScore.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		sc.refreshScore();
        	}
        });
        
        scrollPane4 = new JScrollPane();
        ScoreInfo.add(scrollPane4, BorderLayout.CENTER);
        
        scrollPane4.setViewportView(Score.table4);

        CourseStateInfo.setLayout(new BorderLayout(0, 0));
        toolBar_6 = new JToolBar();
        CourseStateInfo.add(toolBar_6, BorderLayout.NORTH);
      
        RefreshCourseState = new JButton("刷新");
        RefreshCourseState.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

        		cs.refreshCourseState();
        	}
        });
        
        scrollPane6 = new JScrollPane();
        CourseStateInfo.add(scrollPane6, BorderLayout.CENTER);
        
        scrollPane6.setViewportView(CourseState.table6);
        
        {//权限分块
        	if(Login.level==3){//教务人员权限
            	//1
        		tabbedPane.add(TeacherInfo,"教师信息");
            	toolBar_1.add(AddTeacher);
            	toolBar_1.add(UpdateTeacher);
            	toolBar_1.add(DeleteTeacher);
            	//2
            	tabbedPane.add(StudentInfo,"学生信息");
            	toolBar_2.add(AddStudent);
            	toolBar_2.add(UpdateStudent);
            	toolBar_2.add(DeleteStudent);
            	//3
            	tabbedPane.add(CourseInfo,"课程信息");
            	toolBar_3.add(AddCourse);
            	toolBar_3.add(UpdateCourse);
            	toolBar_3.add(DeleteCourse);
            	//5
            	tabbedPane.add(SelectCourseInfo,"选课管理");
            	toolBar_5.add(AddSelCou);
            	toolBar_5.add(DeleteSelCou);
            	//4
            	tabbedPane.add(ScoreInfo,"成绩管理");
            	toolBar_4.add(AddScore);
            	toolBar_4.add(UpdateScore);
            	toolBar_4.add(DeleteScore);
            }
            else if(Login.level==2){//教师权限
            	tabbedPane.add(CourseInfo,"课程信息");
            	tabbedPane.add(ScoreInfo,"成绩管理");
            	toolBar_4.add(AddScore);
            	toolBar_4.add(UpdateScore);
            }
            else if(Login.level==1){//学生权限
            	tabbedPane.add(CourseInfo,"课程信息");
            	tabbedPane.add(SelectCourseInfo,"选课管理");
            	toolBar_5.add(AddSelCou);
            	toolBar_5.add(DeleteSelCou);
            	tabbedPane.add(ScoreInfo,"成绩管理");
            }
        }
        
        {
        	 toolBar_1.add(SearchTeacher);
        	 toolBar_1.add(RefreshTeacher);
        	 toolBar_2.add(SearchStudent);
        	 toolBar_2.add(RefreshStudent);
             toolBar_3.add(SearchCourse);
             toolBar_3.add(RefreshCourse);
             toolBar_5.add(SearchSelCou);
             toolBar_5.add(RefreshSelCou);
             toolBar_4.add(SearchScore);
             toolBar_4.add(AvgRank);
             toolBar_4.add(RefreshScore);
             toolBar_6.add(RefreshCourseState);     	
        }
        tabbedPane.add(CourseStateInfo,"状态信息");
    }
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemUI frame = new SystemUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    public static void clickable() {
    	tabbedPane.setEnabled(true);
		AddTeacher.setEnabled(true);
		DeleteTeacher.setEnabled(true);
		RefreshTeacher.setEnabled(true);
		SearchTeacher.setEnabled(true);
		UpdateTeacher.setEnabled(true);
		AddStudent.setEnabled(true);
		DeleteStudent.setEnabled(true);
		RefreshStudent.setEnabled(true);
		SearchStudent.setEnabled(true);
		UpdateStudent.setEnabled(true);
		AddCourse.setEnabled(true);
		DeleteCourse.setEnabled(true);
		RefreshCourse.setEnabled(true);
		SearchCourse.setEnabled(true);
		UpdateCourse.setEnabled(true);
		AddScore.setEnabled(true);
		DeleteScore.setEnabled(true);
		RefreshScore.setEnabled(true);
		SearchScore.setEnabled(true);
		UpdateScore.setEnabled(true);
		Statistical.setEnabled(true);
		AddSelCou.setEnabled(true);
		DeleteSelCou.setEnabled(true);
		SearchSelCou.setEnabled(true);
		RefreshSelCou.setEnabled(true);
		AvgRank.setEnabled(true);
        RefreshCourseState.setEnabled(true);
    }
  public static void unclickable() {
    	tabbedPane.setEnabled(false);
		AddTeacher.setEnabled(false);
		DeleteTeacher.setEnabled(false);
		RefreshTeacher.setEnabled(false);
		SearchTeacher.setEnabled(false);
		UpdateTeacher.setEnabled(false);
		AddStudent.setEnabled(false);
	    DeleteStudent.setEnabled(false);
	    RefreshStudent.setEnabled(false);
	    SearchStudent.setEnabled(false);
	    UpdateStudent.setEnabled(false);
	    AddCourse.setEnabled(false);
		DeleteCourse.setEnabled(false);
		RefreshCourse.setEnabled(false);
		SearchCourse.setEnabled(false);
		UpdateCourse.setEnabled(false);
		AddScore.setEnabled(false);
		DeleteScore.setEnabled(false);
		RefreshScore.setEnabled(false);
		SearchScore.setEnabled(false);
		UpdateScore.setEnabled(false);
		Statistical.setEnabled(false);
		AddSelCou.setEnabled(false);
		DeleteSelCou.setEnabled(false);
		SearchSelCou.setEnabled(false);
		RefreshSelCou.setEnabled(false);
		AvgRank.setEnabled(false);
        RefreshCourseState.setEnabled(false);
    }
}
