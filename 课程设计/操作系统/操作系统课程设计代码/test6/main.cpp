#include<stdio.h>
#include<stdlib.h>
 
#define False 0
#define True 1
 
/********��Ҫ���ݽṹ********/
char NAME[100]={0};//��Դ������
int Max[100][100]={0};//����������
int Allocation[100][100]={0};//ϵͳ�ѷ������
int Need[100][100]={0};//����Ҫ��Դ����
int Available[100]={0};//������Դ����
int Request[100]={0};//������Դ����
int Work[100]={0};//���ϵͳ���ṩ��Դ��
int Finish[100]={0}; //���ϵͳ�Ƿ����㹻����Դ�������������
int Security[100]={0};//��Ű�ȫ����
 
int M=100;//���̵������
int N=100;//��Դ�������
 
/********��ʼ�����ݣ����������������Դ���ࡢ�����̶���Դ�����������
�����̵���Դ�ѷ���������������Դ���������ȡ�********/
void init()
{
    int i,j;
	int flag;
	printf("\n------------------------���м��㷨ʵ��-----------------------\n\n");
	//����ϵͳ��Դ��Ŀ������Դ���ø���
	printf("�����������Դ������mΪ:");
	scanf("%d",&N);
	for(i=0;i<N;i++)
	{
		printf("��Դ%d������Ϊ:",i);
		fflush(stdin);  //������������������ַ���ע���������#include<stdlib.h>ͷ�ļ�
		scanf("%c",&NAME[i]);
		printf("��Դ%c�Ŀ��ø���Ϊ:",NAME[i]);
		scanf("%d",&Available[i]);
	}
 
	//����������������̵�����������
	printf("\n��������̵�����nΪ:");
	scanf("%d",&M);
	printf("����������̵������������ֵ[Max](��ʾ��n��m����ʽ���м��Կո���зָ�):\n");
	for(i=0;i<M;i++)
		for(j=0;j<N;j++)
			scanf("%d",&Max[i][j]);
		
	//����������Ѿ��������Դ��������û���Ҫ����Դ��
	do{
		flag=False;
		printf("������������ѷ������Դ��[Allocation](��ʾ��n��m����ʽ���м��Կո���зָ�):\n");
		for(i=0;i<M;i++)
		{
			for(j=0;j<N;j++)
		  	{
				scanf("%d",&Allocation[i][j]);
				if(Allocation[i][j]>Max[i][j])
					flag=True;
				Need[i][j]=Max[i][j]-Allocation[i][j];
		  	}
		}
		if(flag)
			printf("�������Դ���������ֵ�������������������!\n");
	}while(flag);
}
 
/********��ʾ��Դ�������********/
void showdata()
{
	int i,j;
	printf("\n-------------------------------------------------------------\n");
	printf("\n");
	printf("ϵͳ��ǰ����Դ����������£�\n");
	printf("           Max    	 Allocation    Need          Available\n");
	printf("������     ");
	//����������ͬ�е���Դ����Max��Allocation��Need�·ֱ��Ӧ
	for(j=0;j<4;j++){
		for(i=0;i<N;i++)
			printf("%c  ",NAME[i]);
		printf("     ");
	}
	printf("\n");
	//���ÿ�����̵�Max��Allocation��Need
	for(i=0;i<M;i++){
		printf(" P%d        ",i);
	    for(j=0;j<N;j++)
			printf("%d  ",Max[i][j]);
		printf("     ");
		for(j=0;j<N;j++)
			printf("%d  ",Allocation[i][j]);
		printf("     ");
		for(j=0;j<N;j++)
			printf("%d  ",Need[i][j]);
		printf("     ");
		if (i == 0)
		{
			for(j=0;j<N;j++)
				printf("%d  ",Available[j]);
		}
		printf("\n");
	}
}
 
/********���Է�����Դ********/
int test(int i) //��̽�ԵĽ���Դ�������i������
{
	for(int j=0;j<N;j++)
	{
		Available[j]=Available[j]-Request[j];
		Allocation[i][j]=Allocation[i][j]+Request[j];
		Need[i][j]=Need[i][j]-Request[j];
	}
	return True;
}
 
/********��̽�Է�����Դ����********/
int Retest(int i) //��test�����෴
{
	for(int j=0; j<N; j++)
	{
		Available[j] = Available[j] + Request[j];
		Allocation[i][j] = Allocation[i][j] - Request[j];
		Need[i][j] = Need[i][j] + Request[j];
	}
	return True;
}
 
