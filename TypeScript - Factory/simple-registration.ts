import { v4 as uuidv4 } from "uuid";

enum Product {
  ArtisticChair,
  RegularChair,
  ClassicalChair,
  IkeaChair,
  ArtisticTable,
  RegularTable,
  ClassicalTable,
  IkeaTable,
  ArtisticSofa,
  RegularSofa,
  ClassicalSofa,
  IkeaSofa,
  ArtisticShelf,
  RegularShelf,
  ClassicalShelf,
  IkeaShelf,
}

// CHAIRS
class ArtisticChair {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, ArtisticChair.name);
  }

  public describe() {
    console.log("It looks nice and you can sit on it!");
  }
}
class RegularChair {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, RegularChair.name);
  }

  public describe() {
    console.log("Well you can sit on it!");
  }
}
class ClassicalChair {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, ClassicalChair.name);
  }

  public describe() {
    console.log("It's very serious and you can sit on it!");
  }
}
class IkeaChair {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, IkeaChair.name);
  }

  public describe() {
    console.log("You can sit on it and you built it yourself!");
  }
}

// TABLES
class ArtisticTable {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, ArtisticTable.name);
  }

  public describe() {
    console.log("It looks nice and you can eat dinner at it!");
  }
}
class RegularTable {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, RegularTable.name);
  }

  public describe() {
    console.log("Well you can eat dinner at it!");
  }
}
class ClassicalTable {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, ClassicalTable.name);
  }

  public describe() {
    console.log("It's very serious and you can eat dinner at it!");
  }
}
class IkeaTable {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, IkeaTable.name);
  }

  public describe() {
    console.log("You can eat dinner at it and you built it yourself!");
  }
}

// SOFAS
class ArtisticSofa {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, ArtisticSofa.name);
  }

  public describe() {
    console.log("It looks nice and you can use it for a nap!");
  }
}
class RegularSofa {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, RegularSofa.name);
  }

  public describe() {
    console.log("Well you can use it for a nap!");
  }
}
class ClassicalSofa {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, ClassicalSofa.name);
  }

  public describe() {
    console.log("It's very serious and you can use it for a nap!");
  }
}
class IkeaSofa {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, IkeaSofa.name);
  }

  public describe() {
    console.log("You can use it for a nap and you built it yourself!");
  }
}

// SHELVES
class ArtisticShelf {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, ArtisticShelf.name);
  }

  public describe() {
    console.log("It looks nice and you can put flowers on it!");
  }
}
class RegularShelf {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, RegularShelf.name);
  }

  public describe() {
    console.log("Well you can put flowers on it!");
  }
}
class ClassicalShelf {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, ClassicalShelf.name);
  }

  public describe() {
    console.log("It's very serious and you can put flowers on it!");
  }
}
class IkeaShelf {
  id: String;
  constructor() {
    this.id = uuidv4();
    SimpleFactory.getInstance().register(this.id, IkeaShelf.name);
  }

  public describe() {
    console.log("You can put flowers on it and you built it yourself!");
  }
}

class SimpleFactory {
  private static instance: SimpleFactory;

  public static registry: Map<String, String> = new Map<String, String>();

  private constructor() {}

  public static getInstance(): SimpleFactory {
    if (!SimpleFactory.instance) SimpleFactory.instance = new SimpleFactory();
    return SimpleFactory.instance;
  }

  public register(id, className) {
    SimpleFactory.registry.set(id, className);
  }

  public produce(product: Product) {
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
  }
}

const factory = SimpleFactory.getInstance();

factory.produce(Product.RegularChair).describe();
factory.produce(Product.ArtisticShelf).describe();
factory.produce(Product.ClassicalTable).describe();
factory.produce(Product.IkeaSofa).describe();
factory.produce(Product.IkeaShelf).describe();

console.log(SimpleFactory.registry);
