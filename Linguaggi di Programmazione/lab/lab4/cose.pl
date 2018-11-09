%%%% -*- Mode: Prolog -*-

%%%% bst.pl --
%%%% A simple "Binary Search Trees Library" (with numeric keys).

%%%%---------------------------------------------------------------------------

%%	Public interface
% is_binary_tree(Tree)
% is_leaf(Node)
% is_bst(Tree)
% tree_insert(K, V, Tree, NewTree)
% tree_search(K, Tree, V)
% tree_delete(K, Tree, DeletedValue, NewTree)
% tree_print(Tree)
% tree_traverse(Tree, Order, KVs)
% tree_leaves(Tree, LeafKVs).


%%	is_binary_tree(Tree).
% True if the argument is a "binary tree", i.e., either an empty tree,
% represented by the atom 'void', or a compound term node/4:
%
% node(Key, Value, LeftSubtree, RightSubtree).

is_binary_tree(void).
is_binary_tree(node(K, _, Left, Right)) :-
	number(K),
	is_binary_tree(Left),
	is_binary_tree(Right).


%%	is_leaf(Tree).
% True if the argument is a node leaf, i.e., a node with empty left and
% right subtrees.

is_leaf(node(_, _, void, void)).


%%	is_bst(Tree)
%%      is_bst(KMin, KMax, Tree).
% True if the argument is a "binary search tree" (a BST), i.e., either
% an empty tree, or a tree where all the keys in the left subtree are
% smaller than the key at root and the keys in the right subtree are
% bigger that the key at the root. The property recursively holds on the
% left and right subtrees.
%
% The is_bst/1 predicate is the main entry point. The is_bst/3 predicate
% is true when the all the keys of the subtree Tree are bounded below by
% KMin and above by KMax.

is_bst(void).
is_bst(nodo(K, V, S, D)) :-
	is_bst(-infinity, +infinity, nodo(K, V, S, D)).
is_bst(_, _, void).
is_bst(Kmin, Kmax, nodo(KN, _, void, void)) :-
	cmp('>=', KN, Kmin),
	cmp('=<', KN, Kmax).
is_bst(Kmin, Kmax, nodo(KN, _, nodo(KS, _, SS, SD), void)) :-
	!,
	cmp('>=', KN, Kmin),
	cmp('=<', KN, Kmax),
	cmp('<', KS, KN),
	is_bst(Kmin, KS, SS),
	is_bst(KS, KN, SD).
is_bst(Kmin, Kmax, nodo(KN, _, void, nodo(KD, _, DS, DD))) :-
	!,
	cmp('>=', KN, Kmin),
	cmp('=<', KN, Kmax),
	cmp('>', KD, KN),
	is_bst(KN, KD, DS),
	is_bst(KD, Kmax, DD).
is_bst(Kmin, Kmax, nodo(KN, _, nodo(KS, _, SS, SD), nodo(KD, _, DS, DD))) :-
	!,
	cmp('>=', KN, Kmin),
	cmp('=<', KN, Kmax),
	cmp('<', KS, KN),
	cmp('>', KD, KN),
	is_bst(Kmin, KS, SS),
	is_bst(KS, KN, SD),
	is_bst(KN, KD, DS),
	is_bst(KD, Kmax, DD).



%%	tree_insert(K, V, Tree, NewTree)
% Inserting a pair <K, V> in a Tree verifies this predicate when NewTree
% is the tree Tree withe the pair properly inserted.

tree_insert(Key, V, void, node(Key, V, void, void)).
tree_insert(Key, V, node(Key, _, L, R), node(Key, V, L, R)).
tree_insert(Key, V, node(K, C, L, R), node(K, C, NL, R)) :-
	Key < K,
	tree_insert(Key, V, L, NL).
tree_insert(Key, V, node(K, C, L, R), node(K, C, L, NR)) :-
	Key > K,
	tree_insert(Key, V, R, NR).


%%	tree_search(K, T, V)
% The predicate is true when the pair <K, V> is present in the tree T.

tree_search(K, node(K, V, _, _), V).
tree_search(K, node(NK, _, L, _), V) :-
	K < NK,
	tree_search(K, L, V).
tree_search(K, node(NK, _, _, R), V) :-
	K > NK,
	tree_search(K, R, V).


%%	tree_delete(K, Tree, DV, DTree)
% The predicate is true when DTree is the tree Tree with the pair
% <K, DV> removed.
% The deletion algorithm (NB, unbalanced) uses the "delete at root"
% strategy: when the node to be deleted is found, its "successor"
% replaces its key and value while the subtree that containes the
% successor is modified by removing that node. tree_root_delete/3 and
% tree_sans_successor/4 implement this algorithm.

tree_delete(K, node(K, DV, L, R), DV, DTree) :-
	tree_root_delete(node(K, _, L, R), DV, DTree).
tree_delete(K, node(Key, V, L, R), DV, node(Key, V, DL, R)) :-
	K < Key,
	tree_delete(K, L, DV, DL).
tree_delete(K, node(Key, V, L, R), DV, node(Key, V, L, DR)) :-
	K > Key,
	tree_delete(K, R, DV, DR).


