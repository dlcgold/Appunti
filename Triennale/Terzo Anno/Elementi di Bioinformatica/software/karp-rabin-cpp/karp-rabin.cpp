/* usage: ./bin/karp-rabin [file]*/

#include <iostream>
#include <fstream>
#include <string>
#include <set>
#include <vector>

#include "primes.h"
#include "search.h"

int main(int argc, char *argv[]){

  if(argc == 1){
    std::cerr << "Missing file-name\nusage: ./bin/karp-rabin [file]\n";
    return -1;
  }
  // read pattern
  std::string pattern;
  std::cout << "insert pattern: ";
  std::getline(std::cin, pattern);
  
  // generate primes vector
  unsigned long long int start = 3;
  unsigned long long int end;
  std::cout << "insert max prime number: ";
  std::cin >> end;
  std::vector<unsigned long long int> primes = sieveRange(start, end);
  int size = primes.size();
  
  // read file
  std::string text;
  std::fstream ifs(argv[1]);
  std::getline(ifs, text, (char) ifs.eof());
 
  int n;
  std::cout << "insert number of iteration: ";
  std::cin >> n;
  std::set<int> result_total;
  int count = 0; // count for iteration
  for(int i = 0; i < n; i++){
    if(primes.size() == 0)
      break;
    int random = rand() % primes.size();
    int q = primes[random];
    std::set<int> results = search(pattern, text, q);
    primes.erase(primes.begin() + random);
    for(auto elem: results)
      result_total.insert(elem);
    count ++;
  }
  if(size == count){
    std::cout << "\nbest precision possible (" << count << ")\n";
  }
 
  
  std::cout << "\noccurrences at: ";
  for(auto i: result_total)
    std::cout << i << " ";
  std::cout << std::endl;
  std::cout << "\nnumber of occurrences: " <<
    result_total.size()<< std::endl;
}

