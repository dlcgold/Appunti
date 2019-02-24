lavora_per(bill, google). %questo è un fatto
lavora_per(mario, oracle).
lavora_per(steve, apple).  % con ; scorro le varie alternative nel compilatore
lavora_per(jane, google).

collega(X, Y) :- lavora_per(X, Z),
                 lavora_per(Y, Z),
                 X \= Y. % questa è una regola e con \= evita che X = X sia un risultato