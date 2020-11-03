## lab 4
## load csv (NA for treat the blank string as NA,
## tratto gli spazi vuori come dati non disponibili)
dataset = read.csv("titanic.csv", na.strings=c("NA", ""))
## test stampa
## titanic.str = str(dataset)

## come target uso variabile survived, trattata come intero (1 si 0 no)
## test stampa
## str(dataset)

## non considero ad esempio Name perché non ci porta informazione
## inoltre ha tutti valori diversi e quindi per il ML è inutile,
## essendo invariante (l variabile va castata a factor), così come il ticket
## tengo solo attributi discriminanti
## teniamo la cabina quando diversa da NA (visto che il dato mancante l'ho reso
## NA) e la classe del viaggiatore
## casto l'utile a factor
## survived è a due livelli Pcalss a 3

## sistemo per come ha detto la faibene
dataset$Survived = factor(dataset$Survived)
dataset$Pclass = factor(dataset$Pclass)
dataset$Name = factor(dataset$Name)
dataset$Sex = factor(dataset$Sex)
dataset$Cabin = factor(dataset$Cabin)
dataset$Ticket = factor(dataset$Ticket)
dataset$Embarked = factor(dataset$Embarked)

## un po di plot
## morti sopravvissuti
barplot(table(dataset$Survived), main="Passenger Survival",
        names= c("Perished", "Survived"))

## distribuzione maschi femmine
barplot(table(dataset$Sex), main="Passenger Gender")

## distribuzione classi
barplot(table(dataset$Pclass), main="Passenger Class")

## distribuzione età
hist(dataset$Age, main="Passenger Age", xlab = "Age")

## vedo morti/vivi per sesso
## muoiono meno donne
## classifica meglio le istanze
countsS = table(dataset$Survived, dataset$Sex)
barplot(countsS, col=c("darkgreen","red"),
        legend = c("Perished", "Survived"),
        main = "Passenger Survival by Sex")

## vedo morti/vivi per classe
## muoiono più in terza classe
countsP = table(dataset$Survived, dataset$Pclass)
barplot(countsP, col=c("darkgreen","red"),
        legend = c("Perished", "Survived"),
        main = "Passenger Survival by Pclass")

## età più significativa di classe

## divido training e testing set
## funzione che prende in input dataset, percentuale nel training (default 0.7)
## ed eventuale seed per random (default 1). Ritorna due liste, trin e set
split.data = function(data, p = 0.7, s = 1){
    set.seed(s)
    index = sample(1:dim(data)[1])
    train = data[index[1:floor(dim(data)[1] * p)], ]
    test = data[index[((ceiling(dim(data)[1] * p)) + 1):dim(data)[1]], ]
    return(list(train=train, test=test))
}
## effettuo lo split coi valori di default (training 70%)
allset = split.data(dataset)
## training set
trainset = allset$train
## testing set
testset = allset$test

## studio training

## vedo percentuali sopravvissuti 38% vivo 
stable = table(trainset$Survived)
sptable = prop.table(stable)

## testset basico su classe di maggioranza
## definisco modello con istanza che la persona muore (scelgo per maggioranza)
## parto quindi con tutti morti nel modello baseline
## creo quindi una colonna Prediction con tutti 0
## e lo fattorizzo, solo per testset
testset$Prediction = rep(0, nrow(testset))
testset$Prediction = factor(testset$Prediction)

## faccio matrice di confusione contanto veri e flasi positivi e nagativi
## veri positivi | falsi negativi
## flasi negativi | veri negativi
confusion.matrix = table(testset$Survived, testset$Prediction)

## calcolo accuratezza
accurancy = sum(diag(confusion.matrix))/sum(confusion.matrix)

## faccio una baseline più furba
## baso sesso e sopravvissuti
btable = prop.table(table(trainset$Sex, trainset$Survived),1)

## aggiorno prediction
testset$Prediction = 0
testset$Prediction[testset$Sex == 'female'] = 1
testset$Prediction = factor(testset$Prediction)
## e matrice/accuratezza
confusion.matrix = table(testset$Survived, testset$Prediction)
accurancy = sum(diag(confusion.matrix))/sum(confusion.matrix)


## passo agli alberi di decisione
library(rpart)

## creo albero con rpart
## uso variabile survived come target, espressa con ~., ovvero in funzione di
## tutte le altre istanze
## dati dal trainset
decisionTree = rpart(Survived ~ ., data=trainset, method="class")

