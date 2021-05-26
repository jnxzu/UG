"use strict";
exports.__esModule = true;
var ShopUpdater = /** @class */ (function () {
    function ShopUpdater() {
    }
    ShopUpdater.prototype.visit = function (target, operation) {
        if (operation === "price") {
            target.prevSales = target.totalSales;
            target.totalSales = 0;
            target.finalPrice =
                target.basePrice * target.inflation * target.profitMargin;
            console.log("SHOP UPDATER: Product price set at " + target.finalPrice + ".");
            return;
        }
        if (operation === "sale") {
            for (var _i = 0, _a = target.observers; _i < _a.length; _i++) {
                var client = _a[_i];
                for (var index = 0; index < 1000; index++) {
                    if (Math.random() <= client.chanceToBuy)
                        target.totalSales += 1;
                }
            }
            console.log("SHOP UPDATER: Made " + target.totalSales + " sales for a total of " + target.totalSales * target.finalPrice + ".");
            target.totalSales = target.totalSales * target.finalPrice;
            if (target.prevSales) {
                if (target.totalSales > target.prevSales) {
                    console.log(target.name + " SHOP: Sales increased.");
                    if (target.profitMargin > 1.1)
                        target.profitMargin += 0.05;
                }
                if (target.totalSales < target.prevSales) {
                    console.log(target.name + " SHOP: Sales dropped.");
                    target.profitMargin -= 0.05;
                }
                console.log("SHOP UPDATER: New profit margin set at " + target.profitMargin + ".");
            }
            target.finalPrice = null;
            return;
        }
    };
    return ShopUpdater;
}());
exports["default"] = ShopUpdater;
