library(evir)
#--------------- estymacja par. GEV w bibliotece evir
load("C:\\Users\\xsero\\Desktop\\Projekty\\UG\\R - Intro 2\\10min.Rdata")

data <- full10min$X350150510[!is.na(full10min$X350150510)]
  
# dla uproszczenia przyjmijmy ze w kazdym miesiacu jest  6*24*30 obserwacji
b <- 6*24*30
fit <- evir::gev(data, b)  

#parametry rozkladu GEV
parGEV <- fit$par.ests; parGEV

#wyniki dla Leborka (porownaj z wynikami dla Twojej stacji)
#       xi      sigma         mu 
#-0.5817646  9.5194353 18.8392023 

#maksima z blokow (w przyblizeniu miesieczne)
Max <- fit$data

#------------------ ponowna estymacja w bibliotece ismev 
#po to by wykorzystac  zaimplementowane tam wykresy diagnostyczne
fit2 <- ismev::gev.fit(Max)

#wykresy diagnostyczne 
ismev::gev.diag(fit2)

#------------------ ponowna estymacja  w bibliotece   fExtremes
#beda inne wykresy diagnostyczne
fit3 <- fExtremes::gevFit(data,b)

#wykresy diagnostyczne - inny pomysl opisany nizej
summary(fit3)
