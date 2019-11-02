#include <iostream>
#include "method.h"

int main(int argc, char *argv[]){
  dbuffer db; 
	
  dbuffer db2(100); 

  dbuffer db3(10,0); 
  
  dbuffer db4[5];

  db2.set_value(0, 100);
  
  int tmp = db2.get_value(0);

  db2.set_value(0,db2.get_value(50));

  db2.value(0) = db2.value(50);
}
