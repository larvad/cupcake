if ( document.getElementById("admintab") != null) {
    document.getElementById("admintab").onchange = function () {
        if (this.value === "users") {
            document.getElementById("userTab").style.display = "block";
            document.getElementById("orderTab").style.display = "none";
        }
        if (this.value === "orders") {
            document.getElementById("userTab").style.display = "none";
            document.getElementById("orderTab").style.display = "block";
        }
    }
}
