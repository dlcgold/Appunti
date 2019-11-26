CXXFLAGS = -D NDEBUG -O3
#CXXFLAGS = 

main.out: main.o 
	g++ main.o -o main.out

main.o: main.cpp dbufferT.hpp
	g++ -c $(CXXFLAGS) main.cpp -o main.o


.PHONY: clean
clean:
	rm *.o *.out
