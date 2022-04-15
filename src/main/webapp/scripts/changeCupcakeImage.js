let topEn = false;
let botEn = false;
if ((document.getElementById("cupcakeTop") != null) &&
    (document.getElementById("cupcakeBot") != null)) {
    let addToButton = document.querySelector("#addTo");
    addToButton.disabled = true;
    document.getElementById("cupcakeTop").onchange = function() {
        let cupcakeTop = document.querySelector("#cupcakeTop");
        let imgId = cupcakeTop.options[cupcakeTop.selectedIndex].id;
        let imgTop = "images/Top/top_" + imgId + ".png";
        document.getElementById("topImg").setAttribute("src", imgTop);
        topEn = true;
        if (topEn && botEn) {
            addToButton.disabled = false;
        }
    }
    document.getElementById("cupcakeBot").onchange = function() {
        let cupcakeBot = document.querySelector("#cupcakeBot");
        let imgId = cupcakeBot.options[cupcakeBot.selectedIndex].id;
        let imgBot = "images/Bot/bot_" + imgId + ".png";
        document.getElementById("botImg").setAttribute("src", imgBot);
        botEn = true;
        if (topEn && botEn) {
            addToButton.disabled = false;
        }
    }
}