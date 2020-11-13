  // query
  // Find the actor named "Tom Hanks"
MATCH (n:Person) WHERE n.name = "Tom Hanks" RETURN n
      // Find the movie with title "Cloud Atlas"
MATCH (m:Movie) WHERE m.title = "Cloud Atlas" RETURN m
      // Find 10 people
MATCH (n:Person) RETURN n LIMIT 10      
      // Find movies released in the 1990s
MATCH (m:Movie) WHERE 1990 <= m.released <= 1999 RETURN m
      // List all Tom Hanks movies
MATCH (tom:Person {name:"Tom Hanks"})-[:ACTED_IN]->(m:Movie) RETURN tom, m
      // Who directed "Cloud Atlas"?
MATCH (p:Person)-[:DIRECTED]->(m:Movie {title: "Cloud Atlas"}) RETURN p, m
      // Tom Hanks' co-actors
MATCH (tom:Person {name:"Tom Hanks"})-[:ACTED_IN]->(m:Movie)<-[:ACTED_IN]
      -(coactors) RETURN tom, m, coactors
      // How people are related to "Cloud Atlas" (cerco una relazione r)    
MATCH (p:Person)-[r]->(m:Movie) return p.name, type(r), r, m.title
      // Movies and actors up to 4 "hops" away from Kevin Bacon
MATCH (p:Person {name: "Kevin Bacon"})-[*1..4]-(m) RETURN DISTINCT p, m
      // Bacon path, the shortest path of any relationships to Meg Ryan
MATCH sp = shortestPath((p:Person {name: "Kevin Bacon"})-[*]
		       -(m:Person {name: "Meg Ryan"})) RETURN sp
