#CXXFLAGS = -D NDEBUG -O3
CXXFLAGS = 

main.exe: main.o method.o
	g++ method.o main.o -o a.out

main.o: main.cpp method.h
	g++ -c $(CXXFLAGS) main.cpp -o main.o

dbuffer.o: method.cpp method.h
	g++ -c $(CXXFLAGS) method.cpp -o method.o

.PHONY: clean
clean:
	rm *.o *.out
