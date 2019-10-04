#include <iostream>
#include <fstream>
#include <string>
#include <set>
#include <cstdlib>
#include <vector>
#include <cmath>
#include <algorithm>
#include <bits/stdc++.h>
#define d 256 //diciamo di avere un alfabeto da 256 caratteri

std::set<int> search(std::string, std::string, int);
std::vector<unsigned long long int> sieve(unsigned long long int);
bool isZero(unsigned long long int);
std::vector< unsigned long long int> sieveRange(unsigned long long int,
						unsigned long long int);

int main(){
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
  std::fstream ifs("lorem.txt");
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
    std::cout << "best precision possible (" << count << ")\n";
  }
 
  
  std::cout << "occurrences at: ";
  for(auto i: result_total)
    std::cout << i << ", ";
  std::cout << "\b\b" << std::endl;
  std::cout << "number of occurrences: " <<
    result_total.size() + 1 << std::endl;
}

std::set<int> search(std::string pattern, std::string text, int q){
  std::set<int> results;
  int m = pattern.size();
  int n = text.size();
  int j; // iterator check
  int p = 0; // hash for pattern
  int t = 0; // hash for text
  int h = (int)std::pow(d, m-1) % q; // n_bit^(m-1)

  // hash of pattern
  for(auto i: pattern){
    p = (d * p + i) % q;
  }
  
  // hash for text
  for(int i = 0; i < m; i++){
    t = (d * t + text[i]) % q;
  }

  // slide over text
  for(int i = 0; i <= n - m; i++){
    // first check of hashes
    // if correct check characters
    if (p == t){
      // check characters one by one
      for(j = 0; j < m; j++){
	if(text[i + j] != pattern[j])
	  break;
      }
      if(j == m)
	results.insert(i);
    }
    // Calculate hash value for next window of text
    if (i < n-m){
      t = (d * (t - text[i] * h) + text[i + m]) % q;
      // if negative make it positive
      if(t < 0)
	t = (t + q);
    }
  }
  return results;
}


std::vector<unsigned long long int> sieve(unsigned long long int n) { 
  // Create a boolean vector "prime[0..n]" and 
  // initialize all entries it as true. A value 
  // in prime[i] will finally be false if i is 
  // Not a prime, else true. 
  std::vector<bool> prime(n + 1, true); 
       
  prime[0] = false; 
  prime[1] = false; 
  unsigned long long int m = std::sqrt(n); 
  for (unsigned long long int p = 2; p <= m; p++) { 
       
    // If prime[p] is not changed, then it 
    // is a prime  
    if (prime[p]) { 
      // Update all multiples of p 
      for (unsigned long long int i = p * 2; i <= n; i += p) 
	prime[i] = false; 
    } 
  } 
   
  // push all the primes into the vector ans 
  std::vector<unsigned long long int> ans; 
  for (unsigned long long int i = 0; i < n; i++) 
    if (prime[i]) 
      ans.push_back(i); 
  return ans; 
} 
   
// Used to remove zeros from a vector using  
// library function remove_if() 
bool isZero(unsigned long long int i) { 
  return i == 0; 
} 
   
std::vector<unsigned long long int> sieveRange(unsigned long long int start,
					       unsigned long long int end){ 
  // find primes from [0..start] range
  std::vector<unsigned long long int> s1 = sieve(start);   
       
  // find primes from [0..end] range  
  std::vector<unsigned long long int> s2 = sieve(end);   
   
  std::vector<unsigned long long int> ans(end - start); 
       
  // find set difference of two vectors and 
  // push result in vector ans 
  // O(2*(m+n)-1)  
  std::set_difference(s2.begin(), s2.end(), s1.begin(),  
		      s2.end(), ans.begin()); 
  
  // remove extra zeros if any. O(n) 
  std::vector<unsigned long long int>::iterator itr = 
    std::remove_if(ans.begin(), ans.end(), isZero); 
   
  // resize it. // O(n) 
  ans.resize(itr - ans.begin()); 
  
  return ans; 
} 
