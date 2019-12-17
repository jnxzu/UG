using System;
using System.Diagnostics;
using System.IO;

namespace Pattern_Matching{
    internal static class Program{
        public static void Main(){
            var txt=File.ReadAllText("text.txt");
            var pattern=File.ReadAllText("pattern.txt");
            var sw=new Stopwatch();
            sw.Start();
            Console.WriteLine("Naiwny znalazl: "+Naive.Search(txt, pattern)+" dopasowan.");
            sw.Stop();
            Console.WriteLine("Naiwny czas: "+sw.Elapsed.Milliseconds+"ms");
            sw.Reset();
            sw.Start();
            Console.WriteLine("RabinKarp znalazl: "+RabinKarp.Search(txt, pattern)+" dopasowan.");
            sw.Stop();
            Console.WriteLine("RabinKarp czas: "+sw.Elapsed.Milliseconds+"ms");
            sw.Reset();
            sw.Start();
            Console.WriteLine("KMP znalazl: "+Kmp.Search(txt, pattern)+" dopasowan.");
            sw.Stop();
            Console.WriteLine("KMP czas: "+sw.Elapsed.Milliseconds+"ms");
        }
    }
}