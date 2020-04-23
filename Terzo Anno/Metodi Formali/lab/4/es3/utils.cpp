#include "utils.hpp"


std::string mToStr(std::vector<int> m){
  std::string result = "";
  for(auto elem: m)
    result += (std::to_string(elem) + " ");
  result += "\b";
  return result;
}

std::vector<int> strToM(std::string str){
  std::vector<std::string> elems;
  boost::split(elems, str, boost::is_any_of(" "), boost::token_compress_on);
  std::vector<int> elemsI;
  for(int i = 0; i < 3; i++){
    //std::cout << e << std::endl;
    elemsI.push_back(std::stoi(elems[i]));
  }
  return elemsI;
}

petriNet getNet(std::string filename) {

  std::ifstream inp(filename);
  std::vector<std::string> pos;
  std::vector<std::string> tra;
  std::vector<std::string> edg;
  std::string line;
  while (std::getline(inp, line)){
    if(line[0] == 'p'){
      pos.push_back(line.substr(1, line.size() - 1));
    }else if(line[0] == 't'){
      tra.push_back(line.substr(1, line.size() - 1));
    }else{
      edg.push_back(line.substr(2, line.size() - 1));
    }
  }

  std::vector<int> initial_marc;
  for(auto str: pos){
    std::vector<std::string> elems;
    boost::split(elems, str, boost::is_any_of(" "), boost::token_compress_on);
    //std::cout << elems[2] << std::endl;
    initial_marc.push_back(std::stoi(elems[2]));
  }
  int numberPos = pos.size();
  int numberTra = tra.size();
  // inizializzo matrice back e for
  std::vector<std::vector<int>> forw(numberPos, std::vector<int>(numberTra));
  std::vector<std::vector<int>> backw(numberPos, std::vector<int>(numberTra));

  for(auto e: edg){
    //std::cout << e << std::endl;
    std::vector<std::string> elems;
    boost::split(elems, e, boost::is_any_of(" "), boost::token_compress_on);
    auto start = std::make_pair(elems[0][0], elems[0].substr(1, elems[0].size()));
    auto end = std::make_pair(elems[1][0], elems[1].substr(1, elems[1].size()));
    int weight = std::stoi(elems[2]);
    if(start.first == 'p'){
      backw[std::stoi(start.second)][std::stoi(end.second)] = weight;
    }else
      forw[std::stoi(end.second)][std::stoi(start.second)] = weight;
  }

  
  petriNet pnet(numberPos, numberTra, forw, backw, initial_marc);
  return pnet;
}

const std::vector<int> petriNet::get_mi() const {
  return mi;
}

const std::vector<std::vector<int> > petriNet::get_backward() const {
  return backward;
}


const std::vector<std::vector<int> > petriNet::get_forward() const {
  return forward;
}

int petriNet::get_tra() const {
  return tra;
}


int petriNet::get_pos() const {
  return pos;
}

void petriNet::print() const{
  std::cout << "numero posti: " << pos << "\n";
  std::cout << "numero transizioni: " << tra << "\n";
  
  std::cout << "matrice forward:\n";
  for(auto r: forward){
    for(auto c: r)
      std::cout << c << " ";
    std::cout << std::endl;
  }
  
  std::cout << "matrice backward:\n";
  for(auto r: backward){
    for(auto c: r)
      std::cout << c << " ";
    std::cout << std::endl;
  }
  std::cout << "Marcatura iniziale:\n";
  for(auto i: mi)
    std::cout << i << std::endl;
}

std::vector<int> petriNet::getCol(std::vector<std::vector<int> > m, int c){
  std::vector<int> r;
  for(auto row: m){
    r.push_back(row[c]);
  }
  return r;
}


std::vector<int> petriNet::sumVec(std::vector<int> m1, std::vector<int> m2){
  std::vector<int> r;
  for(unsigned int i = 0; i < m1.size(); i++){
    r.push_back(m1[i] + m2[i]);
  }
  return r;
}


std::vector<int> petriNet::subVec(std::vector<int> m1, std::vector<int> m2){
  std::vector<int> r;
  for(unsigned int i = 0; i < m1.size(); i++){
    r.push_back(m1[i] - m2[i]);
  }
  return r;
}

std::vector<int> petriNet::transition(std::vector<int> m, int t){
  bool check = true;
  std::vector<int> r;
  std::vector<int> checkb;
  checkb = subVec(m, getCol(backward, t));
  for(auto elem: checkb){
    if(elem < 0)
      check = false;
  }		  
  if(check){
    auto sub = subVec(m, getCol(backward, t));
    r = sumVec(sub, getCol(forward, t));
  }
  return r;
}

bool petriNet::hasTransition(std::vector<int> m, int t){
  bool check = true;
  std::vector<int> r;
  std::vector<int> checkb;
  checkb = subVec(m, getCol(backward, t));
  for(auto elem: checkb){
    if(elem < 0)
      check = false;
  }		  
  return check;
}

void petriNet::createGraph(){
  std::set<std::string> nodesString;
  std::set<std::string> nodesDots;
  std::set<std::string> nodes;
  auto curr = mi;
  nodes.insert(mToStr(curr));

  std::set<std::string>::iterator node;
  for(node = nodes.begin(); node != nodes.end(); node++){
    unsigned int s = nodes.size();
    for(int t = 0; t < tra; t++){
      if(hasTransition(strToM(*node), t)){
	auto newnode = transition(strToM(*node), t);
	nodes.insert(mToStr(newnode));
	std::string arc = *node + " -> t" + std::to_string(t) + " -> "
	  + mToStr(newnode);
	nodesString.insert(arc);
	nodesDots.insert("\"" + *node + "\" -> \"" + mToStr(newnode)
			 + "\" [label=" +"\"t" + std::to_string(t) +  "\"];\n");
      }
    }
    if(s != nodes.size()){
      node = nodes.begin();
    }
  }
  dotsString = "digraph G {\n";
  for(auto i: nodesDots){
    dotsString += i;
  }
  dotsString += "}";
}

void petriNet::saveGraph(std::string filename){
  std::ofstream out(filename);
  out << dotsString;
}
