import random
import matplotlib.pyplot as plt
import numpy as np
import math

plt.figure(figsize = (15, 10))

matrix = [
            [   0.0,   0.0,   0.0,   0.16,   0.0,   0.0,   0.01],
            [  0.85,  0.04, -0.04,   0.85,   0.0,   1.6,   0.85],
            [   0.2, -0.26,  0.23,   0.22,   0.0,   1.6,   0.07],
            [ -0.15,  0.28,  0.26,   0.24,   0.0,  0.44,   0.07],
          ]

points_x = []
points_y = []

x = 0
y = 0

index = 1

for i in range(0, 100000):
    p = random.random()
    if p <= matrix[0][6]:
        k = 0
    elif p <= matrix[0][6] + matrix[1][6]:
        k = 1
    elif p <= matrix[0][6] + matrix[1][6] + matrix[2][6]:
        k = 2
    else:
        k = 3

    x0 = x * matrix[k][0] + y * matrix[k][1] + matrix[k][4]
    y  = x * matrix[k][2] + y * matrix[k][3] + matrix[k][5]
    x = x0

    points_x.append(x)
    points_y.append(y)

    if ((i + 1) % 10000 == 0):
        plt.subplot(2, 5, index)
        index = index + 1
        plt.scatter(points_x, points_y, color = "teal", s = 0.02)
        plt.title("Кол-во итераций: " + str(i + 1))

plt.show()

plt.figure(figsize = (5.5, 7.5))
plt.scatter(points_x, points_y, color = "teal", s = 0.3)
plt.show()

def grid(delta):
    centers = []

    i1 = np.arange(-2, 11, delta)
    i2 = np.arange(-2.5, 4, delta)
    x, y = np.meshgrid(i2, i1)

    for i in range(len(x)):
        for k in range(len(x[i])):
            centers.append([x[i][k], y[i][k]])

    squares = []

    for i in range(len(centers)):
        temp = [
                [centers[i][0] - delta / 2, centers[i][1] - delta / 2],
                [centers[i][0] - delta / 2, centers[i][1] + delta / 2],
                [centers[i][0] + delta / 2, centers[i][1] + delta / 2],
                [centers[i][0] + delta / 2, centers[i][1] - delta / 2],   
            ]
        squares.append(temp)

    return squares

sigma = []
N = []

for t in range(1, 16):
    
    #plt.figure(figsize = (5.5, 7.5))
    #plt.scatter(points_x, points_y, color = "teal", s = 0.3)

    grids = grid(3 / t)
    for i in range(len(grids)):
        temp = grids[i]
        temp_x = []
        temp_y = []
        for j in range(len(temp)):
            temp_x.append(temp[j][0])
            temp_y.append(temp[j][1])
        #plt.scatter(temp_x, temp_y, color = "r", s = 0.3)

    marks = [0] * len(grids)

    for i in range(len(points_x)):
        for j in range(len(grids)):
            if (grids[j][0][0] <= points_x[i]) and (grids[j][2][0] >= points_x[i]) and (grids[j][0][1] <= points_y[i]) and (grids[j][2][1] >= points_y[i]):
                marks[j] = 1

    for i in range(len(marks)):
        if (marks[i] == 1):
            temp = grids[i]
            temp_x = []
            temp_y = []
            for j in range(len(temp)):
                temp_x.append(temp[j][0])
                temp_y.append(temp[j][1])
            #plt.scatter(temp_x, temp_y, color = "gold", s = 4)

    index = 0
    
    for i in range(len(marks)):
        if (marks[i] == 1):
            index = index + 1

    sigma.append(3 / t)
    N.append(index)
    
    #plt.show()

for i in range(len(sigma)):
    sigma[i] = math.log(sigma[i])
    N[i] = math.log(N[i])

plt.plot(sigma, N)
plt.show()

def getSumX(mas_x):
    sum = 0
    for i in range(len(mas_x)):
        sum = sum + mas_x[i]
    return sum

def getSumY(mas_y):
    sum = 0
    for i in range(len(mas_y)):
        sum = sum + mas_y[i]
    return sum

def getSumXY(mas_x, mas_y):
    sum = 0
    for i in range(len(mas_y)):
        sum = sum + mas_y[i] * mas_x[i]
    return sum

def getSumX2(mas_x):
    sum = 0
    for i in range(len(mas_x)):
        sum = sum + math.pow(mas_x[i], 2)
    return sum

def getSumY2(mas_y):
    sum = 0
    for i in range(len(mas_y)):
        sum = sum + math.pow(mas_y[i], 2)
    return sum

def getResult(mas_x, mas_y):
    a = ((getSumX(mas_x) * getSumY(mas_y)) - (len(mas_x) * getSumXY(mas_x, mas_y))) / (math.pow(getSumX(mas_x), 2) - (len(mas_x) * (getSumX2(mas_x))))
    b = ((getSumX(mas_x) * getSumXY(mas_x, mas_y)) - (getSumX2(mas_x) * getSumY(mas_y))) / (math.pow(getSumX(mas_x), 2) - (len(mas_x) * getSumX2(mas_x)))
    return a, b

a, b = getResult(sigma, N)

print("Фрактальная размерность: " + str(round(abs(a), 2)))

