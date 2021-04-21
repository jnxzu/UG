const { performance } = require("perf_hooks");
var Product;
(function (Product) {
  Product[(Product["ArtisticChair"] = 0)] = "ArtisticChair";
  Product[(Product["RegularChair"] = 1)] = "RegularChair";
  Product[(Product["ClassicalChair"] = 2)] = "ClassicalChair";
  Product[(Product["IkeaChair"] = 3)] = "IkeaChair";
  Product[(Product["ArtisticTable"] = 4)] = "ArtisticTable";
  Product[(Product["RegularTable"] = 5)] = "RegularTable";
  Product[(Product["ClassicalTable"] = 6)] = "ClassicalTable";
  Product[(Product["IkeaTable"] = 7)] = "IkeaTable";
  Product[(Product["ArtisticSofa"] = 8)] = "ArtisticSofa";
  Product[(Product["RegularSofa"] = 9)] = "RegularSofa";
  Product[(Product["ClassicalSofa"] = 10)] = "ClassicalSofa";
  Product[(Product["IkeaSofa"] = 11)] = "IkeaSofa";
  Product[(Product["ArtisticShelf"] = 12)] = "ArtisticShelf";
  Product[(Product["RegularShelf"] = 13)] = "RegularShelf";
  Product[(Product["ClassicalShelf"] = 14)] = "ClassicalShelf";
  Product[(Product["IkeaShelf"] = 15)] = "IkeaShelf";
})(Product || (Product = {}));
var ArtisticChair = /** @class */ (function () {
  function ArtisticChair() {}
  ArtisticChair.prototype.describe = function () {
    console.log("It looks nice and you can sit on it!");
  };
  return ArtisticChair;
})();
var RegularChair = /** @class */ (function () {
  function RegularChair() {}
  RegularChair.prototype.describe = function () {
    console.log("Well you can sit on it!");
  };
  return RegularChair;
})();
var ClassicalChair = /** @class */ (function () {
  function ClassicalChair() {}
  ClassicalChair.prototype.describe = function () {
    console.log("It's very serious and you can sit on it!");
  };
  return ClassicalChair;
})();
var IkeaChair = /** @class */ (function () {
  function IkeaChair() {}
  IkeaChair.prototype.describe = function () {
    console.log("You can sit on it and you built it yourself!");
  };
  return IkeaChair;
})();
var ArtisticTable = /** @class */ (function () {
  function ArtisticTable() {}
  ArtisticTable.prototype.describe = function () {
    console.log("It looks nice and you can eat dinner at it!");
  };
  return ArtisticTable;
})();
var RegularTable = /** @class */ (function () {
  function RegularTable() {}
  RegularTable.prototype.describe = function () {
    console.log("Well you can eat dinner at it!");
  };
  return RegularTable;
})();
var ClassicalTable = /** @class */ (function () {
  function ClassicalTable() {}
  ClassicalTable.prototype.describe = function () {
    console.log("It's very serious and you can eat dinner at it!");
  };
  return ClassicalTable;
})();
var IkeaTable = /** @class */ (function () {
  function IkeaTable() {}
  IkeaTable.prototype.describe = function () {
    console.log("You can eat dinner at it and you built it yourself!");
  };
  return IkeaTable;
})();
var ArtisticSofa = /** @class */ (function () {
  function ArtisticSofa() {}
  ArtisticSofa.prototype.describe = function () {
    console.log("It looks nice and you can use it for a nap!");
  };
  return ArtisticSofa;
})();
var RegularSofa = /** @class */ (function () {
  function RegularSofa() {}
  RegularSofa.prototype.describe = function () {
    console.log("Well you can use it for a nap!");
  };
  return RegularSofa;
})();
var ClassicalSofa = /** @class */ (function () {
  function ClassicalSofa() {}
  ClassicalSofa.prototype.describe = function () {
    console.log("It's very serious and you can use it for a nap!");
  };
  return ClassicalSofa;
})();
var IkeaSofa = /** @class */ (function () {
  function IkeaSofa() {}
  IkeaSofa.prototype.describe = function () {
    console.log("You can use it for a nap and you built it yourself!");
  };
  return IkeaSofa;
})();
var ArtisticShelf = /** @class */ (function () {
  function ArtisticShelf() {}
  ArtisticShelf.prototype.describe = function () {
    console.log("It looks nice and you can put flowers on it!");
  };
  return ArtisticShelf;
})();
var RegularShelf = /** @class */ (function () {
  function RegularShelf() {}
  RegularShelf.prototype.describe = function () {
    console.log("Well you can put flowers on it!");
  };
  return RegularShelf;
})();
var ClassicalShelf = /** @class */ (function () {
  function ClassicalShelf() {}
  ClassicalShelf.prototype.describe = function () {
    console.log("It's very serious and you can put flowers on it!");
  };
  return ClassicalShelf;
})();
var IkeaShelf = /** @class */ (function () {
  function IkeaShelf() {}
  IkeaShelf.prototype.describe = function () {
    console.log("You can put flowers on it and you built it yourself!");
  };
  return IkeaShelf;
})();
var SimpleFactory = /** @class */ (function () {
  function SimpleFactory() {}
  SimpleFactory.getInstance = function () {
    if (!SimpleFactory.instance) SimpleFactory.instance = new SimpleFactory();
    return SimpleFactory.instance;
  };
  SimpleFactory.prototype.produce = function (product) {
    switch (product) {
      // CHAIRS
      case Product.ArtisticChair:
        return new ArtisticChair();
      case Product.RegularChair:
        return new RegularChair();
      case Product.ClassicalChair:
        return new ClassicalChair();
      case Product.IkeaChair:
        return new IkeaChair();
      // SOFAS
      case Product.ArtisticSofa:
        return new ArtisticSofa();
      case Product.RegularSofa:
        return new RegularSofa();
      case Product.ClassicalSofa:
        return new ClassicalSofa();
      case Product.IkeaSofa:
        return new IkeaSofa();
      // TABLES
      case Product.ArtisticTable:
        return new ArtisticTable();
      case Product.RegularTable:
        return new RegularTable();
      case Product.ClassicalTable:
        return new ClassicalTable();
      case Product.IkeaTable:
        return new IkeaTable();
      // SHELVES
      case Product.ArtisticShelf:
        return new ArtisticShelf();
      case Product.RegularShelf:
        return new RegularShelf();
      case Product.ClassicalShelf:
        return new ClassicalShelf();
      case Product.IkeaShelf:
        return new IkeaShelf();
    }
  };
  return SimpleFactory;
})();
var factory = SimpleFactory.getInstance();

