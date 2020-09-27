#include "forma.hpp"
#include <algorithm>


forma::forma(const std::string &id, bool empty, int color = 0):
  _id(id), _color(color), _empty(empty){}
  
forma::~forma(){}

forma::forma(const forma &other): _id(other._id),
				  _color(other._color),
				  _empty(other._empty){}

forma &forma::operator=(const forma &other){
  if(this != &other){
    _id = other._id;
    _color = other._color;
    _empty = other._empty;
  }
  return *this;
}

std::string forma::get_id() const{
  return _id;
}

bool forma::get_empty() const {
  return _empty;
}

int forma::get_color() const {
  return _color;
}

void Forma::set_color(int color)  {
  _color = color;
}	

void forma::set_empty(bool empty) {
  _empty = empty;
}

std::ostream &operator<<(std::ostream& os, const forma &f) {
  os << "id: " << f.get_id() << std::endl;
  os << "colore: " << f.get_color() << std::endl;
  os << "pieno: " << f.get_empty();
  return os;
}

point::point(): x(0), y(0){}
point::point(double x1, double y1): x(x1), y(y1){}


std::ostream &operator<<(std::ostream &os, const point &p)  {
  os << "[" << p.x << " " << p.y << "]";
  return os;
}

double operator-(const point &p1, const point &p2){
  return std::sqrt((p1.x - p2.x) * (p1.x - p2.x) +
		   (p1.y - p2.y) * (p1.y - p2.y));
}
