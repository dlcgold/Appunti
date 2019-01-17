%{
  import java.io.*;
%}

%token OPEN_PAREN;
%token CLOSE_PAREN;
%token <sval> SKIP;

%start s

%%

parens  : OPEN_PAREN s CLOSE_PAREN
        | OPEN_PAREN CLOSE_PAREN

exp     : parens

exps    : exp SKIP {System.out.println("S: "+$2); }
        | exp

s       : SKIP {int n = controllo($1);
				if($1.charAt(0) == ':' && (n % 2 != 0 || n == 0))
					System.out.println("txt: "+rimozione($1));
				else if($1.charAt(0) == ':' && (n % 2 == 0))
					System.out.println("txt: "+(':'+rimozione($1)));
				else if(Character.isUpperCase($1.charAt(0)) 
							&& Character.isUpperCase($1.charAt(1))
							&& $1.charAt(0) == $1.charAt(1))
					System.out.println("txt: "+$1.substring(2));
				else if(Character.isUpperCase($1.charAt(0)) 
							&& Character.isUpperCase($1.charAt(1))
							&& $1.charAt(0) != $1.charAt(1))
					System.out.println("Err: "+$1.substring(2));
				else
					System.out.println("txt: "+$1);}
        | exps
        | s exps

%%

void yyerror(String s)
{
 System.out.println("err:"+s);
 System.out.println("   :"+yylval.sval);
}

static Yylex lexer;
int yylex()
{
 try {
  return lexer.yylex();
 }
 catch (IOException e) {
  System.err.println("IO error :"+e);
  return -1;
 }
}

public static void main(String args[])
{
 System.out.println("[Quit with CTRL-D]");
 Parser par = new Parser();
 lexer = new Yylex(new InputStreamReader(System.in), par);
 par.yyparse();
}

public static int controllo(String a){
	int conta = 0;
	for (int i = 0; i < a.length() - 1 ; i++) {
		if(a.charAt(i) == ':' && a.charAt(i) == a.charAt(i+1))
			conta ++;
		else
			break;
	}
	return conta;
}

public static String rimozione(String a){
	int conta = 0;
	String av="";
	for (int i = 0; i < a.length() - 1 ; i++) {
		if(a.charAt(i) == ':')
			conta ++;
	}
	av=a.substring(conta);
	return av;
}