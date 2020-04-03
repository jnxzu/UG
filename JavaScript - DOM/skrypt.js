const zadanie1 = () => {
  var list = document
    .getElementById("pro-plan")
    .getElementsByClassName("card-body")[0]
    .getElementsByTagName("ul")[0];
  var element = document.createElement("li");
  element.appendChild(document.createTextNode("Wsparcie telefoniczne 24/7"));
  list.appendChild(element);
};

const zadanie2 = () => {
  var container = document.getElementsByClassName("card-deck")[0];
  var pro = document.getElementById("pro-plan");
  container.appendChild(pro);
};

const zadanie3 = () => {
  var guzik = document
    .getElementById("pro-plan")
    .getElementsByClassName("card-body")[0]
    .getElementsByTagName("button")[0];
  guzik.style.background = "red";
  guzik.style.color = "white";
  guzik.innerHTML = "Kup teraz";
};

const zadanie4 = () => {
  var pro = document.getElementById("pro-plan");
  var basic = document.getElementById("basic-plan");
  var proVal = pro.getElementsByClassName("storage-amount")[0].innerHTML;
  var basicVal = basic.getElementsByClassName("storage-amount")[0].innerHTML;
  proVal = parseInt(proVal) * 1.5;
  basicVal = parseInt(basicVal) * 1.25;
  pro.getElementsByClassName("storage-amount")[0].innerHTML = proVal;
  basic.getElementsByClassName("storage-amount")[0].innerHTML = basicVal;
};

const toggle = event => {
  var pro = document.getElementById("pro-plan");
  var basic = document.getElementById("basic-plan");
  var proPricing = pro.getElementsByClassName("pricing")[0].innerHTML;
  var basicPricing = basic.getElementsByClassName("pricing")[0].innerHTML;
  var proPricingList = proPricing.split(" ");
  var basicPricingList = basicPricing.split(" ");
  if (proPricingList[proPricingList.length - 1] == "miesiąc") {
    proPricingList[proPricingList.length - 1] = "rok";
    proPricingList[0] = parseInt(proPricingList[0]) * 10;
  } else {
    proPricingList[proPricingList.length - 1] = "miesiąc";
    proPricingList[0] = parseInt(proPricingList[0]) / 10;
  }
  if (basicPricingList[basicPricingList.length - 1] == "miesiąc") {
    basicPricingList[basicPricingList.length - 1] = "rok";
    basicPricingList[0] = parseInt(basicPricingList[0]) * 10;
  } else {
    basicPricingList[basicPricingList.length - 1] = "miesiąc";
    basicPricingList[0] = parseInt(basicPricingList[0]) / 10;
  }
  pro.getElementsByClassName("pricing")[0].innerHTML = proPricingList.join(" ");
  basic.getElementsByClassName("pricing")[0].innerHTML = basicPricingList.join(
    " "
  );
};

const zadanie5 = () => {
  var container = document.getElementsByClassName("container")[0];
  var radioM = document.createElement("input");
  radioM.setAttribute("type", "radio");
  radioM.setAttribute("name", "payment");
  radioM.setAttribute("value", "M");
  radioM.checked = true;
  var radioY = document.createElement("input");
  radioY.setAttribute("type", "radio");
  radioY.setAttribute("name", "payment");
  radioY.setAttribute("value", "Y");
  var labelM = document.createElement("label");
  labelM.setAttribute("for", "M");
  labelM.appendChild(document.createTextNode("Mies."));
  var labelY = document.createElement("label");
  labelY.setAttribute("for", "Y");
  labelY.appendChild(document.createTextNode("Rocz."));
  radioM.addEventListener("change", toggle);
  radioY.addEventListener("change", toggle);
  container.appendChild(radioM);
  container.appendChild(labelM);
  container.appendChild(radioY);
  container.appendChild(labelY);
};
