const poKolei = async (funTab, cb) => {
  a = 2;
  let results = new Array();
  for (let i = 0; i < funTab.length; i++) {
    results.push(await funTab[i](a));
  }
  cb(results);
};

poKolei(
  [async x => 2 * x, async x => 3 * x, async x => 4 * x, async x => x + 2],
  x => console.log(x)
);
