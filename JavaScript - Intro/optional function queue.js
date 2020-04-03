const poKolei = async (funTab, cb) => {
  a = 2;
  let results = new Array();
  for (let i = 0; i < funTab.length; i++) {
    results.push(funTab[i](a));
  }
  cb(results);
};

poKolei([x => undefined, x => 3 * x, x => 4 * x, x => x + 2], x =>
  console.log(x)
);

// ????????????????????????
