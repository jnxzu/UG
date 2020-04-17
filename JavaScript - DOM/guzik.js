window.addEventListener("DOMContentLoaded", function () {
  var element = document.getElementsByClassName("spec")[0];
  var guzik = element.getElementsByTagName("button")[0];
  guzik.onclick = nowy;
});

function nowy(e) {
  var spec = e.target.parentNode;
  var nowySpec = spec.cloneNode(true);
  nowySpec.childNodes[0].nodeValue = "nowy";
  nowySpec.getElementsByTagName("button")[0].textContent = "spec";
  nowySpec.onclick = nowy;
  spec.after(nowySpec);
}
