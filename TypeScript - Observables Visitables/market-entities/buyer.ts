import { IObserver } from "./interfaces";

export default class Buyer implements IObserver {
  name: string;
  prevPrice: number;
  currentPrice: number;
  chanceToBuy: number;

  constructor(name: string, chance: number) {
    this.name = name;
    this.currentPrice = null;
    this.chanceToBuy = chance;
  }

  public update(newPrice: number, _) {
    if (!newPrice) return;

    this.prevPrice = this.currentPrice;
    this.currentPrice = newPrice;
    console.log(
      `${this.name} BUYER: New product price recieved. (${this.currentPrice})`
    );

    if (!this.prevPrice || this.prevPrice === this.currentPrice) return;

    if (this.currentPrice > this.prevPrice) {
      if (this.chanceToBuy > 0.25) this.chanceToBuy -= 0.025;
    }

    if (this.currentPrice < this.prevPrice) {
      if (this.chanceToBuy < 0.75) this.chanceToBuy += 0.025;
    }

    console.log(
      `${this.name} BUYER: New chance to buy set at ${Math.round(
        this.chanceToBuy * 100
      )}%.`
    );
  }
}
