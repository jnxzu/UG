window.addEventListener("DOMContentLoaded", function () {
  var headers = document.querySelectorAll(".hd");
  headers.forEach((header) => {
    header.onclick = fold;
  });
});

function peep(e) {
  var sasiad = e.target.nextElementSibling;
  sasiad.style.display = "block";
}
function hide(e) {
  var sasiad = e.target.nextElementSibling;
  sasiad.style.display = "none";
}
function nic() {
  return false;
}

function fold(e) {
  var sasiad = e.target.nextElementSibling;
  if (sasiad.style.display === "none") {
    sasiad.style.display = "block";
  } else if (!!e.target.onmouseover && e.target.onmouseover != nic) {
    e.target.onmouseover = nic;
    e.target.onmouseout = nic;
  } else {
    sasiad.style.display = "none";
    e.target.onmouseover = peep;
    e.target.onmouseout = hide;
  }
}
