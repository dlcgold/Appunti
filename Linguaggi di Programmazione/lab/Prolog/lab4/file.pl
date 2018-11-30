/* data stringa e carattere rimuova tutte le copie di quel carattere */

remove([], _,[]).
remove([H|T], H, L) :- remove(T, H, L).
remove([H|T], X, [H|S]) :- H\= X,
                           remove(T, X, S).


remove_char([], _, []).
remove_char([X], X, []).
remove_char(String, Char, NewString) :- atom_string(Char, C),
                                        string_codes(C, [Charc]),
                                        string_codes(String, S),
                                        remove(S, Charc, NewString2),
                                        string_codes(NewString, NewString2).


library(readutil).
read_file_to_string(Filename, Result) :- read_file_to_string(Filename, Result, []).
read_file_to_string(Filename, Result, Options) :- open(Filename, read, In, Options),
                                                 read_string(In, _, Result),
                                                  close(In).

%parse_integer(Chars, I, MoreChars).

%parse_integer(Chars, DigitsSoFar, I, IntegerCodes, MoreChars).

parse_integer(Chars, I, MoreChars) :- parse_integer(Chars, [], I, _, MoreChars).
parse_integer([D | Ds], DsSoFar, I, ICs, Rest) :- is_digit(D),
                                                  !,
                                                  parse_integer(Ds, [D | DsSoFar], I, ICs, Rest).

parse_integer([C | Cs], DsR, I, Digits, [C | Cs]) :-
% not_is_digit(C),
                                                 !,
                                                  reverse(DsR, Digits),
                                                  number_string(I, Digits).

parse_integer([], DsR, I, Digits, []) :- !,
                                        reverse(DsR, Digits),
                                        number_string(I, Digits).

parse_float(Chars, F, MoreChars).
parse_float(Chars,F,FloatCodes,MoreChars).
parse_float(Chars, F, MoreChars) :- parse_float(Chars, F, _, MoreChars).
parse_float(Chars, F, FloatCodes, MoreChars) :-
parse_integer(Cs, [], _I, IntegerDigits, MoreInput),
parse_float_decimal(MoreInput,_Decimal,DecimalChars,DecimalRestCs),
                   append(IntegerDigits, DecimalChars, FloatChars),
                   number_string(F, FloatChars).
