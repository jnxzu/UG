import inputs
import genetic
import bfs
import grapher
import display

from time import perf_counter
import numpy as np
import matplotlib.pyplot as plt
from statistics import mean


def run_and_time_bfs(lab):
    start = perf_counter()
    path = bfs.bfs(lab)
    end = perf_counter()
    time = end - start
    return path, time


def run_and_time_genetic(fitness_func, lab):
    start = perf_counter()
    average, best, individual = genetic.runGenetic(fitness_func, lab)
    end = perf_counter()
    time = end - start
    return average, best, individual, time


def get_path_from_individual(individual, lab):
    path = [(1, 1)]
    x, y = 1, 1
    for move in individual:
        if move == 1:
            if(lab[y - 1][x]):
                y -= 1
                path.append((x, y))
        elif move == 2:
            if(lab[y][x + 1]):
                x += 1
                path.append((x, y))
        elif move == 3:
            if(lab[y + 1][x]):
                y += 1
                path.append((x, y))
        elif move == 4:
            if(lab[y][x - 1]):
                x -= 1
                path.append((x, y))
    return path


def graph_time(bfs_lab1, bfs_lab2, bfs_lab3, genetic_a_lab1, genetic_a_lab2, genetic_a_lab3, genetic_b_lab1, genetic_b_lab2, genetic_b_lab3):
    x = ['BFS x LAB1', 'BFS x LAB2', 'BFS x LAB3', 'Fitness A x LAB1', 'Fitness A x LAB2',
         'Fitness A x LAB3', 'Fitness B x LAB1', 'Fitness B x LAB2', 'Fitness B x LAB3']
    y = [bfs_lab1, bfs_lab2, bfs_lab3, genetic_a_lab1, genetic_a_lab2,
         genetic_a_lab3, genetic_b_lab1, genetic_b_lab2, genetic_b_lab3]
    plt.bar(x, y)
    plt.xlabel('Algorithm')
    plt.ylabel('Time in Seconds')
    xlocs, _ = plt.xticks()
    for i, v in enumerate(y):
        val_v = str(v)
        plt.text(xlocs[i] - 0.25, v + 0.01, f'{val_v}s')
    plt.show()


def get_average_of_series(series):
    data = np.array([s for s in series])
    average_of_series = np.average(data, axis=0)
    return average_of_series


def graph_average_fitness_change(avg_a_1, avg_a_2, avg_a_3, avg_b_1, avg_b_2, avg_b_3):
    plt.plot(range(100), avg_a_1, '-b', label="Fitness A x LAB1")
    plt.plot(range(100), avg_a_2, '-g', label="Fitness A x LAB2")
    plt.plot(range(100), avg_a_3, '-r', label="Fitness A x LAB3")
    plt.plot(range(100), avg_b_1, '-c', label="Fitness B x LAB1")
    plt.plot(range(100), avg_b_2, '-m', label="Fitness B x LAB2")
    plt.plot(range(100), avg_b_3, '-y', label="Fitness B x LAB3")
    plt.xlabel('Generations')
    plt.ylabel('Fitness')
    plt.legend(loc='upper right')
    plt.title('Average Fitness Change')
    plt.show()


def graph_best_fitness_change(best_a_1, best_a_2, best_a_3, best_b_1, best_b_2, best_b_3):
    plt.plot(range(100), best_a_1, '-b', label="Fitness A x LAB1")
    plt.plot(range(100), best_a_2, '-g', label="Fitness A x LAB2")
    plt.plot(range(100), best_a_3, '-r', label="Fitness A x LAB3")
    plt.plot(range(100), best_b_1, '-c', label="Fitness B x LAB1")
    plt.plot(range(100), best_b_2, '-m', label="Fitness B x LAB2")
    plt.plot(range(100), best_b_3, '-y', label="Fitness B x LAB3")
    plt.xlabel('Generations')
    plt.ylabel('Fitness')
    plt.legend(loc='upper right')
    plt.title('Best Fitness Change')
    plt.show()


def compare_averages(averages):
    # graph that
    pass


def save_checked_path(lab, path, filename):
    display.show_path(lab, path, filename)


# BFS x LAB1
bfs_lab1_path, bfs_lab1_time = run_and_time_bfs(inputs.labirynth1)
save_checked_path(inputs.labirynth1, bfs_lab1_path, 'paths/bfs_lab_1.jpg')
bfs_lab1_time = round(bfs_lab1_time, 10)

# BFS x LAB2
bfs_lab2_path, bfs_lab2_time = run_and_time_bfs(inputs.labirynth2)
save_checked_path(inputs.labirynth2, bfs_lab2_path, 'paths/bfs_lab_2.jpg')
bfs_lab2_time = round(bfs_lab2_time, 10)

# BFS x LAB3
bfs_lab3_path, bfs_lab3_time = run_and_time_bfs(inputs.labirynth3)
save_checked_path(inputs.labirynth3, bfs_lab3_path, 'paths/bfs_lab_3.jpg')
bfs_lab3_time = round(bfs_lab3_time, 10)

# Fitness A x LAB1
changes_to_avg_fitness = []
changes_to_best_fitness = []
times = []
for i in range(5):
    average, best, individual, time = run_and_time_genetic(
        genetic.fitness_a, inputs.labirynth1)
    changes_to_avg_fitness.append(average)
    changes_to_best_fitness.append(best)
    times.append(time)
    path = get_path_from_individual(individual, inputs.labirynth1)
    save_checked_path(inputs.labirynth1, path,
                      f'paths/run_{i}_fitness_a_lab_1.jpg')
