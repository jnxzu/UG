namespace Disjoint_set{
    internal sealed class Set{
        internal readonly int Key;
        private Set _parent;
        private int _rank;

        private Set(int key){
            Key=key;
        }

        internal static Set MakeSet(int k){
            var x=new Set(k){_rank=0};
            x._parent=x;
            return x;
        }

        internal static Set FindSet(Set x){
            if(x!=x._parent)
                x._parent=FindSet(x._parent);
            return x._parent;
        }

        internal static void Union(Set x, Set y){
            if(x._rank>y._rank){
                y._parent=x;
            }
            else{
                x._parent=y;
                if(x._rank==y._rank) y._rank++;
            }
        }

        internal static bool SameSet(Set x, Set y){
            return FindSet(x)==FindSet(y);
        }
    }
}