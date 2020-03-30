const groupBy = (tab, key) => {
  let map = new Map();
  tab.forEach(el => {
    if (map.get(key(el)) == null) map.set(key(el), new Array());
    map.get(key(el)).push(el);
  });
  console.log(map);
};

groupBy([3, 2, 4, 4, 3], n => n % 2 === 0);
