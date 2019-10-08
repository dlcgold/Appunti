#include "roman.h"


/** print with roman notaion
 * @param n number to concert
 */

void print_roman_helper(int digit, char s){
  switch(digit){
  case 2:
    std::cout << s << s;
  }
}

void print_roman(int n){
  print_roman_helper(n / 1000, 'M');
}
