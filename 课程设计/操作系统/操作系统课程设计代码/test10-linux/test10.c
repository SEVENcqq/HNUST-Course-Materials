#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<string.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<fcntl.h>
#include<sys/stat.h>
#include<dirent.h>
#include<pwd.h>

#define normal 0
#define out_redirect 1
#define in_redirect 2
#define have_pipe 3

void print_prompt();
void get_input(char *);
void explain_input(char *,int *,char a[100][256]);
void do_cmd(int,char a[100][256]);
int find_command(char *);
void exec_cd(int,char a[100][256]);
void exec_help(int,char a[100][256]);
void exec_jobs(int,char a[100][256]);

int main(int argc,char **argv){
	int i;
	int argcount = 0;
	char arglist[100][256];
	char **arg = NULL;
	char *buf = NULL;

	buf = (char *)malloc(256);
	if(buf == NULL){
		perror("malloc failed");
		exit(-1);
	}
	while(1){
		memset(buf, 0, 256);
		print_prompt();
		get_input(buf);
		if(strcmp(buf,"exit\n") == 0||strcmp(buf,"logout\n") == 0)
			break;
		for(i = 0;i < 100; i++){
			arglist[i][0] = '\0';
		}
		argcount = 0;
		explain_input(buf,&argcount,arglist);
		if(strncmp(buf,"cd",2) == 0){
			exec_cd(argcount,arglist);
			continue;
		}

        if(strncmp(buf,"help",2) == 0){
			exec_help(argcount,arglist);
			continue;
		}
		
		if(strncmp(buf,"jobs",2) == 0){
			exec_jobs(argcount,arglist);
			continue;
		}

		do_cmd(argcount,arglist);
	}
	if(buf != NULL){
		free(buf);
		buf = NULL;
	}
	exit(0);
}

void print_prompt(){

	char hostname[50];
    gethostname(hostname,50);  //获取主机名
    struct passwd* username;
    username=getpwuid(getuid());
    char path[256];
    getcwd(path,256);
    int x;
    char m;
    x=geteuid();
    if(x==0)
    m='#';
    else
    m='$';
    char colorhost[100];
    char colorusername[PATH_MAX];
    char  colorpath[PATH_MAX];
    sprintf(colorhost,"\033[%dm%s\033[0m",32,hostname);
    sprintf(colorusername,"\033[%dm%s\033[0m",33,username->pw_name);
    sprintf(colorpath,"\033[%dm%s\033[0m",31,path);
    printf("%s@%s:%s%c",colorhost,colorusername,colorpath,m);
}
void get_input(char *buf){
	int len = 0;
	char ch;
	ch = getchar();
	while(len < 256 && ch != '\n'){
		buf[len++] = ch;
		ch = getchar();
	}
	if(len == 256){
		printf("command is too long \n");
		exit(-1);
	}
	buf[len] = '\n';
	len++;
	buf[len] = '\0';
}
void explain_input(char *buf,int *argcount,char arglist[100][256]){
	char *p = buf;
	char *q = buf;
	int number = 0;
	while(1){
		if(p[0] == '\n')
			break;
		if(p[0] == ' ')
			p++;
		else{
			q = p;
			number = 0;
			while((q[0] != ' ')&&(q[0] != '\n')){
				number++;
				q++;
			}
			strncpy(arglist[*argcount],p,number+1);
			arglist[*argcount][number] = '\0';
			*argcount = *argcount + 1;
			p = q;
			}
	}
}
void do_cmd(int argcount,char arglist[100][256]){
	int flag = 0;
	int how = 0;
	int background = 0;
	int status;
	int i;
	int fd;
	char* arg[argcount+1];
	char* argnext[argcount+1];
	char* file;
	pid_t pid;

	for(i = 0;i < argcount ;i++){
		arg[i] = (char *)arglist[i];
	}
	arg[argcount] = NULL;

	for(i = 0;i < argcount;i++){
		if(strncmp(arg[i],"&",1) == 0){
			if(i == argcount-1){
				background = 1;
				arg[argcount-1] = NULL;
				break;
			}
			else{
				printf("wrong command\n");
				return ;
			}
		}
	}
	for(i = 0;arg[i] != NULL;i++){
		if(strcmp(arg[i],">") == 0){
			flag++;
			how = out_redirect;
			if(arg[i+1] == NULL)
				flag++;
		}
		if(strcmp(arg[i],"<") == 0){
			flag++;
			how = in_redirect;
			if(arg[i+1] == NULL)
				flag++;
		}
		if(strcmp(arg[i],"|") == 0){
			flag++;
			how = have_pipe;
			if(arg[i+1] == NULL)
				flag++;
			if(i == 0)
				flag++;
		}
	}
	if(flag > 1){
		printf("wrong command\n");
		return;
	}
	if(how == out_redirect){
		for(i = 0;arg[i]!=NULL;i++){
			if(strcmp(arg[i],">")==0){
				file = arg[i+1];
				arg[i] = NULL;
			}
		}
	}
	if(how == in_redirect){
		for(i=0;arg[i] != NULL;i++){
			if(strcmp(arg[i],"<")==0){
				file = arg[i+1];
				arg[i] = NULL;
			}
		}
	}
	if(how == have_pipe){
		for(i = 0;arg[i] != NULL;i++){
			if(strcmp(arg[i],"|") == 0){
				arg[i] = NULL;
				int j;
				for(j = i+1;arg[j] != NULL;j++){
					argnext[j-i-1] = arg[j];
				}
				argnext[j-i-1] = arg[j];
				break;
			}
		}
	}
	if((pid = fork())<0){
		printf("fork error\n");
		return;
	}
	switch(how){
		case 0:
			if(pid == 0){
				if(!(find_command(arg[0]))){
					printf("%s : command not found\n",arg[0]);
					exit(0);
				}
				execvp(arg[0],arg);
				exit(0);
			}
			break;
            case 1:
			if(pid == 0){
				if(!(find_command(arg[0]))){
					printf("%s : command not found\n",arg[0]);
					exit(0);
				}
				fd = open(file,O_RDWR|O_CREAT|O_TRUNC,0644);
				dup2(fd,1);
				execvp(arg[0],arg);
				exit(0);
			}
			break;   
		case 2:
			if(pid == 0){
				if(!(find_command(arg[0]))){
					printf("%s : command not found\n",arg[0]);
					exit(0);
				}
				fd = open(file,O_RDONLY);
				dup2(fd,0);
				execvp(arg[0],arg);
				exit(0);
			}
			break;
		case 3:
			if(pid == 0){
				int pid2;
				int status2;
				int fd2;
				if((pid2 = fork()) < 0){
					printf("fork2 error\n");
					return;
				}
				else if(pid2 == 0){
					if(!(find_command(arg[0]))){
						printf("%s : command not found\n",arg[0]);
						exit(0);
					}
					fd2 = open("/tmp/youdonotknowfile",O_WRONLY|O_CREAT|O_TRUNC,0644);
					dup2(fd2,1);
					execvp(arg[0],arg);
					exit(0);
				}
				if(waitpid(pid2,&status2,0) == -1)
					printf("wait for child process error\n");
				if(!(find_command(argnext[0]))){
					printf("%s : command not found\n",argnext[0]);
					exit(0);
				}
				fd2 = open("/tmp/youdonotknowfile",O_RDONLY);
				dup2(fd2,0);
				execvp(argnext[0],argnext);
				if(remove("/tmp/youdonotknowfile"))
					printf("remove error\n");
				exit(0);
			}
			break;
		default:
			break;
	}
	if(background == 1){
		printf("[process id %d]\n",pid);
		return;
	}
	if(waitpid(pid,&status,0) == -1)
		printf("wait for child process error\n");
}

