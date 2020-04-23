#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <sstream>
#include <set>
#include <map>
#include <boost/algorithm/string/classification.hpp> 
#include <boost/algorithm/string/split.hpp> 

class petriNet{
private:
  int pos;
  int tra;
  std::vector<std::vector<int>> forward;
  std::vector<std::vector<int>> backward;
  std::vector<int> mi;
  std::string dotsString;
  std::vector<int> getCol(std::vector<std::vector<int> >, int);
  std::vector<int> sumVec(std::vector<int>, std::vector<int>);
  std::vector<int> subVec(std::vector<int>, std::vector<int>);
public:
   petriNet(int p, int t,
	    std::vector<std::vector<int>> f,
	    std::vector<std::vector<int>> b,
	    std::vector<int> m) : pos(p), tra(t), forward(f), backward(b), mi(m)
  {}
  
  const std::vector<int> get_mi() const;
  const std::vector<std::vector<int> > get_backward() const ;
  const std::vector<std::vector<int> > get_forward() const;
  int get_tra() const;
  int get_pos() const;
  void print() const;
  std::vector<int> transition(std::vector<int>, int);
  bool hasTransition(std::vector<int>, int);
  void createGraph();
  void saveGraph(std::string);
  
};

petriNet getNet(std::string);

