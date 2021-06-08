"use strict";
exports.__esModule = true;
exports.FlightControl = void 0;
var AirportExtensions_1 = require("./AirportExtensions");
var FlightControl = /** @class */ (function () {
    function FlightControl() {
    }
    FlightControl.getInstance = function () {
        if (!FlightControl.instance) {
            FlightControl.instance = new FlightControl();
        }
        return FlightControl.instance;
    };
    FlightControl.prototype.handleLandingRequest = function (flight) {
        console.log();
        console.log("Trying to find airport for " + flight.icon);
        for (var i = 0; i < this.availableAirports.length; i++) {
            var airport = this.availableAirports[i];
            if (airport.canLandHere(flight)) {
                airport.accept(flight);
                return true;
            }
        }
        for (var i = 0; i < this.availableAirports.length; i++) {
            var airport = this.availableAirports[i];
            if (flight.size > 100 && airport.size === "S") {
                new AirportExtensions_1.BigFlightAdapter().accept(airport, flight);
                return true;
            }
            if (flight.cargo && airport.type === "P") {
                new AirportExtensions_1.CargoFlightAdapter().accept(airport, flight);
                return true;
            }
        }
        console.log("Could not find a place to land at the moment.");
        return false;
    };
    FlightControl.prototype.update = function (subject) {
        if (subject.busy) {
            var airporIndex = this.availableAirports.indexOf(subject);
            this.availableAirports.splice(airporIndex, 1);
        }
        else {
            this.availableAirports.push(subject);
        }
    };
    return FlightControl;
}());
exports.FlightControl = FlightControl;
