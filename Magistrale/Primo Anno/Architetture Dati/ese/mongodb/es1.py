import pymongo
# importo MongoClient
from pymongo import MongoClient
# pprint per la stampa formattata
from pprint import pprint

# connessione al db
client = MongoClient('localhost', 27017)
# avre potutto fare anche "client = MongoClient('mongodb://localhost:27017/')"
db = client['test']
movies = db['movies']

# vedo operazioni CRUD

# inserimento
newmovie = {'title': "Star Trek III: The final blabla",
            'year': 1982,
            'imdb': "zz08"}
x = movies.insert_one(newmovie)

# con find query, si restituisce un documento unico con tutti i sottodocumenti
# che sono risultato della query, salvati in cursor, che dovrò iterare per
# stampare

# prendo il primo documento con limit(1)
cursor = movies.find({}).limit(1)
for document in cursor:
    pprint(document)

# prendo film del 2000
cursor = movies.find({'year': 2000})
for document in cursor:
    pprint(document)

# prendo film con un awards vinto, vado quindi tramite .win nel sottodocumento
# di awards
cursor = movies.find({"awards.wins": 1})
for document in cursor:
    pprint(document)

# query con anche più valori, tipo una doppia lingua, due elementi specifici
# dell'array languages
cursor = movies.find({"languages": ["English", "French"]})
for document in cursor:
    pprint(document)
# uso all se non so l'ordine
# movies.find({languages:{$all:["English", "French"]}})

# non voglio _id quindi lo metto a 0
cursor = movies.find({'year': 2000}, {'_id' : 0}).limit(5)
for document in cursor:
    pprint(document)

# query specifica (anno 2000) volendo type e awards, settandoli a 1
# con solo i primi 2 risultati
cursor = movies.find({'year': 2000}, {'type': 1, 'awards': 1}).limit(2)
for document in cursor:
    pprint(document)

# query ordinata su solo year, che viene settato quindi a 1 per crescente,
# -1 per decrescente
cursor = movies.find().sort('year', 1).limit(2)
for document in cursor:
    pprint(document)

# su slide lista comparatori
# query con essi
# cerco rutine tra 90 e 100, non volendo _id ma solo titole e runtime
# prendendo i primi 10
cursor = movies.find({'runtime': {'$gt': 90,
                                  '$lt': 100}},
                     {'_id': 0,
                      'title': 1,
                      'runtime': 1}).limit(10)
for document in cursor:
    pprint(document)

# anche con sort su runtime
cursor = movies.find({'runtime': {'$gt': 90,
                                  '$lt': 100}},
                     {'_id': 0,
                      'title': 1,
                      'runtime': 1}).sort('runtime', 1).limit(10)
for document in cursor:
    pprint(document)

# uso operatori logici
# query con or tra le due condizioni (sulle review di tomatoes e imdb)
# prendo titoli review di tomatoes e voti di imdb,
# prendendo primi due risultati
cursor = movies.find({'$or':
                      [{'tomatoes.viewer.numReviews': {'$gt': 95}},
                       {"imdb.votes": {'$gt': 88}}]},
                     {'_id': 0,
                      'title': 1,
                      'tomatoes.viewer.numReviews': 1,
                      'imdb.votes': 1}).limit(2)
for document in cursor:
    pprint(document)

# ho anche l'exist, che matcha documenti con un certo field
# prendo i dati dove genres non esiste
cursor = movies.find({'genres': {'$exists': 'false'}},
                     {'_id': 0,
                      'title': 1}).limit(3)
for document in cursor:
    pprint(document)

# e il type, che seleizona documenti se un field è di un certo tipo
cursor = movies.find({'awards.wins': {'$type': 'int'}},
                     {'_id': 0,
                      'title': 1,
                      'awards': 1}).limit(10)
for document in cursor:
    pprint(document)


# si hanno altri operatori, come per le regex
cursor = movies.find({'title': {'$regex': '/*lov.*/'}},
                     {'_id': 0,
                      'title': 1})
for document in cursor:
    pprint(document)

# si hanno operatori per query geospaziali (che non vediamo come esempio)
# o per array
# voglio tutti e tre tra Comedy, crime e drama, con $all
cursor = movies.find({'genres': {'$all': ["Comedy", "Crime", "Drama"]}},
                     {'_id': 0,
                      'title': 1,
                      'genres': 1}).limit(5)
for document in cursor:
    pprint(document)

# su slide varie regole di update
# esempio di $set su un singolo documento con uodate_one ma su multicampi
movies.update_one({'title': "Regeneration"},
                  {'$set': {"awards": {"wins": 8,
                                       "nominations": 14,
                                       "text": "3 Golden Globes."}}})

# possiamo fare in modo simile i delete, regole su slide

# c'è indicizzazione, di default indice su id secondo vari altri parametri
movies.create_index([("name", pymongo.DESCENDING)])

# c'è aggregazione, secondo vari modi
# modo: pipeline di aggregazione, in primis tramite $match
# con $limit
cursor = movies.aggregate([
    {"$match": {"cast": "Will Smith"}},
    {"$project": {"_id": 0,
                  "title": 1}},
    {"$limit": 5}
])
for document in cursor:
    pprint(document)

# con $count
cursor = movies.aggregate([
    {"$match": {"cast": "Will Smith"}},
    {"$project": {"_id": 0,
                  "title": 1,
                  "year": 1,
                  "actors":1}},
    {"$count": "num_movies"}
])

for document in cursor:
    pprint(document)

# studio media rating di imdb dopo un certo $match, usando $avg
cursor = movies.aggregate([
    {'$match': {'cast': 'Will Smith'}},
    {'$project': {'_id': 0,
                  'title': 1,
                  'imdb.rating': 1}},
    {'$group': {'_id': 'The average for imdb',
                'avg_rating': {
                    '$avg': '$imdb.rating'}}}
])
for document in cursor:
    pprint(document)
