import CubicSplineInterpolation as csi
import TerrainDataLoader as tdl
import csv


def printMatrix(matrix):
    for row in matrix:
        print(row)


def test(algo_precision, points, graph=False):
    # świnoujście -> hel
    coast_x, coast_y = tdl.getPath(
        (53.897380, 14.268526), (54.609053, 18.801119), points)
    coast_y_gsm, coast_gsm_time, coast_y_jm, coast_jm_time, coast_y_ppgm, coast_ppgm_time = csi.csi(
        coast_x[0::5], coast_y[0::5], jump_count=points, algo_precision=algo_precision)
    if graph:
        with open("coast.csv", mode="w") as coast_results:
            coast_writer = csv.writer(coast_results, delimiter=",")
            coast_writer.writerow(["original", "gsm", "jm", "ppgm"])
            for i in range(len(coast_y)):
                coast_writer.writerow(
                    [coast_y[i], coast_y_gsm[i], coast_y_jm[i], coast_y_ppgm[i]])
    # salzburg -> milan
    alps_x, alps_y = tdl.getPath(
        (47.782240, 13.220064), (45.464157, 9.185970), points)
    alps_y_gsm, alps_gsm_time, alps_y_jm, alps_jm_time, alps_y_ppgm, alps_ppgm_time = csi.csi(
        alps_x[0::5], alps_y[0::5], jump_count=points, algo_precision=algo_precision)
    if graph:
        with open("alps.csv", mode="w") as alps_results:
            alps_writer = csv.writer(alps_results, delimiter=",")
            alps_writer.writerow(["original", "gsm", "jm", "ppgm"])
            for i in range(len(alps_y)):
                alps_writer.writerow(
                    [alps_y[i], alps_y_gsm[i], alps_y_jm[i], alps_y_ppgm[i]])
    # kolkata -> mt. everest
    slope_x, slope_y = tdl.getPath(
        (22.382430, 88.313330), (27.988916, 86.922186), points)
    slope_y_gsm, slope_gsm_time, slope_y_jm, slope_jm_time, slope_y_ppgm, slope_ppgm_time = csi.csi(
        slope_x[0::5], slope_y[0::5], jump_count=points, algo_precision=algo_precision)
    if graph:
        with open("slope.csv", mode="w") as slope_results:
            slope_writer = csv.writer(slope_results, delimiter=",")
            slope_writer.writerow(["original", "gsm", "jm", "ppgm"])
            for i in range(len(slope_y)):
                slope_writer.writerow(
                    [slope_y[i], slope_y_gsm[i], slope_y_jm[i], slope_y_ppgm[i]])
    with open("results.csv", mode="a") as results:
        result_writer = csv.writer(results, delimiter=",")
        result_writer.writerow(
            ["coast", points, algo_precision, coast_gsm_time, coast_jm_time, coast_ppgm_time])
        result_writer.writerow(
            ["alps", points, algo_precision, alps_gsm_time, alps_jm_time, alps_ppgm_time])
        result_writer.writerow(
            ["slope", points, algo_precision, slope_gsm_time, slope_jm_time, slope_ppgm_time])


test(25, 200, True)
print("8%")

test(10, 200)
print("16%")

test(15, 200)
print("24%")

test(20, 200)
print("32%")

test(30, 200)
print("40%")

test(35, 200)
print("48%")

test(40, 200)
print("56%")

test(25, 50)
print("64%")

test(25, 100)
print("72%")

test(25, 150)
print("80%")

test(25, 250)
print("88%")

test(25, 300)
print("96%")

test(25, 350)
print("100%")
