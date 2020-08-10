class RecMatrix():
    def __init__(self, products, customers, ratings):
        self.rows = len(customers)
        self.cols = len(products)
        self.ratingsmatrix = [[0]*self.cols for _ in range(self.rows)]
        for key, value in ratings.items():
            self.ratingsmatrix[customers.index(
                key[1])][products.index(key[0])] = value

    def productsRatedByCustomer(self, idx):
        return [i for i in range(self.cols) if self.ratingsmatrix[idx][i] != 0]

    def customersWhoRatedProduct(self, idx):
        return [i for i in range(self.rows) if self.ratingsmatrix[i][idx] != 0]
