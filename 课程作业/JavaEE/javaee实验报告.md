#    实验⼀：基于MyBatis的校级课程管理程序

### 一. 任务目的

掌握 MyBatis 的环境搭建、核心配置文件、映射文件，学会使用基于 xml 和基于注解的 MyBatis 进行关系数据库的增删查改操作。

### 二. 实验要求

1.技术选型：Java + Spring + MyBatis；推荐使⽤的数据库为 MySQL，引擎为innoDB 存储引擎； 

2.实验结果在 Test 包中创建测试类，将实验结果直接输出到控制台，不需要使用前端页面进行展示；

3.提交实验报告，项⽬代码推荐提交到Github 并在实验报告中给出项⽬的仓库地址。

### 三. 实验环境

1.操作系统：windows11

2.IDE：IntelliJ IDEA 2022.3.2 (Ultimate Edition)

3.Mysql版本：8.0.26

4.Navicat版本：Navicat Premium 15

### 四. 实验内容

现有⼀个课程表 c_course 和⼀个学院表 s_school, 学院表和课程表之间是⼀对多的关系。课程表和学院表分别如表 1 和表 2 所示。

课程表 c_course 表 1:

![img](D:\Picture\学习笔记存放区\Linux\wps2-16823426587422.jpg) 

学院表 s_school 表 2:

![img](D:\Picture\学习笔记存放区\Linux\wps3-16823426587421.jpg) 

请根据以上表的字段及值完成数据库建库及数据初始化的过程，逐步完成以下子任务。 

1. 查询 id=2 的课程信息； 

2. 查询出所有计算机学院开设的课程信息 ； 

3. 将id=4 这门课程的课时数修改为32+8=40； 

4. 插入⼀条新的课程记录： names=“大数据存储”，hours=32，schools=1； 

5. 输出所有的学院开设的课程信息。

### 五. 结果分析

```java
public class TestAll {

    private CourseMapper courseMapper;
    private SchoolMapper schoolMapper;
    private SqlSession sqlSession;
    @Before
    public void setUp(){
        sqlSession = SqlSessionUtil.getSqlSession();
        courseMapper = sqlSession.getMapper(CourseMapper.class);
        schoolMapper = sqlSession.getMapper(SchoolMapper.class);
    }

    @After
    public void release(){
        sqlSession.close();
    }

    // 1. 查询 id=2 的课程信息；
    @Test
    public void testSelectCourseById(){
        System.out.println(courseMapper.selectCourseById(1));
    }

    // 2. 查询出所有计算机学院开设的课程信息 ；
    @Test
    public void testSelectCourseBySchoolName(){
        System.out.println(schoolMapper.selectCourseBySchoolName("计算机科学与工程学院"));
    }

    // 3. 将 id=4 这⻔课程的课时数修改为 32+8=40；
    @Test
    public void testUpdateCourseById(){
        courseMapper.updateCourseById(4,40);
    }

    // 4. 插⼊⼀条新的课程记录： names=”⼤数据存储“，hours=32，schools =1；
    @Test
    public void testInsertCourse(){
        Course course = new Course(null,"大数据存储",32,1,null,null);
        courseMapper.insertCourse(course);
    }

    // 5. 输出所有的学院开设的课程信息。
    @Test
    public void testSelectAllSchools(){
        List<School> schools = schoolMapper.selectAllSchools();
        schools.forEach(System.out::println);
    }

}
```

1. 查询 id=2 的课程信息

```sql
Course{cid=2, cname='Python程序设计', hours=70, schools=1, cpath='310da3a0-de32-4ae5-a81c-4ebe7d012f75.png', cinf='Python提供了高效的高级数据结构，还能简单有效地面向对象编程。Python语法和动态类型，以及解释型语言的本质，使它成为多数平台上写脚本和快速开发应用的编程语言。'}
```

2. 查询出所有计算机学院开设的课程信息 

```sql
School{sid=1, name='计算机科学与工程学院', courses=[Course{cid=1, cname='C语言程序设计', hours=64, schools=1, cpath='5866e464-2a24-48f3-b315-40fcd25e23d7.png', cinf='C语言是一门面向过程的、抽象化的通用程序设计语言，广泛应用于底层开发。C语言能以简易的方式编译、处理低级存储器。'}, Course{cid=2, cname='Python程序设计', hours=70, schools=1, cpath='310da3a0-de32-4ae5-a81c-4ebe7d012f75.png', cinf='Python提供了高效的高级数据结构，还能简单有效地面向对象编程。Python语法和动态类型，以及解释型语言的本质，使它成为多数平台上写脚本和快速开发应用的编程语言。'}]}
```

