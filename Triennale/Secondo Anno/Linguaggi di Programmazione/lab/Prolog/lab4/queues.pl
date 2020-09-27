%%%% -*- Mode: Prolog -*-

%%	queues.pl --

queue_empty_s(q([])).

queue_head_s(Head, q(Queue1), q([Head | Queue1])).

queue_last_s(Last, q(Queue1), q(Queue2)) :-
	append(Queue2, [Last], Queue1).



%%	Queues with "difference lists".
% The property is that in the term 'q(Front, Back)', Back is a suffix of
% Front, and the sequence (i.e., list) represented is what is not shared
% by Front and Back.

%%	queue_empty_dl(Q).
% This predicate is true when the argument is an empty (or new) queue.
% Note that the two arguments to the term q(_, _) are the same, hence
% the resulting sequences would be empty.

queue_empty(q(Q, Q)).


%%	queue_is_empty(Q).
% True if a queue is really empty. This means checking that the front
% and back lists are structurally equivalent.

queue_is_empty(q(F, B)) :- F == B.


%%	queue_not_empty(Q).
% True if a queue is really empty.

queue_not_empty(q(F, B)) :- F \== B.


%%	queue_dequeue(Element, Q, NQ).
% True if NQ is a new queue with Element removed from the front of the
% queue Q.

queue_dequeue(Element, q([Element | Front], Back), q(Front, Back)).


%%	queue_enqueue(LastElement, Q, NQ).
% True if LastElement is inserted as the last element of the queue Q.
% The magic is achieved through unification. Upon proving queue_enqueue,
% the term representing Q will be a 'q' term with (if produced through
% the standard protocol) the first element of the 'back' being
% LastElement; the resulting queue NQ will have LastElement 'removed'
% from the 'back', thus, by unification of the incomplete logical
% variables arriving from previous unifications (all the way back to the
% initial 'queue_empty', the 'front' will have LastElement appear just
% before Back.

queue_enqueue(LastElement, q(Front, [LastElement | Back]), q(Front, Back)).


%%	Example.

/*
:- queue_empty(Q),
	queue_enqueue(12, Q, Q1),
	format('L ~nQ  = ~w~nQ1 = ~w~n~n', [Q, Q1]),
	queue_enqueue(123, Q1, Q2),
	format('L ~nQ1 = ~w~nQ2 = ~w~n~n', [Q1, Q2]),
	queue_enqueue(42, Q2, Q3),
	format('L ~nQ2 = ~w~nQ3 = ~w~n~n', [Q2, Q3]),
	queue_dequeue(A, Q3, Q4),
	format('H ~w~nQ3 = ~w~nQ4 = ~w~n~n', [A, Q3, Q4]),
	queue_dequeue(B, Q4, Q5),
	format('H ~w~nQ4 = ~w~nQ5 = ~w~n~n', [B, Q4, Q5]),
	\+ queue_is_empty(Q5),
	queue_not_empty(Q5),
	queue_dequeue(C, Q5, Q6),
	format('H ~w~nQ5 = ~w~nQ6 = ~w~n~n', [C, Q5, Q6]),
	queue_is_empty(Q6),
	\+ queue_not_empty(Q6),
	queue_enqueue(new_thing, Q6, Q7),
	format('H ~w~nQ6 = ~w~nQ7 = ~w~n~n', [new_thing, Q6, Q7]).
*/

%%	end of file -- queues.pl --
