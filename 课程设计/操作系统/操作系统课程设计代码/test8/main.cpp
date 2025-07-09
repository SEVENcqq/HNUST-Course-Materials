#include <iostream>
#include <cstdio>
#include <time.h>
#include <cstdlib>
#include <vector>

#define MEM_PAHE_NUM 4
#define COMMD_NUM 320
#define PAGE_COMMD 10
#define MAX_FAR 1000000000

using namespace std;

struct MemoryCell
{
    int index;//ҳ��
    int time;//ʱ���
};

int commds[COMMD_NUM];    //��ŵ���ÿһ��ָ���ҳ��
MemoryCell memory[MEM_PAHE_NUM];  //�ڴ��
vector<int> order_commd;
vector<int> order_page;

void initPage();    //��ʼ��ҳ��
void createArray(); //��������
double FIFO();      //��FIFO�û��㷨
double LRU();       //��LRU�û��㷨
double OPT();       //��OPT�û��㷨

int main()
{
    initPage();
    createArray();
    double fifo = FIFO();
    double lru = LRU();
    double opt = OPT();
    printf("OPT = %f FIFO = %f LRU = %f\n",opt,fifo,lru);
    return 0;
}

void initPage()
{
    //���ڿ�ʼ��ÿ��ҳ����ָ��
    int page_index=0;
    for(int i=0;i<COMMD_NUM;i++)
    {
        commds[i]=page_index;
        if((i+1) % PAGE_COMMD ==0)
        {
            page_index++;
        }
    }

//    for(int i=0;i<COMMD_NUM;i++)
//    {
//        printf("%d ",commds[i]);
//        if((i+1) % PAGE_COMMD ==0)
//        {
//            printf("\n");
//        }
//    }
}

void createArray()
{
    srand((unsigned)time(NULL));
    int commd = 0;
    while(commd < COMMD_NUM)
    {
        //��Χ��[0,319]
        int m = rand()%COMMD_NUM;
        order_commd.push_back(m);
        commd++;
        if(commd >= COMMD_NUM)
            break;
        if(m == COMMD_NUM-1)
            continue;
        order_commd.push_back(m+1);
        commd++;
        if(commd >= COMMD_NUM)
            break;

        //��Χ��[0,m+1]
        m = rand()% (m+2);
        order_commd.push_back(m);
        commd++;
        if(commd >= COMMD_NUM)
            break;
        if(m == COMMD_NUM-1)
            continue;
        order_commd.push_back(m+1);
        commd++;
        if(commd >= COMMD_NUM)
            break;

        //��Χ��[m+2,319]
        m = rand()% (COMMD_NUM -m -2) + m+2;
        order_commd.push_back(m);
        commd++;
        if(commd >= COMMD_NUM)
            break;
    }

    //��ָ������ת��Ϊҳ������
    for(int i=0;i<order_commd.size();i++)
    {
        order_page.push_back(commds[order_commd[i]]);
    }

//    printf("order_commd_size = %d\n",order_commd.size());
//    for(int i=0;i<order_commd.size();i++)
//    {
//        printf("%d ",order_commd[i]);
//    }

//    printf("order_page_size = %d\n",order_page.size());
//    for(int i=0;i<order_page.size();i++)
//    {
//        printf("%d ",order_page[i]);
//    }
}

double FIFO()
{
    int memory_page_num = 0;
    int err = 0;
    for(int i=0;i<order_page.size();i++)
    {
        bool flag =0;
        for(int j=0;j<memory_page_num;j++)
        {
            if(memory[j].index == order_page[i])
            {
                flag = true;
                break;
            }
        }

        if(!flag)   //û�ҵ�,����ȱҳ
        {
            err++;
            if(memory_page_num < MEM_PAHE_NUM)
            {
                memory[memory_page_num++].index = order_page[i];
            }
            else    //�����û��㷨��ʼ�û�
            {
                for(int j=1;j<memory_page_num;j++)
                {
                    memory[j-1] = memory[j];//����һ���û���ȥ
                }
                memory[memory_page_num-1].index=order_page[i];
            }
        }

        for(int j=0;j<memory_page_num;j++)
        {
            printf("%d",memory[j].index);
            (memory[j].index == order_page[i])?printf("* "):printf(" ");
        }
        if(!flag) printf("F");
        printf("\n");
    }
    printf("FIFO err =%d\n",err);
    return (double)err / COMMD_NUM;
}

double LRU()
{
    int memory_page_num = 0;
    int err = 0;
    for(int i=0;i<order_page.size();i++)
    {
        bool flag =0;
        for(int j=0;j<memory_page_num;j++)
        {
            if(memory[j].index == order_page[i])
            {
                flag = true;
                memory[j].time = i; //����ʱ���
                break;
            }
        }

        if(!flag)   //û�ҵ�,����ȱҳ
        {
            int minn=0;//�����С��
            err++;
            if(memory_page_num < MEM_PAHE_NUM)
            {
                memory[memory_page_num].time = i; //����ʱ���
                memory[memory_page_num++].index = order_page[i];
            }
            else    //�����û��㷨��ʼ�û�
            {
                for(int j=0;j<memory_page_num;j++)
                {
                    if(memory[minn].time > memory[j].time)
                    {
                        minn = j;
                    }
                }
                memory[minn].time = i;
                memory[minn].index = order_page[i];
            }
        }

        for(int j=0;j<memory_page_num;j++)
        {
            printf("%d",memory[j].index);
            (memory[j].index == order_page[i])?printf("* "):printf(" ");
        }
        if(!flag) printf("F");
        printf("\n");
    }
    printf("LRU err =%d\n",err);
    return (double)err / COMMD_NUM;
}

double OPT()
{
    int memory_page_num = 0;
    int err = 0;
    for(int i=0;i<order_page.size();i++)
    {
        bool flag =0;
        for(int j=0;j<memory_page_num;j++)
        {
            if(memory[j].index == order_page[i])
            {
                flag = true;
                break;
            }
        }

        if(!flag)   //û�ҵ�,����ȱҳ
        {
            err++;
            if(memory_page_num < MEM_PAHE_NUM)
            {
                memory[memory_page_num++].index = order_page[i];
            }
            else    //�����û��㷨��ʼ�û�
            {
                int far[MEM_PAHE_NUM] ;//�洢����
                int furthest = 0;//����Զ��λ�ã�
                for(int j=0;j<MEM_PAHE_NUM;j++) //��ʼ��Ϊ���ֵ
                    far[j] = MAX_FAR;
                for(int j=0;j<memory_page_num;j++)
                {
                    for(int k=i+1;k<order_page.size();k++)
                    {
                        if(memory[j].index == order_page[k]) //δ�����ֵ�λ��
                            far[j] = k;
                    }
                }
                for(int j=0;j<memory_page_num;j++) //����Զ��λ��
                    if(far[furthest] < far[j])
                        furthest = j;
                //�ҵ�֮���û�
                memory[furthest].index = order_page[i];
            }
        }

        for(int j=0;j<memory_page_num;j++)
        {
            printf("%d",memory[j].index);
            (memory[j].index == order_page[i])?printf("* "):printf(" ");
        }
        if(!flag) printf("F");
        printf("\n");
    }
    printf("OPT err =%d\n",err);
    return (double)err / COMMD_NUM;
}

