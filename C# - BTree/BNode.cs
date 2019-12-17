

using System;
using System.IO;

// ReSharper disable PossibleInvalidOperationException
namespace cs_btree{
    internal sealed class BNode{
        internal string Id;
        internal int N;
        internal int?[] Keys;
        internal readonly bool Leaf;
        internal string[] Children;
        private readonly int _t;

        internal BNode(int n, int?[] keys, bool leaf, string[] children, int t){
            Id=GetHashCode().ToString();
            N=n;
            Keys=keys;
            Leaf=leaf;
            Children=children;
            _t=t;
        }

        internal BNode(int t, bool leaf){
            Id=GetHashCode().ToString();
            Leaf=leaf;
            _t=t;
            N=0;
            Keys=new int?[2*_t-1];
            Children=new string[2*_t];
        }

        internal bool Search(int k){
            var i=0;
            while(i<N && k>Keys[i]) i++;
            if(k==Keys[i]) return true;

            return!Leaf && Load(Children[i]).Search(k);
        }

        internal void Print(){
            int i;
            for(i=0;i<N;i++){
                if(!Leaf) Load(Children[i]).Print();
                Console.Write(Keys[i]+" ");
            }
            if(!Leaf) Load(Children[i]).Print();
        }

        internal void Split(int i, BNode y){
            var z=new BNode(y._t, y.Leaf){N=_t-1};
            for(var j=0;j<_t-1;j++) z.Keys[j]=y.Keys[j+_t];
            if(!y.Leaf)
                for(var j=0;j<_t;j++)
                    z.Children[j]=y.Children[j+_t];
            y.N=_t-1;
            for(var j=N;j>=i+1;j--) Children[j+1]=Children[j];
            Children[i+1]=z.Save();
            for(var j=N-1;j>=i;j--) Keys[j+1]=Keys[j];
            Keys[i]=y.Keys[_t-1];
            y.Clean();
            N++;
            y.Save();
            z.Save();
            Save();
        }

        private void Clean(){
            for(var i=0;i<2*_t-1;i++)
                if(i>=N)
                    Keys[i]=null;
        }

        internal void Insert(int k){
            var i=N-1;
            if(Leaf){
                while(i>=0 && Keys[i]>k){
                    Keys[i+1]=Keys[i];
                    i--;
                }
                Keys[i+1]=k;
                N++;
                Save();
            }
            else{
                while(i>=0 && Keys[i]>k) i--;
                if(Load(Children[i+1]).N==2*_t-1){
                    Split(i+1, Load(Children[i+1]));
                    if(Keys[i+1]<k) i++;
                }
                Load(Children[i+1]).Insert(k);
            }
        }

        private int FindKey(int k){
            var i=0;
            while(i<N && Keys[i]<k) ++i;
            return i;
        }

        internal void Remove(int k){
            var i=FindKey(k);
            if(i<N && Keys[i]==k){
                if(Leaf) RemoveFromLeaf(i);
                else RemoveFromNonLeaf(i);
            }
            else{
                if(Leaf){
                    Console.WriteLine("Klucza "+k+" nie ma w tym drzewie.");
                    return;
                }
                var flag=i==N;
                if(Load(Children[i]).N<_t) Fill(i);
                if(flag && i>N)
                    Load(Children[i-1]).Remove(k);
                else
                    Load(Children[i]).Remove(k);
            }
            Save();
        }

        private void RemoveFromLeaf(int i){
            for(var j=i+1;j<N;++j) Keys[j-1]=Keys[j];
            N--;
            Clean();
        }

        private void RemoveFromNonLeaf(int i){
            var k=(int)Keys[i];
            if(Load(Children[i]).N>=_t){
                var pred=GetPred(i);
                Keys[i]=pred;
                Load(Children[i]).Remove(pred);
            }
            else if(Load(Children[i+1]).N>=_t){
                var succ=GetSucc(i);
                Keys[i]=succ;
                Load(Children[i+1]).Remove(succ);
            }
            else{
                Merge(i);
                Load(Children[i]).Remove(k);
            }
        }

        private int GetPred(int i){
            var cur=Load(Children[i]);
            while(!cur.Leaf) cur=Load(Children[cur.N]);
            return(int)cur.Keys[cur.N-1];
        }

        private int GetSucc(int i){
            var cur=Load(Children[i+1]);
            while(!cur.Leaf) cur=Load(Children[0]);
            return(int)cur.Keys[0];
        }

        private void Fill(int i){
            if(i!=0 && Load(Children[i-1]).N>=_t){
                BorrowFromPrev(i);
            }
            else if(i!=N && Load(Children[i+1]).N>=_t){
                BorrowFromNext(i);
            }
            else{
                if(i!=N)
                    Merge(i);
                else
                    Merge(i-1);
            }
        }

        private void BorrowFromPrev(int i){
            var child=Load(Children[i]);
            var sibling=Load(Children[i-1]);
            for(var j=child.N-1;j>=0;--j) child.Keys[j+1]=child.Keys[j];
            if(!child.Leaf)
                for(var j=child.N;j>=0;--j)
                    child.Children[j+1]=child.Children[j];
            child.Keys[0]=Keys[i-1];
            if(!child.Leaf) child.Children[0]=sibling.Children[sibling.N];
            Keys[i-1]=sibling.Keys[sibling.N-1];
            child.N++;
            sibling.N--;
            sibling.Clean();
            child.Save();
        }

        private void BorrowFromNext(int i){
            var child=Load(Children[i]);
            var sibling=Load(Children[i+1]);
            child.Keys[child.N]=Keys[i];
            if(!child.Leaf) child.Children[child.N+1]=sibling.Children[0];
            Keys[i]=sibling.Keys[0];
            for(var j=1;j<sibling.N;++j) sibling.Keys[j-1]=sibling.Keys[j];
            if(!sibling.Leaf)
                for(var j=1;j<=sibling.N;++j)
                    sibling.Children[j-1]=sibling.Children[j];
            child.N++;
            sibling.N--;
            sibling.Clean();
            child.Save();
        }

        private void Merge(int i){
            var child=Load(Children[i]);
            var sibling=Load(Children[i+1]);
            child.Keys[_t-1]=Keys[i];
            for(var j=0;j<sibling.N;++j) child.Keys[j+_t]=sibling.Keys[j];
            if(!child.Leaf)
                for(var j=0;j<=sibling.N;++j)
                    child.Children[j+_t]=sibling.Children[j];
            for(var j=i+1;j<N;++j) Keys[j-1]=Keys[j];
            for(var j=i+2;j<=N;++j) Children[j-1]=Children[j];
            child.N+=sibling.N+1;
            N--;
            Clean();
            child.Save();
            sibling.Save();
        }

        private string Save(){
            var name=Id;
            var keys="";
            var children="";
            for(var i=0;i<2*_t-1;i++)
                if(Keys[i]!=null)
                    keys=keys+Keys[i]+" ";
                else
                    keys+="null ";
            for(var i=0;i<2*_t;i++)
                if(Children[i]!=null)
                    children=children+Children[i]+" ";
                else
                    children+="null ";
            string[] data={N.ToString(), keys, Leaf.ToString(), children, _t.ToString()};
            File.WriteAllLines(name, data);
            return name;
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