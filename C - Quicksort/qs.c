#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define C 4
#define MLD 1000000000.0

void swap(int* a, int* b) {
    int t=*a;
    *a=*b;
    *b=t;
}

int partition (int A[], int p, int r) {
    int x = A[r];
    int i = (p-1);
    for (int j=p;j<=r-1;j++){
        if (A[j]<=x) {
            i++;
            swap(&A[i], &A[j]);
        }
    }
    swap(&A[i+1], &A[r]);
    return (i+1);
}

void bubbleSort(int A[], int n){
   int i, j;
   for (i=0;i<n-1;i++)
       for (j=0;j<n-i-1;j++)
           if (A[j]>A[j+1])
              swap(&A[j], &A[j+1]);
}

void quickSort(int A[], int p, int r){
	if(p<r){
		int pi = partition(A, p, r);
		quickSort(A, p, pi-1);
		quickSort(A, pi+1, r);
	}
}

void quickSortBubble(int A[], int p, int r){
    if(r-p+1<C){
		bubbleSort(A, r-p+1);
	}
    else{
        if(p<r){
			int pi = partition(A, p, r);
			quickSort(A, p, pi-1);
			quickSort(A, pi+1, r);
		}
    }
}

void rnd(int A[], int n){
	for(int i=0;i<n;i++){
		A[i]=(rand()%2000);
	}
}

void bad(int A[], int n){
	int j=0;
	for(int i=n-1;i>=0;i--){
		A[j]=i;
		j++;
	}
}

void test_random_qs(int n){
	int A[n];
	struct timespec tp0, tp1;
	double Tn;

	rnd(A, n);

	clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
	quickSort(A, 0, n);
	clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);
	Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
	printf("|	%.10lf	",Tn);
}

void test_random_qsb(int n){
	int A[n];
	struct timespec tp0, tp1;
	double Tn;

	rnd(A, n);

	clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
	quickSortBubble(A, 0, n);
	clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);
	Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
	printf("|	%.10lf \n",Tn);
}

void test_bad_qs(int n){
	int A[n];
	struct timespec tp0, tp1;
	double Tn;

	bad(A, n);

	clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
	quickSort(A, 0, n);
	clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);
	Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
	printf("|	%.10lf	",Tn);
}

void test_bad_qsb(int n){
	int A[n];
	struct timespec tp0, tp1;
	double Tn;

	bad(A, n);

	clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
	quickSortBubble(A, 0, n);
	clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);
	Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
	printf("|	%.10lf \n",Tn);
}

int main(){
    int i;
    int j;

	printf("Wartosci losowe: \n");
	printf("Rozmiar		|	Quicksort	|	Quicksort/Bubblesort \n");
	printf("--------------------------------------------------------------------------------\n");
	for(i=1;i<=4;i++){
		if(i==1) j=1000;
		if(i==2) j=5000;
		if(i==3) j=10000;
		if(i==4) j=15000;
		printf("%d		", j);
		test_random_qs(j);
		test_random_qsb(j);
	}

	printf("\nWartosci niekorzystne: \n");
	printf("Rozmiar		|	Quicksort	|	Quicksort/Bubblesort \n");
	printf("--------------------------------------------------------------------------------\n");
	for(i=1;i<=4;i++){
		if(i==1) j=1000;
		if(i==2) j=5000;
		if(i==3) j=10000;
		if(i==4) j=15000;
		printf("%d		", j);
		test_bad_qs(j);
		test_bad_qsb(j);
	}
    return 0;
}
