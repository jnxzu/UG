"use strict";
exports.__esModule = true;
var Shop = /** @class */ (function () {
    function Shop(name, price) {
        this.name = name;
        this.basePrice = price;
        this.inflation = null;
        this.profitMargin = 1.5;
        this.prevSales = null;
        this.totalSales = null;
        this.finalPrice = null;
        this.observers = [];
    }
    Shop.prototype.accept = function (visitor, operation) {
        visitor.visit(this, operation);
    };
    Shop.prototype.attach = function (observer) {
        this.observers.push(observer);
    };
    Shop.prototype.detach = function (observer) {
        var idx = this.observers.indexOf(observer);
        this.observers.splice(idx, 1);
    };
    Shop.prototype.notify = function () {
        for (var _i = 0, _a = this.observers; _i < _a.length; _i++) {
            var obs = _a[_i];
            obs.update(this.finalPrice, this.totalSales);
        }
    };
    Shop.prototype.update = function (inflation, _) {
        if (inflation) {
            this.inflation = inflation;
            console.log(this.name + " SHOP: New inflation value recieved. (" + this.inflation + ")");
        }
    };
    return Shop;
}());
exports["default"] = Shop;
