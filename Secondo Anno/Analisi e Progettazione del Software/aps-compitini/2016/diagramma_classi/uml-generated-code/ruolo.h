
#ifndef RUOLO_H
#define RUOLO_H

#include <string>

/**
  * class ruolo
  * 
  */

class ruolo
{
public:

  // Constructors/Destructors
  //  


  /**
   * Empty Constructor
   */
  ruolo ();

  /**
   * Empty Destructor
   */
  virtual ~ruolo ();

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

  std::string nome;
public:


  // Private attribute accessor methods
  //  

private:

public:


  // Private attribute accessor methods
  //  


  /**
   * Set the value of nome
   * @param new_var the new value of nome
   */
  void setNome (std::string new_var)   {
      nome = new_var;
  }

  /**
   * Get the value of nome
   * @return the value of nome
   */
  std::string getNome ()   {
    return nome;
  }
private:


  void initAttributes () ;

};

#endif // RUOLO_H
