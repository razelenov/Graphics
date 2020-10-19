from scipy import misc, ndimage, signal
from matplotlib import pyplot as plt
import cv2
import numpy as np

photo = str(input("Введите имя файла: "))
original_photo = cv2.imread(photo)[0:512, 0:512, 2]
poisson_photo = np.random.poisson(original_photo)
poisson_photo[poisson_photo < 0] = 0
poisson_photo[poisson_photo > 255] = 255
wiener_photo = signal.wiener(poisson_photo, (7, 7), 300)

plt.figure(figsize=(9, 3.5))
plt.subplot(131)
plt.imshow(original_photo, cmap=plt.cm.gray)
plt.axis('off')
plt.title('Оригинал')

plt.subplot(132)
plt.imshow(poisson_photo, cmap=plt.cm.gray)
plt.axis('off')
plt.title('Пуассоновский шум')

plt.subplot(133)
plt.imshow(wiener_photo, cmap=plt.cm.gray)
plt.axis('off')
plt.title('Фильтр Винера')

plt.subplots_adjust(wspace=.05, left=.01, bottom=.01, right=.99, top=.99)

plt.show()
