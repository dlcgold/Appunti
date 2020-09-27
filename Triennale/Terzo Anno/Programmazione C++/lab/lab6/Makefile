MODE = -DNDEBUG 

main.out: main.o rubrica.o voce.o
	g++ $(MODE) main.o rubrica.o voce.o -o main.out

main.o: main.cpp voce.h rubrica.h
	g++ $(MODE) -c main.cpp -o main.o

voce.o: voce.cpp voce.h 
	g++ $(MODE) -c voce.cpp -o voce.o

rubrica.o: rubrica.cpp rubrica.h voce.h
	g++ $(MODE) -c rubrica.cpp -o rubrica.o

.PHONY: clean

clean:
	rm *.out *.o
