"use strict";
exports.__esModule = true;
exports.SmallPlaneFlightGenerator = exports.BigPlaneFlightGenerator = void 0;
var Flight_1 = require("./Flight");
function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
}
var BigPlaneFlightGenerator = /** @class */ (function () {
    function BigPlaneFlightGenerator() {
    }
    BigPlaneFlightGenerator.getInstance = function () {
        if (!BigPlaneFlightGenerator.instance) {
            BigPlaneFlightGenerator.instance = new BigPlaneFlightGenerator();
        }
        return BigPlaneFlightGenerator.instance;
    };
    BigPlaneFlightGenerator.prototype.createPassengerFlight = function (id) {
        return new Flight_1.PassengerFlight(getRandomInt(100, 200), id);
    };
    BigPlaneFlightGenerator.prototype.createCargoFlight = function (id) {
        return new Flight_1.CargoFlight(getRandomInt(100, 200), id);
    };
    return BigPlaneFlightGenerator;
}());
exports.BigPlaneFlightGenerator = BigPlaneFlightGenerator;
var SmallPlaneFlightGenerator = /** @class */ (function () {
    function SmallPlaneFlightGenerator() {
    }
    SmallPlaneFlightGenerator.getInstance = function () {
        if (!SmallPlaneFlightGenerator.instance) {
            SmallPlaneFlightGenerator.instance = new SmallPlaneFlightGenerator();
        }
        return SmallPlaneFlightGenerator.instance;
    };
    SmallPlaneFlightGenerator.prototype.createPassengerFlight = function (id) {
        return new Flight_1.PassengerFlight(getRandomInt(1, 100), id);
    };
    SmallPlaneFlightGenerator.prototype.createCargoFlight = function (id) {
        return new Flight_1.CargoFlight(getRandomInt(1, 100), id);
    };
    return SmallPlaneFlightGenerator;
}());
exports.SmallPlaneFlightGenerator = SmallPlaneFlightGenerator;
