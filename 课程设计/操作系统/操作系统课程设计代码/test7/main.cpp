#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define max 20
int CN;//�������  Cylindrical  Number
int CO[max];//����˳�� Cylindrical Order
void FCFS()
{
	printf("\n---------------------FCFS-----------------------\n");
	int sum = 0,i;
	printf("����˳��: ");
	for (i = 1; i < CN; i++)
		printf("%-4d", CO[i]);
	printf("\n�ƶ�����: ");
	for (i = 1; i < CN; i++)
	{
		int j = abs(CO[i] - CO[i - 1]);
		printf("%-4d", j);
		sum += j;
	}
	  printf("\nһ���ƶ���%d������\n", sum);
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
		printf("����˳��: ");
		for (i = 1; i < CN; i++)
			printf("%-4d", copy[i]);
		printf("\n�ƶ�����: ");
		for (i = 1; i < CN; i++)
		{
			j = distance[i];
			printf("%-4d", j);
			distance[0] += j;
		}
		printf("\nһ���ƶ���%d������\n",distance[0]);
	
}
void SCAN()
{
	printf("\n---------------------SCAN-----------------------\n");
	int choice,copy[max]; 
	printf("��ѡ���ͷ�ƶ����� 1.��������  2.�������� :  ");
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
	printf("����˳��: ");
	for (i = 1; i < CN; i++)
		printf("%-4d", copy[i]);
	int distance[max] = { 0 };
	printf("\n�ƶ�����: ");
	for (i = 1; i < CN;i++ )
	{
		distance[i] = abs(copy[i] - copy[i - 1]);
		printf("%-4d", distance[i]);
		distance[0] += distance[i];
	}
	printf("\nһ���ƶ���%d������\n", distance[0]);
}
int  main()
{
	int i;

	Test:printf("�����������: "); scanf("%d", &CN);
	printf("������������: ");
	for (i = 0; i < CN; i++)
		scanf("%d", &CO[i]);
	FCFS();
	SSTF();
	SCAN();
	int choice;
	printf("�Ƿ��������? ��(1) ��(0)  :   "); scanf("%d", &choice);
	if (choice == 1) goto Test;
	else return 0;
}


