
#ifndef CLIENTE_H
#define CLIENTE_H

#include <string>
#include <vector>



/**
  * class cliente
  * 
  */

class cliente
{
public:

  // Constructors/Destructors
  //  


  /**
   * Empty Constructor
   */
  cliente ();

  /**
   * Empty Destructor
   */
  virtual ~cliente ();

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

  std::string sconto_fisso;
public:


  // Private attribute accessor methods
  //  

private:

public:


  // Private attribute accessor methods
  //  


  /**
   * Set the value of sconto_fisso
   * @param new_var the new value of sconto_fisso
   */
  void setSconto_fisso (std::string new_var)   {
      sconto_fisso = new_var;
  }

  /**
   * Get the value of sconto_fisso
   * @return the value of sconto_fisso
   */
  std::string getSconto_fisso ()   {
    return sconto_fisso;
  }
private:


  void initAttributes () ;

};

#endif // CLIENTE_H
