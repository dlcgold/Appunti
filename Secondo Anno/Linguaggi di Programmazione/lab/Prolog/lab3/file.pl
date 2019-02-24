/* gioco del tris
  uso una lista a 9 entrate per le caselle
  e una con le x e gli o */

/* vedo se c'è posto in una lista, prendendo il primo libero,
    dove c'è ancora un numero,
    [1,2,x,o,o,x,7,8,o] -> X=[1, 2, 7, 8] */

find_free_position([], []).
find_free_position([x|T], X) :- find_free_position(T, X). % no x in H
find_free_position([o|T], X) :- find_free_position(T, X). % no o in H
% se H è numero controllo la coda
find_free_position([H|T], [H|T2]) :- find_free_position(T, T2).

/* stampa della tabella */

print_board([]).
print_board([X|T]) :-  write(X), write(" "),
                       print_board2(T). % prima colonna
print_board2([X|T]) :-  write(X), write(" "),
                        print_board3(T). % seconda colonna
print_board3([X|T]) :-  write(X), write(" "),
                        nl,
                        print_board(T). % terza colonna, nl=newline


/* giocatore vince se tre X di fila */

win(X, [X, X, X, _, _, _, _, _, _]).
win(X, [_, _, _, X, X, X, _, _, _]).
win(X, [_, _, _, _, _, _, X, X, X]).
win(X, [X, _, _, X, _, _, X, _, _]).
win(X, [_, X, _, _, X, _, _, X, _]).
win(X, [_, _, X, _, _, X, _, _, X]).
win(X, [X, _, _, _, X, _, _, _, X]).
win(X, [_, _, X, _, X, _, X, _, _]).


/* nth0(Pos, Lista, Elem, Rest) è gia in prolog */

/* replace (Pos, Lista, Elem, Lista2) per le giocate
   replace(2, [1,3,8], 4, X) -> X=[1, 3, 4]*/

/* rimuovo qualsiasi cosa in pos e salvo la lista rimanente
   a quel punto uso nth0 per chiedere la lista L2 tale che
   ci sia I in pos a partire da L3 che non ha più l'elemento
   che aveva prima */
replace(Pos, List, Index, List2) :- nth0(Pos, List, _, List3),
                                    nth0(Pos, List2, Index, List3).

/* richiedo mossa e salvo la mossa */
 /* member mi dice se elemento è in lista */
player_move(Board, Player, NewBoard) :- print_board(Board),
                                        write("Dove vuoi fare la mossa?"),
                                        nl,
                                        read(X),
                                        find_free_position(Board, FP),
                                        X<10,
                                        member(X, FP),  % libero?
                                        Pos is X-1, % si parte da 0
                                        replace(Pos, Board, Player, NewBoard).

player_move(Board, Player, NewBoard) :- write("Posizione non valida"),
                                         nl,
                                         player_move(Board, Player, NewBoard).

/* passiamo al gioco */

game(Board, _) :- win(x, Board),
                  writeln("Ha vinto il giocatore x").
game(Board, _) :- win(o, Board),
                  writeln("Ha vinto il giocatore o").
/* se non ho più mosse possibili è pareggio */
game(Board, _) :- find_free_position(Board, []),
                  writeln("Pareggio").

game(Board, x) :- player_move(Board, x, NewBoard),
                  game(NewBoard, o).
game(Board, o) :- player_move(Board, o, NewBoard),
                  game(NewBoard, x).

/* faccio iniziare il gioco */

one_game :- write("Inizia x oppure o?"),
            nl,
            read(X),
            member(X, [x,o]),
            game([1, 2, 3, 4, 5, 6, 7, 8, 9], X).
