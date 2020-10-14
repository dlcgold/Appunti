library(e1071)

a = 10
v = c(1, 3, 5, 7)
i = c(1L, 6L, 10L)
l = list(v, i, c("c","i","ao"), TRUE)
m = matrix(c(1:16), nrow = 4, ncol = 4, byrow = TRUE)
factor_data = factor(v)
std_id=c(1:5)
std_name=c("Rick","Dan","Michelle","Ryan","Gary")
marks=c(623.3,515.2,611.0,729.0,843.25)
std.data<-data.frame(std_id,std_name,marks)

