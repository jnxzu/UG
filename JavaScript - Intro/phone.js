const telefon = tab => {
  if (!Array.isArray(tab) || tab.length != 9) {
    console.log("not valid array");
    return 0;
  }
  var tel = tab
    .toString()
    .split(",")
    .join("");
  if (!/^\d+$/.test(tel)) {
    console.log("invalid digits");
    return 0;
  }
  console.log(
    "+48 " + tel.slice(0, 3) + "-" + tel.slice(3, 6) + "-" + tel.slice(6, 9)
  );
};

telefon([1, 2, 3, 4, 5, 6, 7, 8, 9]);
