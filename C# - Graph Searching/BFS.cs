using System;
using System.Collections;
using System.Collections.Generic;

namespace Przeszukiwanie_Grafow{
    internal static class Bfs{
        internal static void Traverse(int starting, List<List<int>> adjlist){
            Console.WriteLine("BFS: ");
            var queue=new Queue();
            var visited=new ArrayList{adjlist[starting][0]};
            for(var i=1;i<adjlist[starting].Count;i++)
                if(!visited.Contains(adjlist[starting][i]))
                    queue.Enqueue(adjlist[starting][i]);
            while(queue.Count>0){
                var v=(int)queue.Peek();
                queue.Dequeue();
                visited.Add(v);
                for(var i=1;i<adjlist[v].Count;i++)
                    if(!queue.Contains(adjlist[v][i]))
                        if(!visited.Contains(adjlist[v][i]))
                            queue.Enqueue(adjlist[v][i]);
            }
            foreach(var visit in visited) Console.Write(visit+" ");
        }
    }
}