int find_command(char *command){
	DIR* dp;
	struct dirent* dirp;
	char* path[] = {"./","/bin","/user/bin",NULL};
	if(strncmp(command,"./",2) == 0)
		command = command + 2;
	int i = 0;
	while (path[i] != NULL){
		if((dp = opendir(path[i])) == NULL)
			printf("can not open /bin \n");
		while((dirp = readdir(dp)) != NULL){
			if(strcmp(dirp -> d_name,command) == 0){
				closedir(dp);
				return 1;
			}
		}
		closedir(dp);
		i++;
	}
	return 0;
}

void exec_cd(int argcount,char arglist[100][256]){
	if(argcount != 2){
		printf("%s : command not found\n",arglist[0]);
		return;
	}
	chdir(arglist[1]);
}

void exec_help(int argcount,char arglist[100][256]){
	printf("-------help-------\n");
    printf("cd -- <目录>更改当前的工作目录到另一个<目录>\n");
    printf("evn -- 列出所有环境变量字符串的设置\n");
	printf("jobs -- 输出 shell 当前的一系列子进程\n");
	printf("ls -- 列出当前目录中所有的子目录和文件\n");
	printf("man -- 命令说明书\n");
	printf("touch -- 新增文件\n");
	printf("vim/vi -- 编辑文件\n");
	printf("cat -- 查看文件\n");
	printf("mkdir -- 创建目录\n");
	printf("rm -- 删除目录和文件\n");
	printf("mv -- 修改目录\n");
	printf("cp -- 拷贝目录\n");
	printf("find -- 搜索目录\n");
	printf("pwd -- 查看当前目录\n");
    printf("exit -- 退出 shell\n");
	
}

void exec_jobs(int argcount,char arglist[100][256]){
	execlp("pstree","-p",NULL);
	
}
