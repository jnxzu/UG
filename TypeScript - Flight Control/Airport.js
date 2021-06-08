"use strict";
exports.__esModule = true;
exports.Airport = void 0;
var consts_1 = require("./consts");
var Airport = /** @class */ (function () {
    function Airport(name, size, type) {
        this.busy = false;
        this.observers = [];
        this.name = name;
        this.size = size;
        this.type = type;
    }
    Airport.prototype.accept = function (visitor) {
        if (!this.canLandHere(visitor))
            return;
        visitor.visit(this);
    };
    Airport.prototype.canLandHere = function (flight) {
        if (this.size === "S" && flight.size > 100)
            return false;
        if (this.type === "C" && flight.cargo)
            return false;
        return true;
    };
    Airport.prototype.makeBusy = function () {
        var _this = this;
        this.busy = true;
        console.log(this.name + " - is now busy!");
        this.notify();
        setTimeout(function () {
            _this.busy = false;
            console.log(_this.name + " - is now open for planes.");
            _this.notify();
        }, consts_1.OCCUPY_TIME);
    };
    Airport.prototype.attach = function (observer) {
        this.observers.push(observer);
    };
    Airport.prototype.detach = function (observer) {
        var observerIndex = this.observers.indexOf(observer);
        this.observers.splice(observerIndex, 1);
    };
    Airport.prototype.notify = function () {
        var _this = this;
        this.observers.forEach(function (obs) { return obs.update(_this); });
    };
    return Airport;
}());
exports.Airport = Airport;
