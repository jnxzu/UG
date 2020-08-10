import re
import pandas as pd
import sys
import numpy as np
from Matrix import *
from scipy import sparse

rx_dict = {
    'product': re.compile(
        r'ASIN:\s(?P<product>\d+)\n'),
    'customer_rating': re.compile(
        r'cutomer:\s+(?P<customer>\S+)\s\srating:\s(?P<rating>\d)')
}


def _parse_line(line):
    for key, rx in rx_dict.items():
        match = rx.search(line)
        if match:
            return key, match
    return None, None


def _prep_data(raw_data):
    products = []
    customers = []
    for key, value in raw_data.items():
        if key[0] not in products:
            products.append(key[0])
        if key[1] not in customers:
            customers.append(key[1])
    return RecMatrix(products, customers, raw_data)


def _parse_file(size):
    products_checked = 0
    raw_data = {}
    continue_flag = True
    with open('amazon-short.txt', 'r') as fo:
        line = fo.readline()
        while line and continue_flag:
            key, match = _parse_line(line)
            if key == 'product':
                products_checked += 1
                if products_checked == size+1:
                    break
                product = match.group('product')
            if key == 'customer_rating':
                customer = match.group('customer')
                rating = match.group('rating')
                raw_data.update({(product, customer): float(rating)})
            line = fo.readline()
    return _prep_data(raw_data)
