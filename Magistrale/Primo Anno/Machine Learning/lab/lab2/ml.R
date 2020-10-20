library(caret)

data(iris)
iris[1,"Sepal.Length"] 
Sepal.iris= iris[1:5, c("Sepal.Length", "Sepal.Width")] 



## cose statistica
m = mean(iris$Sepal.Length)
s = sd(iris$Sepal.Length)
v = var(iris$Sepal.Length)
min = min(iris$Sepal.Length)
max = max(iris$Sepal.Length)
med = median(iris$Sepal.Length) 
ran = range(iris$Sepal.Length)
qua = quantile(iris$Sepal.Length)

## applico la media ai primi 4 elementi
## la parte finale per togliere NAN
appm = sapply(iris[1:4], mean, na.rm=TRUE) 

## piechart
table.iris= table(iris$Species)
## pie(table.iris)

## istogramma
## hist(iris$Sepal.Length)

## boxplot con indicazioni su covariata (attributo/colonna) per specifica classe
## ~ species distingue tutte le classi di tipo species
## boxplot(Petal.Width ~ Species, data = iris)
## sopra setosa ho due outlier (i due puntini) per la width
## inoltre gli altri due boxplot rischiano di essere mal distinti, infatti
## rischiano di sovrapporsi, a differenza di setosa che ben si distingue
## riga nera spessa mediana, box sono primo e terzo quartile e le righe fuori
## dal box sono q1-1.5*IQR  e q3+1.5*IQR (sono intervalli di confidenza)
## detti range interquartile, IQR=range interquartile
## boxplot(Petal.Length ~ Species, data = iris) 

## scatterplot, con col coloro diversamente le specie
## la posizione è proporzionale al boxplot
## (per setosa si vedono anche gli outlier)
## plot(x=iris$Petal.Length, y=iris$Petal.Width, col=iris$Species) 

## altre info utili
dime = dim(iris)
applyc = sapply(iris, class)
h = head(iris)
lev = levels(iris$Species)

## visualizzare dataset
xv=iris[,1:4] ## spazio di input
yv=iris[,5] ## spazio di output
## faccio plot univariati, boxplot per tutte e 4 le covariate
## par(mfrow = c(1,4)) ## compatta i plot
## for(i in 1:4) {
##     boxplot(xv[,i], main=names(iris)[i])
## }

## caret
## install.packages('caret', repos='http://cran.us.r-project.org')
## featurePlot(xv, yv, plot="density", scales=list(xv=list(relation="free"),
##                                               yv=list(relation="free")),
##             auto.key=list(columns=3))

## vista super completa del problema
## tutte i grafici scatterplot le covariate
## vedo che setosa si distingue per le dimensioni del petalo
featurePlot(x=xv, y=yv, plot="pairs", auto.key=list(columns=3))
## plot target variable
#plot(yv,col=c(4,6,3))

