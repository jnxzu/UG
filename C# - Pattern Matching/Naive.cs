using System;

namespace Pattern_Matching{
    internal static class Naive{
        public static int Search(string txt, string pat){
            var count=0;
            var m=pat.Length;
            var n=txt.Length;

            for(var i=0;i<=n-m;i++){
                if(txt[i]=='\n' || txt[i]=='\r') continue;

                int j;
                var mov=0;
                for(j=0;j<m;j++){
                    if(i+mov==n) break;

                    if(pat[j]=='\n' || pat[j]=='\r'){
                        if(txt[i+mov]=='\n' || txt[i+mov]=='\r') mov++;
                        continue;
                    }

                    if(txt[i+mov]=='\n' || txt[i+mov]=='\r'){
                        mov++;
                        j--;
                    }
                    else{
                        if(txt[i+mov]!=pat[j])
                            break;

                        mov++;
                    }
                }

                if(j!=m) continue;

                if(count<3)
                    Console.WriteLine("Znaleziono wzor na indeksie: "+i);
                count++;
            }
            return count;
        }
    }
}