/* es 1 */
lavora_per(bill, google). %questo è un fatto
lavora_per(mario, oracle).
lavora_per(steve, apple).  % CON ; MI FRACCIO DARE TUTTE LE OPZIONI NEL COMPILATORE
lavora_per(jane, google).

collega(X, Y) :- lavora_per(X, Z),
                 lavora_per(Y, Z),
                 X \= Y. % questa è una regola e con \= evita che X = X sia un risultato
/* es2 */
nat(0). % definisco i naturali, 0 è naturale e lo sono anche i successori di un naturale
nat(s(N)) :- nat(N). % s(N) indica il successore, in compilatore sarà s(s(0)) etc...
/* definisco la somma */
/* n + m = (n+1) + (m-1) se m>0
n + 0 = n*/
succ(N, s(N)).
sum(N, 0, N). % il terzo è il risultato
sum(N, s(M), s) :- sum(N, M, T),
                succ(T, s).

summ(N, 0, N).
summ(N, s(M), s(T)) :- summ(N, M, T).
summ(N, M, S) :-  summ(s(N), P, S),
                   succ(P, M).

/* fattoriale */

fact(0, 1). % fattoriale di 0 è 1
fact(N, M) :- N>0,
              N1 is N-1,
              fact(N1, M1),
              M is N * M1. /* ogni volta salva in M1 il risultato parizale */

/* controllo se in testa a lista c'è un certo valore */
 intesta(X, [X|_]). /* | separa tedsta e coda (che non ci interessa e indico con _,                         senza non si sa che la lista può continuare.
                    interrogo con, per esempio  intesta(2, [2,3]). */

/* trovo ennesimo elemento*/
 nth(0, [X|_], X). % se cerco lo 0, caso base
nth(N, [_|T], X) :- N1 is N-1,
                     nth(N1, T, X). /* se non è all'inizio cerco nella coda
                                     ad ogni passo la head auementa di uno
                                      tengo solo T che è la coda, ovvero tutto tranne il primo*/

/*lista contiene elemento?*/
contains(X, [X|_]). % controllo testa
contains(X,[_|T]) :- contains(X, T). % controllo ricorsivcamente la coda

/* funzione append per concatenare */
append([], L, L). % vuota più L è L
append([H|T], L, [H|X]) :- append(T, L, X). /* l'inizio della lista finale è H  ela fine è l                                              a fine delle liste concatenate*/


/*chiedo se una lista  è ordinbata */

sorted([]). /*lista vuota ordinata */
sorted([_]). /*listadi un elemento ordinata */
sorted([X, Y| T]) :- X =< Y,
                     sorted([Y| T]). /* confrpnto sempre l'elemento col resto della tail */

/* chiedo ultimo elemento */

last([X], X). %lista di un elemento ha come ultimo quell'elemento */
 sommalast([_|T], X) :- last(T, X). /*controllo ricorsivamente la coda della lista finché non
                                 ho solo T e posso usare il caso base
                                 posso anche chieder euna lista che finisca con un certo N
                                per esempio 4  last(X, 4). */

/*tolgo tutte le occorrenze*/

remove_all([], _, []). % la lista vuota non ha nulla da rimuovere */
remove_all([X|T], X, L) :- remove_all(T, X, L). /* se la testa è quel numero rimuovo tutte
                                                   le occorrenze... ma se la testa \= X
                                                   non va*/
remove_all([H|T], X, [H|L]) :- H \= X,
                               remove_all(T, X, L). /* se H\=X  faccio il controllo senza H */

/*somma eslementi lista */
somma_lista([], 0).
somma_lista([H|T], X) :-  somma_lista(T, N),
                            X is H + N. /*sommo tutte le tail ricorsivamente e poi ci
                                           sommo la H */

/* duplico lista [1,2]->[1,1,2,2] */

duplico([], []).
duplico([H|T], [H, H|X]) :- duplico(T, X). /* a priori dublico H riscivendpo nelk risultato
                                              poi dublico il tail, dove di volta in volta
                                              ogni head verrà dublicato */
