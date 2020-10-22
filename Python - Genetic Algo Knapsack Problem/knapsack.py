from pyeasyga import pyeasyga
import random
import matplotlib.pyplot as plt


# custom class allows overriding the "run" function to extract fitness values from each generation
class MyGA(pyeasyga.GeneticAlgorithm):
    def run(self):
        average_fitness_table = []
        best_fitness_table = []

        self.create_first_generation()

        avg_fit = sum([i.fitness for i in self.current_generation]) / len(self.current_generation)     
        average_fitness_table.append(avg_fit)    
        best_fit = self.current_generation[0].fitness
        best_fitness_table.append(best_fit)

        for _ in range(1, self.generations):
            self.create_next_generation()

            avg_fit = sum([i.fitness for i in self.current_generation]) / len(self.current_generation)     
            average_fitness_table.append(avg_fit)    
            best_fit = self.current_generation[0].fitness
            best_fitness_table.append(best_fit)
        
        return (average_fitness_table, best_fitness_table)

# weight limit 1000 / optimal profit 3103
# source https://link.springer.com/chapter/10.1007/978-3-642-23214-5_27
cap = 1000
data = [{'weight': 80, 'profit': 220}, {'weight': 82, 'profit': 208}, 
        {'weight': 85, 'profit': 198}, {'weight': 70, 'profit': 192}, 
        {'weight': 72, 'profit': 180}, {'weight': 70, 'profit': 180}, 
        {'weight': 66, 'profit': 165}, {'weight': 50, 'profit': 162}, 
        {'weight': 55, 'profit': 160}, {'weight': 25, 'profit': 158}, 
        {'weight': 50, 'profit': 155}, {'weight': 55, 'profit': 130}, 
        {'weight': 40, 'profit': 125}, {'weight': 48, 'profit': 122}, 
        {'weight': 50, 'profit': 120}, {'weight': 32, 'profit': 118}, 
        {'weight': 22, 'profit': 115}, {'weight': 60, 'profit': 110}, 
        {'weight': 30, 'profit': 105}, {'weight': 32, 'profit': 101}, 
        {'weight': 40, 'profit': 100}, {'weight': 38, 'profit': 100}, 
        {'weight': 35, 'profit': 98}, {'weight': 32, 'profit': 96}, 
        {'weight': 25, 'profit': 95}, {'weight': 28, 'profit': 90}, 
        {'weight': 30, 'profit': 88}, {'weight': 22, 'profit': 82}, 
        {'weight': 50, 'profit': 80}, {'weight': 30, 'profit': 77}, 
        {'weight': 45, 'profit': 75}, {'weight': 30, 'profit': 73}, 
        {'weight': 60, 'profit': 72}, {'weight': 50, 'profit': 70}, 
        {'weight': 20, 'profit': 69}, {'weight': 65, 'profit': 66}, 
        {'weight': 20, 'profit': 65}, {'weight': 25, 'profit': 63}, 
        {'weight': 30, 'profit': 60}, {'weight': 10, 'profit': 58}, 
        {'weight': 20, 'profit': 56}, {'weight': 25, 'profit': 50}, 
        {'weight': 15, 'profit': 30}, {'weight': 10, 'profit': 20}, 
        {'weight': 10, 'profit': 15}, {'weight': 10, 'profit': 10}, 
        {'weight': 4, 'profit': 8}, {'weight': 4, 'profit': 5}, 
        {'weight': 2, 'profit': 3}, {'weight': 1, 'profit': 1}]

ga = MyGA(
        data,
          population_size=200,
          generations=100,
          mutation_probability=0.05,
          elitism=False
        )


# fitness function
def fitness(individual, data):
    weight, profit = 0, 0
    for selected, item in zip(individual, data):
        if selected:
            weight += item.get('weight')
            profit += item.get('profit')
    if weight > cap:
        return 0
    else:
        return profit

ga.fitness_function = fitness


# output tables
average_fitness, best_fitness = ga.run()
print(ga.best_individual())


# graph
plt.plot(range(100), average_fitness, 'y')
plt.plot(range(100), best_fitness, 'r')
plt.xlabel("Generation")
plt.ylabel("Fitness")
plt.show()
