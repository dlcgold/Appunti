library("e1071")

## carico dataset e divido in training e test set
ind = sample(2,
             nrow(iris),
             replace= TRUE,
             prob=c(0.7, 0.3))

testset = iris[ind == 2,] 
trainset = iris[ind == 1,]

## train del modello con kernel lineare e costo 1
svm.modelT = svm(Species~ .,
                data = trainset,
                kernel = 'linear',
                cost = 1)

## mi creo il subset solo con setosa e virginica per sepal
iris.subset = subset(trainset,
                    select=c("Sepal.Length", "Sepal.Width", "Species"),
                    Species%in% c("setosa","virginica"))

## plotto il subset con misure sepal per setosa e virginica
plot(x = iris.subset$Sepal.Length,
     y = iris.subset$Sepal.Width,
     col = iris.subset$Species,
     pch=19)

## train del modello per il subset, kernel lineare, costo 1
svm.model= svm(Species~ .,
               data = iris.subset,
               kernel = 'linear',
               cost = 1,
               scale = FALSE) 

## nel grafico cerchi blu su svm
points(iris.subset[svm.model$index, c(1, 2)],
       col = "blue",
       cex = 2)

## aggiungo linea (l'ipepriano)
w = t(svm.model$coefs) %*% svm.model$SV
c = -svm.model$rho
abline(a = -c/w[1, 2],
       b = -w[1, 1]/w[1, 2],
       col = "red",
       lty = 5)

## si aggiungono anche i margini
abline(a = (-c-1)/w[1,2],
       b = -w[1,1]/w[1,2],
       col = "orange",
       lty = 3)
abline(a = (-c+1)/w[1,2],
       b = -w[1,1]/w[1,2],
       col = "orange",
       lty = 3)

## proviamo con un altro costo, molto alto dicendo di essere perfetta
## ma si rischia overfitting, essendo perfetta sui dati di training
## reset del plot
plot(x = iris.subset$Sepal.Length,
     y = iris.subset$Sepal.Width,
     col = iris.subset$Species,
     pch = 19)

## macchina con costo 10000
svm.model = svm(Species~ .,
                data = iris.subset,
                kernel = 'linear',
                cost = 10000,
                scale = FALSE) 

## punti blu per vettori
points(iris.subset[svm.model$index, c(1, 2)],
       col = "blue",
       cex = 2)

## linea separazione e margini
w = t(svm.model$coefs) %*% svm.model$SV
c = -svm.model$rho
abline(a=-c/w[1, 2],b=-w[1, 1]/w[1, 2],
       col = "red",
       lty = 5)
abline(a = (-c-1)/w[1, 2],
       b = -w[1, 1]/w[1, 2],
       col = "orange",
       lty = 3)
abline(a = (-c+1)/w[1, 2], b = -w[1, 1]/w[1, 2],
       col = "orange",
       lty = 3)


## per fare predizioni
svm.pred = predict(svm.model,
                  testset)

## creando matrice di confusione
svm.table = table(svm.pred,
                testset$Species)

## svm con tuning automatico su un vettore di costi con kernel linerae
tuned= tune.svm(Species~ .,
                data = iris.subset,
                kernel = 'linear',
                cost = c(0.001, 0.01, 0.1, 1,5,10,100))
