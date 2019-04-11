
#ifndef FORNITORE_H
#define FORNITORE_H

#include <string>
#include <vector>



/**
  * class fornitore
  * 
  */

class fornitore
{
public:

  // Constructors/Destructors
  //  


  /**
   * Empty Constructor
   */
  fornitore ();

  /**
   * Empty Destructor
   */
  virtual ~fornitore ();

  // Static Public attributes
  //  

  // Public attributes
  //  


  // Public attribute accessor methods
  //  


  // Public attribute accessor methods
  //  


protected:

  // Static Protected attributes
  //  

  // Protected attributes
  //  

public:


  // Protected attribute accessor methods
  //  

protected:

public:


  // Protected attribute accessor methods
  //  

protected:


private:

  // Static Private attributes
  //  

  // Private attributes
  //  

  std::string giorno_paga;
public:


  // Private attribute accessor methods
  //  

private:

public:


  // Private attribute accessor methods
  //  


  /**
   * Set the value of giorno_paga
   * @param new_var the new value of giorno_paga
   */
  void setGiorno_paga (std::string new_var)   {
      giorno_paga = new_var;
  }

  /**
   * Get the value of giorno_paga
   * @return the value of giorno_paga
   */
  std::string getGiorno_paga ()   {
    return giorno_paga;
  }
private:


  void initAttributes () ;

};

#endif // FORNITORE_H
