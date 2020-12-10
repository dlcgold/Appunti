library(cluster)
library(fpc)
library(seriation)
library(factoextra)
library(TSclust)

## carichiamo i dati
customer = read.csv('customer.csv', header=TRUE)
## scaliamo tutti i dati sulla stessa unità
customer = scale(customer[,-1])

## kmeans su 4 cluster con seed a 22 per generare i centroidi iniziali
set.seed(22)
fit = kmeans(customer, 2)
## ora so i centroidi e ogni istanza a quale cluster appartiene
## so anche la distanza media interna di ogni cluster, più è bassa e più
## i vari punti sono vicini

## vedo numero di iterazioni fino a convergenza
iter = fit$iter
 
## numero di valori di ogni cluster
size = fit$size

## bisogna capire il numero di cluster ideale
## uso la silhouette usando dist() per la distanza
## silhouette negativa ci dice che devo cambiare numero di cluster
## aggiungendone o togliendone
kms = silhouette(fit$cluster, dist(customer))
## un poco di plot
dev.new()
plot(kms)

dev.set(dev.cur() + 1)
dev.new()
barplot(t(fit$centers), beside = TRUE, xlab="cluster", ylab = "value")

dev.set(dev.cur() + 1)
dev.new()
plot(customer, col = fit$cluster)

## sfrutto silhouette per trovare il K migliore di cluster
## in nk numeri da 2 a 10 per testare vari K
nk = 2:10
## setto il seed
set.seed(22)

## in SW calcolo la silhouette media per ogni K cluster
SW = sapply(nk, function(k) {
    cluster.stats(dist(customer),
                  kmeans(customer, centers=k)$cluster)$avg.silwidth
})

dev.set(dev.cur() + 1)
dev.new()
plot(nk, SW, type="l", xlab="numberof clusers", ylab="averagesilhouette")

dev.set(dev.cur() + 1)
dev.new()
dissplot(dist(customer),
         labels=fit$cluster,
         options=list(main="KmeansClustering With k=4"))

bestK = 0;
maxcurr = 0;
for(i in nk){
    if(SW[i-1] >= maxcurr){
        bestK = i;
        maxcurr = SW[i-1]
    } 
}

set.seed(22)
fitB = kmeans(customer, 4)
## ora so i centroidi e ogni istanza a quale cluster appartiene
## so anche la distanza media interna di ogni cluster, più è bassa e più
## i vari punti sono vicini

## vedo numero di iterazioni fino a convergenza
iterB = fitB$iter
 
## numero di valori di ogni cluster
sizeB = fitB$size

## bisogna capire il numero di cluster ideale
## uso la silhouette usando dist() per la distanza
## silhouette negativa ci dice che devo cambiare numero di cluster
## aggiungendone o togliendone
kmsB = silhouette(fitB$cluster, dist(customer))
## un poco di plot
dev.set(dev.cur() + 1)
dev.new()
plot(kmsB)

## assignment
data(iris)
irisT = iris
iris = iris[, -5]
iris = scale(iris)

set.seed(22)
fitI = kmeans(iris, 4)
iterI = fitI$iter
sizeI = fitI$size
kmsI = silhouette(fitI$cluster, dist(iris))
dev.set(dev.cur() + 1)
dev.new()
plot(kmsI)
SWI = sapply(nk, function(k) {
    cluster.stats(dist(iris),
                  kmeans(iris, centers=k)$cluster)$avg.silwidth
})

bestKI = 0;
maxcurrI = 0;
for(i in nk){
    if(SWI[i-1] >= maxcurrI){
        bestKI = i;
        maxcurrI = SWI[i-1]
    } 
}
fitIB = kmeans(iris, bestKI)
iterIB = fitI$iter
sizeIB = fitI$size
kmsIB = silhouette(fitIB$cluster, dist(iris))
dev.set(dev.cur() + 1)
dev.new()
plot(nk, SWI, type="l", xlab="numberof clusers", ylab="averagesilhouette")
dev.set(dev.cur() + 1)
dev.new()
plot(kmsIB)
cluster.evaluation(irisT$Species, fitIB$cluster)
cluster.evaluation(irisT$Species, fitI$cluster)

## dev.set(dev.cur() + 1)
## dev.new()
## clu = fitIB$cluster
## fviz_cluster(clu, iris[, -5])

