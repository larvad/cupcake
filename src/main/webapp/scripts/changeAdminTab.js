if ( document.getElementById("admintab") != null) {
    document.getElementById("admintab").onchange = function () {
        let admintab = document.querySelector("#admintab");
        if (this.value === "orders") {
            document.getElementById("userTab").style.display = "none";
            document.getElementById("orderTab").style.display = "block";
        }
        if (this.value === "users") {
            document.getElementById("userTab").style.display = "block";
            document.getElementById("orderTab").style.display = "none";
        }
    }

}