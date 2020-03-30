let szablon =
  '<table border="{border}">' +
  "  <tr><td>{first}</td><td>{last}</td></tr>" +
  "</table>";

let dane = {
  first: "Jan",
  last: "Kowalski",
  pesel: "97042176329"
};

String.prototype.podstaw = function(dane) {
  myString = this;
  keys = Object.keys(dane);
  vals = Object.values(dane);
  for (let i = 0; i < keys.length; i++) {
    re = new RegExp("{" + keys[i] + "}", "g");
    myString = myString.replace(re, vals[i]);
  }
  console.log(myString);
};

szablon.podstaw(dane);
