using System;
using System.Collections.Generic;

namespace cs_nwp{
    internal class Nwp{
        public static void Main(){
            var test=new Nwp("abbaac", "bacbacba");
//            test.DrawDirs();
//            Console.WriteLine();
//            test.DrawNums();
//            Console.WriteLine();
//            test.EasyNwp();
//            Console.WriteLine();
//            test.HardNwp();
            test = new Nwp(System.IO.File.ReadAllText("lcs_b.txt"),System.IO.File.ReadAllText("lcs_a.txt"));
            Console.WriteLine();
            test.HardNwp();
            test = new Nwp(System.IO.File.ReadAllText("lcs_a2.txt"),System.IO.File.ReadAllText("lcs_b2.txt"));
            Console.WriteLine();
            test.EasyNwp();
        }
        private readonly char[] _word1;
        private readonly char[] _word2;
        private readonly int[,] _num;
        private readonly char[,] _dir;
        private readonly HashSet<string> _nwps;
        private Nwp(string w1, string w2){
            _nwps=new HashSet<string>();
            _word1=w1.ToCharArray();
            _word2=w2.ToCharArray();
            _num=new int[w1.Length+1, w2.Length+1];
            _dir=new char[w1.Length+1, w2.Length+1];
            for(var i=0;i<w1.Length;i++){
                _num[i, 0]=0;
                _dir[i, 0]='0';
            }
            for(var i=0;i<w2.Length;i++){
                _num[0, i]=0;
                _dir[0, i]='0';
            }
            for(var i=1;i<w1.Length+1;i++){
                for(var j=1;j<w2.Length+1;j++){
                    if(_word1[i-1]==_word2[j-1]){
                        _num[i, j]=_num[i-1, j-1]+1;
                        _dir[i, j]='\\';
                    }
                    else{
                        if(_num[i-1, j]>_num[i, j-1]){
                            _num[i, j]=_num[i-1, j];
                            _dir[i, j]='|';
                        }
                        else if(_num[i-1, j]<_num[i, j-1]){
                            _num[i, j]=_num[i, j-1];
                            _dir[i, j]='-';
                        }
                        else{
                            _num[i, j]=_num[i, j-1];
                            _dir[i, j]='+';
                        }
                    }
                }
            }
        }
        private void DrawNums(){
            for(var i=0;i<_word1.Length+2;i++){
                for(var j=0;j<_word2.Length+2;j++){
                    if(i!=0 || j!=0){
                        if(i==0){
                            Console.Write(_word2[j-1]+" ");
                        }
                        else if(j==0){
                            Console.Write(_word1[i-1]+" ");
                        }
                        else{
                            Console.Write(_num[i-1, j-1]+" ");
                        }
                    }
                    else{
                        Console.Write("  ");
                    }
                }
                Console.Write('\n');
            }
        }
        private void DrawDirs(){
            for(var i=0;i<_word1.Length+1;i++){
                for(var j=0;j<_word2.Length+1;j++){
                    if(i!=0 || j!=0){
                        if(i==0){
                            Console.Write(" ");
                        }
                        else if(i==1){
                            Console.Write(_word2[j-1]+" ");
                        }
                        else if(j==0){
                            Console.Write(" ");
                        }
                        else if(j==1){
                            Console.Write(_word1[i-1]+" ");
                        }
                        else{
                            Console.Write(_dir[i-1, j-1]+" ");
                        }
                    }
                    else{
                        Console.Write("  ");
                    }
                }
                Console.Write('\n');
            }
        }
        private void EasyNwp(){
            var i=_word1.Length;
            var j=_word2.Length;
            var dir=_dir[i, j];
            var resultpos=_num[i, j];
            var result=new char[resultpos];
            while(dir!='0'){
                if(dir!='-' && dir!='+'){
                    if(dir!='|'){
                        if(dir=='\\'){
                            result[resultpos-1]=_word1[i-1];
                            resultpos--;
                            i--;
                            j--;
                        }
                    }
                    else{
                        i--;
                    }
                }
                else{
                    j--;
                }
                dir=_dir[i, j];
            }
            Console.WriteLine("Przykladowy NWP z \"{0}\" i \"{1}\" to \"{2}\" ktory jest dlugosci {3}.",
                new string(_word1),
                new string(_word2), new string(result), _num[_word1.Length, _word2.Length]);
        }
        private void HardNwp(){
            HardNwp(_word1.Length, _word2.Length, _dir[_word1.Length, _word2.Length],
                _num[_word1.Length, _word2.Length], new char[_num[_word1.Length, _word2.Length]]);
            Console.WriteLine("NWP z \"{0}\" i \"{1}\" sa dlugosci {2} i sa to:", new string(_word1),
                new string(_word2), _num[_word1.Length, _word2.Length]);
            foreach(var nwps in _nwps){
                Console.WriteLine(nwps);
            }
        }
        private void HardNwp(int i, int j, char dir, int resultpos, char[] result){
            while(dir!='0'){
                if(dir!='-'){
                    if(dir!='|'){
                        if(dir!='\\'){}
                        else{
                            result[resultpos-1]=_word1[i-1];
                            resultpos--;
                            i--;
                            j--;
                        }
                        if(dir=='+'){
                            HardNwp(i-1, j, _dir[i-1, j], resultpos, result);
                            j--;
                        }
                    }
                    else{
                        i--;
                    }
                }
                else{
                    j--;
                }
                dir=_dir[i, j];
            }
            _nwps.Add(new string(result));
        }
    }
}