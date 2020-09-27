#include "nsqrt.h"


// commento per doxygen

/** square
 * @param x value to calculate square
 * @param epsilon precision of square
 */

double nsqrt(double x, double epsilon){
  // check positive 
  if(x == 0)
    return 0;
  
  if(x < 0){
    std::cerr << "\nerror: no square for negative numbers\n";
    return -1;
  }
  
  double s = x / 2;
  std::cout << s << "\n";

  while(std::abs((s * s) - x) > epsilon){
    s = ((s * s) + x) / (2 * s);
  }
  
  return s;
}
