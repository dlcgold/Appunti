// per namespace faccio
// namespace nome{qui scrivo tutto il resto}
#ifndef DYNBUF_H
#define DYNBUF_H


namespace dynbuf{

  struct dynamic_buffer{
    unsigned int size;
    int *buffer;
  };

  void print_buf(const dynamic_buffer &);
  void costr_zero(dynamic_buffer &);
  void costr(dynamic_buffer &, unsigned int size = 0);
  void dealloc(dynamic_buffer &);
  void charge(dynamic_buffer &, const int*, unsigned int);
  dynamic_buffer copy(const dynamic_buffer &);
}

#endif
