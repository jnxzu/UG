"use strict";
exports.__esModule = true;
var uuid_1 = require("uuid");
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
// CHAIRS
var ArtisticChair = /** @class */ (function () {
  function ArtisticChair() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, ArtisticChair.name);
  }
  ArtisticChair.prototype.describe = function () {
    console.log("It looks nice and you can sit on it!");
  };
  return ArtisticChair;
})();
var RegularChair = /** @class */ (function () {
  function RegularChair() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, RegularChair.name);
  }
  RegularChair.prototype.describe = function () {
    console.log("Well you can sit on it!");
  };
  return RegularChair;
})();
var ClassicalChair = /** @class */ (function () {
  function ClassicalChair() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, ClassicalChair.name);
  }
  ClassicalChair.prototype.describe = function () {
    console.log("It's very serious and you can sit on it!");
  };
  return ClassicalChair;
})();
var IkeaChair = /** @class */ (function () {
  function IkeaChair() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, IkeaChair.name);
  }
  IkeaChair.prototype.describe = function () {
    console.log("You can sit on it and you built it yourself!");
  };
  return IkeaChair;
})();
// TABLES
var ArtisticTable = /** @class */ (function () {
  function ArtisticTable() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, ArtisticTable.name);
  }
  ArtisticTable.prototype.describe = function () {
    console.log("It looks nice and you can eat dinner at it!");
  };
  return ArtisticTable;
})();
var RegularTable = /** @class */ (function () {
  function RegularTable() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, RegularTable.name);
  }
  RegularTable.prototype.describe = function () {
    console.log("Well you can eat dinner at it!");
  };
  return RegularTable;
})();
var ClassicalTable = /** @class */ (function () {
  function ClassicalTable() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, ClassicalTable.name);
  }
  ClassicalTable.prototype.describe = function () {
    console.log("It's very serious and you can eat dinner at it!");
  };
  return ClassicalTable;
})();
var IkeaTable = /** @class */ (function () {
  function IkeaTable() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, IkeaTable.name);
  }
  IkeaTable.prototype.describe = function () {
    console.log("You can eat dinner at it and you built it yourself!");
  };
  return IkeaTable;
})();
// SOFAS
var ArtisticSofa = /** @class */ (function () {
  function ArtisticSofa() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, ArtisticSofa.name);
  }
  ArtisticSofa.prototype.describe = function () {
    console.log("It looks nice and you can use it for a nap!");
  };
  return ArtisticSofa;
})();
var RegularSofa = /** @class */ (function () {
  function RegularSofa() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, RegularSofa.name);
  }
  RegularSofa.prototype.describe = function () {
    console.log("Well you can use it for a nap!");
  };
  return RegularSofa;
})();
var ClassicalSofa = /** @class */ (function () {
  function ClassicalSofa() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, ClassicalSofa.name);
  }
  ClassicalSofa.prototype.describe = function () {
    console.log("It's very serious and you can use it for a nap!");
  };
  return ClassicalSofa;
})();
var IkeaSofa = /** @class */ (function () {
  function IkeaSofa() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, IkeaSofa.name);
  }
  IkeaSofa.prototype.describe = function () {
    console.log("You can use it for a nap and you built it yourself!");
  };
  return IkeaSofa;
})();
// SHELVES
var ArtisticShelf = /** @class */ (function () {
  function ArtisticShelf() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, ArtisticShelf.name);
  }
  ArtisticShelf.prototype.describe = function () {
    console.log("It looks nice and you can put flowers on it!");
  };
  return ArtisticShelf;
})();
var RegularShelf = /** @class */ (function () {
  function RegularShelf() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, RegularShelf.name);
  }
  RegularShelf.prototype.describe = function () {
    console.log("Well you can put flowers on it!");
  };
  return RegularShelf;
})();
var ClassicalShelf = /** @class */ (function () {
  function ClassicalShelf() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, ClassicalShelf.name);
  }
  ClassicalShelf.prototype.describe = function () {
    console.log("It's very serious and you can put flowers on it!");
  };
  return ClassicalShelf;
})();
var IkeaShelf = /** @class */ (function () {
  function IkeaShelf() {
    this.id = uuid_1.v4();
    SimpleFactory.getInstance().register(this.id, IkeaShelf.name);
  }
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
  SimpleFactory.prototype.register = function (id, className) {
    SimpleFactory.registry.set(id, className);
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
  SimpleFactory.registry = new Map();
  return SimpleFactory;
})();
var factory = SimpleFactory.getInstance();

const initialMemoryUsage = process.memoryUsage().heapUsed;
factory.produce(Product.RegularChair).describe();
factory.produce(Product.ArtisticShelf).describe();
factory.produce(Product.ClassicalTable).describe();
factory.produce(Product.IkeaSofa).describe();
factory.produce(Product.IkeaShelf).describe();
const newMemoryUsage = process.memoryUsage().heapUsed;
const diff = newMemoryUsage - initialMemoryUsage;
console.log(diff / 1000, "kb");
console.log(diff / 1000 / 5, "kb per product");

console.log(SimpleFactory.registry);
