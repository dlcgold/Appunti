#ifndef TRIANGOLO_H
#define TRIANGOLO_H

#include "forma.hpp"
#include <ostream>
#include <string>

class triangolo: public forma{
private:
  point _v1, _v2, _v3;

public:
  triangolo(const std::string &id, int color, bool empty,
	   const point &v1, const point &v2, const point &v3);
  triangolo(const triangolo &other);

  triangolo &operator=(const triangolo &other);
  
  const point& get_v1(void) const;
  const point& get_v2(void) const;
  const point& get_v3(void) const;

  void set_v1(const point &p1);
  void set_v2(const point &p2);
  void set_v3(const point &p3);
  void set_all(const point &p1, const point &p2, const point &p3);

  double get_perimeter(void) const;	
};
std::ostream &operator<<(std::ostream &os, const triangolo &t);
#endif
