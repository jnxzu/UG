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
// CHAIRS
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
// TABLES
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
// SOFAS
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
// SHELVES
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
var MethodBasedFactory = /** @class */ (function () {
  function MethodBasedFactory() {}
  return MethodBasedFactory;
})();
var ArtisticChairFactory = /** @class */ (function (_super) {
  __extends(ArtisticChairFactory, _super);
  function ArtisticChairFactory() {
    return _super.call(this) || this;
  }
  ArtisticChairFactory.getInstance = function () {
    if (!ArtisticChairFactory.artisticChairFactoryInstance)
      ArtisticChairFactory.artisticChairFactoryInstance = new ArtisticChairFactory();
    return ArtisticChairFactory.artisticChairFactoryInstance;
  };
  ArtisticChairFactory.prototype.factoryMethod = function () {
    return new ArtisticChair();
  };
  return ArtisticChairFactory;
})(MethodBasedFactory);
var RegularChairFactory = /** @class */ (function (_super) {
  __extends(RegularChairFactory, _super);
  function RegularChairFactory() {
    return _super.call(this) || this;
  }
  RegularChairFactory.getInstance = function () {
    if (!RegularChairFactory.regularChairFactoryInstance)
      RegularChairFactory.regularChairFactoryInstance = new RegularChairFactory();
    return RegularChairFactory.regularChairFactoryInstance;
  };
  RegularChairFactory.prototype.factoryMethod = function () {
    return new RegularChair();
  };
  return RegularChairFactory;
})(MethodBasedFactory);
var ClassicalChairFactory = /** @class */ (function (_super) {
  __extends(ClassicalChairFactory, _super);
  function ClassicalChairFactory() {
    return _super.call(this) || this;
  }
  ClassicalChairFactory.getInstance = function () {
    if (!ClassicalChairFactory.classicalChairFactoryInstance)
      ClassicalChairFactory.classicalChairFactoryInstance = new ClassicalChairFactory();
    return ClassicalChairFactory.classicalChairFactoryInstance;
  };
  ClassicalChairFactory.prototype.factoryMethod = function () {
    return new ClassicalChair();
  };
  return ClassicalChairFactory;
})(MethodBasedFactory);
var IkeaChairFactory = /** @class */ (function (_super) {
  __extends(IkeaChairFactory, _super);
  function IkeaChairFactory() {
    return _super.call(this) || this;
  }
  IkeaChairFactory.getInstance = function () {
    if (!IkeaChairFactory.ikeaChairFactoryInstance)
      IkeaChairFactory.ikeaChairFactoryInstance = new IkeaChairFactory();
    return IkeaChairFactory.ikeaChairFactoryInstance;
  };
  IkeaChairFactory.prototype.factoryMethod = function () {
    return new IkeaChair();
  };
  return IkeaChairFactory;
})(MethodBasedFactory);
var ArtisticTableFactory = /** @class */ (function (_super) {
  __extends(ArtisticTableFactory, _super);
  function ArtisticTableFactory() {
    return _super.call(this) || this;
  }
  ArtisticTableFactory.getInstance = function () {
    if (!ArtisticTableFactory.artisticTableFactoryInstance)
      ArtisticTableFactory.artisticTableFactoryInstance = new ArtisticTableFactory();
    return ArtisticTableFactory.artisticTableFactoryInstance;
  };
  ArtisticTableFactory.prototype.factoryMethod = function () {
    return new ArtisticTable();
  };
  return ArtisticTableFactory;
})(MethodBasedFactory);
var RegularTableFactory = /** @class */ (function (_super) {
  __extends(RegularTableFactory, _super);
  function RegularTableFactory() {
    return _super.call(this) || this;
  }
  RegularTableFactory.getInstance = function () {
    if (!RegularTableFactory.regularTableFactoryInstance)
      RegularTableFactory.regularTableFactoryInstance = new RegularTableFactory();
    return RegularTableFactory.regularTableFactoryInstance;
  };
  RegularTableFactory.prototype.factoryMethod = function () {
    return new RegularTable();
  };
  return RegularTableFactory;
})(MethodBasedFactory);
var ClassicalTableFactory = /** @class */ (function (_super) {
  __extends(ClassicalTableFactory, _super);
  function ClassicalTableFactory() {
    return _super.call(this) || this;
  }
  ClassicalTableFactory.getInstance = function () {
    if (!ClassicalTableFactory.classicalTableFactoryInstance)
      ClassicalTableFactory.classicalTableFactoryInstance = new ClassicalTableFactory();
    return ClassicalTableFactory.classicalTableFactoryInstance;
  };
  ClassicalTableFactory.prototype.factoryMethod = function () {
    return new ClassicalTable();
  };
  return ClassicalTableFactory;
})(MethodBasedFactory);
var IkeaTableFactory = /** @class */ (function (_super) {
  __extends(IkeaTableFactory, _super);
  function IkeaTableFactory() {
    return _super.call(this) || this;
  }
  IkeaTableFactory.getInstance = function () {
    if (!IkeaTableFactory.ikeaTableFactoryInstance)
      IkeaTableFactory.ikeaTableFactoryInstance = new IkeaTableFactory();
    return IkeaTableFactory.ikeaTableFactoryInstance;
  };
  IkeaTableFactory.prototype.factoryMethod = function () {
    return new IkeaTable();
  };
  return IkeaTableFactory;
})(MethodBasedFactory);
var ArtisticSofaFactory = /** @class */ (function (_super) {
  __extends(ArtisticSofaFactory, _super);
  function ArtisticSofaFactory() {
    return _super.call(this) || this;
  }
  ArtisticSofaFactory.getInstance = function () {
    if (!ArtisticSofaFactory.artisticSofaFactoryInstance)
      ArtisticSofaFactory.artisticSofaFactoryInstance = new ArtisticSofaFactory();
    return ArtisticSofaFactory.artisticSofaFactoryInstance;
  };
  ArtisticSofaFactory.prototype.factoryMethod = function () {
    return new ArtisticSofa();
  };
  return ArtisticSofaFactory;
})(MethodBasedFactory);
var RegularSofaFactory = /** @class */ (function (_super) {
  __extends(RegularSofaFactory, _super);
  function RegularSofaFactory() {
    return _super.call(this) || this;
  }
  RegularSofaFactory.getInstance = function () {
    if (!RegularSofaFactory.regularSofaFactoryInstance)
      RegularSofaFactory.regularSofaFactoryInstance = new RegularSofaFactory();
    return RegularSofaFactory.regularSofaFactoryInstance;
  };
  RegularSofaFactory.prototype.factoryMethod = function () {
    return new RegularSofa();
  };
  return RegularSofaFactory;
})(MethodBasedFactory);
var ClassicalSofaFactory = /** @class */ (function (_super) {
  __extends(ClassicalSofaFactory, _super);
  function ClassicalSofaFactory() {
    return _super.call(this) || this;
  }
  ClassicalSofaFactory.getInstance = function () {
    if (!ClassicalSofaFactory.classicalSofaFactoryInstance)
      ClassicalSofaFactory.classicalSofaFactoryInstance = new ClassicalSofaFactory();
    return ClassicalSofaFactory.classicalSofaFactoryInstance;
  };
  ClassicalSofaFactory.prototype.factoryMethod = function () {
    return new ClassicalSofa();
  };
  return ClassicalSofaFactory;
})(MethodBasedFactory);
var IkeaSofaFactory = /** @class */ (function (_super) {
  __extends(IkeaSofaFactory, _super);
  function IkeaSofaFactory() {
    return _super.call(this) || this;
  }
  IkeaSofaFactory.getInstance = function () {
    if (!IkeaSofaFactory.ikeaSofaFactoryInstance)
      IkeaSofaFactory.ikeaSofaFactoryInstance = new IkeaSofaFactory();
    return IkeaSofaFactory.ikeaSofaFactoryInstance;
  };
  IkeaSofaFactory.prototype.factoryMethod = function () {
    return new IkeaSofa();
  };
  return IkeaSofaFactory;
})(MethodBasedFactory);
var ArtisticShelfFactory = /** @class */ (function (_super) {
  __extends(ArtisticShelfFactory, _super);
  function ArtisticShelfFactory() {
    return _super.call(this) || this;
  }
  ArtisticShelfFactory.getInstance = function () {
    if (!ArtisticShelfFactory.artisticShelfFactoryInstance)
      ArtisticShelfFactory.artisticShelfFactoryInstance = new ArtisticShelfFactory();
    return ArtisticShelfFactory.artisticShelfFactoryInstance;
  };
  ArtisticShelfFactory.prototype.factoryMethod = function () {
    return new ArtisticShelf();
  };
  return ArtisticShelfFactory;
})(MethodBasedFactory);
var RegularShelfFactory = /** @class */ (function (_super) {
  __extends(RegularShelfFactory, _super);
  function RegularShelfFactory() {
    return _super.call(this) || this;
  }
  RegularShelfFactory.getInstance = function () {
    if (!RegularShelfFactory.regularShelfFactoryInstance)
      RegularShelfFactory.regularShelfFactoryInstance = new RegularShelfFactory();
    return RegularShelfFactory.regularShelfFactoryInstance;
  };
  RegularShelfFactory.prototype.factoryMethod = function () {
    return new RegularShelf();
  };
  return RegularShelfFactory;
})(MethodBasedFactory);
var ClassicalShelfFactory = /** @class */ (function (_super) {
  __extends(ClassicalShelfFactory, _super);
  function ClassicalShelfFactory() {
    return _super.call(this) || this;
  }
  ClassicalShelfFactory.getInstance = function () {
    if (!ClassicalShelfFactory.classicalShelfFactoryInstance)
      ClassicalShelfFactory.classicalShelfFactoryInstance = new ClassicalShelfFactory();
    return ClassicalShelfFactory.classicalShelfFactoryInstance;
  };
  ClassicalShelfFactory.prototype.factoryMethod = function () {
    return new ClassicalShelf();
  };
  return ClassicalShelfFactory;
})(MethodBasedFactory);
var IkeaShelfFactory = /** @class */ (function (_super) {
  __extends(IkeaShelfFactory, _super);
  function IkeaShelfFactory() {
    return _super.call(this) || this;
  }
  IkeaShelfFactory.getInstance = function () {
    if (!IkeaShelfFactory.ikeaShelfFactoryInstance)
      IkeaShelfFactory.ikeaShelfFactoryInstance = new IkeaShelfFactory();
    return IkeaShelfFactory.ikeaShelfFactoryInstance;
  };
  IkeaShelfFactory.prototype.factoryMethod = function () {
    return new IkeaShelf();
  };
  return IkeaShelfFactory;
})(MethodBasedFactory);
function client(factory) {
  return factory.factoryMethod();
}

