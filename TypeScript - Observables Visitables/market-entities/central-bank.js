"use strict";
exports.__esModule = true;
var CentralBank = /** @class */ (function () {
    function CentralBank() {
        this.previousSales = null;
        this.totalSales = null;
        this.inflation = 1.05;
        this.observers = [];
        this.salesIncreaseCounter = 0;
    }
    CentralBank.prototype.adjustInflation = function () {
        if (this.totalSales && this.previousSales && this.inflation > 1.01) {
            if (this.previousSales < this.totalSales) {
                this.inflation += 0.01;
                console.log("BANK: Total sales increased.");
            }
            if (this.previousSales > this.totalSales) {
                this.inflation -= 0.01;
                console.log("BANK: Total sales dropped.");
            }
            console.log("BANK: Inflation set at " + this.inflation + ".");
        }
        this.previousSales = this.totalSales;
        this.totalSales = 0;
    };
    CentralBank.prototype.attach = function (observer) {
        this.observers.push(observer);
    };
    CentralBank.prototype.detach = function (observer) {
        var idx = this.observers.indexOf(observer);
        this.observers.splice(idx, 1);
    };
    CentralBank.prototype.notify = function () {
        for (var _i = 0, _a = this.observers; _i < _a.length; _i++) {
            var observer = _a[_i];
            observer.update(this.inflation, null);
        }
    };
    CentralBank.prototype.update = function (_, newSales) {
        if (!newSales)
            return;
        console.log("BANK: New sales recieved. (" + newSales + ")");
        this.totalSales += newSales;
    };
    return CentralBank;
}());
exports["default"] = CentralBank;
