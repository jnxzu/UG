plot(1:10)
points(1:10, rep(4, 10), pch = 19, col = 2)
points(1:10, rep(2, 10), type = "h", col = "blue")

curve(x^2,
    xlim = c(-4, 4), ylim = c(-16, 16),
    xlab = NA, ylab = NA, col = "blue"
)
curve(-x^3, col = "green", add = T)
curve(sin(x), col = "red", add = T)
curve(cos(x), col = "pink", add = T)
abline(a = 0, b = 2, col = "orange")
points(1, 2, pch = 19, col = "black")
abline(h = 0, col = "grey")
abline(v = 0, col = "gray")