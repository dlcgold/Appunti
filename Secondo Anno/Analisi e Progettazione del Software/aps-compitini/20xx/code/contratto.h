
#ifndef CONTRATTO_H
#define CONTRATTO_H

#include stringa
#include vettore



/**
  * class contratto
  * 
  */

class contratto
{
public:

  // Constructors/Destructors
  //  


  /**
   * Empty Constructor
   */
  contratto ();

  /**
   * Empty Destructor
   */
  virtual ~contratto ();

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

  Date data_inizio;
  stringa qualifica;
public:


  // Private attribute accessor methods
  //  

private:

public:


  // Private attribute accessor methods
  //  


  /**
   * Set the value of data_inizio
   * @param new_var the new value of data_inizio
   */
  void setData_inizio (Date new_var)   {
      data_inizio = new_var;
  }

  /**
   * Get the value of data_inizio
   * @return the value of data_inizio
   */
  Date getData_inizio ()   {
    return data_inizio;
  }

  /**
   * Set the value of qualifica
   * @param new_var the new value of qualifica
   */
  void setQualifica (stringa new_var)   {
      qualifica = new_var;
  }

  /**
   * Get the value of qualifica
   * @return the value of qualifica
   */
  stringa getQualifica ()   {
    return qualifica;
  }
private:


  void initAttributes () ;

};

#endif // CONTRATTO_H
