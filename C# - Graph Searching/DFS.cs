using System;
using System.Collections;
using System.Collections.Generic;

namespace Przeszukiwanie_Grafow{
    internal static class Dfs{
        internal static void Traverse(int starting, List<List<int>> adjlist){
            Console.WriteLine("DFS: ");
            var stack=new Stack();
            var visited=new ArrayList{adjlist[starting][0]};
            for(var i=adjlist[starting].Count-1;i>0;i--)
                if(!visited.Contains(adjlist[starting][i]))
                    stack.Push(adjlist[starting][i]);
            while(stack.Count>0){
                if(visited.Contains(stack.Peek())) stack.Pop();
                var v=(int)stack.Peek();
                stack.Pop();
                visited.Add(v);
                for(var i=adjlist[v].Count-1;i>0;i--)
                    if(!visited.Contains(adjlist[v][i]))
                        stack.Push(adjlist[v][i]);
            }
            foreach(var visit in visited) Console.Write(visit+" ");
        }
    }
}