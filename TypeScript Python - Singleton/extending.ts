//////// SPOSOB A

class Singleton {
  private static instance: Singleton;

  // konstruktor nie moze byc prywatny zeby dziedziczyc
  protected constructor() {}

  public static getInstance(): Singleton {
    if (!Singleton.instance) Singleton.instance = new Singleton();
    return Singleton.instance;
  }

  public talk() {
    console.log(this, ": I'm a Singleton");
  }
}

function equivalenceTest() {
  const s1 = Singleton.getInstance();
  const s2 = Singleton.getInstance();

  s1.talk();
  s2.talk();

  console.log("Same instance? ", s1 === s2);
}

equivalenceTest();

class ChildSingleton extends Singleton {
  private static childInstance: ChildSingleton;

  private constructor() {
    super();
  }

  public static getInstance(): ChildSingleton {
    if (!ChildSingleton.childInstance)
      ChildSingleton.childInstance = new ChildSingleton();
    return ChildSingleton.childInstance;
  }

  public talk() {
    console.log(this, ": I'm a child Singleton");
  }
}

function likeFatherLikeSon() {
  const parent = Singleton.getInstance();
  const child = ChildSingleton.getInstance();

  parent.talk();
  child.talk();

  console.log("Same instance? ", parent === child);
}

likeFatherLikeSon();

//////////////// SPOSOB B

class SingletonMom {
  // instancja protected a nie private
  protected static instance: SingletonMom;

  protected constructor() {}

  public static getInstance(): Singleton {
    if (!SingletonMom.instance) SingletonMom.instance = new SingletonMom();
    return SingletonMom.instance;
  }

  public talk() {
    console.log(this, ": I'm a Singleton");
  }
}

class BastardSingleton extends SingletonMom {
  protected static instance: BastardSingleton;

  private constructor() {
    super();
  }

  public static getInstance(): BastardSingleton {
    if (!BastardSingleton.instance)
      BastardSingleton.instance = new BastardSingleton();
    return BastardSingleton.instance;
  }

  public talk() {
    console.log(this, ": I'm an unwanted child Singleton");
  }
}

function notMyKid() {
  const child = BastardSingleton.getInstance();
  child.talk();
  const mom = SingletonMom.getInstance();
  mom.talk();

  console.log("Same instance? ", mom === child);
}

notMyKid();