3. 将 id=4 这门课程的课时数修改为 32+8=40

![image-20230424213338591](D:\Picture\学习笔记存放区\Linux\image-20230424213338591.png)

4. 插入⼀条新的课程记录： names=”大数据存储“，hours=32，schools =1

![image-20230424213406111](D:\Picture\学习笔记存放区\Linux\image-20230424213406111.png)

5. 输出所有的学院开设的课程信息。

```sql
School{sid=1, name='计算机科学与工程学院', courses=[Course{cid=1, cname='C语言程序设计', hours=64, schools=1, cpath='5866e464-2a24-48f3-b315-40fcd25e23d7.png', cinf='C语言是一门面向过程的、抽象化的通用程序设计语言，广泛应用于底层开发。C语言能以简易的方式编译、处理低级存储器。'}, Course{cid=2, cname='Python程序设计', hours=70, schools=1, cpath='310da3a0-de32-4ae5-a81c-4ebe7d012f75.png', cinf='Python提供了高效的高级数据结构，还能简单有效地面向对象编程。Python语法和动态类型，以及解释型语言的本质，使它成为多数平台上写脚本和快速开发应用的编程语言。'}]}

School{sid=2, name='外国语学院', courses=[Course{cid=3, cname='大学英语', hours=96, schools=2, cpath='null', cinf='大学英语是以英语语言知识与应用技能、学习策略和跨文化交际为主要内容，以外语教学理论为指导，以现代教育技术和信息技术为支撑，集多种教学模式和教学手段为一体，实施开放式、交互型、立体化的教学体系。'}, Course{cid=10, cname='高级英语', hours=40, schools=2, cpath='', cinf='null'}]}
```

### 六. 心得体会

> 在本次实验中通过搭建环境配置以及自行编写代码更好地掌握了与mybatis技术相关的知识，收获颇多！





# 实验二：**基于** SSM 的校级课程管理程序

### 一. 任务目的

掌握SpringMVC的数据绑定和响应，学会使用 JSP、Velocity等页面视图进行交互和结果的页面展示。

### 二. 实验要求

1，技术选型：Java + Spring + MyBatis + SpringMVC； 2，实验交互及结果要以Web视图形式进行展示（推荐）； 3，提交实验报告，项目代码推荐提交至Github并在实验报告中给出项目的仓库地址。

### 三. 实验环境

1.操作系统：windows11

2.IDE：IntelliJ IDEA 2022.3.2 (Ultimate Edition)

3.Mysql版本：8.0.26

4.Navicat版本：Navicat Premium 15

### 四. 实验内容

该实验继续沿用高级Web 技术实验⼀中的题目背景，结合你设计的数据模型和表设计，在此基础上，结合SpringMVC 框架完成：

1. 新增课程：支持通过表单提交⼀门新的课程，该表单包含的元素：课程名（文本框），课时（文本框），开课学院（文本框）。要求：课程名不能与已有的课程重复。

2. 展示课程：显示所有学院开设的所有课程，按照学院类别进行排序。要求：暂时不要求分页展示所有的课程。

3. 修改课程：在课程列表展示中可以选择任⼀⼀门课程进行修改。要求：新修改课程名不能与已有的课程名重复。

4. 删除课程：选择⼀门课程进行删除，删除前，弹出对话框，确认是否删除。暂时不考虑批量删除。页面设计及交互参考下图，可以自己设计css和js脚本，js使用jquery即可。学有余力的同学，可以尝试使用VUE、React等前端框架结合流行的UI组件等来完成展示。

### 五. 结果分析

- 学院表（school_list.html）：

![image-20230424214543419](D:\Picture\学习笔记存放区\Linux\image-20230424214543419.png)

- 课程表（course_list.html）

![image-20230424214713748](D:\Picture\学习笔记存放区\Linux\image-20230424214713748.png)

- 添加课程页面（course_add.list）

  <img src="D:\Picture\学习笔记存放区\Linux\image-20230424215504444.png" alt="image-20230424215504444" style="zoom: 33%;" />

  添加已有课程后得到的信息提示：
  
  <img src="D:\Picture\学习笔记存放区\Linux\image-20230424220112041.png" alt="image-20230424220112041" style="zoom:33%;" />
  
