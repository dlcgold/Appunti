library(tm)
library(wordcloud)
library(e1071)
library(caret) 
## cerco sms spam
## importo file con stringhe che non devono diventare fattori
sms = read.csv("spam.csv", stringsAsFactors= F)
## setto il tipo come fattore
sms$type= factor(sms$type)

## costruisco un corpus, una struttura coi testi
sms_corpus = VCorpus(VectorSource(sms$text))
## print e ispezione primi tre elementi
## print(sms_corpus)
## inspect(sms_corpus[1:3]) 

## puliamo il corpus
## tutto in lower
corpus_clean = tm_map(sms_corpus, content_transformer(tolower))
## tolgo le stop word (articoli, congiunzioni etc), default inglese
## potrei mettere una lista custom di stopwords ma supporta anche l'italiano
corpus_clean = tm_map(corpus_clean, removeWords, stopwords())
## tolgo i numeri perché non significativi 
corpus_clean = tm_map(corpus_clean, removeNumbers)
## tolgo punteggiatura (si può fare solo su testi ben formati)
## se fosse un testo di social netowork dovrei studiare meglio
corpus_clean = tm_map(corpus_clean, removePunctuation)
## tolgo spazi inutili
corpus_clean = tm_map(corpus_clean, stripWhitespace)
## mappo ottenendo il plaintext
corpus_clean = tm_map(corpus_clean, PlainTextDocument)
## costruisco la matrice sparsa con righe come sms e colonne parole del corpus
## celle contengono il numero di volte che il termine compare
dtm = DocumentTermMatrix(corpus_clean)  

## separo train e test
sms.train = sms[1:4200, ] # about 75%
sms.test = sms[4201:5574, ] # the rest 
## splitto matrice
dtm.train = dtm[1:4200, ]
dtm.test = dtm[4201:5574, ] 

## splitto corpus
corpus.train = corpus_clean[1:4200]
corpus.test = corpus_clean[4201:5574] 

## creo wordcloud
## con wordcloud vedo più grandi parole frequenti
dev.new()
wordcloud(corpus.train, min.freq=40, random.order= FALSE)
dev.set(dev.cur() + 1)
dev.new()
wordcloud(corpus.test, min.freq=40, random.order= FALSE)
dev.set(dev.cur() + 1)
dev.new()
spam <- subset(sms, type == "spam")
ham <- subset(sms, type == "ham")

wordcloud(corpus.train[sms.train$type == "spam"],
          min.freq=40,
          random.order= FALSE)
dev.set(dev.cur() + 1)
dev.new()
wordcloud(corpus.train[sms.train$type == "ham"],
          min.freq=40,
          random.order= FALSE)

## riduco lo spazio
## tolgo parole che sono in meno di 5 sms
freq_terms= findFreqTerms(dtm.train, 5)
reduced_dtm.train = DocumentTermMatrix(corpus.train,
                                      list(dictionary=freq_terms))
reduced_dtm.test = DocumentTermMatrix(corpus.test,
                                     list(dictionary=freq_terms)) 

## se frequenza 0 mette 0 se >1 mette 1
## convertiamo i 1/0 a fattori yes/no
## binarizzo le osservazioni
convert_counts = function(x) {
    x = ifelse(x > 0, 1, 0)
    x = factor(x, levels = c(0, 1), labels=c("No", "Yes"))
    return (x)
}

## applichiamo la cosa alle matrici
reduced_dtm.train = apply(reduced_dtm.train, MARGIN=2, convert_counts)
reduced_dtm.test = apply(reduced_dtm.test, MARGIN=2, convert_counts)

## faccio il train con e1071
## classificatore su traion con bayes
sms_classifier = naiveBayes(reduced_dtm.train, sms.train$type)
## predizione sul test
sms_test.predicted = predict(sms_classifier, reduced_dtm.test) 
sms_test.table = table(sms_test.predicted, sms.test$type)
cm = confusionMatrix(sms_test.table)

## ## DA QUI IN POI ERRATO
## Filter(function(x) sd(x) != 0, reduced_dtm.train)
## Filter(function(x) sd(x) != 0, reduced_dtm.test)

## Filter(function(x) -2*deviance(x) < sd(x) && sd(x) < 2*deviance(x),
##        reduced_dtm.train)
## Filter(function(x) -2*deviance(x) < sd(x) && sd(x)< 2*deviance(x),
##        reduced_dtm.test)
## reduced_dtm.train = apply(reduced_dtm.train, MARGIN=2, convert_counts)
## reduced_dtm.test = apply(reduced_dtm.test, MARGIN=2, convert_counts)
## sms_classifiers = naiveBayes(reduced_dtm.train, sms.train$type)
## ## predizione sul test
## sms_test.predicteds = predict(sms_classifiers, reduced_dtm.test) 
## sms_test.tableS = table(sms_test.predicteds, sms.test$type)
## cms = confusionMatrix(sms_test.tableS)
