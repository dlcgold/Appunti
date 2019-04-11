
#ifndef ITEM_H
#define ITEM_H

#include <string>
#include <vector>



/**
  * class item
  * 
  */

class item
{
public:

  // Constructors/Destructors
  //  


  /**
   * Empty Constructor
   */
  item ();

  /**
   * Empty Destructor
   */
  virtual ~item ();

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

  std::string articolo;
  double costo;
public:


  // Private attribute accessor methods
  //  

private:

public:


  // Private attribute accessor methods
  //  


  /**
   * Set the value of articolo
   * @param new_var the new value of articolo
   */
  void setArticolo (std::string new_var)   {
      articolo = new_var;
  }

  /**
   * Get the value of articolo
   * @return the value of articolo
   */
  std::string getArticolo ()   {
    return articolo;
  }

  /**
   * Set the value of costo
   * @param new_var the new value of costo
   */
  void setCosto (double new_var)   {
      costo = new_var;
  }

  /**
   * Get the value of costo
   * @return the value of costo
   */
  double getCosto ()   {
    return costo;
  }
private:


  void initAttributes () ;

};

#endif // ITEM_H
