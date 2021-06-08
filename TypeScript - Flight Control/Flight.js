"use strict";
exports.__esModule = true;
exports.CargoFlight = exports.PassengerFlight = void 0;
var PassengerFlight = /** @class */ (function () {
    function PassengerFlight(size, id) {
        this.icon = size > 100 ? "P" + id : "p" + id;
        this.size = size;
        this.cargo = false;
    }
    PassengerFlight.prototype.visit = function (airport) {
        console.log(this.icon + ": Landing at " + airport.name + ".");
        airport.makeBusy();
    };
    return PassengerFlight;
}());
exports.PassengerFlight = PassengerFlight;
var CargoFlight = /** @class */ (function () {
    function CargoFlight(size, id) {
        this.icon = size > 100 ? "C" + id : "c" + id;
        this.size = size;
        this.cargo = true;
    }
    CargoFlight.prototype.visit = function (airport) {
        console.log(this.icon + ": Landing at " + airport.name + ".");
        airport.makeBusy();
    };
    return CargoFlight;
}());
exports.CargoFlight = CargoFlight;
