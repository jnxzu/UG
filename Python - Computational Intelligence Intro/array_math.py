import numpy as np
from random import randrange

# a)
print("a)")
a = 123
b = 321
print(a * b)

# b)
print("\nb)")
vec1 = np.array([3, 8, 9, 10, 12])
vec2 = np.array([8, 7, 7, 5, 6])
print(vec1 + vec2)
print(vec1 * vec2)

# c)
print("\nc)")
print(np.dot(vec1, vec2))
print(np.linalg.norm(vec1))
print(np.linalg.norm(vec2))

# d)
print("\nd)")
mat1 = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
mat2 = np.array([[9, 8, 7], [6, 5, 4], [3, 2, 1]])
print(mat1 * mat2)
print(np.matmul(mat1, mat2))

# e)
print("\ne)")
rand = [randrange(100) for i in range(50)]
print(rand)

# f)
print("\nf)")
avg = np.average(rand)
print(avg)
maxv = np.max(rand)
print(maxv)
minv = np.min(rand)
print(minv)
stdev = np.std(rand)
print(stdev)

# g)
print("\ng)")
new_rand = [round((x - minv)/(maxv - minv), 2) for x in rand]
print(new_rand)
