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

interface IProduct {
  describe(): void;
}

// CHAIRS
class ArtisticChair implements IProduct {
  public describe() {
    console.log("It looks nice and you can sit on it!");
  }
}
class RegularChair implements IProduct {
  public describe() {
    console.log("Well you can sit on it!");
  }
}
class ClassicalChair implements IProduct {
  public describe() {
    console.log("It's very serious and you can sit on it!");
  }
}
class IkeaChair implements IProduct {
  public describe() {
    console.log("You can sit on it and you built it yourself!");
  }
}

// TABLES
class ArtisticTable implements IProduct {
  public describe() {
    console.log("It looks nice and you can eat dinner at it!");
  }
}
class RegularTable implements IProduct {
  public describe() {
    console.log("Well you can eat dinner at it!");
  }
}
class ClassicalTable implements IProduct {
  public describe() {
    console.log("It's very serious and you can eat dinner at it!");
  }
}
class IkeaTable implements IProduct {
  public describe() {
    console.log("You can eat dinner at it and you built it yourself!");
  }
}

// SOFAS
class ArtisticSofa implements IProduct {
  public describe() {
    console.log("It looks nice and you can use it for a nap!");
  }
}
class RegularSofa implements IProduct {
  public describe() {
    console.log("Well you can use it for a nap!");
  }
}
class ClassicalSofa implements IProduct {
  public describe() {
    console.log("It's very serious and you can use it for a nap!");
  }
}
class IkeaSofa implements IProduct {
  public describe() {
    console.log("You can use it for a nap and you built it yourself!");
  }
}

// SHELVES
class ArtisticShelf implements IProduct {
  public describe() {
    console.log("It looks nice and you can put flowers on it!");
  }
}
class RegularShelf implements IProduct {
  public describe() {
    console.log("Well you can put flowers on it!");
  }
}
class ClassicalShelf implements IProduct {
  public describe() {
    console.log("It's very serious and you can put flowers on it!");
  }
}
class IkeaShelf implements IProduct {
  public describe() {
    console.log("You can put flowers on it and you built it yourself!");
  }
}

abstract class MethodBasedFactory {
  protected constructor() {}

  abstract factoryMethod(): IProduct;
}

class RegularChairFactory extends MethodBasedFactory {
  private static regularChairFactoryInstance: RegularChairFactory;

  private constructor() {
    super();
  }

  static getInstance(): RegularChairFactory {
    if (!RegularChairFactory.regularChairFactoryInstance)
      RegularChairFactory.regularChairFactoryInstance = new RegularChairFactory();
    return RegularChairFactory.regularChairFactoryInstance;
  }

  factoryMethod(): RegularChair {
    return new RegularChair();
  }
}

class ArtisticShelfFactory extends MethodBasedFactory {
  private static artisticShelfFactoryInstance: ArtisticShelfFactory;

  private constructor() {
    super();
  }

  static getInstance(): ArtisticShelfFactory {
    if (!ArtisticShelfFactory.artisticShelfFactoryInstance)
      ArtisticShelfFactory.artisticShelfFactoryInstance = new ArtisticShelfFactory();
    return ArtisticShelfFactory.artisticShelfFactoryInstance;
  }

  factoryMethod(): ArtisticShelf {
    return new ArtisticShelf();
  }
}

class ClassicalTableFactory extends MethodBasedFactory {
  private static classicalTableFactoryInstance: ClassicalTableFactory;

  private constructor() {
    super();
  }

  static getInstance(): ClassicalTableFactory {
    if (!ClassicalTableFactory.classicalTableFactoryInstance)
      ClassicalTableFactory.classicalTableFactoryInstance = new ClassicalTableFactory();
    return ClassicalTableFactory.classicalTableFactoryInstance;
  }

  factoryMethod(): ClassicalTable {
    return new ClassicalTable();
  }
}

class IkeaSofaFactory extends MethodBasedFactory {
  private static ikeaSofaFactoryInstance: IkeaSofaFactory;

  private constructor() {
    super();
  }

  static getInstance(): IkeaSofaFactory {
    if (!IkeaSofaFactory.ikeaSofaFactoryInstance)
      IkeaSofaFactory.ikeaSofaFactoryInstance = new IkeaSofaFactory();
    return IkeaSofaFactory.ikeaSofaFactoryInstance;
  }

  factoryMethod(): IkeaSofa {
    return new IkeaSofa();
  }
}

class IkeaShelfFactory extends MethodBasedFactory {
  private static ikeaShelfFactoryInstance: IkeaShelfFactory;

  private constructor() {
    super();
  }

  static getInstance(): IkeaShelfFactory {
    if (!IkeaShelfFactory.ikeaShelfFactoryInstance)
      IkeaShelfFactory.ikeaShelfFactoryInstance = new IkeaShelfFactory();
    return IkeaShelfFactory.ikeaShelfFactoryInstance;
  }

  factoryMethod(): IkeaShelf {
    return new IkeaShelf();
  }
}

function client(factory: MethodBasedFactory) {
  factory.factoryMethod().describe();
}

client(RegularChairFactory.getInstance());
client(ArtisticShelfFactory.getInstance());
client(ClassicalTableFactory.getInstance());
client(IkeaSofaFactory.getInstance());
client(IkeaShelfFactory.getInstance());
