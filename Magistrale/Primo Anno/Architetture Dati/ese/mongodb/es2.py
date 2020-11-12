# link dataset nel readme del progettp
import pymongo
from pymongo import MongoClient
from pprint import pprint

client = MongoClient('localhost', 27017)

db = client['global_trades']
trades = db['trades']

# con 'use global_trades' nella shell di mongo mi collego al db embedding
# esempio di documento embedded:

# {"_id": ObjectId("5b1d661f6f185a3946d5c52c"),
#   "trade_details" : { "weight_kg": 2339,
#                       "quantity_name": "Number of items",
#                       "flow": "Export",
#                       "trade_usd": 6088,
#                       "quantity": 51 },
#   "commodity": { "category": "01_live_animals",
#                   "code": "010410",
#                   "name": "Sheep, live"},
#   "country_or_area":"Afghanistan",
#   "year": "2016"}

# struttura separata sepraando commercio e prodotti,
# avendo, ad esempio, per "trade":

# {"_id": ObjectId("5b1d67f86f185a3a2c8ee0ce"),
#   "trade_details": { "weight_kg": 2339,
#                      "quantity_name": "Number of items",
#                      "flow": "Export",
#                      "trade_usd": 6088,
#                      "quantity": 51},
#   "comm_code": "010410",
#   "country_or_area": "Afghanistan",
#   "year": "2016"}

# e, ad esempio, per "comodity":

# {"_id": ObjectId("5b1d68d86f185a3a2c0c651e"),
#   "category": "15_animal_vegetable_fats_and_oils_cleavage_products_et",
#   "code": "151930",
#   "name" : "Industrial fatty alcohols"}

cursor = trades.find({}).limit(2)
for document in cursor:
    pprint(document)

cursor = trades.find({'year': '2016'}).limit(5)
for document in cursor:
    pprint(document)

# trade più costosa per ogni anno, per ogni stato, con embedding
# raggruppo su id in base a stato ed anno (specificati con $ per specificare
# che sono il percorso di un campo)
# estraggo il valore massimo di trade_details.trade_usd
# ordino i risultati in base all'anno
# proiezione su stato, anno e trade più costosa (usando il $max_value
# calcolato) escludo id che non mi serve
cursor = trades.aggregate([
    {'$group': {"_id": {"country_or_area": "$country_or_area",
                        "year": "$year"},
                "max_value": {'$max': "$trade_details.trade_usd"}}},
    {'$sort': {"_id.year": 1}},
    {'$project': {
        "_id": 0,
        "country_or_area": "$_id.country_or_area",
        "year": "$_id.year",
        "most_expensive_trade_dollars": "$max_value"}}])

for document in cursor:
    pprint(document)

# vedo se canada ha più trade con pecore o con capre vive, con embedding
# matcho su canada, pecore vive e capre vive
# raggruppo su id e sulla somma di trade,
# ottenuta con $sum di $trade_details.quantity
# proiezione su id (che stampo come nome e non come id vero e proprio)
# e sulla quantità
cursor = trades.aggregate([
    {'$match': {"country_or_area": "Canada",
                "commodity.name": {'$in': ["Sheep, live", "Goats, live"]}}},
    {'$group': {"_id": "$commodity.name",
                "quantity": {'$sum': "$trade_details.quantity"}}},
    {'$project': {
        "_id": 0,
        "name": "$_id",
        "quantity": "$quantity"}}])
for document in cursor:
    pprint(document)

# con 'use global_trades_ref' nella shell di mongo passo al db referencing
# con le due collections
# trade più costosa per ogni anno, per ogni stato, con referencing
# con $lookup "collego" le collections tramite i field "code" e "common_code"
# $unwind per produrre unico documento (??)
# poi procedo come al prima

# cursor = commodities_ref.aggregate([
#     {'$lookup': {from: "trades_ref",
#                  localField: "code",
#                  foreignField: "comm_code",
#                  as: "commodities_trades"}},
#     {'$unwind': "$commodities_trades"},
#     {'$group': {"_id": {"country_or_area": "$country_or_area",
#                         "year": "$year"},
#                 "max_value": {'$max': "$trade_details.trade_usd"}}},
#     {'$sort': {"_id.year": 1}},
#     {'$project': {
#         "_id": 0,
#         "country_or_area": "$_id.country_or_area",
#         "year": "$_id.year",
#         "most_expensive_trade_dollars": "$max_value"}}
# ])
# for document in cursor:
#     pprint(document)
