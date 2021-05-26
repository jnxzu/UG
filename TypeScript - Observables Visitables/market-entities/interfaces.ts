export interface IVisitable {
  accept(visitor: IVisitor, operation: string): void;
}

export interface IVisitor {
  visit(target: IVisitable, operation: string): void;
}

export interface IObservable {
  attach(observer: IObserver): void;

  detach(observer: IObserver): void;

  notify(): void;
}

export interface IObserver {
  update(value: number, value2: number): void;
}
