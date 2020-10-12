import pandas as pd
import matplotlib.pyplot as plt

# a)
data = pd.read_csv('miasta.csv')
print(data)

# b)
new_row = {'Rok': 2010, 'Gdansk': 460, 'Poznan': 555, 'Szczecin': 405}
data = data.append(new_row, ignore_index=True)
print(data)

# c)
ax = plt.gca()
data.plot(style="ro-", x="Rok", y="Gdansk", ax=ax)
ax.set_title("Populacja Gdanska 1900-2010")
ax.set_ylabel("Tysiecy Mieszkancow")
ax.legend().remove()
plt.savefig('gdansk.png')

# d)
data.plot(style="bo-", x="Rok", y="Poznan", ax=ax)
data.plot(style="go-", x="Rok", y="Szczecin", ax=ax)
ax.set_title("Porownanie Populacji Wybranych Miast 1900-2010")
ax.legend()
plt.savefig('all.png')
