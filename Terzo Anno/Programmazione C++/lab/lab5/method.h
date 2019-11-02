#ifndef DYNBUF_H
#define DYNBUF_H


class dbuffer{
private:
  unsigned int _size;
  int *_buffer;
  
public:
  // default constructor
  dbuffer();
  
  // other constructors
  dbuffer(unsigned int size);
  dbuffer(unsigned int size, int value);

  // distructor
  ~dbuffer();

  // other methods
  int get_value(unsigned int index);
  void set_value(unsigned int index, int value);
  int& value(unsigned int index);
  unsigned int size();

  // assignment
  //dbuffer& operator=(const dbuffer& rhs);
  
};

#endif