genetic_fitness_a_lab_1_avg_fitness_change = get_average_of_series(
    changes_to_avg_fitness)
genetic_fitness_a_lab_1_best_fitness_change = get_average_of_series(
    changes_to_best_fitness)
genetic_fitness_a_lab_1_time = round(mean(times), 10)


# Fitness A x LAB2
changes_to_avg_fitness = []
changes_to_best_fitness = []
times = []
for i in range(5):
    average, best, individual, time = run_and_time_genetic(
        genetic.fitness_a, inputs.labirynth2)
    changes_to_avg_fitness.append(average)
    changes_to_best_fitness.append(best)
    times.append(time)
    path = get_path_from_individual(individual, inputs.labirynth2)
    save_checked_path(inputs.labirynth2, path,
                      f'paths/run_{i}_fitness_a_lab_2.jpg')
genetic_fitness_a_lab_2_avg_fitness_change = get_average_of_series(
    changes_to_avg_fitness)
genetic_fitness_a_lab_2_best_fitness_change = get_average_of_series(
    changes_to_best_fitness)
genetic_fitness_a_lab_2_time = round(mean(times), 10)

# Fitness A x LAB3
changes_to_avg_fitness = []
changes_to_best_fitness = []
times = []
for i in range(5):
    average, best, individual, time = run_and_time_genetic(
        genetic.fitness_a, inputs.labirynth3)
    changes_to_avg_fitness.append(average)
    changes_to_best_fitness.append(best)
    times.append(time)
    path = get_path_from_individual(individual, inputs.labirynth3)
    save_checked_path(inputs.labirynth3, path,
                      f'paths/run_{i}_fitness_a_lab_3.jpg')
genetic_fitness_a_lab_3_avg_fitness_change = get_average_of_series(
    changes_to_avg_fitness)
genetic_fitness_a_lab_3_best_fitness_change = get_average_of_series(
    changes_to_best_fitness)
genetic_fitness_a_lab_3_time = round(mean(times), 10)

# Fitness B x LAB1
changes_to_avg_fitness = []
changes_to_best_fitness = []
times = []
for i in range(5):
    average, best, individual, time = run_and_time_genetic(
        genetic.fitness_b, inputs.labirynth1)
    changes_to_avg_fitness.append(average)
    changes_to_best_fitness.append(best)
    times.append(time)
    path = get_path_from_individual(individual, inputs.labirynth1)
    save_checked_path(inputs.labirynth1, path,
                      f'paths/run_{i}_fitness_b_lab_1.jpg')
genetic_fitness_b_lab_1_avg_fitness_change = get_average_of_series(
    changes_to_avg_fitness)
genetic_fitness_b_lab_1_best_fitness_change = get_average_of_series(
    changes_to_best_fitness)
genetic_fitness_b_lab_1_time = round(mean(times), 10)


# Fitness B x LAB2
changes_to_avg_fitness = []
changes_to_best_fitness = []
times = []
for i in range(5):
    average, best, individual, time = run_and_time_genetic(
        genetic.fitness_b, inputs.labirynth2)
    changes_to_avg_fitness.append(average)
    changes_to_best_fitness.append(best)
    times.append(time)
    path = get_path_from_individual(individual, inputs.labirynth2)
    save_checked_path(inputs.labirynth2, path,
                      f'paths/run_{i}_fitness_b_lab_2.jpg')
genetic_fitness_b_lab_2_avg_fitness_change = get_average_of_series(
    changes_to_avg_fitness)
genetic_fitness_b_lab_2_best_fitness_change = get_average_of_series(
    changes_to_best_fitness)
genetic_fitness_b_lab_2_time = round(mean(times), 10)


# Fitness B x LAB3
changes_to_avg_fitness = []
changes_to_best_fitness = []
times = []
for i in range(5):
    average, best, individual, time = run_and_time_genetic(
        genetic.fitness_b, inputs.labirynth3)
    changes_to_avg_fitness.append(average)
    changes_to_best_fitness.append(best)
    times.append(time)
    path = get_path_from_individual(individual, inputs.labirynth3)
    save_checked_path(inputs.labirynth3, path,
                      f'paths/run_{i}_fitness_b_lab_3.jpg')
genetic_fitness_b_lab_3_avg_fitness_change = get_average_of_series(
    changes_to_avg_fitness)
genetic_fitness_b_lab_3_best_fitness_change = get_average_of_series(
    changes_to_best_fitness)
genetic_fitness_b_lab_3_time = round(mean(times), 10)


# Graphing
graph_time(bfs_lab1_time, bfs_lab2_time,
           bfs_lab3_time, genetic_fitness_a_lab_1_time,
           genetic_fitness_a_lab_2_time, genetic_fitness_a_lab_3_time,
           genetic_fitness_b_lab_1_time, genetic_fitness_b_lab_2_time,
           genetic_fitness_b_lab_3_time)

graph_average_fitness_change(genetic_fitness_a_lab_1_avg_fitness_change, genetic_fitness_a_lab_2_avg_fitness_change,
                             genetic_fitness_a_lab_3_avg_fitness_change, genetic_fitness_b_lab_1_avg_fitness_change,
                             genetic_fitness_b_lab_2_avg_fitness_change, genetic_fitness_b_lab_3_avg_fitness_change)

graph_best_fitness_change(genetic_fitness_a_lab_1_best_fitness_change, genetic_fitness_a_lab_2_best_fitness_change,
                          genetic_fitness_a_lab_3_best_fitness_change, genetic_fitness_b_lab_1_best_fitness_change,
                          genetic_fitness_b_lab_2_best_fitness_change, genetic_fitness_b_lab_3_best_fitness_change)
