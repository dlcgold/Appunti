library(neuralnet)

## carico iris
data(iris)
## lo divido in training e test
ind = sample(2, nrow(iris), replace = TRUE, prob=c(0.7, 0.3))
trainset = iris[ind == 1,]
testset = iris[ind == 2,]

## aggiungo colonne fittizzie sulla base della specie
## true se ho la giusta specie
trainset$setosa = trainset$Species == "setosa"
trainset$virginica = trainset$Species == "virginica"
trainset$versicolor = trainset$Species == "versicolor"

## ## istanzio la rete specifciando come si effettua il training
## ## per le tre etichette ragiono su alcuni input, lunghezza/larghezza petalo
## ## e lunghezza/larghezza petal sepal
## ## uso 3 neuroni nascosti
## ## aggiunge da solo i nodi bias
## network = neuralnet(versicolor + virginica + setosa~ Sepal.Length +
##                         Sepal.Width + Petal.Length + Petal.Width,
##                     trainset, hidden=3)


## ## pesi iniziali
## msw = network$startweights
## #pesi finali
## nw = network$weights
## ## la rete potrebbe non riuscire ad imparare
## steps = network$result.matrix["steps",]
## err = network$result.matrix["error",]

## ## plotto la rete
## plot(network)

## ## preparo canvas per i 4 grafici 
## par(mfrow=c(2,2))
## ## posso vedere pesi generalizzati e li plotto
## gwplot(network, selected.covariate="Petal.Width")
## gwplot(network, selected.covariate="Sepal.Width")
## gwplot(network, selected.covariate="Petal.Length")
## gwplot(network, selected.covariate="Sepal.Length")

## ## passo alla predizione con compute che restituisce la distribuzione
## ## di probabilità di ogni etichetta
## ## testset[-5] per fissare ul target
## net.predict = compute(network, testset[-5])$net.result
## ## scelgo classe con predizione più alta
## net.prediction = c("versicolor", "virginica", "setosa")[apply(net.predict,
##                                                               1,
##                                                               which.max)]
## ## ne stampo la tabella di classificazione/confusione
## predict.table = table(testset$Species, net.prediction)

## ## per ora ho usato funzione logistica
## ## passo a tanh
## networkt = neuralnet(versicolor + virginica + setosa~ Sepal.Length +
##                         Sepal.Width + Petal.Length + Petal.Width,
##                     trainset, hidden=3, act.fct = "tanh")


## ## pesi iniziali
## mswt = networkt$startweights
## #pesi finali
## nwt = networkt$weights
## stepst = networkt$result.matrix["steps",]
## errt = networkt$result.matrix["error",]
## ## plotto la rete
## plot(networkt)
## par(mfrow=c(2,2))
## ## posso vedere pesi generalizzati e li plotto
## gwplot(networkt, selected.covariate="Petal.Width")
## gwplot(networkt, selected.covariate="Sepal.Width")
## gwplot(networkt, selected.covariate="Petal.Length")
## gwplot(networkt, selected.covariate="Sepal.Length")

## net.predictt = compute(networkt, testset[-5])$net.result
## net.predictiont = c("versicolor", "virginica", "setosa")[apply(net.predictt,
##                                                               1,
##                                                               which.max)]
## predictt.table = table(testset$Species, net.predictiont)

err = 0
step = 0
count = 0
## TODO testa 10 volta ogni rete
for(i in 1:11){
    network = neuralnet(versicolor + virginica + setosa~ Sepal.Length +
                            Sepal.Width + Petal.Length + Petal.Width,
                        trainset, hidden=3)
    if(!is.null(network$result)){
        err = err+ network$result.matrix["steps",]
        step = step + network$result.matrix["error",]
        count = count + 1
        plot(network)
    }else{
        i = i - 1
    }
}
mids = step/count
mide = err/count

errta = 0
stepta = 0
count = 0
## TODO testa 10 volta ogni rete
for(i in 1:11){
    network = neuralnet(versicolor + virginica + setosa~ Sepal.Length +
                            Sepal.Width + Petal.Length + Petal.Width,
                        trainset, hidden=3)
    if(!is.null(network$result)){
        errta = errta + network$result.matrix["steps",]
        stepta = stepta + network$result.matrix["error",]
        count = count + 1
        plot(network)
    }else{
        i = i - 1
    }

}
midsta = stepta/count
mideta = errta/count

cat("confront 10 net")
cat("err mid logistic", mide)
cat("step mid logistic", mids)
cat("err mid tanh", mideta)
cat("step mid tanh", midsta)

