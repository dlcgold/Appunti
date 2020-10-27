## TUTTO MOLTO A CASO
## tutto da rifare
## cose a caso
## lasciate ogni speranze voi che leggete
## avete di sicuro di meglio da fare

library("FactoMineR")
library("factoextra")
library("dplyr")  

data(iris)

row_calc = nrow(iris) / 1
iris.active <- iris[sample(nrow(iris), row_calc), 1:4]

res.pca <- PCA(iris.active, graph = FALSE)
eig.val <- get_eigenvalue(res.pca)
ploteig <- fviz_eig(res.pca, addlabels = TRUE, ylim = c(0, 50))

plot.ind <- fviz_pca_ind(res.pca,
             geom.ind = "point",
             palette = c("#00AFBB", "#E7B800", "#FC4E07"),
             addEllipses = TRUE,
             legend.title = "groups")

plot.cos <- fviz_pca_var(res.pca, select.var = list(cos2=0.6))

name <- list(name = c("Sepal.Length",
                      "Sepal.Width",
                      "Petal.Length",
                      "Petal.Width"))

plot.var <- fviz_pca_var(res.pca, select.var = name)
plot.bi <- fviz_pca_biplot(res.pca,
                select.ind = list(contrib = 5),
                select.var = list(contrib = 5),
                ggtheme = theme_minimal())
