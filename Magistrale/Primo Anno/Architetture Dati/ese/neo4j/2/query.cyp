  //list alla emplyer sold order
MATCH (e:Employee)-[r:SOLD]-(o:Order) RETURN e, r, o
      
  // List the product categories provided by each supplier
MATCH (s:Supplier)-->(:Product)-->(c:Category)
RETURN s.companyName as Company,
       collect(distinct c.categoryName) as Categories
       
      // Find the produce suppliers
MATCH (c:Category {categoryName:"Produce"})<--(:Product)<--(s:Supplier)
RETURN DISTINCT s.companyName as ProduceSuppliers

       // Find all Products Ordering and Paging
MATCH (p:Product)
RETURN p.productName, p.unitPrice ORDER BY p.unitPrice DESC LIMIT 10;
       
      // Find single Product by Name Filter by Equality
MATCH (p:Product) WHERE p.productName = "Chocolade"
RETURN p.productName, p.unitPrice;
       
      // Find single Product Filter Products Filter by List/Range
MATCH (p:Product) WHERE p.productName IN ['Chocolade','Chai']
RETURN p.productName, p.unitPrice;
       
       // Find single Product Filter Filter by Multiple Numeric
       // and Textual Predicates
MATCH (p:Product) WHERE p.productName STARTS WITH "C" AND p.unitPrice > 100
RETURN p.productName, p.unitPrice;


       // Joining Products with Customers Join Records, Distinct Results
MATCH (p:Product {productName:"Chocolade"})<-[:ORDERS]-(:Order)<-[:PURCHASED]
      -(c:Customer) RETURN distinct c.companyName;

      // New Customers without Orders yet Outer Joins, Aggregation
MATCH (c:Customer {companyName:"Drachenblut Delikatessen"}) OPTIONAL MATCH
      (p:Product)<-[pu:PRODUCT]-(:Order)<-[:PURCHASED]-(c)
RETURN p.productName, toInt(sum(pu.unitPrice * pu.quantity)) AS volume
ORDER BY volume DESC;
      
      // Top-Selling Employees Aggregation, Grouping (errata)
MATCH (:Order)<-[:SOLD]-(e:Employee) RETURN e.name, count(*) AS cnt
ORDER BY cnt DESC LIMIT 10

      // Employee Territorie Collecting Master-Detail Queries
MATCH (t:Territory)<-[:IN_TERRITORY]-(e:Employee) RETURN t.description,
      collect(e.lastName);
      
      // Product Categories Hierarchies and Trees, Variable Length Joins
MATCH (p:Product)-[:PART_OF]->(l:ProductCategory)-[:PARENT*0..]
      -(:ProductCategory {name:"Dairy Products"}) RETURN p.name
