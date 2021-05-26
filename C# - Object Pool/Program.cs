using System.Linq;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace object_pool
{
    class Program
    {
        static void Main(string[] args)
        {
            ObjectPool<Fibonacci> FibPool = new ObjectPool<Fibonacci>(/*new Fibonacci()*/);
            ObjectPool<TwoExp> TEPool = new ObjectPool<TwoExp>(/*new TwoExp()*/);
            ObjectPool<MyClass> MCPool = new ObjectPool<MyClass>(/*new MyClass()*/);

            List<double> fibObjCount = new List<double>();
            List<double> teObjCount = new List<double>();
            List<double> mcObjCount = new List<double>();

            Parallel.For(0, 1000000, (i, _) =>
            {
                fibObjCount.Add(FibPool.Count());
                teObjCount.Add(TEPool.Count());
                mcObjCount.Add(MCPool.Count());

                Builder builder = new Builder();
                builder.AddFib(FibPool);
                builder.AddTE(TEPool);
                builder.AddMC(MCPool);
            });

            System.Console.WriteLine($"Fib objects\nAverage: {fibObjCount.Average()}\t Max: {fibObjCount.Max()}");
            System.Console.WriteLine($"TE objects\nAverage: {teObjCount.Average()}\t Max: {teObjCount.Max()}");
            System.Console.WriteLine($"MC objects\nAverage: {mcObjCount.Average()}\t Max: {mcObjCount.Max()}");
        }
    }
}
