using System;

namespace Disjoint_set{
    internal static class Program{
        public static void Main(){
            var s1=Set.MakeSet(1);
            var s2=Set.MakeSet(2);
            var s3=Set.MakeSet(3);
            var s4=Set.MakeSet(4);
            var s5=Set.MakeSet(5);
            var s6=Set.MakeSet(6);
            var s7=Set.MakeSet(7);

            Set.Union(s1, s3);
            Set.Union(s3, s5);
            Set.Union(s2, s4);
            Set.Union(s4, s6);
            Set.Union(s6, s7);

            Console.WriteLine(Set.FindSet(s5).Key);
            Console.WriteLine(Set.FindSet(s6).Key);
            Console.WriteLine(Set.FindSet(s1).Key);

            Console.WriteLine(Set.SameSet(s3, s2));
        }
    }
}