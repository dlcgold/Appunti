#include <iostream>
#include "dbufferT.hpp" // dbuffer
#include <cassert>   // assert

template <typename T>
void myswap(T &a, T &b) {
  T c = a;
  a = b;
  b = c;
}

int main() {
	
  int a,b;
  dbuffer<double> d1,d2;

  myswap<int>(a,b);

  myswap(d1,d2);

  return 0;
}
