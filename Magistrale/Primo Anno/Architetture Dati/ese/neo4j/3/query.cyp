  // point of each driver
MATCH (d:Driver)-[f:FINISHED]-(c:GrandPrix)
RETURN d.name, sum(f.points) as points ORDER BY points DESC
       
       // point of each constructor
MATCH (co:Constructor)-[b:BELONGS_TO]-(d:Driver)-[f:FINISHED]-(c:GrandPrix)
RETURN co.name, sum(f.points) as points ORDER BY points DESC

       // count Alonso for every position (secondo me non ha senso)
MATCH (d:Driver {name: "Fernando Alonso"})-[f:FINISHED]-()
WHERE f.position=f.position
Return d.name, count(f.position) as time, f.position as position
ORDER BY d.name, time DESC

      // driver country = constructor country
MATCH (d:Driver)-[]->(co:Constructor)-[co:COUNTRY_ORIGIN]
      ->(c:Country)<-[cd: COUNTRY_ORIGIN]-(d)
      RETURN d.name, co.name, c.name
