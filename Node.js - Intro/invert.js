const odwracanie = napis => {
  var words = napis.split(/(\b)/);
  console.log(
    words
      .map(function(word) {
        if (word.length > 4) {
          return word
            .split("")
            .reverse()
            .join("");
        } else {
          return word;
        }
      })
      .join("")
  );
};

odwracanie("Dzik jest dziki, dzik jest zły. Dzik ma bardzo ostre kły.");
