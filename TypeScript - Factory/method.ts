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

class ArtisticChairFactory extends MethodBasedFactory {
  private static artisticChairFactoryInstance: ArtisticChairFactory;

  private constructor() {
    super();
  }

  static getInstance(): ArtisticChairFactory {
    if (!ArtisticChairFactory.artisticChairFactoryInstance)
      ArtisticChairFactory.artisticChairFactoryInstance = new ArtisticChairFactory();
    return ArtisticChairFactory.artisticChairFactoryInstance;
  }

  factoryMethod(): ArtisticChair {
    return new ArtisticChair();
  }
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

class ClassicalChairFactory extends MethodBasedFactory {
  private static classicalChairFactoryInstance: ClassicalChairFactory;

  private constructor() {
    super();
  }

  static getInstance(): ClassicalChairFactory {
    if (!ClassicalChairFactory.classicalChairFactoryInstance)
      ClassicalChairFactory.classicalChairFactoryInstance = new ClassicalChairFactory();
    return ClassicalChairFactory.classicalChairFactoryInstance;
  }

  factoryMethod(): ClassicalChair {
    return new ClassicalChair();
  }
}

class IkeaChairFactory extends MethodBasedFactory {
  private static ikeaChairFactoryInstance: IkeaChairFactory;

  private constructor() {
    super();
  }

  static getInstance(): IkeaChairFactory {
    if (!IkeaChairFactory.ikeaChairFactoryInstance)
      IkeaChairFactory.ikeaChairFactoryInstance = new IkeaChairFactory();
    return IkeaChairFactory.ikeaChairFactoryInstance;
  }

  factoryMethod(): IkeaChair {
    return new IkeaChair();
  }
}

class ArtisticTableFactory extends MethodBasedFactory {
  private static artisticTableFactoryInstance: ArtisticTableFactory;

  private constructor() {
    super();
  }

  static getInstance(): ArtisticTableFactory {
    if (!ArtisticTableFactory.artisticTableFactoryInstance)
      ArtisticTableFactory.artisticTableFactoryInstance = new ArtisticTableFactory();
    return ArtisticTableFactory.artisticTableFactoryInstance;
  }

  factoryMethod(): ArtisticTable {
    return new ArtisticTable();
  }
}

class RegularTableFactory extends MethodBasedFactory {
  private static regularTableFactoryInstance: RegularTableFactory;

  private constructor() {
    super();
  }

  static getInstance(): RegularTableFactory {
    if (!RegularTableFactory.regularTableFactoryInstance)
      RegularTableFactory.regularTableFactoryInstance = new RegularTableFactory();
    return RegularTableFactory.regularTableFactoryInstance;
  }

  factoryMethod(): RegularTable {
    return new RegularTable();
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

class IkeaTableFactory extends MethodBasedFactory {
  private static ikeaTableFactoryInstance: IkeaTableFactory;

  private constructor() {
    super();
  }

  static getInstance(): IkeaTableFactory {
    if (!IkeaTableFactory.ikeaTableFactoryInstance)
      IkeaTableFactory.ikeaTableFactoryInstance = new IkeaTableFactory();
    return IkeaTableFactory.ikeaTableFactoryInstance;
  }

  factoryMethod(): IkeaTable {
    return new IkeaTable();
  }
}

class ArtisticSofaFactory extends MethodBasedFactory {
  private static artisticSofaFactoryInstance: ArtisticSofaFactory;

  private constructor() {
    super();
  }

  static getInstance(): ArtisticSofaFactory {
    if (!ArtisticSofaFactory.artisticSofaFactoryInstance)
      ArtisticSofaFactory.artisticSofaFactoryInstance = new ArtisticSofaFactory();
    return ArtisticSofaFactory.artisticSofaFactoryInstance;
  }

  factoryMethod(): ArtisticSofa {
    return new ArtisticSofa();
  }
}

class RegularSofaFactory extends MethodBasedFactory {
  private static regularSofaFactoryInstance: RegularSofaFactory;

  private constructor() {
    super();
  }

  static getInstance(): RegularSofaFactory {
    if (!RegularSofaFactory.regularSofaFactoryInstance)
      RegularSofaFactory.regularSofaFactoryInstance = new RegularSofaFactory();
    return RegularSofaFactory.regularSofaFactoryInstance;
  }

  factoryMethod(): RegularSofa {
    return new RegularSofa();
  }
}

class ClassicalSofaFactory extends MethodBasedFactory {
  private static classicalSofaFactoryInstance: ClassicalSofaFactory;

  private constructor() {
    super();
  }

  static getInstance(): ClassicalSofaFactory {
    if (!ClassicalSofaFactory.classicalSofaFactoryInstance)
      ClassicalSofaFactory.classicalSofaFactoryInstance = new ClassicalSofaFactory();
    return ClassicalSofaFactory.classicalSofaFactoryInstance;
  }

  factoryMethod(): ClassicalSofa {
    return new ClassicalSofa();
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

class RegularShelfFactory extends MethodBasedFactory {
  private static regularShelfFactoryInstance: RegularShelfFactory;

  private constructor() {
    super();
  }

  static getInstance(): RegularShelfFactory {
    if (!RegularShelfFactory.regularShelfFactoryInstance)
      RegularShelfFactory.regularShelfFactoryInstance = new RegularShelfFactory();
    return RegularShelfFactory.regularShelfFactoryInstance;
  }

  factoryMethod(): RegularShelf {
    return new RegularShelf();
  }
}

class ClassicalShelfFactory extends MethodBasedFactory {
  private static classicalShelfFactoryInstance: ClassicalShelfFactory;

  private constructor() {
    super();
  }

  static getInstance(): ClassicalShelfFactory {
    if (!ClassicalShelfFactory.classicalShelfFactoryInstance)
      ClassicalShelfFactory.classicalShelfFactoryInstance = new ClassicalShelfFactory();
    return ClassicalShelfFactory.classicalShelfFactoryInstance;
  }

  factoryMethod(): ClassicalShelf {
    return new ClassicalShelf();
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
  return factory.factoryMethod();
}
