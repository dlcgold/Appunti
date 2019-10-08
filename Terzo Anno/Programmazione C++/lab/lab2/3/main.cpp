#include "nsqrt.h"
#include <iomanip>


/* compilazione bella (-g per debus e doxygen):
 * g++ -c -g nsqrt.cpp
 * g++ -c -g main.cpp
 * g++ nsqrt.o nsqrt.o [-o nome_executable]
 */

int main(){
  float epsilon = 0;
  std::cout << "insert precision: ";
  std::cin >> epsilon;

  double x = 0;
  std::cout << "insert number to calc square: ";
  std::cin >> x;

  double result = nsqrt(x, epsilon);
  double correct = std::sqrt(x);

  if(result == -1)
    return -1;
  
  std::cout << std::setprecision(15) << "\nresult: " << result << "\n";
  std::cout << std::setprecision(15) << "correct result: " << correct << "\n";
  std::cout << "error: " << std::abs(correct - result) << "\n";

  return 0;
}
