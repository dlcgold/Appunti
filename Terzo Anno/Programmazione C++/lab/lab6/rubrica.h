#ifndef RUBRICA_H
#define RUBRICA_H


#include "voce.h"
#include <ostream>

class duplicated_voce_exception {};
class rubrica_out_of_space_exception {};
class voce_not_found_exception{};
class file_error_exception{};
class fail_load_expection{};

class rubrica{
 private:
  
  unsigned int _size; // current size rubrica
  unsigned int _capacity; // max size rubrica
  voce* _voci;

  voce *find_voce_helper(const std::string &nt) const;
 public:

  rubrica(); // constructor
  ~rubrica(); // destructor
  rubrica& operator=(const rubrica &other);
  rubrica(const rubrica& other); // copy constructor
  unsigned int get_size() const; // getter
  unsigned int get_capacity() const; // getter
  explicit rubrica(unsigned int limit); // constructor with limit
  void set_capacity(unsigned int limit); // setter
  voce& operator[](unsigned int index); // access wr single voce in rubrica
  const voce& operator[](unsigned int index) const; // access r single voce 
  voce find_voce(const std::string &nt) const; // find voce in rubrica
  void add_voce(const voce &v);
  void add_voce(const std::string &name, const std::string &surname,
		const std::string &ntel);
  void clear(); // clear rubrica
  void save(const std::string &filename) const;
  void load(const std::string &filename);
};
std::ostream& operator<<(std::ostream &os,const rubrica &r);

#endif
