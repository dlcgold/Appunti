#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable> 
#include <unistd.h>
#include <random>


int cars;
int park_spaces;
bool freeS;
std::condition_variable freeSpace;
std::mutex mutexin;
std::mutex mutexout;

void thread_car(int id) {
  // abbellimento id
  id += 1;

  // tentativo inserimento macchina
  mutexin.lock();
  std::cout << "prova ad entrare macchina " << id << "\n";
  mutexin.unlock();
  
  // check parcheggio pieno
  std::unique_lock<std::mutex> lockin(mutexin);
  while(park_spaces <= 0){
    std::cout << "full\n";
    freeSpace.wait(lockin);
    mutexin.lock();
  }

  // inserimento macchina e occupamento posto
  park_spaces--;
  std::cout << "entra macchina " << id << ", "
	    << "numero di posti liberi: " << park_spaces << "\n";
  
  mutexin.unlock();
  mutexout.lock();
  usleep(1000000);

  //uscita macchina ed eventuale segnale per sbloccare ingresso
  std::cout << "esce macchina " << id << ", "
	    << "numero di posti liberi: " << park_spaces << "\n";
  park_spaces++;
  freeSpace.notify_all();
  mutexout.unlock();
 
}

int main(int argc, char** argv) {
  cars = atoi(argv[1]);
  park_spaces = atoi(argv[2]);
  std::cout << "simulazione con " << cars << " macchine per " << park_spaces << " spazi\n";
  std::thread threads[cars];
  while(true){
    
    for (int n = 0; n < cars; n++){
      threads[n] = std::thread(thread_car, n);  
    }
    // std::random_device random;
    // std::mt19937 gen(random());
    // std::uniform_int_distribution<> distr(0, cars - 1);
    
    for (int n = 0; n < cars; n++){
      threads[n].join();    
    }
  }

  return 0;
}
