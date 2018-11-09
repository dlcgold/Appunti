%%%% -*- Mode: Prolog -*-

%%%% poly-arith.pl --
%%%% Arithmetics on Monovariate Polynomials.
%%%%
%%%% Copyright (c) 2014 Marco Antoniotti, DISCo, Universita` degli Studi
%%%% di Milano Bicocca.

:- module(poly_arith, [is_monomial/2,
		       is_poly/2,
		       poly_sum/4,
		       poly_mult/4,
		       poly_scale/4,
		       evaluate/4,
		       horner/4
		      ]).

%%	constant(X)
%       True if X is, for the time being, 'atomic'.

constant(X) :- atomic(X).


%%	is_monomial(M, X)
%       True if M is a monomial with variable X (or a constant).

is_monomial(X, X).
is_monomial(C, X) :- C \= X, constant(C).
is_monomial(X^N, X) :- integer(N), N >= 0.
is_monomial(X^N, X) :- atom(N).
is_monomial(C * X, X) :- constant(C).
is_monomial(C * X^N, X) :- constant(C), is_monomial(X^N, X).


%%	is_poly(P, X)
%       True if P is a polynomial in variable X.

is_poly(P1 + P2, X) :- is_poly(P1, X), is_poly(P2, X).
is_poly(P1 - P2, X) :- is_poly(P1, X), is_poly(P2, X).
is_poly(P1 * P2, X) :- is_poly(P1, X), is_poly(P2, X).
is_poly(P / C, X) :- is_poly(P, X), constant(C).
is_poly(P, X) :- is_monomial(P, X).


%%	poly_sum(P1, P2, X, PS)
%       True if PS is the polynomial sum of P1 and P2 (in variable X).
%       Note that the first step of the algorithms is to move to a
%       "coefficient-based" representation.

poly_sum(P1, P2, X, PS) :-
	coefficients(P1, X, Cs1),
	coefficients(P2, X, Cs2),
	normalize_coefficients(Cs1, NCs1),
	normalize_coefficients(Cs2, NCs2),
	coefficients_sum(NCs1, NCs2, CsSum),
	poly_coefficients(CsSum, X, PS).


%%	poly_scale(S, P, X, SP)
%	True if PS is a "scaled" version of the polynomial P by S; both
%	P and SP are polynomial in the variable X.

poly_scale(S, P, X, SP) :-
	coefficients(P, X, PCs),
	normalize_coefficients(PCs, NPCs),
	coefficients_scale(S, NPCs, SCS),
	poly_coefficients(SCS, X, SP).


%%	poly_mult(P1, P2, X, PM)
%	True if PS is the polynomial multiplication of P1 and P2 in
%       variable X.

poly_mult(P1, P2, X, PM) :-
	coefficients(P1, X, Cs1),
	coefficients(P2, X, Cs2),
	normalize_coefficients(Cs1, NCs1),
	normalize_coefficients(Cs2, NCs2),
	coefficients_mult(NCs1, NCs2, CsMult),
	poly_coefficients(CsMult, X, PM).


%%	coefficients(P, X, Cs)
%	True if Cs is the list of terms 'c(Grade, Coefficient)'
%	representing the polynomial P (in variable X). Note that the
%	list Cs can be ordered in standard term order.

coefficients(X, X, poly([c(1, 1)])).

coefficients(C, X, poly([c(0, C)])) :- constant(C), C \= X.

coefficients(X^N, X, poly([c(N, 1)])) :- constant(N).

coefficients(C*X, X, poly([c(1, C)])) :- constant(C).

coefficients(C*X^N, X, poly([c(N, C)])) :- constant(N), constant(C).

coefficients(P + M, X, poly([MC | CsRP])) :-
	is_monomial(M, X),
	coefficients(M, X, poly([MC])),
	coefficients(P, X, poly(CsRP)).

coefficients(P - M, X, poly([c(N, NC) | CsRP])) :-
	is_monomial(M, X),
	coefficients(M, X, poly([c(N, C)])),
	coefficients(P, X, poly(CsRP)),
	NC is -1 * C.

coefficients(poly(Cs), _, poly(Cs)).


%%	normalize_coefficients(Cs, NCs)
%       True when NCs is a sorted (in standard term order) list of terms
%       'c(Grade, Coefficient)' without terms of the form 'c(Grade, 0)'
%       and with only one term per grade.

normalize_coefficients(poly(Cs), ncs(NCs)) :-
	sort(Cs, SCs),
	gather_sum_coefficients(SCs, SSCs),
	include(\=(c(_, 0) /* , X */), SSCs, NCs). % 'include' is a SWI predicate.
normalize_coefficients(ncs(NCs), ncs(NCs)).


%%	gather_sum_coefficients(Cs, SCs)
%       True when SCs is a list of unique terms 'c(Grade, Coefficient)'
%       gathered from an initial list of coefficients Cs. Cs is assumed
%       to be sorted in standard term order.

gather_sum_coefficients([], []).

gather_sum_coefficients([C], [C]).

gather_sum_coefficients([c(N, C1), c(N, C2) | Cs1], Cs2) :-
	NewC is C1 + C2,
	gather_sum_coefficients([c(N, NewC) | Cs1], Cs2).

gather_sum_coefficients([c(N1, C1), c(N2, C2) | Cs1],
			[c(N1, C1) | Cs2]) :-
	N1 \= N2,
	gather_sum_coefficients([c(N2, C2) | Cs1], Cs2).


%%	coefficient_sum(Cs1, Cs2, SummedCs).
%       True when SummedCs is the "coefficients sum" of Cs1 and Cs2 that
%       are two normalized coefficient-based representations of two
%       polynomials.

coefficients_sum(ncs([]), ncs([]), ncs([])).

coefficients_sum(ncs([]), ncs([C | Cs]), ncs([C | Cs])).

coefficients_sum(ncs([C | Cs]), ncs([]), ncs([C | Cs])).

coefficients_sum(ncs([c(N, C1) | C1s]),
		 ncs([c(N, C2) | C2s]),
		 ncs([c(N, RC) | Cs])) :-
	RC is C1 + C2,
	coefficients_sum(ncs(C1s), ncs(C2s), ncs(Cs)).

coefficients_sum(ncs([c(N1, C1) | C1s]),
		 ncs([c(N2, C2) | C2s]),
		 ncs([c(N1, C1) | Cs])) :-
	N1 \= N2,
	compare(<, N1, N2),
	coefficients_sum(ncs(C1s), ncs([c(N2, C2) | C2s]), ncs(Cs)).

coefficients_sum(ncs([c(N1, C1) | C1s]),
		 ncs([c(N2, C2) | C2s]),
		 ncs([c(N2, C2) | Cs])) :-
	N1 \= N2,
	compare(>, N1, N2),
	coefficients_sum(ncs([c(N1, C1) | C1s]), ncs(C2s), ncs(Cs)).


%%	coefficient_scale(S, Cs, SCs)
%       True when the coefficients in SCs are those in Cs scaled by S.

coefficients_scale(_, ncs([]), ncs([])).
coefficients_scale(S, ncs([c(N, C) | Cs]), ncs([c(N, SC) | SCs])) :-
	SC is S * C,
	coefficients_scale(S, Cs, ncs(SCs)).


%%	coefficients_mult(C1, C2, MCs)
%       True when MCs is the coefficient-based representation of the
%	multiplication of C1 and C2 that are normalized
%	coefficient-based representations of two polynomials.

coefficients_mult(ncs([]), _, ncs([])).
coefficients_mult(ncs([C1 | C1s]), ncs(C2s), R) :-
	coefficients_monomial_scale(C1, C2s, C1xC2s),
	coefficients_mult(ncs(C1s), ncs(C2s), C1sxC2s),
	coefficients_sum(C1xC2s, C1sxC2s, R).


coefficients_monomial_scale(_, [], ncs([])).
coefficients_monomial_scale(c(N, C), [c(N1, C1) | Cs], ncs([c(NR, CR) | SCs])) :-
	NR is N + N1,
	CR is C * C1,
	coefficients_monomial_scale(c(N, C), Cs, ncs(SCs)).


%%	poly_coefficients(NormalizedCs, X, P)
%	True when P is a polynomial in X built from the coefficients
%	NormalizedCs.

poly_coefficients(ncs([c(0, C)]), _, C).

poly_coefficients(ncs([c(1, 1)]), X, X).

poly_coefficients(ncs([c(N, 1)]), X, X^N) :- N \= 1, N \= 0.

poly_coefficients(ncs([c(1, C)]), X, C*X) :- C \= 1.

poly_coefficients(ncs([c(N, C)]), X, C*X^N) :- N \= 1, N \= 0, C \= 1.

poly_coefficients(ncs([C1 | Cs]), X, Ps + P1) :-
	Cs \= [],
	poly_coefficients(ncs([C1]), X, P1),
	poly_coefficients(ncs(Cs), X, Ps).


%%	evaluate(P, X, V, R)
%
%	True when R is the value of the polynomial function P in
%	the variable X, computed at V. Simple version that just sums up
%	the value of each monomial.

evaluate(P, X, V, R) :-
	coefficients(P, X, Cs),
	normalize_coefficients(Cs, NCs),
	nc_evaluate(NCs, V, R).

%%	nc_evaluate(NCs, V, R)
%	Actual workhorse of evaluate/4. It is true when R is the value
%	of the polynomial whose "normalized coefficient" representation
%	is NCs.

nc_evaluate(ncs([]), _, 0).
nc_evaluate(ncs([c(E, C) | Cs]), V, R) :-
	MV is C * (V ** E),
	nc_evaluate(ncs(Cs), V, RR),
	R is RR + MV.


%%	horner(P, X, V, R)
%
%	True when the monovariate polynomial P in X, evaluates to R at V
%	(i.e., when X is given the value V) by using Horner's rule.
%	NB. nc_horner should not be called by itself.
%
%	The predicate nc_horner/5 is complicated because the coefficient
%	list is not complete; therefore we must keep computing by
%	traking the "missing" degrees. Hence the hairiness, especially
%	for polynomials without the low degree monomials.
%
%	Yet, you can see that the predicate horner/5 is
%	"tail-recursive", hence a loop.

horner(P, X, V, R) :-
	coefficients(P, X, Cs),
	normalize_coefficients(Cs, ncs(NCs)),
	reverse(NCs, RNCs),
	nc_horner(RNCs, V, R).


%%	nc_horner(Cs, V, R)
%%      nc_horner(Cs, Degree, V, Bi, R)
%	Actual implementation of horner/4. The behavior is complicated
%	because, as previously said, because the termination condition
%	is ((a1) lone coefficient at zeroth power and (a1) Degree 0)
%	OR ((a2) coefficient list empty AND (b2) Degree 0). Again. this
%	is because the normalized coefficient representation is
%	"sparse".
%
%	The algorithm is an adaptation of the one found at the Wikipedia
%	page on "Horner's method" (cfr.,
%	http://en.wikipedia.org/wiki/Horner's_method).

nc_horner([], _, 0).
nc_horner([c(N, AN) | Cs], V, R) :-
	N_1 is N - 1,
	nc_horner(Cs, N_1, V, AN, R).


nc_horner([c(0, A0)], 0, V, B1, R) :-
	R is A0 + (V * B1).
nc_horner([c(Ni_1, Ai_1) | Cs], Ni_1, V, Bi, R) :-
	Ni_1 > 0,
	Ni_2 is Ni_1 - 1,
	Bi_1 is Ai_1 + (V * Bi),
	nc_horner(Cs, Ni_2, V, Bi_1, R).
nc_horner([c(Ni_k, Ai_k) | Cs], Ni_1, V, Bi, R) :-
	Ni_k >= 0,
	Ni_1 > Ni_k,
	Ni_2 is Ni_1 - 1,
	Bi_1 is (V * Bi),
	nc_horner([c(Ni_k, Ai_k) | Cs], Ni_2, V, Bi_1, R).

nc_horner([], 0, V, B1, R) :-
	R is V * B1.
nc_horner([], N1_1, V, Bi, R) :-
	N1_1 > 0,
	N1_2 is N1_1 - 1,
	Bi_1 is V * Bi,
	nc_horner([], N1_2, V, Bi_1, R).


%%%% end of file -- poly-arith.pl --

