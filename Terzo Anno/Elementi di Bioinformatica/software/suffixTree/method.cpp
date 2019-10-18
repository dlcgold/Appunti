#include "method.hpp"

suffixNode::suffixNode(int begin, int end, int depth, suffixNode *parent){
  children = new suffixNode* [38];
  this->begin = begin;
  this->end = end;
  this->depth = depth;
  this->parent = parent;
}

bool suffixNode::contains(int value){
  return this->depth <= value && value <
    this->depth + (this->end -this->begin);
}

suffixNode* suffixTree::buildSuffixTree(std::string str){
  int str_size = str.size();
  std::vector<int> app;
  std::string alphabet = "abcdefghijklmnopqrstuvwxyz1234567890+*-#?!òàèùìé";

  for(int i = 0; i < str_size; i++)
    app.push_back(alphabet.find(str.at(i)));

  suffixNode* root = new suffixNode(0, 0, 0, nullptr);
  // iterator that starts at root
  suffixNode* iter = root;
  root->suffixLink = root;
  suffixNode *needsSuffixLink = nullptr;
  int lastRule = 0;
  int j = 0;
  for(int i = -1; i < str_size - 1; i++){
    int current = app[i + 1];
    for(; j <= i + 1; j++){
      int currentDepth = i + 1 - j;
    }
  }
}
