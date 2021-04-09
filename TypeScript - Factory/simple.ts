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
  public describe() {
    console.log("It looks nice and you can sit on it!");
  }
}
class RegularChair {
  public describe() {
    console.log("Well you can sit on it!");
  }
}
class ClassicalChair {
  public describe() {
    console.log("It's very serious and you can sit on it!");
  }
}
class IkeaChair {
  public describe() {
    console.log("You can sit on it and you built it yourself!");
  }
}

// TABLES
class ArtisticTable {
  public describe() {
    console.log("It looks nice and you can eat dinner at it!");
  }
}
class RegularTable {
  public describe() {
    console.log("Well you can eat dinner at it!");
  }
}
class ClassicalTable {
  public describe() {
    console.log("It's very serious and you can eat dinner at it!");
  }
}
class IkeaTable {
  public describe() {
    console.log("You can eat dinner at it and you built it yourself!");
  }
}

// SOFAS
class ArtisticSofa {
  public describe() {
    console.log("It looks nice and you can use it for a nap!");
  }
}
class RegularSofa {
  public describe() {
    console.log("Well you can use it for a nap!");
  }
}
class ClassicalSofa {
  public describe() {
    console.log("It's very serious and you can use it for a nap!");
  }
}
class IkeaSofa {
  public describe() {
    console.log("You can use it for a nap and you built it yourself!");
  }
}

// SHELVES
class ArtisticShelf {
  public describe() {
    console.log("It looks nice and you can put flowers on it!");
  }
}
class RegularShelf {
  public describe() {
    console.log("Well you can put flowers on it!");
  }
}
class ClassicalShelf {
  public describe() {
    console.log("It's very serious and you can put flowers on it!");
  }
}
class IkeaShelf {
  public describe() {
    console.log("You can put flowers on it and you built it yourself!");
  }
}

class SimpleFactory {
  private static instance: SimpleFactory;

  private constructor() {}

  public static getInstance(): SimpleFactory {
    if (!SimpleFactory.instance) SimpleFactory.instance = new SimpleFactory();
    return SimpleFactory.instance;
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
