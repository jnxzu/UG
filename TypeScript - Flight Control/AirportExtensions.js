"use strict";
exports.__esModule = true;
exports.CargoFlightAdapter = exports.BigFlightAdapter = void 0;
var BigFlightAdapter = /** @class */ (function () {
    function BigFlightAdapter() {
    }
    BigFlightAdapter.prototype.accept = function (airport, flight) {
        console.log(airport.name + ": Using a big plane extension.");
        flight.visit(airport);
    };
    return BigFlightAdapter;
}());
exports.BigFlightAdapter = BigFlightAdapter;
var CargoFlightAdapter = /** @class */ (function () {
    function CargoFlightAdapter() {
    }
    CargoFlightAdapter.prototype.accept = function (airport, flight) {
        console.log(airport.name + ": Using a cargo plane extension.");
        flight.visit(airport);
    };
    return CargoFlightAdapter;
}());
exports.CargoFlightAdapter = CargoFlightAdapter;
