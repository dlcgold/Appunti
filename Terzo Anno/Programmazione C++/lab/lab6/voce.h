#ifndef VOCE_H
#define VOCE_H

#include <string>
#include <ostream>

struct voce{
  std::string name;
  std::string surname;
  std::string ntel;
  voce();
  voce(const std::string &n, const std::string &s,
       const std::string &);
};

std::ostream &operator<<(std::ostream &os, 
			 const voce &v);

#endif
