const poKolei = async (funTab, fcb) => {
  a = 2;
  for (let i = 0; i < funTab.length; i++) {
    fun = funTab[i];
    a = fun(a);
    console.log(a);
  }
  fcb(a);
};

poKolei([x => 2 * x, x => 3 * x, x => 4 * x, x => x + 2], x => console.log(x));
