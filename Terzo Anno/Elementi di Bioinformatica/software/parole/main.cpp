#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>
#include <set>
#include <algorithm>


struct comp{
  std::string word;
  int count;
};

int main(int argc, char *argv[]){

  if(argc == 1){
    std::cerr << "Missing file-name\nusage: ./a.out [file]\n";
    return -1;
  }
  std::string text;
  

  // read file from stdin
  std::fstream ifs(argv[1]);
  std::getline(ifs, text, (char) ifs.eof());

  // replace endline with a space
  std::replace(text.begin(), text.end(), '\n', ' ');
  
  std::istringstream ss(text);
  
  std::string token, tokenb;
  std::vector<std::string> words;

  // split text in array
  while(std::getline(ss, token, ' ')) {
    // erase bad char
    token.erase(std::remove(token.begin(), token.end(), '.'), token.end());
    token.erase(std::remove(token.begin(), token.end(), ','), token.end());
    token.erase(std::remove(token.begin(), token.end(), ';'), token.end());
    token.erase(std::remove(token.begin(), token.end(), ':'), token.end());
    token.erase(std::remove(token.begin(), token.end(), '?'), token.end());
    token.erase(std::remove(token.begin(), token.end(), '!'), token.end());
    words.push_back(token);
      
  }

  // remove empty words
  words.erase(std::remove(words.begin(), words.end(), ""), words.end());
  // set with unique words
  std::set<std::string> unique;
  for(auto i: words)
    unique.insert(i);

  std::vector<comp> result;

  // search unique words in all words and count
  // push result to vector of tmp
  int count = 0;
  for(auto i: unique){
    for(auto o: words){
      if(i == o)
	count ++;
    }
    comp tmp;
    tmp.word = i;
    tmp.count = count;
    result.push_back(tmp);
    count = 0;
  }
 
  std::sort(result.begin(), result.end(),
	    [](comp first, comp second){
	      return (first.count > second.count);
	    });
  for(unsigned int i = 0; i < result.size(); i++){
    std::cout << result[i].word << " => " << result[i].count << "\n";
  }
}
