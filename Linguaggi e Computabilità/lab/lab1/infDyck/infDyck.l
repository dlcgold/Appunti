
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

%%

"("                   { yyparser.yylval = new ParserVal(""+yycharat(0));
                        return Parser.OPEN_PAREN; }
")"                   { yyparser.yylval = new ParserVal(""+yycharat(0));
                        return Parser.CLOSE_PAREN; }
"["                   { yyparser.yylval = new ParserVal(""+yycharat(0));
                        return Parser.OPEN_PARENQ; }
"]"                   { yyparser.yylval = new ParserVal(""+yycharat(0));
                        return Parser.CLOSE_PARENQ; }
[a-z]+                { yyparser.yylval = new ParserVal(yytext());
                        return Parser.SKIP; }
"::"                  { yyparser.yylval = new ParserVal(yytext());
                        return Parser.COLPAR; }
[A-Z]{2}              { yyparser.yylval = new ParserVal(yytext());
                        return Parser.TWOUP; }

[^] {} /*butta il resto*/
