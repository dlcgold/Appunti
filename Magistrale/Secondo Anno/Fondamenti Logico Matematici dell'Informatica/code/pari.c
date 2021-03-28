#include <stdio.h>

void A1(int* fdv){
  *fdv = 0;
}
void A2(int* j, int* fdv){
  *fdv = 1;
  *j = *j + 1;
}
void A3(int* j, int* fdv){
  *fdv = 0;
  *j = *j + 1;
}
void base(int* f){
  A1(&*f);
}
void passo(int* j, int ff1, int* ff2){
  if (ff1 == 0){
    A2(&*j, &*ff2);
  }else{
    A3(&*j, &*ff2);
  }
}

int main(){
  int x, j, ff;
  scanf("%d", &x);
  j = 0;
  base(&ff);
  while(j <= x - 1){
    passo(&j, ff, &ff);
  }
  if (ff == 0){
    printf("%d è pari\n", x);
  }else{
    printf("%d è dispari\n", x);
  }
  return 0;
}
