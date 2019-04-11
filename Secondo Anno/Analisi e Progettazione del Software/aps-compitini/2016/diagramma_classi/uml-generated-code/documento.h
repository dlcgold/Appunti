
#ifndef DOCUMENTO_H
#define DOCUMENTO_H

#include <string>

/**
  * class documento
  * 
  */

class documento
{
public:

  // Constructors/Destructors
  //  


  /**
   * Empty Constructor
   */
  documento ();

  /**
   * Empty Destructor
   */
  virtual ~documento ();

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

  std::string protocollo;
  std::string data_emissione;
public:


  // Private attribute accessor methods
  //  

private:

public:


  // Private attribute accessor methods
  //  


  /**
   * Set the value of protocollo
   * @param new_var the new value of protocollo
   */
  void setProtocollo (std::string new_var)   {
      protocollo = new_var;
  }

  /**
   * Get the value of protocollo
   * @return the value of protocollo
   */
  std::string getProtocollo ()   {
    return protocollo;
  }

  /**
   * Set the value of data_emissione
   * @param new_var the new value of data_emissione
   */
  void setData_emissione (std::string new_var)   {
      data_emissione = new_var;
  }

  /**
   * Get the value of data_emissione
   * @return the value of data_emissione
   */
  std::string getData_emissione ()   {
    return data_emissione;
  }
private:


  void initAttributes () ;

};

#endif // DOCUMENTO_H
