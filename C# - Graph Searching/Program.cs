using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;

namespace Przeszukiwanie_Grafow
{
    internal static class Program
    {
        public static void Main()
        {
            var matrix1 = CreateAdjMatrix("ex2.txt");
            var list1 = CreateAdjList(matrix1);
            PrintAdjList(list1);
            Console.WriteLine();
            Bfs.Traverse(0, list1);
            Console.WriteLine();
            Dfs.Traverse(0, list1);
        }
        private static void PrintAdjList(IEnumerable<List<int>> lists)
        {
            foreach (var list in lists)
            {
                Console.Write(list[0] + "; ");
                for (var i = 1; i < list.Count - 1; i++) Console.Write(list[i] + ", ");
                if (list.Count > 1)
                    Console.Write(list[list.Count - 1] + "\n");
                else
                    Console.WriteLine();
            }
        }

        private static List<List<int>> CreateAdjList(int[,] matrix)
        {
            var adjList = new List<List<int>>();
            for (var i = 0; i < matrix.GetLength(0); i++)
            {
                var list = new List<int> { i };
                for (var j = 0; j < matrix.GetLength(1); j++)
                    if (matrix[i, j] != 0)
                        list.Add(j);
                adjList.Add(list);
            }
            return adjList;
        }

        private static int[,] CreateAdjMatrix(string url)
        {
            var reader = File.OpenText(url);
            var adjMatrix = new int[0, 0];
            var n = 0;
            var i = 0;
            string line;
            while ((line = reader.ReadLine()) != null)
                if (n == 0)
                {
                    n = int.Parse(line);
                    adjMatrix = new int[n, n];
                }
                else
                {
                    var vals = line.Split(null);
                    for (var j = 0; j < n; j++) adjMatrix[i, j] = int.Parse(vals[j]);
                    i++;
                }
            return adjMatrix;
        }
    }
}