#Przyklad 5 (kopula gaussowska w zastosowaniu)
#==========
#Problem: Jakich ekstremalnych wartosci mozna sie spodziewac 
#w  przypadku Z=2X1+3X2? (precyzyjnie dalej)

#Na postawiony problem mamy odpowiedziec wykorzystujac dane.
Dane <- read.csv2("~/Documents/0InstInformatyki/2021MZEwR/10Copula/dane.csv")
head(Dane)

X=Dane$X1
Y=Dane$X2

#obejrzyjmy dane
plot(X,Y)
hist(X,prob=T)
hist(Y,prob=T)

#Krok 1 
Tworzymy pseudo-obserwacje.

#a) podejscie parametryczne - "dopasowujemy najlepszy"  rozklad do danych brzegowych
#np. po obejrzeniu histogramow decydujemy sie na rozklad wykladniczy i normalny 
library(MASS)
fit.exp=fitdistr(X,"exponential")
fit.norm=fitdistr(Y,"normal")

#parametry rozkladow
lambda=round(fit.exp$estimate,2)
par=round(fit.norm$estimate,2)
lambda; par

#obejrzyjmy histogramy i gestosci
hist(X,prob=T,col='grey')
curve(dexp(x,fit.exp$estimate[[1]]),
      col=2,lwd=2,add=T)
hist(Y,prob=T,col='grey')
curve(dnorm(x,fit.norm$estimate[[1]],fit.norm$estimate[[2]]),
      col=2,lwd=2,add=T)

#pseudo-obserwacje (przeksztalcamy dane wykorzystujac dystrybuanty wyestymowanych rozkladow)
Ua <- cbind(pexp(X,lambda),pnorm(Y,par[[1]],par[[2]]))
head(Ua)

#b) podejscie nieparametryczne (skladamy z dystrybuantami empirycznymi) - korzystamy z gotowego rozwiazania w bibliotece 'copula'
library(copula)
Ub <- pobs(Dane)
head(Ub)

#Ogladamy pseudo-obserwacje i decydujemy sie na ktoras z rodzin kopul
plot(X,Y)
plot(Ua)
plot(Ub)


#Krok 2. 
#Estymujemy parametry kopu³y


#w obydwu przypadkach wybieramy kopule Gaussa, estymujemy parametry  
fita=fitCopula(copula=normalCopula(), data=Ua)
fitb=fitCopula(copula=normalCopula(), data=Ub)
fita
fitb

#dla porownania wspolczynnik korelacji Pearsona z wyjsciowej proby
cor(X,Y)

#wykresy: kopula i jej gestosc 
persp(normalCopula(dim=2,fita@estimate),pCopula, col=8)
persp(normalCopula(dim=2,fita@estimate),dCopula, col=8)

persp(normalCopula(dim=2,fitb@estimate),pCopula, col=8)
persp(normalCopula(dim=2,fitb@estimate),dCopula, col=8)

#Krok 3 
#Korzystaj¹c z kopu³y i rozk³adów brzegowych (wyestymowanych  rozkladów lub dystrybuant empirycznych) generujemy du¿¹ próbê z rozkladu wektora $(X,Y)$.

#Generujemy dwie proby licznosci n=10000 z otrzymanych kopul
sampa=rCopula(copula=normalCopula(fita@estimate),n=10000)
sampb=rCopula(copula=normalCopula(fitb@estimate),n=10000)

head(sampa)

plot(sampa)
plot(sampb)

ua=sampa[,1]; va=sampa[,2]
ub=sampb[,1]; vb=sampb[,2]


#Z pdeudo-obserwacji 'wracamy' do obserwacji, obliczamy kwantyle 
#x1, x2 rzedu u1, u2 wykorzystujac
#a)   wyestymowane rozklady brzegowe: x1=F^-1(u1), x2=G^-1(u2),
#b) wykorzystujemy estymator empiryczny (quantile()) dla kwantyli z proby

#Mamy w ten sbosob dwie duze proby (n=10000) z rozkladu wektora (X1,X2)
#a) 
xa = qexp(ua,lambda)
ya = qnorm(va,par[[1]],par[[2]])
#b)
xb <- quantile(X,ub)
yb <- quantile(Y,vb)

#Dla porownania wykresy rozrzutu dla posiadanej proby wyjsciowej (X,Y)
#oraz prob (xa,ya), (xb,yb)
plot(X,Y); plot(xa,ya); plot(xb,yb)


#Krok 4
#Wykorzystujemy wygenerowan¹ próbê do analizy zmiennej Z = h(X,Y).

#korzystajac z nowych prob wyznaczamy  wartosci  zmiennej Z=2X+3Y
#a)metoda parametryczna
Za <- 2*xa+3*ya
#b) metoda nieparametryczna 
Zb <- 2*xb+3*yb

#Majac tak duza probke z rozkladu zmiennej Z, mozemy obliczaæ interesujace nas wartosci
#Porownamy kwantyle rzedu 5% i 95% otrzymane z modelu parametrycznego i nieparametrycznego.
quantile(Za,0.05); quantile(Zb,0.05)
quantile(Za,0.95); quantile(Zb,0.95)

#Wyniki na histogramach
hist(Za,prob=T)
abline(v=quantile(Za,0.05),col=2,lwd=2)
abline(v=quantile(Za,0.95),col='blue',lwd=2)

hist(Zb,prob=T)
abline(v=quantile(Zb,0.05),col=2,lwd=2)
abline(v=quantile(Zb,0.95),col='blue',lwd=2)

#Gotowe rozwiazanie w bibliotece ,,VineCopula''.

library(VineCopula)
ua = Ua[,1]
va = Ua[,2]

ub = Ub[,1]
vb = Ub[,2]

#,,najlepsza'' kopula z zaimplementowanej rodziny kopul
cop.a <- BiCopSelect(ua,va)
cop.b <- BiCopSelect(ub,vb)
cop.a; cop.b