#include <stdio.h>
#include <string.h>
#include "SM.h"
void main( int argc, char *argv[] )
{
FILE *yyin;
++argv; --argc;
yyin = fopen( argv[0], "r" );
//PRIMOtest: for(int i=0;i<16;i++){code[i].op=15-i;code[i].arg=13;stack[i]=14;}
//memcpy(code, (struct instruction[]) { {4,9},{7,0},{1,1},{6,1},{5,10},{9,0},
//		{2,10},{5,1},{1,2},
//		{3,10},{6,1},{5,10},{9,0},
//		{2,23},{5,5},{6,2},{14,0},{1,2},{6,1},{5,1},{12,0},{1,1},{3,10},
//		{6,1},{8,0},{6,2},{8,0},{0,0}}, sizeof code);
 int addr; int op; char text[10]; int arg;
 int stC=0;
 while( fscanf(yyin, "%d-%d/%s %d",&addr,&op,&text,&arg), op>0)
 {
  code[stC].op=op; code[stC].arg=arg;
  ++stC;
 }
fetch_execute_cycle();
}
/*
0-4DATA 9
1-7READ_INT 0
2-1STORE 1
3-6LD_VAR 1
4-5LD_INT 10
5-9LT 0
6-
7-5LD_INT 1
8-1STORE 2
9-
6:2JMP_FALSE 10
9:3GOTO 10
10-6LD_VAR 1
11-5LD_INT 10
12-9LT 0
13-
14-5LD_INT 5
15-6LD_VAR 2
16-14MULT 0
17-1STORE 2
18-6LD_VAR 1
19-5LD_INT 1
20-12ADD 0
21-1STORE 1
22-3GOTO 10
13:2JMP_FALSE 23
23-6LD_VAR 1
24-8WRITE_INT 0
25-6LD_VAR 2
26-8WRITE_INT 0
27-0HALT 0

BEGIN
read R1;
   if R1 < 10 then 
       R2 := 1; 
   else 
       skip; 
   fi;

   while R1 < 10 do 
       R2 := 5*R2; 
       R1 := R1+1; 
   wend;
   skip;
   write R1;
   write R2;
END
//   integer n,x.
*/
