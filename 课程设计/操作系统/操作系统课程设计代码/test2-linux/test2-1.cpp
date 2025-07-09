#include <stdio.h>
#include<time.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
    int x;
    srand((unsigned)time(NULL));
    while((x=fork())==-1);
    if (x==0)
    {
        sleep(rand() % 2);
        printf("a");
    }
    else
    {
        sleep(rand() % 3);
        printf("b");
    }
    printf("c");
    return 0;
}