console.log("Regular Chair:");
factory.produce(Product.RegularChair).describe();
console.log("Classical Table:");
factory.produce(Product.ClassicalTable).describe();
console.log("Ikea Sofa:");
factory.produce(Product.IkeaSofa).describe();

const timeStart = performance.now();
const initialMemoryUsage = process.memoryUsage().heapUsed;
const products = [];

for (let index = 0; index < 1000000; index++) {
  products.push([
    factory.produce(Product.ArtisticChair),
    factory.produce(Product.RegularChair),
    factory.produce(Product.ClassicalChair),
    factory.produce(Product.IkeaChair),

    factory.produce(Product.ArtisticTable),
    factory.produce(Product.RegularTable),
    factory.produce(Product.ClassicalTable),
    factory.produce(Product.IkeaTable),

    factory.produce(Product.ArtisticSofa),
    factory.produce(Product.RegularSofa),
    factory.produce(Product.ClassicalSofa),
    factory.produce(Product.IkeaSofa),

    factory.produce(Product.ArtisticShelf),
    factory.produce(Product.RegularShelf),
    factory.produce(Product.ClassicalShelf),
    factory.produce(Product.IkeaShelf),
  ]);
}

const timeEnd = performance.now();
const newMemoryUsage = process.memoryUsage().heapUsed;
const diff = newMemoryUsage - initialMemoryUsage;

console.log(`\nTotal allocated space: ${diff / 1000000}mb.`);
console.log(`On average ${Math.round(diff / 16000000)} bytes per product`);
console.log(`Execution took: ${timeEnd - timeStart} ms.`);