%%	tree_print(Tree)
%%      tree_print(Indent, Tree)
% Prints Tree on standard output.  Useful for debugging.
% tree_print/2 uses the Indent parameter for formatting.

tree_print(void) :- write([]), nl.
tree_print(node(K, V, L, R)) :-
	tree_print(0, root, node(K, V, L, R)).
tree_print(Indent, Subtree, void) :-
	tab(Indent),
	format('~w []', [Subtree]),
	nl.
tree_print(Indent, Subtree, node(K, V, L, R)) :-
	tab(Indent),
	format('~w <~w, ~w>~n', [Subtree, K, V]),
	NI is Indent + 4,
	tree_print(NI, l, L),
	tree_print(NI, r, R).


%%	traverse(Tree, Order, KVs)
% True when KVs is the list of (key, value) pairs that would
% result from traversing a Tree in a given Order (either 'inorder',
% 'preorder', or 'postorder').

tree_traverse(Tree, inorder, KVs) :- traverse_in(Tree, KVs).
tree_traverse(Tree, preorder, KVs) :- traverse_pre(Tree, KVs).
tree_traverse(Tree, postorder, KVs) :- traverse_post(Tree, KVs).

traverse_in(void, []).
traverse_in(node(K, V, L, R), ITraversal) :-
	traverse_in(L, LTraversal),
	traverse_in(R, RTraversal),
	append(LTraversal, [(K, V) | RTraversal], ITraversal).

traverse_pre(void, []).
traverse_pre(node(K, V, L, R), ITraversal) :-
	traverse_pre(L, LTraversal),
	traverse_pre(R, RTraversal),
	append([(K, V) | LTraversal], RTraversal, ITraversal).

traverse_post(void, []).
traverse_post(node(K, V, L, R), ITraversal) :-
	traverse_post(L, LTraversal),
	traverse_post(R, RTraversal),
	append(LTraversal, RTraversal, IT1),
	append(IT1, [(K, V)], ITraversal).


%%	tree_leaves(Tree, Leaves)
% True when Leaves is a list of pairs (key, value) corresponding to the
% leaves of the tree, collected in order left to right.

tree_leaves(void, []) :- !.
tree_leaves(node(K, V, L, R), [(K, V)]) :- is_leaf(node(K, V, L, R)), !.
tree_leaves(node(_, _, L, R), LeafKVs) :-
	!,
	tree_leaves(L, LLeaves),
	tree_leaves(R, RLeaves),
	append(LLeaves, RLeaves, LeafKVs).


%%%%---------------------------------------------------------------------------

%%	Private predicates

%%	tree_root_delete(Tree, TD)

tree_root_delete(node(_, DV, void, void), DV, void).
tree_root_delete(node(_, DV, void, node(KR, VR, LR, RR)), DV, node(KR, VR, LR, RR)).
tree_root_delete(node(_, DV, node(KL, VL, LL, RL), void), DV, node(KL, VL, LL, RL)).
tree_root_delete(node(_, DV, L, R), DV, node(KS, VS, L, TS)) :-
	tree_sans_successor(R, KS, VS, TS).


%%	tree_sans_successor(Tree, SuccK, SuccV, TreeSansSuccessor)

tree_sans_successor(node(KS, VS, void, RS), KS, VS, RS).
tree_sans_successor(node(K, V, L, R), KS, VS, node(K, V, TSS, R)) :-
	tree_sans_successor(L, KS, VS, TSS).


%%	cmp(OP, X, Y)
% The predicate is an extended (numerical) comparison operator that
% accepts also the symbolic values -infinity and +infinity. It is used
% in lieu of using the built-in SWI-Prolog numerical limits. Besides, it
% has good explanatory value.

cmp('>', -infinity, _) :- !, fail.
cmp('>', _, -infinity) :- !.
cmp('>', +infinity, _) :- !.
cmp('>', _, +infinity) :- !, fail.

cmp('<', _, +infinity) :- !.
cmp('<', +infinity, _) :- !, fail.
cmp('<', -infinity, _) :- !.
cmp('<', _, -infinity) :- !, fail.

/* Version using the built in compare/3 predicate.
cmp('=<', X, Y) :- compare('=', X, Y), !.
cmp('=<', X, Y) :- compare('<', X, Y), !.
cmp('>=', X, Y) :- compare('=', X, Y), !.
cmp('>=', X, Y) :- compare('>', X, Y), !.
cmp(OP, X, Y)   :-
	OP \= '=<',
	OP \= '>=',
	compare(OP, X, Y),
	!.
*/

/* Numeric only version of cmp/3 */

cmp('=<', X, Y) :- X =:= Y, !.
cmp('=<', X, Y) :- X < Y, !.
cmp('>=', X, Y) :- X =:= Y, !.
cmp('>=', X, Y) :- X > Y, !.
cmp('=', X, Y) :- X =:= Y, !.
cmp('>', X, Y) :- X > Y, !.
cmp('<', X, Y) :- X < Y, !.


%%%% end of file -- bst.pl --


