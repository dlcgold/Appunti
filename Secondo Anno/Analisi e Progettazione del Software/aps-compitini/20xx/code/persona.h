
#ifndef PERSONA_H
#define PERSONA_H

#include stringa
#include vettore



/**
  * class persona
  * 
  */

class persona
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

  stringa nome;
  stringa cognome;
  stringa codice_fiscale;
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
  void setNome (stringa new_var)   {
      nome = new_var;
  }

  /**
   * Get the value of nome
   * @return the value of nome
   */
  stringa getNome ()   {
    return nome;
  }

  /**
   * Set the value of cognome
   * @param new_var the new value of cognome
   */
  void setCognome (stringa new_var)   {
      cognome = new_var;
  }

  /**
   * Get the value of cognome
   * @return the value of cognome
   */
  stringa getCognome ()   {
    return cognome;
  }

  /**
   * Set the value of codice_fiscale
   * @param new_var the new value of codice_fiscale
   */
  void setCodice_fiscale (stringa new_var)   {
      codice_fiscale = new_var;
  }

  /**
   * Get the value of codice_fiscale
   * @return the value of codice_fiscale
   */
  stringa getCodice_fiscale ()   {
    return codice_fiscale;
  }
private:


  void initAttributes () ;

};

#endif // PERSONA_H
