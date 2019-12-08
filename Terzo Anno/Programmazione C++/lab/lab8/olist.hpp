#ifndef OLIST_H
#define OLIST_H

#include <algorithm>
#include <iterator>
#include <ostream>
#include <iostream>

/**
   lista ordinata
   @brief ordered_list
   @param T tipo dei del dato della lista
*/
template <typename T, typename O, typename E>
class ordered_list{
private:

  /** 
      @brief nodo della lista
  */
  struct node{
    T value;
    node *next;

    /**
       @brief default constructor of node
    */
    
    node(): next(nullptr){}

    /**
       @brief constructor of node with parameter for value
       @param v value of node
    */
    node(const T &v): value(v), next(nullptr){}
    
    /**
       @brief constructor of node with parameter for value and next
       @param v value of node
       @param n next node
    */
    node(const T &v, node *n): value(v), next(n){}

    /**
       @brief node destructor
    */
    ~node(){
      next = nullptr;
    }
  };
  /**
     @brief head of list
  */
  node *_head;
  /**
     @brief size of list
  */
  unsigned int _size;
  /**
     @brief functor for ordering defined in main
  */
  O _ord;
  /**
     @brief functor for equal compare defined in main
  */
  E _equ;

  /**
     @brief delete a node
     @param n node to delete
  */
  void clear_node(node *n){
    if(n!=nullptr) {
      clear_node(n->next);	
      delete n;
      _size--;
      n = nullptr;	
    }
  }
  
public:
 

  /**
     @brief default constructor for the ordered_list
  */						

  ordered_list(): _head(nullptr), _size(0){}

  /**
     @brief assignment operator
     @param other ordered_list for assignment
     @return ordered_list 
     @throw allocation expeption
  */

  ordered_list &operator=(const ordered_list &other) {

    /**
       @brief if not equal swap head and size
    */
    if(this != &other) {
      ordered_list tmp(other);
      std::swap(_head,tmp._head);
      std::swap(_size,tmp._size);
    }
    return *this;
  }

  /**
     @brief size getter
     @return size of ordered_list
  */
  unsigned int get_size() const {
    return _size;
  }
  
  /**
     @brief find occurrence in ordered_list
     @param v value to search
     @return true if found
  */

  bool find(const T &v) const{
    node *current = _head;
    while(current != nullptr){
      if(_equ(current->value, v))
	return true;
      current = current->next;
    }
    return false;
  }

  /**
     @brief method to add element in order
     @param v value to add
     @throw allocation exception
  */

  void add(const T &v) {
    node *tmp = new node(v);
    if(_head == nullptr){
      _head = tmp;
      _size++;
      return;
    }
    node *current = _head;
    node *previous = _head;
    while(current != nullptr){
      if(_ord(current->value, v)){
	previous = current;
	current = current->next;
      }else{
	if(_head == current){
	  _head = tmp;
	}else{
	  previous->next = tmp;
	}
	tmp->next = current;
	_size++;
	return;
      }
    }
    previous->next = tmp;
    _size++;
  }

  /**
     @brief delete ordered_list value
  */
  void clear(){
    clear_node(_head);
    _head = nullptr;
  }

  /**
     @brief destructor of ordered_list
  */

  ~ordered_list(){
    clear();
  }
  /**
     @brief construtor for data sequence
     @param begin iterator for begin
     @param end iterator for end
     @throw allocation exception
  */

  template <typename IterT>
  ordered_list(IterT begin, IterT end): _head(nullptr), _size(0) {
    try{
      while(begin != end){
	add(static_cast<T>(*begin));
	begin++;
      }
    }catch(...){
      clear();
      throw;
    }
  }

  /**
     @brief copy constructor
     @param other ordered_list to copy
     @throw allocation exception
  */

  ordered_list(const ordered_list &other): _head(nullptr), _size(0) {
    node *curr = other._head;

    try {
      while(curr != nullptr) {
	add(curr->value);
	curr = curr->next;
      }		
    }
    catch(...) {
      clear();
      throw;
    }
  }

  /**
     @brief constant iterator for ordered_list
  */
  class const_iterator{
  private:
    const node *_n;

  public:

    /**
       @brief const_iterator constructor
    */
    const_iterator() : _n(nullptr) {}

    /**
       @brief const_iterator copy constructor
       @param other const_iterator to copy
    */
    const_iterator(const const_iterator &other) : _n(other._n) {
    }
    
    /**
       @brief const_iterator operator=
       @param other const_iterator to check
    */
    const_iterator& operator=(const const_iterator &other) {
      _n = other._n;
      return *this;
    }

    /**
       @brief const_iterator copy destructor
    */
    ~const_iterator() {
    }

    /**
       @brief operator *
       @return value of the iterator
    */
    const T& operator*() const {
      return _n->value;
    }

    /**
       @brief operator ->
       @return pointer to value of the iterator
    */
    const T* operator->() const {
      return &(_n->value);
    }

    /**
       @brief operator post ++
       @return next element
    */
    const_iterator operator++(int) {
      const_iterator tmp(*this);
      _n = _n->next;
      return tmp;
    }
    
    /**
       @brief operator pre ++
       @return next element
    */
    const_iterator& operator++() {
      _n = _n->next;
      return *this;
    }
    
    /**
       @brief operator ==
       @return true if equal
    */
    bool operator==(const const_iterator &other) const {
      return (_n == other._n);
    }
		
    /**
       @brief operator !=
       @return true if not equal
    */
    bool operator!=(const const_iterator &other) const {
      return (_n != other._n);
    }
    
  private:
    friend class ordered_list;

    /**
       @brief constructorn for container class
    */
    const_iterator(const node *n) : _n(n) {}
  };

  /**
     @return iterator at begin
  */
  const_iterator begin() const {
    return const_iterator(_head);
  }
	
  /**
     @return iterator at end
  */
  const_iterator end() const {
    return const_iterator(nullptr);
  }
};

/**
   @brief redefinition of ostream
*/

template <typename T, typename C, typename E>
std::ostream &operator<<(std::ostream &os, 
			 const ordered_list<T, C, E> &ol) {
	
  typename ordered_list<T,C,E>::const_iterator i, ie;
	
  i = ol.begin();
  ie = ol.end();

  while(i!=ie) {
    os << *i << " ";
    ++i;
  }

  return os;
}

/**
   @brief generic function to check value in ordered_list at condition
*/
template <typename T, typename C, typename E, typename P>
void checkif(const ordered_list<T, C, E> &ol, P pred) {

  typename ordered_list<T, C, E>::const_iterator i, ie;

  i = ol.begin();
  ie = ol.end();
	
  while(i!=ie) {
    if(pred(*i)) {
      std::cout << *i << std::endl;
    }
    ++i;
  }
}
#endif
