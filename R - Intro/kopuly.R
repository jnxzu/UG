library(ggplot2)
library(copula)
library(VineCopula)


#Przyklad 5 (kopula gaussowska w zastosowaniu)
#==========
#Problem: Jakich ekstremalnych wartosci mozna sie spodziewac 
#w  przypadku Z=2X1+3X2? (precyzyjnie dalej)

#Na postawiony problem mamy odpowiedziec wykorzystujac dane.
load("C:\\Users\\xsero\\Desktop\\Projekty\\UG\\R - Intro\\data\\dailyWind.Rdata")
wind = daily
load("C:\\Users\\xsero\\Desktop\\Projekty\\UG\\R - Intro\\data\\dailyTemp.Rdata")
temp = daily
wind = wind[1:nrow(temp),]

data = cbind(temp, wind)
colnames(data) = c('date', 'rm1', 'temp', 'rm2', 'rm3', 'wind')
data = data[,c('date','temp','wind')]
data = data[data$temp < 45,]
data = data[data$wind < 45,]
data = data[!is.na(data$temp),]
data = data[!is.na(data$wind),]

X = data$temp
Y = data$wind

#obejrzyjmy dane
plot(X,Y)
hist(X,prob=T)
hist(Y,prob=T)

#Krok 1 
#Tworzymy pseudo-obserwacje.

#b) podejscie nieparametryczne (skladamy z dystrybuantami empirycznymi) - korzystamy z gotowego rozwiazania w bibliotece 'copula'
library(copula)
Ub <- pobs(data[,c('temp', 'wind')])
head(Ub)

#Ogladamy pseudo-obserwacje i decydujemy sie na ktoras z rodzin kopul
plot(X,Y)
plot(Ub)


#Krok 2. 
#Estymujemy parametry kopu³y


#w obydwu przypadkach wybieramy kopule Gaussa, estymujemy parametry  
fitb=fitCopula(copula=normalCopula(), data=Ub)
fitb

#dla porownania wspolczynnik korelacji Pearsona z wyjsciowej proby
cor(X,Y)

#wykresy: kopula i jej gestosc 
persp(normalCopula(dim=2,fitb@estimate),pCopula, col=8)
persp(normalCopula(dim=2,fitb@estimate),dCopula, col=8)

#Krok 3 
#Korzystaj¹c z kopu³y i rozk³adów brzegowych (wyestymowanych  rozkladów lub dystrybuant empirycznych) generujemy du¿¹ próbê z rozkladu wektora $(X,Y)$.

#Generujemy dwie proby licznosci n=10000 z otrzymanych kopul
sampb=rCopula(copula=normalCopula(fitb@estimate),n=10000)

plot(sampb)

ub=sampb[,1]; vb=sampb[,2]


#Z pdeudo-obserwacji 'wracamy' do obserwacji, obliczamy kwantyle 
#x1, x2 rzedu u1, u2 wykorzystujac
#a)   wyestymowane rozklady brzegowe: x1=F^-1(u1), x2=G^-1(u2),
#b) wykorzystujemy estymator empiryczny (quantile()) dla kwantyli z proby

#Mamy w ten sbosob dwie duze proby (n=10000) z rozkladu wektora (X1,X2)
#b)
xb <- quantile(X,ub)
yb <- quantile(Y,vb)

#Dla porownania wykresy rozrzutu dla posiadanej proby wyjsciowej (X,Y)
#oraz prob (xa,ya), (xb,yb)
plot(X,Y); plot(xb,yb)


#Krok 4
#Wykorzystujemy wygenerowan¹ próbê do analizy zmiennej Z = h(X,Y).

#korzystajac z nowych prob wyznaczamy  wartosci  zmiennej Z=2X+3Y
#b) metoda nieparametryczna 
Zb <- 2*xb+3*yb

#Majac tak duza probke z rozkladu zmiennej Z, mozemy obliczaæ interesujace nas wartosci
#Porownamy kwantyle rzedu 5% i 95% otrzymane z modelu parametrycznego i nieparametrycznego.
quantile(Zb,0.05)
quantile(Zb,0.95)

#Wyniki na histogramach
hist(Zb,prob=T)
abline(v=quantile(Zb,0.05),col=2,lwd=2)
abline(v=quantile(Zb,0.95),col='blue',lwd=2)

#Gotowe rozwiazanie w bibliotece ,,VineCopula''.

library(VineCopula)
ub = Ub[,1]
vb = Ub[,2]

#,,najlepsza'' kopula z zaimplementowanej rodziny kopul
cop.b <- BiCopSelect(ub,vb)
cop.b; cop.b$family
