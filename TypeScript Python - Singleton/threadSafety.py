from threading import Lock, Thread


# THREAD NAIVE


class SingletonMetaClass(type):
    _instances = {}

    def __call__(cls, *args, **kwargs):
        if cls not in cls._instances:
            instance = super().__call__(*args, **kwargs)
            cls._instances[cls] = instance
        return cls._instances[cls]


class Singleton(metaclass=SingletonMetaClass):
    state = None

    def __init__(self, state):
        self.state = state

    def whats_inside(self):
        print(self.state)


def test_equivalence():
    s1 = Singleton("hello")
    s2 = Singleton("world")

    s1.whats_inside()
    s2.whats_inside()
    print(f'Same instance? { s1 == s2 }')


test_equivalence()


# THREAD SAFE


class ThreadSafeSingletonMetaClass(type):
    _instances = {}
    _lock: Lock = Lock()

    def __call__(cls, *args, **kwargs):
        with cls._lock:
            if cls not in cls._instances:
                instance = super().__call__(*args, **kwargs)
                cls._instances[cls] = instance
        return cls._instances[cls]


class ThreadSafeSingleton(metaclass=ThreadSafeSingletonMetaClass):
    state = None

    def __init__(self, state):
        self.state = state

    def whats_inside(self):
        print(self.state)


def test_one(val):
    s = ThreadSafeSingleton(val)
    s.whats_inside()


def test_multithreaded_equivalence():
    p1 = Thread(target=test_one, args=("1",))
    p2 = Thread(target=test_one, args=("2",))
    p1.start()
    p2.start()


test_multithreaded_equivalence()
