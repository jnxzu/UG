namespace cs_hoffman{
    public class HuffNode{
        public readonly char Letter;
        public readonly int Value;
        public readonly HuffNode L;
        public readonly HuffNode R;
        public HuffNode(char letter, int value, HuffNode l, HuffNode r){
            Letter=letter;
            Value=value;
            L=l;
            R=r;
        }
    }
}