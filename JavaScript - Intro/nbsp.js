const nbsp = tekst => {
  console.log(tekst.replace(/(\s\w)(\s)/g, "$1&nbsp;"));
};

nbsp("Ala i As poszli w las");
