  // create node from CSV
  // Create products
  LOAD CSV WITH HEADERS FROM "http://data.neo4j.com/northwind/products.csv"
  AS row CREATE (:Product {productName: row.productName,
			   productID:row.productID,
			   unitPrice: toFloat(row.unitPrice)});
  // Create suppliers
  LOAD CSV WITH HEADERS FROM "http://data.neo4j.com/northwind/suppliers.csv"
  AS row CREATE (:Supplier {companyName: row.companyName,
			    supplierID:row.supplierID});

  // create customers
  LOAD CSV WITH HEADERS FROM "http://data.neo4j.com/northwind/customers.csv"
  AS row CREATE (n:Customer)SET n = row
  
  // Create employees
  LOAD CSV WITH HEADERS FROM "http://data.neo4j.com/northwind/employees.csv"
  AS row CREATE (:Employee {employeeID:row.employeeID,
			    firstName: row.firstName,
			    lastName: row.lastName,
			    title: row.title});
  // Create categories
  LOAD CSV WITH HEADERS FROM "http://data.neo4j.com/northwind/categories.csv"
  AS row CREATE (:Category {categoryID: row.categoryID,
			    categoryName: row.categoryName,
			    description: row.description});
  // Create orders
  LOAD CSV WITH HEADERS FROM "http://data.neo4j.com/northwind/orders.csv"
  AS row MERGE (order:Order {orderID: row.orderID})
  ON CREATE SET order.shipName = row.shipName;

  // create indexes
CREATE INDEX ON :Product(productID);
CREATE INDEX ON :Product(productName);
CREATE INDEX ON :Category(categoryID);
CREATE INDEX ON :Employee(employeeID);
CREATE INDEX ON :Supplier(supplierID);
CREATE INDEX ON :Customer(customerID);
CREATE INDEX ON :Customer(customerName);
       
       // create relationship
       LOAD CSV WITH HEADERS FROM
       "http://data.neo4j.com/northwind/orders.csv" AS row
MATCH (order:Order {orderID: row.orderID})
MATCH (employee:Employee {employeeID: row.employeeID}) MERGE
      (employee)-[:SOLD]->(order);
      
      LOAD CSV WITH HEADERS FROM
      "http://data.neo4j.com/northwind/orders.csv" AS row
MATCH (order:Order {orderID: row.orderID})
MATCH (customer:Customer {customerID: row.customerID}) MERGE
      (customer)-[:PURCHASED]->(order);

      LOAD CSV WITH HEADERS FROM
      "http://data.neo4j.com/northwind/products.csv" AS row MATCH
      (product:Product {productID: row.productID})
MATCH (supplier:Supplier {supplierID: row.supplierID})
      MERGE (supplier)-[:SUPPLIES]->(product);

      LOAD CSV WITH HEADERS FROM
      "http://data.neo4j.com/northwind/products.csv" AS row
MATCH (product:Product {productID:row.productID})
MATCH (category:Category {categoryID:row.categoryID})
      MERGE (product)-[:PART_OF]->(category);

      LOAD CSV WITH HEADERS FROM
      "http://data.neo4j.com/northwind/order-details.csv" AS row
MATCH (p:Product), (o:Order) WHERE p.productID = row.productID
      AND o.orderID = row.orderID CREATE (o)-[details:ORDERS]->(p) SET
      details = row, details.quantity = toInteger(row.quantity);
