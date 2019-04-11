
#ifndef DIPENDENTE_H
#define DIPENDENTE_H

#include <string>
#include <vector>



/**
  * class dipendente
  * 
  */

class dipendente
{
public:

  // Constructors/Destructors
  //  


  /**
   * Empty Constructor
   */
  dipendente ();

  /**
   * Empty Destructor
   */
  virtual ~dipendente ();

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

  double stipendio_base;
public:


  // Private attribute accessor methods
  //  

private:

public:


  // Private attribute accessor methods
  //  


  /**
   * Set the value of stipendio_base
   * @param new_var the new value of stipendio_base
   */
  void setStipendio_base (double new_var)   {
      stipendio_base = new_var;
  }

  /**
   * Get the value of stipendio_base
   * @return the value of stipendio_base
   */
  double getStipendio_base ()   {
    return stipendio_base;
  }
private:


  void initAttributes () ;

};

#endif // DIPENDENTE_H
