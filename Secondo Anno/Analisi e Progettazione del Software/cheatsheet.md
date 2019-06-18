# APS

## REFACTORING
- ***extract method***, meglio avere più metodi che troppa roba in un solo metodo
  ```java
  void printOwing() {
    printBanner();

    // Print details.
    System.out.println("name: " + name);
    System.out.println("amount: " + getOutstanding());
  }
  
  \\ to
  
  void printOwing() {
    printBanner();
    printDetails(getOutstanding()); 
  }

  void printDetails(double outstanding) {
    System.out.println("name: " + name);
    System.out.println("amount: " + outstanding);
  }

  ```
- ***move method***, se un metodo è usato più in un'altra classe allora lo sposto lì
- ***rename method***, per rinominare un metodo
- ***move field***, per spostare gli attributi in maniera intelligente
- ***extract class***, se una classe fa le cose di due separo la seconda classe con i metodi necessari

## CODE SMELL

- ***duplicate code***, se ho lo stesso codice in più metodi uso l'*extract method* 
  per estrerre quel codice e lo chiamo in più punti
- ***long method***, quando si ha un metodo troppo lungo, uso *extract method* e se
non posso estraggo il metodo in un'altra classe:
```java
class Order {
  // ...
  public double price() {
    double primaryBasePrice;
    double secondaryBasePrice;
    double tertiaryBasePrice;
    // Perform long computation.
  }
}

// to

class Order {
  // ...
  public double price() {
    return new PriceCalculator(this).compute();
  }
}

class PriceCalculator {
  private double primaryBasePrice;
  private double secondaryBasePrice;
  private double tertiaryBasePrice;
  
  public PriceCalculator(Order order) {
    // Copy relevant information from the
    // order object.
  }
  
  public double compute() {
    // Perform long computation.
  }
}
```
- ***feauture envy***, se una metodo in una classe usa spesso metodi e dati di un'altra
classe uso *extract method* e *move method* e *move field* per spostare gli attributi
- ***large class***, se ho una classe troppo lunga la spacco con *extract class* e 
*extract subclass* con ovviamente *move field*
- ***switch statement***, se ho uno *switch* complicato o una sequenza di *if else*
isoliamo il codice con *extract method* e *move method*.
Posso usare anche *extract subclass* prima di isolare e procedere
- ***data class***, ovvero una classe con solo variabili di classe e getter/setter. 
Uso *move method* per spostare i metodi delle altre classi che usano la data class dentro la data class stessa
- ***long parameter list***, quando ho troppi argomenti per un metodo. 
Risolvo sostitutendo i parametri con metodi getter contentuti nel codice del metodo
oppure passando un oggetto singolo e non i singoli parametri dell'oggetto
- ***shotgun surgery***, se la modifica di una classe è significativa per molte classi figlie.
Per il refactor uso *move method* e *move field* per spezzare la classe madre
- ***comments***, devono essere corti e pochi, con codice auto esplicativo.
Se ho un codice che bnon permette questi commenti uso *rename method* e *extracxt method*. 
Uso *insert assertion* per aiutare a rendere il codice auto esplicativo

## TESTING

# CATEGORY PARTITION

1) identificare le categorie per ogni parametro, ovvero le caratteristiche elementari
2) per ogni categoria identificare individuo i valori significativi, che possono essere considerazioni numeriche o logiche
3) per ogni valore significativo identifico i vincoli con [property] e [if-property] [error] [single] (se da testare una volta sola)
