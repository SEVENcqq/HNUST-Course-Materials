#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define max 20
int CN;//柱面个数  Cylindrical  Number
int CO[max];//柱面顺序 Cylindrical Order
void FCFS()
{
	printf("\n---------------------FCFS-----------------------\n");
	int sum = 0,i;
	printf("柱面顺序: ");
	for (i = 1; i < CN; i++)
		printf("%-4d", CO[i]);
	printf("\n移动距离: ");
	for (i = 1; i < CN; i++)
	{
		int j = abs(CO[i] - CO[i - 1]);
		printf("%-4d", j);
		sum += j;
	}
	  printf("\n一共移动了%d个柱面\n", sum);
}
void SSTF()
{
	printf("\n---------------------SSTF-----------------------\n");
	int i, j, copy[max];
	int pos,min,temp;
	int distance[max] = { 0 };
	for (i = 0; i < CN; i++)
	{
		copy[i] = CO[i];
	}
		for (i = 1; i < CN; i++)
		{
			pos = i;
			min = abs(copy[i] -copy[i - 1]);
			for (j = i + 1; j < CN; j++)
			{
				if (abs(copy[j] - copy[i - 1]) < min)
				{
					min = abs(copy[j] - copy[i - 1]);
					pos = j;
				}
			}
			distance[i] = min;
			if (pos != i)
			{
				temp = copy[pos];
				copy[pos] = copy[i];
				copy[i] = temp;
			}
		}
		printf("柱面顺序: ");
		for (i = 1; i < CN; i++)
			printf("%-4d", copy[i]);
		printf("\n移动距离: ");
		for (i = 1; i < CN; i++)
		{
			j = distance[i];
			printf("%-4d", j);
			distance[0] += j;
		}
		printf("\n一共移动了%d个柱面\n",distance[0]);
	
}
void SCAN()
{
	printf("\n---------------------SCAN-----------------------\n");
	int choice,copy[max]; 
	printf("请选择磁头移动方向 1.由外向里  2.由里向外 :  ");
	scanf("%d", &choice);
	int i, j;
	if (choice == 1)
	{
		j = 0;
		for (i =0 ; i < CN;i++)
			if (CO[i]>=CO[0])
				copy[j++] = CO[i];
		int n = j-1;
		bool flag = true;
		while (n > 1 && flag)
		{
			flag = false;
			for (i = 0; i < n - 1;i++)
				if (copy[i]>copy[i + 1])
				{
					int temp = copy[i];
					copy[i] = copy[i + 1];
					copy[i + 1] = temp;
					flag = true;
				}
			n--;
		}
		int m = j;
		for (i = 1; i < CN; i++)
			if (CO[i] < CO[0])
				copy[j++] = CO[i];
		n = j;
		flag = true;
		while (n >= m&&flag)
		{
			flag = false;
			for (i = m; i < n - 1;i++)
				if (copy[i] < copy[i + 1])
				{
					int temp = copy[i];
					copy[i] = copy[i + 1];
					copy[i + 1] = temp;
					flag = true;
				}
			n--;
		}

	}
	else
	{
		j = 0;
		for (i =0 ; i < CN;i++)
			if (CO[i]<=CO[0])
				copy[j++] = CO[i];
		int n = j;
		bool flag = true;
		while (n > 1 && flag)
		{
			flag = false;
			for (i = 0; i < n - 1;i++)
				if (copy[i]<copy[i + 1])
				{
					int temp = copy[i];
					copy[i] = copy[i + 1];
					copy[i + 1] = temp;
					flag = true;
				}
			n--;
		}
		int m = j;
		for (i = 1; i < CN; i++)
			if (CO[i] > CO[0])
				copy[j++] = CO[i];
		n = j;
		flag = true;
		while (n >= m&&flag)
		{
			flag = false;
			for (i = m; i < n - 1;i++)
				if (copy[i] > copy[i + 1])
				{
					int temp = copy[i];
					copy[i] = copy[i + 1];
					copy[i + 1] = temp;
					flag = true;
				}
			n--;
		}

	}
	printf("柱面顺序: ");
	for (i = 1; i < CN; i++)
		printf("%-4d", copy[i]);
	int distance[max] = { 0 };
	printf("\n移动距离: ");
	for (i = 1; i < CN;i++ )
	{
		distance[i] = abs(copy[i] - copy[i - 1]);
		printf("%-4d", distance[i]);
		distance[0] += distance[i];
	}
	printf("\n一共移动了%d个柱面\n", distance[0]);
}
int  main()
{
	int i;

	Test:printf("输入柱面个数: "); scanf("%d", &CN);
	printf("输入柱面序列: ");
	for (i = 0; i < CN; i++)
		scanf("%d", &CO[i]);
	FCFS();
	SSTF();
	SCAN();
	int choice;
	printf("是否继续测试? 是(1) 否(0)  :   "); scanf("%d", &choice);
	if (choice == 1) goto Test;
	else return 0;
}


