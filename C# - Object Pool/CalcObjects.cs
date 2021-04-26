using System;

namespace object_pool
{
    public interface CalcPrototype
    {
        CalcPrototype Clone();
    }

    class Fibonacci : CalcPrototype
    {
        private double[] _results;
        private double GetFib(int n)
        {
            if (n <= 1) return (double)n;
            else return GetFib(n - 1) + GetFib(n - 2);
        }

        public Fibonacci()
        {
            _results = new double[43];
            for (int i = 0; i < _results.Length; i++)
            {
                _results[i] = GetFib(i);
            }
        }

        public double GetResult() => _results[new Random().Next(0, _results.Length)];

        public CalcPrototype Clone() => this.MemberwiseClone() as CalcPrototype;
    }

    class TwoExp : CalcPrototype
    {
        private double[] _results;
        private double GetTE(int n)
        {
            if (n == 0) return 1.0;
            else return GetTE(n - 1) + GetTE(n - 1);
        }

        public TwoExp()
        {
            _results = new double[29];
            for (int i = 0; i < _results.Length; i++)
            {
                _results[i] = GetTE(i);
            }
        }

        public double GetResult() => _results[new Random().Next(0, _results.Length)];

        public CalcPrototype Clone() => this.MemberwiseClone() as CalcPrototype;
    }

    class MyClass : CalcPrototype
    {
        private double[] _results;

        public MyClass()
        {
            _results = new double[1000000];
            for (int i = 0; i < _results.Length; i++)
            {
                _results[i] = Math.Sqrt(i);
            }
        }

        public double GetResult() => _results[new Random().Next(0, _results.Length)];

        public CalcPrototype Clone() => this.MemberwiseClone() as CalcPrototype;
    }
}