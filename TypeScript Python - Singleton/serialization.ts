interface SingletonState {
  x: Number;
  y: String;
  z: Boolean;
}

class Singleton {
  private static instance: Singleton;
  public state: SingletonState = {
    x: 0,
    y: "hello",
    z: false,
  };

  private constructor() {}

  public static getInstance(): Singleton {
    if (!Singleton.instance) Singleton.instance = new Singleton();
    return Singleton.instance;
  }

  public serialize() {
    return JSON.stringify(Singleton.getInstance().state);
  }

  public deserialize(incomingData: string) {
    const incomingState: SingletonState = JSON.parse(incomingData);
    Singleton.getInstance().state = incomingState;
  }
}

const s1 = Singleton.getInstance();
const originalState = s1.state;

const originalData = s1.serialize();

const newData = originalData
  .replace("0", "1")
  .replace("hello", "world")
  .replace("false", "true");
Singleton.getInstance().deserialize(newData);
const s2 = Singleton.getInstance();
const newState = s2.state;

console.log("Data changed? ", originalData !== newData);
console.log("State changed? ", originalState !== newState);
console.log("Instance changed? ", s1 !== s2);
