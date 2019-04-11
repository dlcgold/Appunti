
#ifndef FATTURE_H
#define FATTURE_H
#include "documento.h"

#include <string>
#include <vector>



/**
  * class fatture
  * 
  */

class fatture : public documento
{
public:

  // Constructors/Destructors
  //  


  /**
   * Empty Constructor
   */
  fatture ();

  /**
   * Empty Destructor
   */
  virtual ~fatture ();

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

  double importo;
public:


  // Private attribute accessor methods
  //  

private:

public:


  // Private attribute accessor methods
  //  


  /**
   * Set the value of importo
   * @param new_var the new value of importo
   */
  void setImporto (double new_var)   {
      importo = new_var;
  }

  /**
   * Get the value of importo
   * @return the value of importo
   */
  double getImporto ()   {
    return importo;
  }
private:


  void initAttributes () ;

};

#endif // FATTURE_H
