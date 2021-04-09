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
interface IChair {
  sit(): void;
}

class ArtisticChair implements IChair {
  public sit() {
    console.log("ArtisticChair");
  }
}
class RegularChair implements IChair {
  public sit() {
    console.log("RegularChair");
  }
}
class ClassicalChair implements IChair {
  public sit() {
    console.log("ClassicalChair");
  }
}
class IkeaChair implements IChair {
  public sit() {
    console.log("IkeaChair");
  }
}

// TABLES
interface ITable {
  dinner(): void;
}

class ArtisticTable implements ITable {
  public dinner() {
    console.log("ArtisticTable");
  }
}
class RegularTable implements ITable {
  public dinner() {
    console.log("RegularTable");
  }
}
class ClassicalTable implements ITable {
  public dinner() {
    console.log("ClassicalTable");
  }
}
class IkeaTable implements ITable {
  public dinner() {
    console.log("IkeaTable");
  }
}

// SOFAS
interface ISofa {
  nap(): void;
}

class ArtisticSofa implements ISofa {
  public nap() {
    console.log("ArtisticSofa");
  }
}
class RegularSofa implements ISofa {
  public nap() {
    console.log("RegularSofa");
  }
}
class ClassicalSofa implements ISofa {
  public nap() {
    console.log("ClassicalSofa");
  }
}
class IkeaSofa implements ISofa {
  public nap() {
    console.log("IkeaSofa");
  }
}

// SHELVES
interface IShelf {
  flowers(): void;
}

class ArtisticShelf implements IShelf {
  public flowers() {
    console.log("ArtisticShelf");
  }
}
class RegularShelf implements IShelf {
  public flowers() {
    console.log("RegularShelf");
  }
}
class ClassicalShelf implements IShelf {
  public flowers() {
    console.log("ClassicalShelf");
  }
}
class IkeaShelf implements IShelf {
  public flowers() {
    console.log("IkeaShelf");
  }
}

abstract class AbstractFactory {
  protected constructor() {}

  abstract produceChair(): IChair;
  abstract produceTable(): ITable;
  abstract produceSofa(): ISofa;
  abstract produceShelf(): IShelf;
}

class ArtisticFurnitureFactory extends AbstractFactory {
  private static artisticFurnitureFactoryInstance: ArtisticFurnitureFactory;

  private constructor() {
    super();
  }

  static getInstance(): ArtisticFurnitureFactory {
    if (!ArtisticFurnitureFactory.artisticFurnitureFactoryInstance)
      ArtisticFurnitureFactory.artisticFurnitureFactoryInstance = new ArtisticFurnitureFactory();
    return ArtisticFurnitureFactory.artisticFurnitureFactoryInstance;
  }

  produceChair(): ArtisticChair {
    return new ArtisticChair();
  }
  produceTable(): ArtisticTable {
    return new ArtisticTable();
  }
  produceSofa(): ArtisticSofa {
    return new ArtisticSofa();
  }
  produceShelf(): ArtisticShelf {
    return new ArtisticShelf();
  }
}

class RegularFurnitureFactory extends AbstractFactory {
  private static regularFurnitureFactoryInstance: RegularFurnitureFactory;

  private constructor() {
    super();
  }

  static getInstance(): RegularFurnitureFactory {
    if (!RegularFurnitureFactory.regularFurnitureFactoryInstance)
      RegularFurnitureFactory.regularFurnitureFactoryInstance = new RegularFurnitureFactory();
    return RegularFurnitureFactory.regularFurnitureFactoryInstance;
  }

  produceChair(): RegularChair {
    return new RegularChair();
  }
  produceTable(): RegularTable {
    return new RegularTable();
  }
  produceSofa(): RegularSofa {
    return new RegularSofa();
  }
  produceShelf(): RegularShelf {
    return new RegularShelf();
  }
}

class ClassicalFurnitureFactory extends AbstractFactory {
  private static classicalFurnitureFactoryInstance: ClassicalFurnitureFactory;

  private constructor() {
    super();
  }

  static getInstance(): ClassicalFurnitureFactory {
    if (!ClassicalFurnitureFactory.classicalFurnitureFactoryInstance)
      ClassicalFurnitureFactory.classicalFurnitureFactoryInstance = new ClassicalFurnitureFactory();
    return ClassicalFurnitureFactory.classicalFurnitureFactoryInstance;
  }

  produceChair(): ClassicalChair {
    return new ClassicalChair();
  }
  produceTable(): ClassicalTable {
    return new ClassicalTable();
  }
  produceSofa(): ClassicalSofa {
    return new ClassicalSofa();
  }
  produceShelf(): ClassicalShelf {
    return new ClassicalShelf();
  }
}

class IkeaFurnitureFactory extends AbstractFactory {
  private static ikeaFurnitureFactoryInstance: IkeaFurnitureFactory;

  private constructor() {
    super();
  }

  static getInstance(): IkeaFurnitureFactory {
    if (!IkeaFurnitureFactory.ikeaFurnitureFactoryInstance)
      IkeaFurnitureFactory.ikeaFurnitureFactoryInstance = new IkeaFurnitureFactory();
    return IkeaFurnitureFactory.ikeaFurnitureFactoryInstance;
  }

  produceChair(): IkeaChair {
    return new IkeaChair();
  }
  produceTable(): IkeaTable {
    return new IkeaTable();
  }
  produceSofa(): IkeaSofa {
    return new IkeaSofa();
  }
  produceShelf(): IkeaShelf {
    return new IkeaShelf();
  }
}

function client(factory: AbstractFactory) {
  factory.produceChair().sit();
  factory.produceTable().dinner();
  factory.produceSofa().nap();
  factory.produceShelf().flowers();
}

client(ArtisticFurnitureFactory.getInstance());
client(RegularFurnitureFactory.getInstance());
client(ClassicalFurnitureFactory.getInstance());
client(IkeaFurnitureFactory.getInstance());
