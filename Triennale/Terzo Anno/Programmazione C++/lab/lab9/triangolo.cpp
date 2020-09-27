#include "trinagolo.hpp"

triangolo::triangolo(const std::string &id, int color, bool empty,
		     const point &v1, const point &v2,
		     const point &v3):
  forma(id, color, empty), _v1(v1), _v2(v2), _v3(v3) {}

triangolo::triangolo(const triangolo &other):
  forma(other), _v1(other._v1), _v2(other._v2), _v3(other._v3) {}

triangolo &triangolo::operator=(const triangolo &other){
  if (this != &other) {
    forma::operator=(other);
    _v1 = other._v1;
    _v2 = other._v2;
    _v3 = other._v3;
  }	

  return *this;
}
const point& triangolo::get_v1(void) const {
  return _v1;	
}

const point& triangolo::get_v2(void) const {
  return _v2;
}

const point& triangolo::get_v3(void) const {
  return _v3;
}

void triangolo::set_v1(const point &p1) {
  _v1 = p1;
}
		
void triangolo::set_v2(const point &p2) {
  _v2 = p2;
}

void triangolo::set_v3(const point &p3) {
  _v3 = p3;
}

void triangolo::set_all(const point &p1, const point &p2,
			const point &p3) {
  _v1 = p1;
  _v2 = p2;
  _v3 = p3;
}

double triangolo::get_perimeter(void) const {
  double l1 = _v1 - _v2;
  double l2 = _v2 - _v3;
  double l3 = _v3 - _v1;

  return l1 + l2 + l3;
}	
