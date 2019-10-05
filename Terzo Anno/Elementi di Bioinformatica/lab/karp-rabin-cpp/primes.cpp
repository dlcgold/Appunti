#include "primes.h"


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