- 修改课程页面（course_update.list）

  <img src="D:\Picture\学习笔记存放区\Linux\image-20230424215724079.png" alt="image-20230424215724079" style="zoom:33%;" />

  修改为已有课程后，得出的信息提示：

  <img src="D:\Picture\学习笔记存放区\Linux\image-20230424220238640.png" alt="image-20230424220238640" style="zoom:33%;" />

- 删除页面（放在课程展示页面）

  点击删除按钮，弹出层弹出提示，确认是否删除，效果展示：

  <img src="D:\Picture\学习笔记存放区\Linux\image-20230424215937543.png" alt="image-20230424215937543" style="zoom: 50%;" />

  

### 六. 心得体会

> 通过本次实验，更好地掌握了SpringMVC的数据绑定和响应，学会使用HTML页面视图进行交互和结果的页面展示，同时进一步掌握使用semantic UI进行页面的美化。





# 实验三：基于 **SSM** 的校级课程管理程序

### 一. 任务目的

掌握SpringMVC的文件上传和展示。

### 二. 实验要求

1，技术选型：Java + Spring + MyBatis + SpringMVC； 2，实验交互及结果要以Web视图形式进行展示； 3，提交实验报告，项目代码推荐提交至Github并在实验报告中给出项目的仓库地址。

### 三. 实验环境

1.操作系统：windows11

2.IDE：IntelliJ IDEA 2022.3.2 (Ultimate Edition)

3.Mysql版本：8.0.26

4.Navicat版本：Navicat Premium 15

### 四. 实验内容

该实验继续沿用高级Web 技术实验⼆中的题目背景，在此基础上结合SpringMVC 框架完成：

1. 新增课程：新增课程时，支持给课程设定封面图，支持从本地硬盘上传，如果用户不上传则系统使用默认的图片。图片样式按照自己的视图进行自由设置。

2. 展示课程：跟之前的逻辑保持⼀致，列表展示课程时要能展示每门课程对应的课程封面图。

3. 修改课程：课程封面也可以进行修改，其它逻辑跟之前的⼀致。

4. 删除课程：跟之前的逻辑保持⼀致。

### 五. 结果分析

这里由于展示页面，修改页面，新增页面，删除页面在实验二已展示，所以就不再展示了，这里仅展示上传页面相关内容。

**上传页面前，使用的默认图片以及效果展示：**

<img src="D:\Picture\学习笔记存放区\Linux\image-20230424221723766.png" alt="image-20230424221723766" style="zoom:33%;" />

**上传页面后，使用的上传图片以及效果展示：**

<img src="D:\Picture\学习笔记存放区\Linux\image-20230424221847545.png" alt="image-20230424221847545" style="zoom:33%;" />

![image-20230424221910594](D:\Picture\学习笔记存放区\Linux\image-20230424221910594.png)

相关代码：

```java
@RequestMapping("/courseImgUp/{cid}")
    public String courseImgUp(MultipartFile photo, Course course, Model model, HttpServletRequest request) throws IOException {
        //获取上传的文件的文件名
        String fileName = photo.getOriginalFilename();
        //处理文件重名问题
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + hzName;
        String photoPath = "D:/StudyMaterial/DataStorage/course";
        String finalPath = photoPath + File.separator + fileName;
        photo.transferTo(new File(finalPath));
        Integer cid = course.getCid();
        course.setCpath(fileName);
        courseService.updateCourseImgByCid(course,cid);
        model.addAttribute("course",course);
        model.addAttribute("spageNum", request.getParameter("spageNum"));
        model.addAttribute("cpageNum", request.getParameter("cpageNum"));
        model.addAttribute("sid", request.getParameter("sid"));
        model.addAttribute("username", request.getParameter("username"));
        return "course_update";
    }
```

### 六. 心得体会

> 通过本次实验，更深入的理解了文件上传的相关内容！





# 实验四：基于 **SSM** 的校级课程管理程序

### 一. 任务目的

进⼀步掌握SpringMVC的数据绑定和页面跳转技术，能利用JSP视图技术来完成实际系统的开发。

### 二. 实验要求

1，技术选型：Java + Spring + MyBatis + SpringMVC； 2，实验交互及结果要以Web视图形式进行展示（在实验⼆是推荐，注意这里是必须）； 3，提交实验报告，项目代码推荐提交至Github并在实验报告中给出项目的仓库地址。

