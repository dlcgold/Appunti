%%%% -*- Mode: Prolog -*-

%%%% parser-examples.pl
%%%% Examples of 'parser' related code, useful for the construction of
%%%% "from scratch" parsers.

/*
 * Strings, atoms and codes.
 * Remember the difference between strings, atoms and "code lists" in
 * SWI Prolog.
 *
 * In SWI Prolog, characters can be referred to as "codes" (usually
 * their ASCII code), or as atoms containing a single character glyph
 * (or an escape sequence, such as '\n').  The predicate
 *
 *     char_code(Character, Code)
 *
 * is useful in converting form one to the other.
 *
 * The most useful string representation for "from scratch" parsers is
 * the "code list" one.
 */

/*
 * First we start by defining a number of predicates that will be useful
 * in building of various "from scratch" parsers. Note the use of the
 * predicate char_type/1. Note also that the predicates strive to be as
 * general as possible.
 *
 * Some of the predicates are only in "code form"; laziness and bad
 * reaction from Emacs indent behavior :)
 */

% is_digit(C) :- char_type(C, digit). % Already available as ctypes:is_digit/1.

is_whitespace(C) :- char_type(C, white).

is_punctuation(C) :- char_type(C, punct).

is_dot('.').			% Single character atom form.
is_dot(0'.).			% Character code form in ISO syntax.
is_dot(".").			% Single character string form.

is_alphanumeric(C) :- char_type(C, alnum).
is_alphabetic(C) :- char_type(C, alpha).

is_lbracket(123).
is_rbracket(125).

is_lparen(40).
is_rparen(41).

is_rsqbracket(93).
is_lsqbracket(91).

is_double_quote(34).
is_single_quote(39).

is_langle(60).
is_rangle(62).

is_backslash(92).

is_comma(44).

is_colon(58).

/*
 * Next we define a predicate to read a whole file (small!) into a
 * string. Note that this predicate is not very robust, as it does not
 * account for errors in the File System interaction.
 *
 * Cfr., the predicate read_fil
 */


%%	read_file_to_string(Filename, Result)
% Reads a Filename into a String.
% The 'readutil' library of SWI Prolog contains a read_file_to_string/3
% predicate.  Hence the definition of read_file_to_string_opt/3.

read_file_to_string(Filename, Result) :-
	read_file_to_string_opt(Filename, Result, []).

read_file_to_string_opt(Filename, Result, Options) :-
	open(Filename, read, In, Options),
	read_string(In, _, Result),
	close(In).

/*
 * Next we start looking at 'parser' predicates.
 * The 'parser predicates' all take an input list of codes and
 * eventually have another argument which is "what is left of the input"
 * (which can be the empty list []).
 *
 * Note that the integer and float parsing predicates do not check for
 * the number signs. You can extend the predicates to handle this as an
 * exercise.
 */


%%	skip_whitespaces(InputCodes, FromFirstNonWSCode)
% This predicates is true when, given a list of codes InputCodes,
% FromFirstNonWSCode is a suffix of InputCodes such that its first
% element is a non whitespace character.
%
% The way to understand this predicate is to look at the "consume"
% action done when the first character is a whitespace and at the
% "undo", or "unget" action done in the second rule when the character
% is not a whitespace character (thanks to the cut).
%
% This "consume/unget" pair of actions is at the core of the parser
% operation called "lexical analysis".
%
% You should also think about how a Finite State Machine would look like
% for this "whitespace skipping" operation.

skip_whitespaces([W | MoreChars], RestChars) :-
	is_whitespace(W),
	!,
	skip_whitespaces(MoreChars, RestChars).

skip_whitespaces([C | Chars], [C | Chars]) :- !. % Why is the 'cut' working?

skip_whitespaces([], []) :- !.


%%	parse_integer(CharacterCodes, I, RestCodes)
% This predicate works like skip_witespaces/2, but it has another
% argument, I, which will eventually contain the actual integer just
% read.
%
% The real workhorse is parse_integer/5, i.e.
%
%	parse_integer(CharacterCodes, DigitsSoFar, I, IntegerCodes, RestCodes)
%
% The predicate is true when DigitsSoFar collects, in reverse order,
% the digits comprising the integer and IntegerCodes contains the digits
% of the integer as they appear; I will eventually contain the integer
% and RestCodes will contain the character codes just following the
% integer just parsed.
%
% Note also the two base cases, where the character C is not a digit.
%
% You should also think about how a Finite State Machine would recognize
% the "language of integers".

parse_integer(Chars, I, Rest) :-
	parse_integer(Chars, [], I, _IntegerCodes, Rest).

parse_integer(Chars, I, IntegerCodes, Rest) :-
	parse_integer(Chars, [], I, IntegerCodes, Rest).

parse_integer([D | Ds], DigitsSoFar, I, IntegerCodes, Rest) :-
	is_digit(D),
	!,
	parse_integer(Ds, [D | DigitsSoFar], I, IntegerCodes, Rest).

parse_integer([C | MoreInput], Digits, I, RDs, [C | MoreInput]) :-
	!,
	reverse(Digits, RDs),
	number_string(I, RDs).

parse_integer([], Digits, I, RDs, []) :-
	!,
	reverse(Digits, RDs),
	number_string(I, RDs).


%%	parse_float(CharCodes, F, CodesAfterFloat)
% The following predicate recognizes floating point numers of the kind
%
%     D+
%
% or
%
%     D+'.'D+
%
% Where D is a digit.
%
% Again, the real workhorse is
%
%     parse_float(Cs, F, FloatChars, CodesAfterDecimal)
%
% As you can see, the predicate is true when we can prove
% parse_integer/5 on a prefix of Cs and then parse_float_decimal/4 on
% the remaining codes. The base cases all serve to produce a proper
% float F.

parse_float(Cs, F, RestCs) :-
	parse_float(Cs, F, _FloatDigits, RestCs).

parse_float(Cs, F, FloatChars, DecimalRestCs) :-
	parse_integer(Cs, [], _I, IntegerDigits, MoreInput),
	parse_float_decimal(MoreInput,
			    _Decimal,
			    DecimalChars,
			    DecimalRestCs),
	append(IntegerDigits, DecimalChars, FloatChars),
	number_string(F, FloatChars).


parse_float_decimal([D | Ds], Decimal, [0'. | DigitChars], Rest) :-
	is_dot(D),
	!,
	parse_integer(Ds, [], I, DigitChars, Rest),
	length(DigitChars, NDs),
	Decimal is I * (10.0 ** NDs).

parse_float_decimal([D | Ds], 0.0 , [0'., 0'0], [D | Ds]).

parse_float_decimal([], 0.0 , [0'., 0'0], []).


%%%% end of file -- parser-examples.pl

