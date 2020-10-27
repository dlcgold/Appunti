## usiamo la tecncia di riduzione absata su PCA
## PCA = Principal COmponent Analysis
## si usa questa tecnica per togliere gli elementi che
## non sono informativi (10 covariate sono meglio di 100000
## di cui solo 10 sono significative)
## si riduce anche la complessità temporale
## è una tecnica di trasformazione lineare
## vedere slides per dettagli


#includo le lib
library("FactoMineR")
library("factoextra")

## uso il dataset decathlon2 contenuto in factoextra
data(decathlon2)
head(decathlon2)

## prendiamo un subset
decathlon2.active <- decathlon2[1:23, 1:10]
head(decathlon2.active[, 1:6], 4)

## info su slides, non stampiamo il grafo perché usiamo un'altra lib
res.pca <- PCA(decathlon2.active, graph = FALSE)

## calcolo autovalori
## informazioni su slide
eig.val <- get_eigenvalue(res.pca)

## stampo autovalori in grafico, scree plot
screeplot <- fviz_eig(res.pca, addlabels = TRUE, ylim = c(0, 50))

## salvo informazioni per interpretare risultati pca con 4 componenti
## ma $cor non ci interessa
var <- get_pca_var(res.pca)

cat("\n")
## setto nuove coordinate 
head(var$coord, 4)

## plot variable
var.plot <- fviz_pca_var(res.pca, col.var = "black")

## salvo informazioni per individui
ind <- get_pca_ind(res.pca)

## plot individui
ind.plot <- fviz_pca_ind(res.pca)

## plot individui più bello, più arancione meglio è per cos2
ind.plotB <- fviz_pca_ind(res.pca, col.ind = "cos2",
                          gradient.cols = c("#00AFBB", "#E7B800", "#FC4E07"),
                          repel = TRUE)


