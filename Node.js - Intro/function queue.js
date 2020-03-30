const poKolei = async (funTab, cb) => {
  a = 2;
  for (let i = 0; i < funTab.length; i++) {
    fun = funTab[i];
    a = await fun(a);
  }
  cb(a);
};

poKolei(
  [async x => 2 * x, async x => 3 * x, async x => 4 * x, async x => x + 2],
  x => console.log(x)
);
