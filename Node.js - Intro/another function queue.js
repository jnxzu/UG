const poKolei = async (funTab, cb) => {
  let results = new Array();
  for (let i = 0; i < funTab.length; i++) {
    if (i == 0) {
      results[i] = funTab[i](2);
    } else if (results[i - 1] == undefined) {
      results[i] = funTab[i](2);
    } else {
      results[i] = funTab[i](results[i - 1]);
    }
  }
  cb(results.pop());
};

poKolei([x => undefined, x => 3 * x, x => 4 * x, x => x + 2], x =>
  console.log(x)
);

// ????????????????????????
