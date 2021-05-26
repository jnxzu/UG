library(ggplot2)
library(copula)
library(VineCopula)



#Przyk³ad 1
#=============
#Generujemy   proby licznosci n=1000 z rozkladu N(0,1) 
set.seed(15)
x1=rnorm(1000)
y1=rnorm(1000)

#tworzymy nowe proby z rozkladu N(0,1), ale zalezne
x2 <- 0.6*x1+0.8*y1
y2 <-  0.8*x1+0.6*y1

x3 <- 0.6*x1+0.8*y1
y3 <- 0.28*x1-0.96*y1

X <- data.frame(x1=x1,y1=y1,x2=x2,y2=y2,x3=x3,y3=y3)

ggplot(X, aes(x=x1, y=y1))+ geom_point()
ggplot(X, aes(x=x2, y=y2))+ geom_point()
ggplot(X, aes(x=x3, y=y3))+ geom_point()

#wspolczynniki korelacji
cor(x1,y1); cor(x2,y2); cor(x3,y3)

#Przyklad 2
#==========
#Generujemy probe licznosci 1000 z rozkladu normalnego dwuwymiarowego N(0,0,1,1,0.9)

library(MASS)
#wektor srednich 
mu <- c(0,0)

#macierz kowariancji 
#gdy wariancje rowne sa 1,  macierz kowariancji = macierz korelacji 
sigma <- matrix(c(1, 0.9, 
                  0.9, 1), 
                nrow=2)  

#generujemy probe licznosci n
n <- 1000

set.seed(100)
X <- MASS::mvrnorm(n,mu=mu,Sigma=sigma)

#proby z rozkladow brzegowych (zauwaz, ze sa to rozklady N(0,1))
X1 <- X[,1]
X2 <- X[,2]
hist(X1,prob=T); hist(X2,prob=T)

#skladamy X1, X2 z dystrybuantami brzegowymi
FX1 <- pnorm(X1)
FX2 <- pnorm(X2)
hist(FX1,prob=T); hist(FX2,prob=T)

#Przyklad 2 cd.
#===========
#wykresy rozrzutu
plot(X1,X2)
plot(FX1,FX2)

#Przyklad 3. Generowanie z kopuly gaussowskiej
#===========
library('copula')

par=seq(-1,1,by=0.2)  #rho
for(rho in par){
  un <- rCopula(copula=normalCopula(rho), n=1000)
  plot(un)
  text(0.3,0, rho, col=2)
}

#Przyklad 4
#===========
#Generujemy proby z rozklau N(0,0,1,s2,0.9) licznosci n=1000
#dla roznych wartoœci odchylenia standardowego s2.
#Przeksztalcamy je poprzez dystrybuanty brzegowe.

#wektor srednich 
m1=0; m2=0
mu <- c(m1,m2)

#ochylenie st. jednej zmiennej brzegowej i wsp. korelacji
s1=1; r=0.9

#generujemy proby i przeksztalcamy
for(s2 in seq(1,17,by=2)){
  
  #macierz kowariancji 
  sigma <- matrix(c(s1^2, s1*s2*r, 
                    s1*s2*r, s2^2), 
                  nrow=2)  
  
  #proba z rozkladu dwuwymiarowego normalnego
  X <- MASS::mvrnorm(n,mu=mu,Sigma=sigma)
  
  #proby z rozkladow brzegowych N(ui,si)
  X1 <- X[,1];   X2 <- X[,2]
  
  #zlozenie z dystrybuantami brzegowymi - proby z rozkladow jednostajnych
  FX1 <- pnorm(X1,m1,s1)
  FX2 <- pnorm(X2,m2,s2)
  
  plot(FX1,FX2)
}

#Przyklad 5 (kopula gaussowska w zastosowaniu)
#==========
#Problem: Jakich ekstremalnych wartosci mozna sie spodziewac 
#w  przypadku Z=2X1+3X2? (precyzyjnie dalej)

#Na postawiony problem mamy odpowiedziec wykorzystujac dane.
Dane <- read.csv2("C:\\Users\\xsero\\Desktop\\Projekty\\UG\\R - Intro\\data\\kopuly.csv")
head(Dane)

X=Dane$X1
Y=Dane$X2

#obejrzyjmy dane
plot(X,Y)
hist(X,prob=T)
hist(Y,prob=T)

#Krok 1 
#Tworzymy pseudo-obserwacje.

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

