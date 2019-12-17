using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;

namespace Pattern_Matching{
    internal static class Kmp{
        public static int Search(string txt, string pat){
            var count=0;
            txt=Regex.Replace(txt, "(\r\n|\r|\n)", "");
            pat=Regex.Replace(pat, "(\r\n|\r|\n)", "");
            var m=pat.Length;
            var n=txt.Length;

            var lps=new int[m];
            var j=0;

            ComputeLpsArray(pat, m, lps);

            var i=0;
            while(i<n){
                if(pat[j]==txt[i]){
                    j++;
                    i++;
                }
                if(j==m){
                    if(count<3)
                        Console.WriteLine("Znaleziono wzor na indeksie: "+(i-m));
                    count++;
                    j=lps[j-1];
                }

                else if(i<n && pat[j]!=txt[i]){
                    if(j!=0)
                        j=lps[j-1];
                    else
                        i++;
                }
            }
            return count;
        }

        private static void ComputeLpsArray(string pat, int m, IList<int> lps){
            var len=0;
            var i=1;
            lps[0]=0;

            while(i<m)
                if(pat[i]==pat[len]){
                    len++;
                    lps[i]=len;
                    i++;
                }
                else{
                    if(len!=0){
                        len=lps[len-1];
                    }
                    else{
                        lps[i]=len;
                        i++;
                    }
                }
        }
    }
}