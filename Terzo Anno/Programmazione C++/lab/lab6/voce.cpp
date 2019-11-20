#include "voce.h"

voce::voce(){
  
}

voce(const std::string &n, const std::string &s,
     const std::string &t):
  name(n), surname(s), ntel(t) {
  
}

std::ostream &operator<<(std::ostream &os,
			 const voce &v){
  os << v.name << " " << v.surname 
     << " " << v.ntel;
  
  return os;
} 