/********��ȫ���㷨********/
int safe()
{
	int i,j,k=0,m,apply;
	//��ʼ��work
	for(j=0;j<N;j++)
        Work[j] = Available[j];
    //��ʼ��Finish
    for(i=0;i<M;i++)
    	Finish[i] = False;
	for (int s=0; s<M; s++)//��ȫ����
	{
		for(i=0;i<M;i++)
		{
			apply=0;
			for(j=0;j<N;j++)
			{
				if(Finish[i]==False && Need[i][j]<=Work[j])
				{
					apply++;
					//ֱ��ÿ����Դ��������С��ϵͳ��������Դ���ſɷ���
					if(apply==N)
					{
						for(m=0;m<N;m++)
							Work[m]=Work[m]+Allocation[i][m];//���ĵ�ǰ�ɷ�����Դ
						Finish[i]=True;
						Security[k++]=i;
					}
				}
			}
		}
		if (k == M)
			break;
	}
 
	for(i=0;i<M;i++)
	{
		if(Finish[i]==False)
		{
			printf("ϵͳ����ȫ\n");//���ɹ�ϵͳ����ȫ
			return False;
		}
	}
    printf("ϵͳ��ȫ!\n");//�����ȫ������ɹ�
    printf("����һ����ȫ����:");
	for(i=0;i<M;i++)
	{//�����ȫ����
		printf("P%d",Security[i]);
		if(i<M-1)
			printf(" --> ");
	}
	printf("\n");
	return True;
}
 
/********�������м��㷨��������Դ�����Է�********/
void bank()
{
	int flag = True;//��־�������ж��ܷ�������м��㷨����һ��
	int i,j;
 
	printf("���������������Դ�Ľ��̺�(0-%d):",M-1);
    scanf("%d",&i);//������������Դ�Ľ��̺�
 
	printf("���������P%d��Ҫ������Դ�ĸ���:\n",i);
	for(j=0;j<N;j++)
	{
		printf("%c:",NAME[j]);
		scanf("%d",&Request[j]);//������Ҫ�������Դ
	}
 
	//�ж����м��㷨��ǰ�������Ƿ����
    for (j=0;j<N;j++)
	{
		if(Request[j]>Need[i][j])//�ж������Ƿ�������������������
		{
			printf("����P%d�������Դ��������Ҫ����Դ������ʧЧ��\n",i);
			//printf("����ʧЧ��\n");
			//printf("���䲻����������䣡\n");
			flag = False;
			break;
		}
		else
		{
            if(Request[j]>Available[j])//�ж������Ƿ���ڵ�ǰ�ɷ�����Դ�������������
			{
				printf("����%d�������Դ����ϵͳ���ڿ����õ���Դ������ʧЧ��\n",i);
				//printf("\n");
				//printf("ϵͳ�����㹻��Դ���������!\n");
				flag = False;
				break;
			}
		}
    }
    //ǰ���������������Է�����Դ��Ѱ�Ұ�ȫ����
    if(flag) 
	{
		test(i); //���ݽ���������,�Է�����Դ
		showdata(); //���ݽ�������������ʾ�Է�������Դ��
		if(!safe()) //Ѱ�Ұ�ȫ����
		{
			printf("����ʧ�ܣ�\n");
			Retest(i);
			showdata();
		}
    }
}
 
 
int main()//������
{
	char choice;
	init();//��ʼ������
    showdata();//��ʾ������Դ
    //�����м��㷨�ж�ϵͳ��ǰʱ���Ƿ�ȫ������ȫ�Ͳ��ټ���������Դ
	if(!safe()) exit(0);
 
	do{
		printf("\n-------------------------------------------------------------\n");
		printf("\n");
		printf("\n");
		printf("--------��ʾ--------\n");
		printf("-   R(r):�������  -\n");
		printf("-   E(e):�˳�      -\n");
		printf("--------------------\n");
		printf("��ѡ��");
		fflush(stdin);  //������������������ַ���ע���������#include<stdlib.h>ͷ�ļ�
		scanf("%c",&choice);
		switch(choice)
		{
			case 'r':
			case 'R':
				bank();break;
			case 'e':
			case 'E':
				exit(0);
			default: printf("�������!\n");break;
		}
	} while(choice);
}
  
