/* trovo min lista*/

% la lista da in automatico false
min([H], H).
min([H | T], H) :- min(T, X),
                 H =< X. % fa solo H
min([H | T], X) :- min(T, X),
                 H > X. % fa tutto il resto

/* rimuovo valore (una sola copia) da lista salvando in lista */
remove_one([], _, []).
remove_one([H | T], H, T). % se è la testa la rimuovo
remove_one([H | T], X, [H | S]):- remove_one(T, X, S),
                                  X \= H. /* la H la tengo e riattacco la nuova S
                                          e uso il primo casoi base e non il secondo
                                           usando il \=*/
/* selection sort */
selection_sort([], []).
selection_sort(X, [H| T]) :- min(X, H), % minimo in testa
                             remove_one(X, H, X1), %toglie il minimo
                             selection_sort(X1, T). % rifa conl T

/* unisco liste in ordine */
merge([], X, X).
merge(X, [], X).
merge([H1 | T1], [H2 | T2], [H1 | T]) :- H1 =< H2,
                             merge(T1, [H2 | T2], T).
merge([H1 | T1], [H2 | T2], [H1 | T]) :- H2 =< H1,
                             merge([H1 | T1], T2, T).


/* spezzo in due lista, pari e dispari */
split_in_two([],[],[]).
split_in_two([X],[X],[]).
split_in_two([H1, H2 | T],[H1 | T1],[H2 | T2]) :- split_in_two(T, T1, T2).

/* mergesort */
/* divide in 2 la lista, fa il mergesort delle due e merge dei risultati*/
mergesort([], []).
mergesort([X], [X]).
mergesort(L1, L2) :-  split_in_two(L1, X1, Y1),
                        mergesort(X1, X2),
                        mergesort(Y1, Y2),
                        merge(X2, Y2, L2).

/* da più listre annidate a una */
/* da [[1, 3],[[4]]] */
/* uso definizione di lista e append */
listp([]).
listp([_ | _]). % vero solo se ho lista

append([], X, X).
append([H|T], L, [H|X]) :- append(T, L, X).

flatten([], []).
flatten([H | T], L) :- listp(H),
                       flatten(H, X),
                       flatten(T, Y),
                       append(X, Y, L). /* se testa è lista la appiattisco
                                          e la aggiungo al risultato */
flatten([H | T], [H | X]) :- flatten(T, X). /* altrimenti appiattisco la tail
                                              ricorsivamente tenendo la head*/
