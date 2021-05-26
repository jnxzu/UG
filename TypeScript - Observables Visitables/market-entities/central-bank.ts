import { IObservable, IObserver } from "./interfaces";

export default class CentralBank implements IObservable, IObserver {
  previousSales: number;
  totalSales: number;
  inflation: number;
  observers: IObserver[];
  salesIncreaseCounter: number;

  constructor() {
    this.previousSales = null;
    this.totalSales = null;
    this.inflation = 1.05;
    this.observers = [];
    this.salesIncreaseCounter = 0;
  }

  adjustInflation() {
    if (this.totalSales && this.previousSales && this.inflation > 1.01) {
      if (this.previousSales < this.totalSales) {
        this.inflation += 0.01;
        console.log("BANK: Total sales increased.");
      }
      if (this.previousSales > this.totalSales) {
        this.inflation -= 0.01;
        console.log("BANK: Total sales dropped.");
      }
      console.log(`BANK: Inflation set at ${this.inflation}.`);
    }

    this.previousSales = this.totalSales;
    this.totalSales = 0;
  }

  attach(observer: IObserver) {
    this.observers.push(observer);
  }

  detach(observer: IObserver) {
    const idx = this.observers.indexOf(observer);
    this.observers.splice(idx, 1);
  }

  notify() {
    for (const observer of this.observers) {
      observer.update(this.inflation, null);
    }
  }

  update(_, newSales: number) {
    if (!newSales) return;
    console.log(`BANK: New sales recieved. (${newSales})`);
    this.totalSales += newSales;
  }
}
