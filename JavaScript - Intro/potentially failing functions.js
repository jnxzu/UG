const poKolei4 = (funTab, fcb) => {
  var outcomes = f1(cb1);
  var err = outcomes[0];
  var res = outcomes[1];
  for (let i = 1; i < funTab.length; i++) {
    outcomes = funTab[i](err, res, cb);
    err = outcomes[0];
    res = outcomes[1];
  }
  fcb(err, res);
};

const f1 = cb => {
  return cb();
};

const f = (err, result, cb) => {
  return cb(err, result);
};

const fcb = (err, result) => {
  if (err != null) console.log("error");
  else console.log(result);
};

const cb1 = () => {
  return [null, 1];
};

const cb = (err, res) => {
  if (err != null) return [null, res + 1];
  else return ["err", res * 2];
};

poKolei4([f1, f, f, f, f, f], fcb);
