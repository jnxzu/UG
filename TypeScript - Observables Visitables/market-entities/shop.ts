import { IVisitable, IObservable, IObserver } from "./interfaces";
import ShopUpdater from "./shop-updater";

export default class Shop implements IVisitable, IObservable, IObserver {
  name: string;
  basePrice: number;
  inflation: number;
  profitMargin: number;
  finalPrice: number;
  prevSales: number;
  totalSales: number;
  observers: IObserver[];

  constructor(name: string, price: number) {
    this.name = name;
    this.basePrice = price;
    this.inflation = null;
    this.profitMargin = 1.5;
    this.prevSales = null;
    this.totalSales = null;
    this.finalPrice = null;
    this.observers = [];
  }

  accept(visitor: ShopUpdater, operation: string) {
    visitor.visit(this, operation);
  }

  attach(observer: IObserver) {
    this.observers.push(observer);
  }

  detach(observer: IObserver) {
    const idx = this.observers.indexOf(observer);
    this.observers.splice(idx, 1);
  }

  notify() {
    for (const obs of this.observers) {
      obs.update(this.finalPrice, this.totalSales);
    }
  }

  update(inflation: number, _) {
    if (inflation) {
      this.inflation = inflation;
      console.log(
        `${this.name} SHOP: New inflation value recieved. (${this.inflation})`
      );
    }
  }
}
