import CentralBank from "./market-entities/central-bank";
import ShopUpdater from "./market-entities/shop-updater";
import Shop from "./market-entities/shop";
import Buyer from "./market-entities/buyer";
import { getYesNo as query } from "cli-interact";

const bank = new CentralBank();
const shopUpdater = new ShopUpdater();

const carDealership = new Shop("CAR", 15000);
const travelAgency = new Shop("TRAVEL", 5000);
const hardwareStore = new Shop("HARDWARE", 1000);

const carBuyer1 = new Buyer("CAR1", 0.65);
const carBuyer2 = new Buyer("CAR2", 0.5);
const carBuyer3 = new Buyer("CAR3", 0.35);
const travelBuyer1 = new Buyer("TRAVEL1", 0.65);
const travelBuyer2 = new Buyer("TRAVEL2", 0.5);
const travelBuyer3 = new Buyer("TRAVEL3", 0.35);
const hardwareBuyer1 = new Buyer("HARDWARE1", 0.65);
const hardwareBuyer2 = new Buyer("HARDWARE2", 0.5);
const hardwareBuyer3 = new Buyer("HARDWARE3", 0.35);

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

  query("\nProceed");
}
