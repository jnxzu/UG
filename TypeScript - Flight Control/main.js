"use strict";
exports.__esModule = true;
var Airport_1 = require("./Airport");
var consts_1 = require("./consts");
var FlightControl_1 = require("./FlightControl");
var FlightGenerators_1 = require("./FlightGenerators");
var smallGenerator = FlightGenerators_1.SmallPlaneFlightGenerator.getInstance();
var bigGenerator = FlightGenerators_1.BigPlaneFlightGenerator.getInstance();
var flights = [];
var flightControl = FlightControl_1.FlightControl.getInstance();
flightControl.availableAirports = [
    new Airport_1.Airport("WAW", "L", "P"),
    new Airport_1.Airport("KRK", "L", "C"),
    new Airport_1.Airport("GDN", "S", "P"),
    new Airport_1.Airport("WRO", "S", "C"),
    new Airport_1.Airport("KTW", "L", "P"),
    new Airport_1.Airport("POZ", "S", "C"),
    new Airport_1.Airport("RZE", "S", "P"),
    new Airport_1.Airport("SZZ", "S", "C"),
];
flightControl.availableAirports.forEach(function (airport) {
    airport.attach(flightControl);
});
var id = 0;
var flightsToRemove = [];
setInterval(function () {
    console.clear();
    for (var index = 0; index < consts_1.PLANE_SPAWN_COUNT; index++) {
        var rand = Math.random();
        if (rand < 0.25)
            flights.push(smallGenerator.createCargoFlight(id));
        else if (rand < 0.5)
            flights.push(bigGenerator.createCargoFlight(id));
        else if (rand < 0.75)
            flights.push(smallGenerator.createPassengerFlight(id));
        else
            flights.push(bigGenerator.createPassengerFlight(id));
        id += 1;
        console.log("Detected a new flight: " + flights[flights.length - 1].icon);
    }
    console.log();
    console.log("Incoming flights:");
    console.log(flights.map(function (f) { return f.icon; }));
    console.log();
    console.log("Available airports:");
    console.log(flightControl.availableAirports.map(function (a) { return a.name; }));
    console.log();
    flightsToRemove = [];
    flights.forEach(function (flight) {
        if (flightControl.handleLandingRequest(flight)) {
            flightsToRemove.push(flight);
        }
    });
    flights = flights.filter(function (flight) { return !flightsToRemove.includes(flight); });
    console.log();
}, consts_1.TURN_TIME);
