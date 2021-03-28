#include <stdio.h>
int main(){
  int x;
  scanf("%d", &x);
  if ((x % 2) == 0){
    printf("%d è pari\n", x);
  }else{
    printf("%d è dispari\n", x);
  }
  return 0;
}
