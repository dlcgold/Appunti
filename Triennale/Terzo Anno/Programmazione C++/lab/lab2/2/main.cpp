// miglioria sulla portabilità di es1 con funzione generica da specificare

#include <iostream>
#include <cmath>

float f(float x);

int main(){
  float begin = 0;
  float end = M_PI;

  int n = 0;
  std::cout << "insert number of steps: ";
  std::cin >> n;

  float delta = (end - begin) / n ;
  
  float sum = 0;

  float* steps = new float[n];
  steps[0] = 0;
  
  for(int i = 1; i <= n; i++){
    steps[i] = steps[i-1] + delta;
  }
  
  for(int i = 1; i <= n; i++){
    sum += ((f(steps[i - 1]) + f(steps[i])) / 2) *
      (steps[i] - steps[i - 1]);
  }

  std::cout << "\nresult with array: ";
  std::cout << sum << std::endl;

  float integrale = 0;
  for(int i = 1; i <= n; i++){
    integrale += ((f(begin + (i - 1) * delta) +
		   f(begin + i * delta)) / 2);
  }
  integrale *= delta;
  std::cout << "\nresult without array: ";
  std::cout << integrale << std::endl;
  return 0;
}

float f(float x){
  return std::sin(x);
}

