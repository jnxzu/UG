#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

void heapify(int *A, int hsize, int i){
  int l=2*i;
  int r=(2*i)+1;
  int max=i;
  int temp;
  if(l<hsize && A[l]>A[max]) max=l;
  if(r<hsize && A[r]>A[max]) max=r;
  if(max!=i){
      temp=A[i];
      A[i]=A[max];
      A[max]=temp;
      heapify(A, hsize, max);
  }
}

void sortH(int *A, int hsize){
  int i;
  int temp;
  for(i=(hsize/2)-1;i>=0;i--) 
    heapify(A, hsize, i);
  for(i=hsize-1;i>=1;i--){
    temp=A[i];
    A[i]=A[0];
    A[0]=temp;
    heapify(A,i,0);
  }
}

int main(){
  int hsize=0;
  int i=0;
  char x;
 
  FILE *data;
  data=fopen("data.txt","r");
 
  while(!feof(data)){
    x=fgetc(data);
    if(x=='\n') hsize++;
  }
 
  int* A = malloc(hsize*sizeof(*A));
 
  rewind(data);
  while(!feof(data)){
    fscanf(data, "%d\n", &A[i]);
    i++;
  }
 
  printf("Nieposortowane: \n");
  for(int i=0;i<hsize;i++){
    printf("%d. %d \n",i+1,A[i]);
  }
  
  sortH(A, hsize);
  FILE *result;
  result=fopen("result.txt","w");
  
  fprintf(result,"Posortowane: \n");
  for(int i=0;i<hsize;i++){
    fprintf(result, "%d. %d \n",i+1,A[i]);
  }
  fclose(data);
  fclose(result);
  free(A);
  return 0;
}