### 三. 实验环境

1.操作系统：windows11

2.IDE：IntelliJ IDEA 2022.3.2 (Ultimate Edition)

3.Mysql版本：8.0.26

4.Navicat版本：Navicat Premium 15

### 四. 实验内容

1. 用户登陆：前端提供用户登陆界面；如果用户输入正确的邮箱密码，提交表单给后端，后端查询数据库后确认邮箱和密码匹配后则自动进入到课程管理主界面（实验三课程展示界面）。否则，返回登陆失败的提示。页面上如果邮箱和密码有⼀个为空的时候点击登陆按钮时前端需要弹出提示框 "不能为空"。
2. 用户登陆成功，转向课程管理主界面，右上角显示用户邮箱和⼀个“退出”按钮（或超链接）。
3. 数据库设计：自己新建 user表，手动插入一个合法的用户用作登陆。
4. 权限控制，只有登陆的用户才能访问课程管理主界面，没有登陆过的用户无法访问课程管理主界面。
5. 如果学有余力，可以完成（选做）：a，注册新用户；b，登陆和注册的时候提供校验码。

### 五. 结果分析

注意：由于在实验二已经展示了增删改界面，本实验就不一一展示了，而是展示拓展的其他功能以及相关数据内容或代码。

1.登录界面展示

![image-20230424222947970](D:\Picture\学习笔记存放区\Linux\image-20230424222947970.png)

输入错误密码后的信息提示：

<img src="D:\Picture\学习笔记存放区\Linux\image-20230424223207764.png" alt="image-20230424223207764" style="zoom:50%;" />

2.注册页面展示（其中包括验证码校验)

![image-20230424223053821](D:\Picture\学习笔记存放区\Linux\image-20230424223053821.png)



3.用户名及头像展示：

<img src="D:\Picture\学习笔记存放区\Linux\image-20230424223311220.png" alt="image-20230424223311220" style="zoom: 50%;" />

4.添加了查询功能，其中也包括了分页功能：

<img src="D:\Picture\学习笔记存放区\Linux\image-20230424223431599.png" alt="image-20230424223431599" style="zoom:50%;" />

![image-20230424223453061](D:\Picture\学习笔记存放区\Linux\image-20230424223453061.png)

5.整体数据库展示：

课程表（c_course表）：

![image-20230424223716781](D:\Picture\学习笔记存放区\Linux\image-20230424223716781.png)

学院表（s_school表）：

<img src="D:\Picture\学习笔记存放区\Linux\image-20230424223740576.png" alt="image-20230424223740576" style="zoom:33%;" />

用户表（t_user表）：

<img src="D:\Picture\学习笔记存放区\Linux\image-20230424224012871.png" alt="image-20230424224012871" style="zoom: 50%;" />

6.拦截器部分（权限控制）：

```java
public class LoginInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求的路径
        String uri = request.getRequestURI();
        // 对用户登录的相关请求进行判断
        if(uri.indexOf("/login") >= 0 || uri.indexOf("/register") >= 0 || uri.indexOf("/toLogin") >= 0 || uri.indexOf("/toRegister") >= 0){
            return true;
        }
        HttpSession session = request.getSession();
        // 如果用户是已登录状态，放行
        if (session.getAttribute("user") != null){
            return true;
        }
        // 其他情况都直接跳转到登录页面
        request.setAttribute("msg","您还没登录，请先登录！");
        //request.getRequestDispatcher("/WEB-INF/templates/login.html").forward(request,response);
        request.getRequestDispatcher("/toLogin").forward(request,response);
        return false;
    }
}
```

### 六. 心得体会

> 通过对整个项目的开发后，进一步掌握了SSM框架的知识，以及进一步理解了SSM框架的相关内容及使用，为后续springboot框架的学习提供了良好的基础。接下来说说整个学习过程，在做本次实验这个项目前，不知如何下手，但是在一步步接触后，有了清晰的认知，同时可以根据自己的想法去添加一些额外的功能，总的来说，很享受，可以说是乐在其中，但是对于自己来说，这些还算是开发中最基础的部分，由于我们用的都是封装后的函数或类，相对于源码来说简单了许多，打个比方来说，也不过是在提供材料的前提下，搭建积木罢了，而且目前学的不过是冰山一角，当然有了这次经验后，激励我去追求更多的知识，去开发更多有用亦或是有难度的项目吧，总的来说是，非常棒！