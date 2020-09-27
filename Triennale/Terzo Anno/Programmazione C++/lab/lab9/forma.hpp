#ifndef forma_H
#define forma_H
#include <ostream>
#include <string>
#include <cmath>

class forma{

private:
  std::string _id;
  int _color;
  bool _empty;

public:

  forma(const std::string &id, bool empty, int color);
  ~forma();
  forma(const forma &other);
  forma &operator=(const forma &other);

  std::string get_id() const;
    
  bool get_empty() const;

  void set_empty(const bool empty);
  
  int get_color() const;
  void set_color(const int color); 

  virtual double get_perimeter(void) const = 0;
};

std::ostream &operator<<(std::ostream& os, const forma &f);

struct point{
  double x;
  double y;

  point();
  point(double x1, double y1);
};

std::ostream &operator<<(std::ostream &os, const point &p);
double operator-(const point &p1, const point &p2);

#endif