## uso un subset di instaze per avere un risultato utile
decisionTreeS = rpart(Survived ~ Pclass + Sex + Age + SibSp + Parch
                     + Fare + Embarked, data=trainset, method="class")

## visualizzo l'albero con text per le label
plot(decisionTreeS)
text(decisionTreeS)

## ma vogliamo una vista più bella
library(rattle)
library(rpart.plot)
library(RColorBrewer)

## visualizzo in modo zarro, cex per grandezza scritta etichette
## nei nodi informazone sulle distribuzioni
## parte dalla variabile più discriminativa: sex
fancyRpartPlot(decisionTreeS, cex=0.9)

## uso l'albero per predizioni sub testset
testset$Prediction = predict(decisionTreeS, testset, type = "class")
## rifaccio matrice e accuratezza
confusion.matrix = table(testset$Survived, testset$Prediction)
accurancy = sum(diag(confusion.matrix))/sum(confusion.matrix)


## altre info utili su albero
## sommario informazioni
## summaryT = summary(decisionTreeS)

## ## the complexity parameter (cp) is the minimum improvement in the model
## ## needed at each node.
## ## valori parametro di complessità (definizione su slide)
## cpT = printcp(decisionTreeS)

## ## plot parametro complessità
## cpTp = plotcp(decisionTreeS)

## taglio albero lasciando complessità quasi invariata ma con meno split
## si evita quindi overfitting
## cerco di capire cp
## vedo grafico
plotcp(decisionTreeS)

###decisionTreeScp = decisionTreeS.cp[which.min(decisionTreeS.cp[,
###"rel error"]),"CP"]


## in general (and considering parsimony) you should prefer the smaller tree
## from those with minimum xerror value, this is, any of those whose xerror
## value is within [min(xerror) - xstd; min(xerror) + xstd].

## According to rpart vignette: "Any risk within one standard error of the
##achieved minimum is marked as being equivalent to the minimum (i.e.
## considered to be part of the flat plateau). Then the simplest model, among
## all those “tied” on the plateau, is chosen."

## See: https://stackoverflow.com/a/15318542/2052738

## You can select the most appropriate cp value (to prune the initial your.tree,
## overfitted with rpart) with an ad-hoc function such as:

## considero anche deviiazone standard
cp.select <- function(big.tree) {
    min.x <- which.min(big.tree$cptable[, "xerror"]) 
    for(i in 1:nrow(big.tree$cptable)) {
        if(big.tree$cptable[i, "xerror"] < big.tree$cptable[min.x, "xerror"]
           + big.tree$cptable[min.x, "xstd"])
            return(big.tree$cptable[i, "CP"]) 
    }
}

## in wiki dice di prendere il più a sinistra sotto la barra
## sceglio il valore relativo minimo
prunedDecisionTree = prune(decisionTreeS, cp = cp.select(decisionTreeS))
fancyRpartPlot(prunedDecisionTree)

prunedDecisionTree2 = prune(decisionTreeS, cp = 0.016)
fancyRpartPlot(prunedDecisionTree2)

## vedo nuova accuratezza
testset$Prediction = predict(prunedDecisionTree, testset, type = "class")
## rifaccio matrice e accuratezza
confusion.matrix = table(testset$Survived, testset$Prediction)
accurancyP = sum(diag(confusion.matrix))/sum(confusion.matrix)

testset$Prediction = predict(prunedDecisionTree2, testset, type = "class")
## rifaccio matrice e accuratezza
confusion.matrix = table(testset$Survived, testset$Prediction)
accurancyP2 = sum(diag(confusion.matrix))/sum(confusion.matrix)
## vedo che l'accuratezza dei due alberi è quasi uguale 0.8277154 vs 0.82397
## più nodi ho più complessità ho
## quindi uso l'albero tagliato, riduco overfitting e complessità
## ma ho pari performance sul risultato

## assignment uso information gain
decisionTreeIG = rpart(Survived ~ Pclass + Sex + Age + SibSp +
                           Parch + Fare + Embarked,
                       data=trainset,
                       method="class",
                       parms = list(split = 'information'))

testset$Prediction = predict(decisionTreeIG, testset, type = "class")
confusion.matrix = table(testset$Survived, testset$Prediction)
accurancyIG = sum(diag(confusion.matrix))/sum(confusion.matrix)

cat("accurancy confront\n")
cat("accurancy", accurancy, "\n")
cat("accurancyP", accurancyP, "\n")
cat("accurancyP2", accurancyP2, "\n")
cat("accurancyIG", accurancyIG, "\n")


