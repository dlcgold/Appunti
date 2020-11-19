## lib per plot zarri
library("ggplot2")
library(rgl)
## genero un semplice dataset con una funzione
## n = numero di elementi
## dim = dimensione
## threshold = soglia per capire se positivo/negativo
### capire che fanno le varie funzioni
### runif gener valori random
Random.Unit = function(n, dim, threshold) {
    points <- runif(n * dim)
    points <- matrix(points, ncol = dim)
    label <- ifelse(apply(points, 1, sum) < threshold, -1, 1)
    return(cbind(label, x0 = rep(1, n), points))
}

## creo dataset
dataset = Random.Unit(1000, 2, 0.75)

## visualizzo il dataset con ggplot2
plot = qplot(dataset[,3],
      dataset[,4],
      colour= factor(dataset[,1]),
      xlab="V3", ylab="V4") + labs(colour= 'class')

## per ogni istanza ho features e pesi
Classify = function(x, weights){
    return(sign(x %*% weights))
}

## algoritmo percettrone
Perceptron.calc = function(data, threshold){
    w = c(-threshold, runif(ncol(data) - 2))
    n = nrow(data)
    ## isolo label
    label = data[, 1]
    ## isolo il resto per poi aggiornare pesi
    obs = data[, 2:ncol(data)]
    convergence = FALSE
    while(!convergence){
        convergence = TRUE
        for (i in 1:n){
            ## confronto la la lebel {-1,1} con la classificazione {-1,0,1}
            ## classificazione ottenuta coi pesi attuali
            ## se prodotto negativo errore
            if (label[i] * Classify(obs[i, ], w) <= 0){
                ## aggiorno pesi
                w = w + label[i] * obs[i, ]
                ## dico che non sto convergendo per continuare a ciclare
                convergence = FALSE
            }  
        } 
    }
    return(w)
}

                                        # cose random da github
## si plotttaaaa!
Plot3D <- function(points, a, b, c, d) {
    plot3d(points[, 3:5], xlab = "X", ylab = "Y", zlab = "Z",
           pch = ifelse(points[, 1] == 1, 2, 8),
           col = ifelse(points[, 1] == 1, "blue", "red"))
    planes3d(a, b, c, d)
}

## Plot2D plots the result of a call to Random.Unit with dim = 2 as well
## as the line parameterized by y = bx + a, as in the call to abline.
Plot2D <- function(points, a, b) {
    plot(points[, 3:4], xlab = "X", ylab = "Y",
         pch = ifelse(points[, 1] == 1, 2, 8),
         col = ifelse(points[, 1] == 1, "blue", "red"))
    abline(a, b)
}

THRESHOLD <- 1.5
pts <- Random.Unit(1000, 3, THRESHOLD)
Plot3D(pts, 1, 1, 1, -THRESHOLD)
w <- Perceptron.calc(pts, THRESHOLD)
Plot3D(pts, w[4], w[3], w[2], w[1])

THRESHOLD <- 0.75
pts <- Random.Unit(1000, 2, THRESHOLD)
Plot2D(pts, THRESHOLD, -1)
w <- Perceptron.calc(pts, THRESHOLD)
Plot2D(pts, -w[1]/w[3], -w[2]/ w[3])

## aggiungo sigmpoide con check su 1/2 avendo comunque solo {0,1}
## per ora loop
## PerceptronSigm.calc = function(data, threshold, eta){
##     max = 10000
##     w = c(-threshold, runif(ncol(data) - 2))
##     n = nrow(data)
##     ## isolo label
##     label = data[, 1]
##     ## isolo il resto per poi aggiornare pesi
##     obs = data[, 2:ncol(data)]
##     convergence = FALSE
##     count = 0
##     while(!convergence){
##         convergence = TRUE
##         for (i in 1:n){
##             ## confronto la la lebel {-1,1} con la classificazione {-1,0,1}
##             ## classificazione ottenuta coi pesi attuali
##             ## se prodotto negativo errore
##             if (eta * label[i] * Classify(obs[i, ], w) <= 0.5){
##                 ## aggiorno pesi
##                 w = w + eta * (label[i]-obs[i,]) * obs[i, ]
##                 ## dico che non sto convergendo per continuare a ciclare
##                 convergence = FALSE
##             }  
##         }
##         count = count + 1
##         if (count == max)
##             break
##     }
##     return(w)
## }
## THRESHOLD <- 0.75
## pts <- Random.Unit(1000, 2, THRESHOLD)
## Plot2D(pts, THRESHOLD, -1)
## w <- PerceptronSigm.calc(pts, THRESHOLD, 0.4)
## Plot2D(pts, -w[1]/w[3], -w[2]/ w[3])
