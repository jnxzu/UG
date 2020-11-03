from pyeasyga import pyeasyga
import random
import math


class MyGA(pyeasyga.GeneticAlgorithm):
    def run(self):
        average_fitness_table = []
        best_fitness_table = []

        self.create_first_generation()

        avg_fit = sum([i.fitness for i in self.current_generation]
                      ) / len(self.current_generation)
        average_fitness_table.append(avg_fit)
        best_fit = self.current_generation[0].fitness
        best_fitness_table.append(best_fit)

        for _ in range(1, self.generations):
            self.create_next_generation()

            avg_fit = sum([i.fitness for i in self.current_generation]
                          ) / len(self.current_generation)
            average_fitness_table.append(avg_fit)
            best_fit = self.current_generation[0].fitness
            best_fitness_table.append(best_fit)

        return average_fitness_table, best_fitness_table


def create_individual(seed_data):
    return [random.randint(0, 4) for _
            in range(math.ceil((len(seed_data) * len(seed_data)) / 2))]


def mutate(individual):
    mutate_index = random.randrange(len(individual))
    individual[mutate_index] = random.randint(0, 4)


def fitness_a(individual, lab):
    x, y = 1, 1
    end_x, end_y = len(lab)-2, len(lab)-2
    for move in individual:
        if move == 1:
            if(lab[y - 1][x]):
                y -= 1
        if move == 2:
            if(lab[y][x + 1]):
                x += 1
        if move == 3:
            if(lab[y + 1][x]):
                y += 1
        if move == 4:
            if(lab[y][x - 1]):
                x -= 1
    return abs(x - end_x) + abs(y - end_y)


def fitness_b(individual, lab):
    x, y = 1, 1
    end_x, end_y = len(lab)-2, len(lab)-2
    wall_hit_penalty = 0
    early_finish_bonus = 0
    for i, move in enumerate(individual):
        if x == end_x and y == end_y:
            early_finish_bonus = len(individual) - i
            # break
        if move == 1:
            if(lab[y - 1][x]):
                y -= 1
            else:
                wall_hit_penalty += 1
        if move == 2:
            if(lab[y][x + 1]):
                x += 1
            else:
                wall_hit_penalty += 1
        if move == 3:
            if(lab[y + 1][x]):
                y += 1
            else:
                wall_hit_penalty += 1
        if move == 4:
            if(lab[y][x - 1]):
                x -= 1
            else:
                wall_hit_penalty += 1
    return abs(x - end_x) + abs(y - end_y) + wall_hit_penalty - early_finish_bonus


def runGenetic(fitness, lab):
    ga = MyGA(lab,
              crossover_probability=0.5,
              mutation_probability=0.25,
              maximise_fitness=False,
              elitism=False
              )
    ga.create_individual = create_individual
    ga.mutate_function = mutate
    ga.fitness_function = fitness
    average, best = ga.run()
    individual = ga.best_individual()
    return average, best, individual[1]
