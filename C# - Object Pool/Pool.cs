using System.Collections.Concurrent;

namespace object_pool
{
    public class ObjectPool<T> where T : new() /*where T : CalcPrototype*/
    {
        private ConcurrentBag<T> _objects;
        // private Func<T> _creator;
        // private T _template;

        public ObjectPool(/*T template*/ /*Func<T> creator*/)
        {
            _objects = new ConcurrentBag<T>();
            // _template = template;
            // _creator = creator;
        }

        public void Release(T item)
        {
            _objects.Add(item);
        }

        public T Get()
        {
            T item;
            if (_objects.TryTake(out item))
            {
                // System.Console.WriteLine("reusing");
                return item;
            }
            else
            {
                // T obj = _creator();
                T obj = new T();
                // T obj = (T)_template.Clone();
                _objects.Add(obj);
                // System.Console.WriteLine("using new");
                return obj;
            }
        }

        public int Count() => _objects.Count;
    }
}