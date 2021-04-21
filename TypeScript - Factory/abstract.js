const { performance } = require("perf_hooks");
var __extends =
  (this && this.__extends) ||
  (function () {
    var extendStatics = function (d, b) {
      extendStatics =
        Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array &&
          function (d, b) {
            d.__proto__ = b;
          }) ||
        function (d, b) {
          for (var p in b)
            if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p];
        };
      return extendStatics(d, b);
    };
    return function (d, b) {
      if (typeof b !== "function" && b !== null)
        throw new TypeError(
          "Class extends value " + String(b) + " is not a constructor or null"
        );
      extendStatics(d, b);
      function __() {
        this.constructor = d;
      }
      d.prototype =
        b === null
          ? Object.create(b)
          : ((__.prototype = b.prototype), new __());
    };
  })();
var ArtisticChair = /** @class */ (function () {
  function ArtisticChair() {}
  ArtisticChair.prototype.sit = function () {
    console.log("ArtisticChair");
  };
  return ArtisticChair;
})();
var RegularChair = /** @class */ (function () {
  function RegularChair() {}
  RegularChair.prototype.sit = function () {
    console.log("RegularChair");
  };
  return RegularChair;
})();
var ClassicalChair = /** @class */ (function () {
  function ClassicalChair() {}
  ClassicalChair.prototype.sit = function () {
    console.log("ClassicalChair");
  };
  return ClassicalChair;
})();
var IkeaChair = /** @class */ (function () {
  function IkeaChair() {}
  IkeaChair.prototype.sit = function () {
    console.log("IkeaChair");
  };
  return IkeaChair;
})();
var ArtisticTable = /** @class */ (function () {
  function ArtisticTable() {}
  ArtisticTable.prototype.dinner = function () {
    console.log("ArtisticTable");
  };
  return ArtisticTable;
})();
var RegularTable = /** @class */ (function () {
  function RegularTable() {}
  RegularTable.prototype.dinner = function () {
    console.log("RegularTable");
  };
  return RegularTable;
})();
var ClassicalTable = /** @class */ (function () {
  function ClassicalTable() {}
  ClassicalTable.prototype.dinner = function () {
    console.log("ClassicalTable");
  };
  return ClassicalTable;
})();
var IkeaTable = /** @class */ (function () {
  function IkeaTable() {}
  IkeaTable.prototype.dinner = function () {
    console.log("IkeaTable");
  };
  return IkeaTable;
})();
var ArtisticSofa = /** @class */ (function () {
  function ArtisticSofa() {}
  ArtisticSofa.prototype.nap = function () {
    console.log("ArtisticSofa");
  };
  return ArtisticSofa;
})();
var RegularSofa = /** @class */ (function () {
  function RegularSofa() {}
  RegularSofa.prototype.nap = function () {
    console.log("RegularSofa");
  };
  return RegularSofa;
})();
var ClassicalSofa = /** @class */ (function () {
  function ClassicalSofa() {}
  ClassicalSofa.prototype.nap = function () {
    console.log("ClassicalSofa");
  };
  return ClassicalSofa;
})();
var IkeaSofa = /** @class */ (function () {
  function IkeaSofa() {}
  IkeaSofa.prototype.nap = function () {
    console.log("IkeaSofa");
  };
  return IkeaSofa;
})();
var ArtisticShelf = /** @class */ (function () {
  function ArtisticShelf() {}
  ArtisticShelf.prototype.flowers = function () {
    console.log("ArtisticShelf");
  };
  return ArtisticShelf;
})();
var RegularShelf = /** @class */ (function () {
  function RegularShelf() {}
  RegularShelf.prototype.flowers = function () {
    console.log("RegularShelf");
  };
  return RegularShelf;
})();
var ClassicalShelf = /** @class */ (function () {
  function ClassicalShelf() {}
  ClassicalShelf.prototype.flowers = function () {
    console.log("ClassicalShelf");
  };
  return ClassicalShelf;
})();
var IkeaShelf = /** @class */ (function () {
  function IkeaShelf() {}
  IkeaShelf.prototype.flowers = function () {
    console.log("IkeaShelf");
  };
  return IkeaShelf;
})();
var AbstractFactory = /** @class */ (function () {
  function AbstractFactory() {}
  return AbstractFactory;
})();
var ArtisticFurnitureFactory = /** @class */ (function (_super) {
  __extends(ArtisticFurnitureFactory, _super);
  function ArtisticFurnitureFactory() {
    return _super.call(this) || this;
  }
  ArtisticFurnitureFactory.getInstance = function () {
    if (!ArtisticFurnitureFactory.artisticFurnitureFactoryInstance)
      ArtisticFurnitureFactory.artisticFurnitureFactoryInstance = new ArtisticFurnitureFactory();
    return ArtisticFurnitureFactory.artisticFurnitureFactoryInstance;
  };
  ArtisticFurnitureFactory.prototype.produceChair = function () {
    return new ArtisticChair();
  };
  ArtisticFurnitureFactory.prototype.produceTable = function () {
    return new ArtisticTable();
  };
  ArtisticFurnitureFactory.prototype.produceSofa = function () {
    return new ArtisticSofa();
  };
  ArtisticFurnitureFactory.prototype.produceShelf = function () {
    return new ArtisticShelf();
  };
  return ArtisticFurnitureFactory;
})(AbstractFactory);
var RegularFurnitureFactory = /** @class */ (function (_super) {
  __extends(RegularFurnitureFactory, _super);
  function RegularFurnitureFactory() {
    return _super.call(this) || this;
  }
  RegularFurnitureFactory.getInstance = function () {
    if (!RegularFurnitureFactory.regularFurnitureFactoryInstance)
      RegularFurnitureFactory.regularFurnitureFactoryInstance = new RegularFurnitureFactory();
    return RegularFurnitureFactory.regularFurnitureFactoryInstance;
  };
  RegularFurnitureFactory.prototype.produceChair = function () {
    return new RegularChair();
  };
  RegularFurnitureFactory.prototype.produceTable = function () {
    return new RegularTable();
  };
  RegularFurnitureFactory.prototype.produceSofa = function () {
    return new RegularSofa();
  };
  RegularFurnitureFactory.prototype.produceShelf = function () {
    return new RegularShelf();
  };
  return RegularFurnitureFactory;
})(AbstractFactory);
var ClassicalFurnitureFactory = /** @class */ (function (_super) {
  __extends(ClassicalFurnitureFactory, _super);
  function ClassicalFurnitureFactory() {
    return _super.call(this) || this;
  }
  ClassicalFurnitureFactory.getInstance = function () {
    if (!ClassicalFurnitureFactory.classicalFurnitureFactoryInstance)
      ClassicalFurnitureFactory.classicalFurnitureFactoryInstance = new ClassicalFurnitureFactory();
    return ClassicalFurnitureFactory.classicalFurnitureFactoryInstance;
  };
  ClassicalFurnitureFactory.prototype.produceChair = function () {
    return new ClassicalChair();
  };
  ClassicalFurnitureFactory.prototype.produceTable = function () {
    return new ClassicalTable();
  };
  ClassicalFurnitureFactory.prototype.produceSofa = function () {
    return new ClassicalSofa();
  };
  ClassicalFurnitureFactory.prototype.produceShelf = function () {
    return new ClassicalShelf();
  };
  return ClassicalFurnitureFactory;
})(AbstractFactory);
var IkeaFurnitureFactory = /** @class */ (function (_super) {
  __extends(IkeaFurnitureFactory, _super);
  function IkeaFurnitureFactory() {
    return _super.call(this) || this;
  }
  IkeaFurnitureFactory.getInstance = function () {
    if (!IkeaFurnitureFactory.ikeaFurnitureFactoryInstance)
      IkeaFurnitureFactory.ikeaFurnitureFactoryInstance = new IkeaFurnitureFactory();
    return IkeaFurnitureFactory.ikeaFurnitureFactoryInstance;
  };
  IkeaFurnitureFactory.prototype.produceChair = function () {
    return new IkeaChair();
  };
  IkeaFurnitureFactory.prototype.produceTable = function () {
    return new IkeaTable();
  };
  IkeaFurnitureFactory.prototype.produceSofa = function () {
    return new IkeaSofa();
  };
  IkeaFurnitureFactory.prototype.produceShelf = function () {
    return new IkeaShelf();
  };
  return IkeaFurnitureFactory;
})(AbstractFactory);
function client(factory) {
  return [
    factory.produceChair(),
    factory.produceTable(),
    factory.produceSofa(),
    factory.produceShelf(),
  ];
}

console.log("Regular Chair:");
client(RegularFurnitureFactory.getInstance())[0].sit();
console.log("Classical Table:");
client(ClassicalFurnitureFactory.getInstance())[1].dinner();
console.log("Ikea Sofa:");
client(IkeaFurnitureFactory.getInstance())[2].nap();

const timeStart = performance.now();
const initialMemoryUsage = process.memoryUsage().heapUsed;
const products = [];

for (let index = 0; index < 1000000; index++) {
  products.push(client(ArtisticFurnitureFactory.getInstance()));
  products.push(client(RegularFurnitureFactory.getInstance()));
  products.push(client(ClassicalFurnitureFactory.getInstance()));
  products.push(client(IkeaFurnitureFactory.getInstance()));
}

const timeEnd = performance.now();
const newMemoryUsage = process.memoryUsage().heapUsed;
const diff = newMemoryUsage - initialMemoryUsage;

console.log(`\nTotal allocated space: ${diff / 1000000}mb.`);
console.log(`On average ${Math.round(diff / 16000000)} bytes per product`);
console.log(`Execution took: ${timeEnd - timeStart} ms.`);
