
#ifndef AZIENDA_H
#define AZIENDA_H
#include "contatto.h"

#include <string>
#include <vector>



/**
  * class azienda
  * 
  */

class azienda : public contatto
{
public:

  // Constructors/Destructors
  //  


  /**
   * Empty Constructor
   */
  azienda ();

  /**
   * Empty Destructor
   */
  virtual ~azienda ();

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

  std::string partita_iva;
public:


  // Private attribute accessor methods
  //  

private:

public:


  // Private attribute accessor methods
  //  


  /**
   * Set the value of partita_iva
   * @param new_var the new value of partita_iva
   */
  void setPartita_iva (std::string new_var)   {
      partita_iva = new_var;
  }

  /**
   * Get the value of partita_iva
   * @return the value of partita_iva
   */
  std::string getPartita_iva ()   {
    return partita_iva;
  }
private:


  void initAttributes () ;

};

#endif // AZIENDA_H
