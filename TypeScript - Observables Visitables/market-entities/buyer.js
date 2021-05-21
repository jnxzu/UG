"use strict";
exports.__esModule = true;
var Buyer = /** @class */ (function () {
    function Buyer(name, chance) {
        this.name = name;
        this.currentPrice = null;
        this.chanceToBuy = chance;
    }
    Buyer.prototype.update = function (newPrice, _) {
        if (!newPrice)
            return;
        this.prevPrice = this.currentPrice;
        this.currentPrice = newPrice;
        console.log(this.name + " BUYER: New product price recieved. (" + this.currentPrice + ")");
        if (!this.prevPrice || this.prevPrice === this.currentPrice)
            return;
        if (this.currentPrice > this.prevPrice) {
            if (this.chanceToBuy > 0.25)
                this.chanceToBuy -= 0.025;
        }
        if (this.currentPrice < this.prevPrice) {
            if (this.chanceToBuy < 0.75)
                this.chanceToBuy += 0.025;
        }
        console.log(this.name + " BUYER: New chance to buy set at " + Math.round(this.chanceToBuy * 100) + "%.");
    };
    return Buyer;
}());
exports["default"] = Buyer;
