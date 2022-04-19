if (document.getElementById("cupcakeTop") != null) {
    document.getElementById("cupcakeTop").onchange = function() {
        let cupcakeTop = document.querySelector("#cupcakeTop");
        let imgId = cupcakeTop.options[cupcakeTop.selectedIndex].id;
        let imgTop = "images/Top/top_" + imgId + ".png";
        document.getElementById("topImg").setAttribute("src", imgTop);
    }
}
if (document.getElementById("cupcakeBot") != null) {
    document.getElementById("cupcakeBot").onchange = function() {
        let cupcakeBot = document.querySelector("#cupcakeBot");
        let imgId = cupcakeBot.options[cupcakeBot.selectedIndex].id;
        let imgBot = "images/Bot/bot_" + imgId + ".png";
        document.getElementById("botImg").setAttribute("src", imgBot);
    }
}