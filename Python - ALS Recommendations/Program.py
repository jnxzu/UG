import Parser as pr
import ALS as als
import csv
import time


def save_result(size, features, lambda_value, result, time):
    with open("results.csv", mode="a") as result_file:
        result_writer = csv.writer(result_file, delimiter=",")
        result_writer.writerow([size, features, lambda_value, result, time])


def save_conv(size, lam, feat, conv):
    with open(str(size)+"-"+str(lam)+"-"+str(feat)+".csv", mode="a") as open_file:
        result_writer = csv.writer(open_file, delimiter=",")
        for i in conv:
            result_writer.writerow([i])


def test(size, lambda_value, features):
    test = pr._parse_file(size)
    start_time = time.time()
    result, converg = als.full(test, lambda_value, features)
    end_time = time.time()
    save_result(size, features, lambda_value, result, end_time-start_time)
    save_conv(size, lambda_value, features, converg)


test(1000, 0.1, 30)
