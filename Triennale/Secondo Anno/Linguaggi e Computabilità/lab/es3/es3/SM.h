/***************************************************************************
Stack Machine
***************************************************************************/
/*=========================================================================
DECLARATIONS
=========================================================================*/
/* OPERATIONS: Internal Representation */
enum code_ops { HALT, STORE, JMP_FALSE, GOTO,
DATA, LD_INT, LD_VAR,
READ_INT, WRITE_INT,
LT, EQ, GT, ADD, SUB, MULT, DIV };
/* OPERATIONS: External Representation */
char *op_name[] = {"HALT", "STORE", "JMP_FALSE", "GOTO",
"DATA", "LD_INT", "LD_VAR",
"READ_INT", "WRITE_INT",
"LT", "EQ", "GT", "ADD", "SUB", "MULT", "DIV" };
struct instruction
{
enum code_ops op;
int arg;
};
/* CODE Array */
struct instruction code[999];
/* RUN-TIME Stack */
int stack[999];
/*-------------------------------------------------------------------------
Registers
-------------------------------------------------------------------------*/
int pc = 0;
struct instruction ir;
int top = -1;
char ch;
/*=========================================================================
Fetch Execute Cycle
=========================================================================*/
void fetch_execute_cycle()
{ do {
/* Fetch */
ir = code[pc];
printf( "PC = %3d IR.op = %4d IR.arg = %4d Top = %3d:%4d / %-4d   %s\n",
           pc, ir.op, ir.arg, top, stack[top],stack[top-1],op_name[ir.op]); 
pc++;
/* Execute */
/*******************/
switch (ir.op) {
case HALT : printf( "halt\n" ); break;
case READ_INT : printf( "Input: " );
 scanf( "%ld", &stack[++top] ); break;
case WRITE_INT : printf( "Output: %d\n", stack[top--] ); break;
case STORE : stack[ir.arg] = stack[top--]; break;
case JMP_FALSE : if ( stack[top--] == 0 )
  pc = ir.arg;
 break;
case GOTO : pc = ir.arg; break;
case DATA : top = top + ir.arg; break;
case LD_INT : stack[++top] = ir.arg; break;
case LD_VAR : stack[++top] = stack[ir.arg]; break;
case LT : if ( stack[top-1] < stack[top] )
  stack[--top] = 1;
 else stack[--top] = 0;
 break;
case EQ : if ( stack[top-1] == stack[top] )
 stack[--top] = 1;
else stack[--top] = 0;
 break;
case GT : if ( stack[top-1] > stack[top] )
  stack[--top] = 1;
 else stack[--top] = 0;
 break;
case ADD : stack[top-1] = stack[top-1] + stack[top];
 top--;
 break;
case SUB : stack[top-1] = stack[top-1] - stack[top];
 top--;
 break;
case MULT : stack[top-1] = stack[top-1] * stack[top];
 top--;
 break;
case DIV : stack[top-1] = stack[top-1] / stack[top];
 top--;
 break;
default : printf( "%sInternal Error: Memory Dump\n" );
 break;
}
/*******************/
}
while (ir.op != HALT);
}
/*************************** End Stack Machine **************************/
