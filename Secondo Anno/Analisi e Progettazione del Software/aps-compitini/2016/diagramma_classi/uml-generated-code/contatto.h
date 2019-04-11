
#ifndef CONTATTO_H
#define CONTATTO_H

#include <string>
#include <vector>



/**
  * class contatto
  * 
  */

class contatto
{
public:

  // Constructors/Destructors
  //  


  /**
   * Empty Constructor
   */
  contatto ();

  /**
   * Empty Destructor
   */
  virtual ~contatto ();

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
  std::string indirizzo;
  std::string numero;
  std::string fax;
  std::string mail;
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

  /**
   * Set the value of indirizzo
   * @param new_var the new value of indirizzo
   */
  void setIndirizzo (std::string new_var)   {
      indirizzo = new_var;
  }

  /**
   * Get the value of indirizzo
   * @return the value of indirizzo
   */
  std::string getIndirizzo ()   {
    return indirizzo;
  }

  /**
   * Set the value of numero
   * @param new_var the new value of numero
   */
  void setNumero (std::string new_var)   {
      numero = new_var;
  }

  /**
   * Get the value of numero
   * @return the value of numero
   */
  std::string getNumero ()   {
    return numero;
  }

  /**
   * Set the value of fax
   * @param new_var the new value of fax
   */
  void setFax (std::string new_var)   {
      fax = new_var;
  }

  /**
   * Get the value of fax
   * @return the value of fax
   */
  std::string getFax ()   {
    return fax;
  }

  /**
   * Set the value of mail
   * @param new_var the new value of mail
   */
  void setMail (std::string new_var)   {
      mail = new_var;
  }

  /**
   * Get the value of mail
   * @return the value of mail
   */
  std::string getMail ()   {
    return mail;
  }
private:


  void initAttributes () ;

};

#endif // CONTATTO_H
