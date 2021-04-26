using System.Collections.Generic;

namespace object_pool
{
    class ListOfResults
    {
        private List<double> _results = new List<double>();

        public void Add(double value)
        {
            _results.Add(value);
        }

        public void PrintResults()
        {
            foreach (var result in _results)
            {
                System.Console.WriteLine(result);
            }
        }
    }

    class Builder
    {
        private ListOfResults _results;

        public Builder()
        {
            Reset();
        }

        public void Reset()
        {
            _results = new ListOfResults();
        }

        public void AddFib(ObjectPool<Fibonacci> pool)
        {
            Fibonacci obj = pool.Get();
            double value = obj.GetResult();
            _results.Add(value);
            pool.Release(obj);
        }

        public void AddTE(ObjectPool<TwoExp> pool)
        {
            TwoExp obj = pool.Get();
            double value = obj.GetResult();
            _results.Add(value);
            pool.Release(obj);
        }

        public void AddMC(ObjectPool<MyClass> pool)
        {
            MyClass obj = pool.Get();
            double value = obj.GetResult();
            _results.Add(value);
            pool.Release(obj);
        }

        public ListOfResults GetResults() => _results;
    }
}