using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace cs_hoffman{
    public class Huff{
        public static void Main(){
            var test=new Huff();
            test.Compress(test._root, "");
            Console.WriteLine("\nRozmiar oryginalny: " + File.ReadAllBytes("text.txt").Length);
            Console.WriteLine("Rozmiar po kompresji: " + test._compSize/8);
            Console.WriteLine("Ilosc znakow: " + test.letters.Count);
        }
        private readonly List<HuffNode> _q=new List<HuffNode>();
        private readonly HuffNode _root;
        private int _compSize;
        private Dictionary<char, int> letters;
        private Huff(){
            _compSize=0;
            letters=File.ReadAllText("text.txt").GroupBy(c=>c).OrderBy(c=>c.Key)
                .ToDictionary(grp=>grp.Key, grp=>grp.Count());
            foreach(var c in letters){
                _q.Add(new HuffNode(c.Key, c.Value, null, null));
            }
            _root=BuildTree();
        }
        private HuffNode ExtractMin(){
            _q.Sort((x, y)=>x.Value.CompareTo(y.Value));
            var ret=_q[0];
            _q.RemoveAt(0);
            return ret;
        }
        private HuffNode BuildTree(){
            while(_q.Count>1){
                var x=ExtractMin();
                var y=ExtractMin();
                var z=new HuffNode('\0', x.Value+y.Value, x, y);
                _q.Add(z);
            }
            return ExtractMin();
        }
        private void Compress(HuffNode root, string str){
            while(true){
                if(root==null){
                    return;
                }
                if(root.Letter!='\0'){
                    _compSize=_compSize+root.Value*str.Length;
                }
                Compress(root.L, str+"0");
                root=root.R;
                str=str+"1";
            }
        }
    }
}