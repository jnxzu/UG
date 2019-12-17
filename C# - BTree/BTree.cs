

using System;
using System.IO;

namespace cs_btree{
    internal sealed class BTree{
        private readonly int _t;
        private BNode _root;

        private BTree(int t){
            _t=t;
            _root=null;
        }

        public static void Main(){
            var test=new BTree(3);
            for(var i=10;i<=100;i=i+10) test.Insert(i);
            test.Print();
//            test.Search(60);
//            test.Search(100);
            test.Remove(40);
//            test.Remove(5);
            test.Print();
//            test.Search(40);
//            test.Insert(40);
//            test.Search(40);
        }

        private void Print(){
            _root.Print();
            Console.WriteLine();
        }

        private void Search(int k){
            Console.WriteLine(_root.Search(k)?"Znaleziono wartosc "+k+".":"Nie znaleziono wartosci "+k+".");
        }

        private void Insert(int k){
            if(_root==null){
                _root=new BNode(_t, true){
                    Keys={[0]=k},
                    N=1
                };
            }
            else{
                if(_root.N==2*_t-1){
                    var s=new BNode(_t, false){Children={[0]=_root.GetHashCode().ToString()}};
                    s.Split(0, _root);
                    var i=0;
                    if(s.Keys[0]<k) i++;
                    Load(s.Children[i]).Insert(k);
                    _root=s;
                }
                else{
                    _root.Insert(k);
                }
            }
        }

        private void Remove(int k){
            if(_root==null){
                Console.WriteLine("Drzewo jest puste.");
                return;
            }
            _root.Remove(k);
            if(_root.N!=0) return;

            _root=_root.Leaf?null:Load(_root.Children[0]);
        }

        private static BNode Load(string name){
            var data=File.ReadAllLines(name);
            var n=int.Parse(data[0]);
            var t=int.Parse(data[4]);
            var leaf=data[2].Equals("True");
            var keys=new int?[2*t-1];
            var children=new string[2*t];
            var keysstring=data[1].Split(null);
            for(var i=0;i<2*t-1;i++)
                if(keysstring[i].Equals("null"))
                    keys[i]=null;
                else
                    keys[i]=int.Parse(keysstring[i]);
            var childstring=data[3].Split(null);
            for(var i=0;i<2*t;i++)
                if(childstring[i].Equals("null"))
                    children[i]=null;
                else
                    children[i]=childstring[i];
            var node=new BNode(n, keys, leaf, children, t){Id=name};
            return node;
        }
    }
}