
#ifndef PERSONA_H
#define PERSONA_H
#include "contatto.h"

#include <string>
#include <vector>



/**
  * class persona
  * 
  */

class persona : public contatto
{
public:

  // Constructors/Destructors
  //  


  /**
   * Empty Constructor
   */
  persona ();

  /**
   * Empty Destructor
   */
  virtual ~persona ();

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

  std::string codice_fiscale;
public:


  // Private attribute accessor methods
  //  

private:

public:


  // Private attribute accessor methods
  //  


  /**
   * Set the value of codice_fiscale
   * @param new_var the new value of codice_fiscale
   */
  void setCodice_fiscale (std::string new_var)   {
      codice_fiscale = new_var;
  }

  /**
   * Get the value of codice_fiscale
   * @return the value of codice_fiscale
   */
  std::string getCodice_fiscale ()   {
    return codice_fiscale;
  }
private:


  void initAttributes () ;

};

#endif // PERSONA_H
