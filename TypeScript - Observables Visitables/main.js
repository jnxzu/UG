"use strict";
exports.__esModule = true;
var central_bank_1 = require("./market-entities/central-bank");
var shop_updater_1 = require("./market-entities/shop-updater");
var shop_1 = require("./market-entities/shop");
var buyer_1 = require("./market-entities/buyer");
var cli_interact_1 = require("cli-interact");
var bank = new central_bank_1["default"]();
var shopUpdater = new shop_updater_1["default"]();
var carDealership = new shop_1["default"]("CAR", 15000);
var travelAgency = new shop_1["default"]("TRAVEL", 5000);
var hardwareStore = new shop_1["default"]("HARDWARE", 1000);
var carBuyer1 = new buyer_1["default"]("CAR1", 0.65);
var carBuyer2 = new buyer_1["default"]("CAR2", 0.5);
var carBuyer3 = new buyer_1["default"]("CAR3", 0.35);
var travelBuyer1 = new buyer_1["default"]("TRAVEL1", 0.65);
var travelBuyer2 = new buyer_1["default"]("TRAVEL2", 0.5);
var travelBuyer3 = new buyer_1["default"]("TRAVEL3", 0.35);
var hardwareBuyer1 = new buyer_1["default"]("HARDWARE1", 0.65);
var hardwareBuyer2 = new buyer_1["default"]("HARDWARE2", 0.5);
var hardwareBuyer3 = new buyer_1["default"]("HARDWARE3", 0.35);
bank.attach(carDealership);
bank.attach(travelAgency);
bank.attach(hardwareStore);
carDealership.attach(bank);
travelAgency.attach(bank);
hardwareStore.attach(bank);
carDealership.attach(carBuyer1);
carDealership.attach(carBuyer2);
carDealership.attach(carBuyer3);
travelAgency.attach(travelBuyer1);
travelAgency.attach(travelBuyer2);
travelAgency.attach(travelBuyer3);
hardwareStore.attach(hardwareBuyer1);
hardwareStore.attach(hardwareBuyer2);
hardwareStore.attach(hardwareBuyer3);
while (true) {
    console.clear();
    console.log("-----\t\tBANK ADJUSTS AND NOTIFIES ABOUT INFLATION");
    bank.adjustInflation(); // korekcja inflacji
    bank.notify(); // info o inflacji
    console.log("-----\t\tSHOP UPDATER CHANGES PRODUCT PRICES");
    carDealership.accept(shopUpdater, "price"); // korekcja ceny
    travelAgency.accept(shopUpdater, "price"); // korekcja ceny
    hardwareStore.accept(shopUpdater, "price"); // korekcja ceny
    console.log("-----\t\tSHOPS NOTIFIES ABOUT NEW PRICES");
    carDealership.notify(); // info o cenie
    travelAgency.notify(); // info o cenie
    hardwareStore.notify(); // info o cenie
    console.log("-----\t\tSHOP UPDATER MAKES TRANSACTIONS");
    carDealership.accept(shopUpdater, "sale"); // sprzedaz
    travelAgency.accept(shopUpdater, "sale"); // sprzedaz
    hardwareStore.accept(shopUpdater, "sale"); // sprzedaz
    console.log("-----\t\tSHOP NOTIFIES ABOUT SALES");
    carDealership.notify(); // info o sprzedazy
    travelAgency.notify(); // info o sprzedazy
    hardwareStore.notify(); // info o sprzedazy
    cli_interact_1.getYesNo("\nProceed");
}
