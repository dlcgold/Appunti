
#ifndef SOCIETÀ_H
#define SOCIETÀ_H

#include stringa
#include vettore



/**
  * class società
  * 
  */

class società
{
public:

  // Constructors/Destructors
  //  


  /**
   * Empty Constructor
   */
  società ();

  /**
   * Empty Destructor
   */
  virtual ~società ();

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

  stringa ragione_sociale;
  stringa partita_iva;
  stringa indirizzo;
public:


  // Private attribute accessor methods
  //  

private:

public:


  // Private attribute accessor methods
  //  


  /**
   * Set the value of ragione_sociale
   * @param new_var the new value of ragione_sociale
   */
  void setRagione_sociale (stringa new_var)   {
      ragione_sociale = new_var;
  }

  /**
   * Get the value of ragione_sociale
   * @return the value of ragione_sociale
   */
  stringa getRagione_sociale ()   {
    return ragione_sociale;
  }

  /**
   * Set the value of partita_iva
   * @param new_var the new value of partita_iva
   */
  void setPartita_iva (stringa new_var)   {
      partita_iva = new_var;
  }

  /**
   * Get the value of partita_iva
   * @return the value of partita_iva
   */
  stringa getPartita_iva ()   {
    return partita_iva;
  }

  /**
   * Set the value of indirizzo
   * @param new_var the new value of indirizzo
   */
  void setIndirizzo (stringa new_var)   {
      indirizzo = new_var;
  }

  /**
   * Get the value of indirizzo
   * @return the value of indirizzo
   */
  stringa getIndirizzo ()   {
    return indirizzo;
  }
private:


  void initAttributes () ;

};

#endif // SOCIETÀ_H
