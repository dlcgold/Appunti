#include <iostream>
#include <vector>
#include "utils.hpp"

int main(int argc, char** argv){
  if(argc == 0)
    exit(-1);
  std::string filename(argv[1]);
  petriNet pnet = getNet(filename);
  //pnet.print();
  pnet.createGraph();
  pnet.saveGraph(filename.substr(0, filename.size()-5) + ".dot");
  
  return 0;
}
