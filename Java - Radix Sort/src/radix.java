    import java.util.*;
     
    class RadixSort {
      public static void main (String[] args) {
        String[] arr = {"syNowa", "mama", "brat", "corka", "dziadek", "babcia", "syn", "bratowa", "ciocia", "dziecko", "tesciowa", "stryjek", "szwagier", "tata", "wujek", "tesc", "siostra", "ziec"};
        for(int i=0;i<arr.length;i++)	arr[i]=arr[i].toLowerCase();
        radixSort(arr,'a','z');
        for(int i=0;i<arr.length;i++){
          System.out.println(arr[i]);
        }
      }
      
      public static void countingSort(String[] arr,int index,char lower,char upper){
        int[] countArray = new int[(upper-lower)+2];
        String[] tempArray = new String[arr.length];
        Arrays.fill(countArray,0);
        
        
        //zliczanie wystapien(countArray) danego znaku(charIndex) na pozycji index
        for(int i=0;i<arr.length;i++){
          int charIndex = (arr[i].length()-1 < index) ? 0 : ((arr[i].charAt(index) - lower)+1);
          countArray[charIndex]++;
        }
        for(int i=1;i<countArray.length;i++){
          countArray[i] += countArray[i-1];
        }
        
        //zapisanie znaku na pozycji(tempArray) oznaczonej przez ilosc wystapien(countArray) danego znaku(charIndex)
        for(int i=arr.length-1;i>=0;i--){
          int charIndex = (arr[i].length()-1 < index) ? 0 : (arr[i].charAt(index) - lower)+1;
          tempArray[countArray[charIndex]-1] = arr[i];
          countArray[charIndex]--;
        }
        for(int i=0;i<tempArray.length;i++){
          arr[i] = tempArray[i];   
        }
      }
      
      public static void radixSort(String[] arr,char lower,char upper){
        int maxIndex = 0;
        for(int i=0;i<arr.length;i++){
          if(arr[i].length()-1 > maxIndex){
            maxIndex = arr[i].length()-1;
          }
        }
        
        for(int i=maxIndex;i>=0;i--){
          countingSort(arr,i,lower,upper);
        }
      }
    }