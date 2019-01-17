
%%
%byaccj

%{
  /* store a reference to the parser object */
  private Parser yyparser;

  /* constructor taking an additional parser object */
  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

DIGIT=[0-9]
ID=R[0-9]
FALSE=("false" | "FALSE")
TRUE=("true" | "TRUE")
AND=("and" | "AND")
DUAL=("dual" | "DUAL")

%%

{FALSE} { return(Parser.FALSE); }
{TRUE} { return(Parser.TRUE); }
{AND} { return(Parser.AND); }
{DUAL} { return(Parser.DUAL); }
":=" { return(Parser.ASSGNOP); }
{DIGIT}+ { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));return(Parser.NUMBER); }
do { return(Parser.DO); }
else { return(Parser.ELSE); }
END { return(Parser.END); }
done { return(Parser.DONE); }
fi { return(Parser.FI); }
if { return(Parser.IF); }
BEGIN { return(Parser.BEGIN); }
read { return(Parser.READ); }
skip { return(Parser.SKIP); }
then { return(Parser.THEN); }
while { return(Parser.WHILE); }
write { return(Parser.WRITE); }
{ID} { yyparser.yylval = new ParserVal(Integer.parseInt(yytext().substring(1)));return(Parser.IDENTIFIER); }
[ \r\t\n]+ { }
"//".* { }
[^] { return yytext().charAt(0); }

