using System;
using System.Text.RegularExpressions;

namespace Pattern_Matching{
    internal static class RabinKarp{
        public static int Search(string txt, string pat){
            var count=0;
            txt=Regex.Replace(txt, "(\r\n|\r|\n)", "");
            pat=Regex.Replace(pat, "(\r\n|\r|\n)", "");
            var m=pat.Length;
            var n=txt.Length;
            int i;
            var p=0;
            var t=0;
            var h=1;
            const int d=256;
            const int q=11;

            for(i=0;i<m-1;i++)
                h=h*d%q;

            for(i=0;i<m;i++){
                p=(d*p+GetValue(pat[i]))%q;
                t=(d*t+GetValue(txt[i]))%q;
            }

            for(i=0;i<=n-m;i++){
                if(p==t){
                    int j;
                    for(j=0;j<m;j++)
                        if(txt[i+j]!=pat[j])
                            break;

                    if(j==m){
                        if(count<3)
                            Console.WriteLine("Znaleziono wzor na indeksie: "+i);
                        count++;
                    }
                }

                if(i>=n-m) continue;

                t=(d*(t-GetValue(txt[i])*h)+GetValue(txt[i+m]))%q;

                if(t<0)
                    t=t+q;
            }
            return count;
        }

        private static int GetValue(char x){
            return x;
        }
    }
}