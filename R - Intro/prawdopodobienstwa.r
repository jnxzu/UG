# P(X>19) rozklad normalny, oczekiwana 38, odchylenie 19
z1 <- pnorm(19, 38, 19, FALSE)
# print(round(z1, 2))

# P(X>18.15) rozklad chi^2, swoboda 14
z2 <- pchisq(18.15, 14, lower.tail = FALSE)
# print(round(z2, 2))

# P(X>0.17) rozklad wykladniczy, lambda 9.53
z3 <- pexp(0.17, 9.53, lower.tail = FALSE)
# print(round(z3, 2))

# P(X>0.87) rozklad t-studenta, swoboda 12
z4 <- pt(0.87, 12, lower.tail = FALSE)
# print(round(z4, 2))

# rozklad normalny
# P(X=<1), a
z5a <- pnorm(1)
# P(X>1), b
z5b <- pnorm(1, lower.tail = FALSE)
# P(X>2), c
z5c <- pnorm(2, lower.tail = FALSE)
# P(X<2), d
z5d <- pnorm(2)
# P(-3<X<3), e
z5e <- pnorm(3) - pnorm(-3)

# print(round(z5a, 2))
# print(round(z5b, 2))
# print(round(z5c, 2))
# print(round(z5d, 2))
# print(round(z5e, 2))

# rozklad wykladniczy
# P(X=<1), a
z6a <- pexp(1)
# P(X>1), b
z6b <- pexp(1, lower.tail = FALSE)
# P(X>2), c
z6c <- pexp(2, lower.tail = FALSE)
# P(X<2), d
z6d <- pexp(2)
# P(-3<X<3), e
z6e <- pexp(3) - pexp(-3)

# print(round(z6a, 2))
# print(round(z6b, 2))
# print(round(z6c, 2))
# print(round(z6d, 2))
# print(round(z6e, 2))

# rozklad log normalny
# P(X=<1), a
z7a <- plnorm(1)
# P(X>1), b
z7b <- plnorm(1, lower.tail = FALSE)
# P(X>2), c
z7c <- plnorm(2, lower.tail = FALSE)
# P(X<2), d
z7d <- plnorm(2)
# P(-3<X<3), e
z7e <- plnorm(3) - plnorm(-3)

# print(round(z7a, 2))
# print(round(z7b, 2))
# print(round(z7c, 2))
# print(round(z7d, 2))
# print(round(z7e, 2))