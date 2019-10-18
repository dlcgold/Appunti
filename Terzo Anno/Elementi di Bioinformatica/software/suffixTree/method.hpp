// https://www.sanfoundry.com/cpp-program-implement-suffix-tree/

#ifndef METHOD_H
#define METHOD_H

#include <string>
#include <vector>

#endif
class suffixNode{
public:
  int begin;
  int end;
  int depth;
  suffixNode **children;
  suffixNode *parent;
  suffixNode *suffixLink;

  suffixNode(int begin, int end, int deptht, suffixNode *parent);
  bool contains(int value);
};


class suffixTree{
public:
  suffixNode *buildSuffixTree(std::string s);
};
