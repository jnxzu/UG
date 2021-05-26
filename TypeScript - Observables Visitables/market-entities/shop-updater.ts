import { IVisitor } from "./interfaces";
import Shop from "./shop";

export default class ShopUpdater implements IVisitor {
  constructor() {}

  visit(target: Shop, operation: string) {
    if (operation === "price") {
      target.prevSales = target.totalSales;
      target.totalSales = 0;

      target.finalPrice =
        target.basePrice * target.inflation * target.profitMargin;
      console.log(`SHOP UPDATER: Product price set at ${target.finalPrice}.`);
      return;
    }

    if (operation === "sale") {
      for (const client of target.observers) {
        for (let index = 0; index < 1000; index++) {
          if (Math.random() <= client.chanceToBuy) target.totalSales += 1;
        }
      }
      console.log(
        `SHOP UPDATER: Made ${target.totalSales} sales for a total of ${
          target.totalSales * target.finalPrice
        }.`
      );

      target.totalSales = target.totalSales * target.finalPrice;

      if (target.prevSales) {
        if (target.totalSales > target.prevSales) {
          console.log(`${target.name} SHOP: Sales increased.`);
          if (target.profitMargin > 1.1) target.profitMargin += 0.05;
        }

        if (target.totalSales < target.prevSales) {
          console.log(`${target.name} SHOP: Sales dropped.`);
          target.profitMargin -= 0.05;
        }
        console.log(
          `SHOP UPDATER: New profit margin set at ${target.profitMargin}.`
        );
      }

      target.finalPrice = null;
      return;
    }
  }
}
