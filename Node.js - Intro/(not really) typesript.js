const defFun = (fun, types) => {
  fun.typesTable = types;
  return fun;
};

const appFun = function() {
  if (arguments[0].hasOwnProperty("typesTable")) {
    for (let i = 1; i < arguments.length; i++) {
      if (!(typeof arguments[i]).localeCompare(arguments[0].typesTable)) {
        throw {
          typerr: "argument " + i + " has wrong type specified"
        };
      }
    }
    console.log(arguments[0].apply(this, Array.from(arguments).slice(1)));
  } else {
    throw {
      typerr: "no types table property in function"
    };
  }
};

appFun(
  defFun((a, b, c) => a + b + c, ["number", "number"]),
  1,
  1,
  1
);
