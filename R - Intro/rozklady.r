# 1
# a)
pnorm(1)
pnorm(1, lower.tail = FALSE)
# b)
pexp(1)
pexp(1, lower.tail = FALSE)
# c)
plnorm(1)
plnorm(1, lower.tail = FALSE)

# 2
# a)
norm1 <- pnorm(2, lower.tail = FALSE)
norm2 <- pnorm(2)
norm3 <- pnorm(3) - pnorm(-3)
exp1 <- pexp(2, lower.tail = FALSE)
exp2 <- pexp(2)
exp3 <- pexp(3) - pexp(-3)
ln1 <- plnorm(2, lower.tail = FALSE)
ln2 <- plnorm(2)
ln3 <- plnorm(3) - plnorm(-3)
# b)
gt2 <- c(norm1, exp1, ln1)
lt2 <- c(norm2, exp2, ln2)
bt33 <- c(norm3, exp3, ln3)
# c)
matrix <- rbind(gt2, lt2, bt33)
rownames(matrix) <- c("P(X>2)", "P(X<2)", "P(-3<X<3)")
colnames(matrix) <- c("norm", "exp", "lnorm")

# 3
vals <- c(1:100)
vals <- pnorm(vals, 0, 50, FALSE)
vals <- round(vals, 2)

# 4
# a)
x <- seq(-5, 5, length = 1000)
# par(mfrow = c(2, 1))
# plot(dnorm(x, mean = 1), col = "red", typ = "l")
# lines(dnorm(x, mean = 4), col = "blue", typ = "l")
# plot(dnorm(x, sd = 4), col = "red", typ = "l")
# lines(dnorm(x, sd = 9), col = "blue", typ = "l")

# b)
# plot(dnorm(x, mean = 1), col = "red", typ = "l")
# lines(dnorm(x, mean = 4), col = "blue")
# lines(dnorm(x, sd = 4), col = "green")
# lines(dnorm(x, sd = 9), col = "orange")

# 5
x2 <- rnorm(1000, 0, 16)
# hist(exp(x2))
# lines(dlnorm(x, 0, 16), col = "red")

# 6
x3 <- c()
for (i in 1:5) {
    norm <- rnorm(100)
    squared <- norm^2
    summed <- sum(squared)
    x3 <- append(x3, summed)
}
# hist(x3)
# lines(dchisq(x, 100), col = "red")

# 7
# plot(dnorm(x), typ = "l", col = "red")
# lines(dt(x, 4), col = "blue")