console.log("Regular Chair:");
client(RegularChairFactory.getInstance()).describe();
console.log("Classical Table:");
client(ClassicalTableFactory.getInstance()).describe();
console.log("Ikea Sofa:");
client(IkeaSofaFactory.getInstance()).describe();

const timeStart = performance.now();
const initialMemoryUsage = process.memoryUsage().heapUsed;
const products = [];

for (let index = 0; index < 1000000; index++) {
  products.push([
    client(ArtisticChairFactory.getInstance()),
    client(RegularChairFactory.getInstance()),
    client(ClassicalChairFactory.getInstance()),
    client(IkeaChairFactory.getInstance()),

    client(ArtisticTableFactory.getInstance()),
    client(RegularTableFactory.getInstance()),
    client(ClassicalTableFactory.getInstance()),
    client(IkeaTableFactory.getInstance()),

    client(ArtisticSofaFactory.getInstance()),
    client(RegularSofaFactory.getInstance()),
    client(ClassicalSofaFactory.getInstance()),
    client(IkeaSofaFactory.getInstance()),

    client(ArtisticShelfFactory.getInstance()),
    client(RegularShelfFactory.getInstance()),
    client(ClassicalShelfFactory.getInstance()),
    client(IkeaShelfFactory.getInstance()),
  ]);
}

const timeEnd = performance.now();
const newMemoryUsage = process.memoryUsage().heapUsed;
const diff = newMemoryUsage - initialMemoryUsage;

console.log(`\nTotal allocated space: ${diff / 1000000}mb.`);
console.log(`On average ${Math.round(diff / 16000000)} bytes per product`);
console.log(`Execution took: ${timeEnd - timeStart} ms.`);
