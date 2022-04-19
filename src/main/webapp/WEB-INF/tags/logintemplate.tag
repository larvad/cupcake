<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginpage.css">
    <title>Login-Register landing page</title>
</head>

<body>
<div class="back-image">
    <img src="${pageContext.request.contextPath}/images/LoginPage/bk2.png" alt="background image">
</div>
<section class="left">
    <section class="side login">
        <p class="title">NY BRUGER?</p>
        <p class="message">Frygt ej - du er kun få klik fra at blive en del af Danmarks største Cupcakefælleskab</p>
        <button>Registrer her</button>
        <img src="${pageContext.request.contextPath}/images/LoginPage/cupcakes2.svg" alt="">
    </section>

    <section class="main register">
        <div class="container">
            <p class="title">Ny bruger</p>
            <form>
                <div class="form-control">
                    <input type="text" placeholder="Username">
                    <i class="fas fa-user"></i>
                </div>
                <div class="form-control">
                    <input type="text" placeholder="E-mail">
                    <i class="fas fa-envelope"></i>
                </div>
                <div class="form-control">
                    <input type="password" placeholder="Password">
                    <i class="fas fa-unlock"></i>
                </div>
                <div class="form-control">
                    <input type="password" placeholder="Confirm password">
                    <i class="fas fa-lock"></i>
                </div>

                <button class="submit">Registrer</button>
            </form>
        </div>
    </section>
</section>

<section class="right">

<section class="side register">
        <p class="title">Allerede oprettet?</p>
        <p class="message">Jamen, så skal du da slet ikke være her</p>
        <button>Login</button>
        <img src="${pageContext.request.contextPath}/images/LoginPage/cupcake.svg" alt="">
    </section>

    <section class="main login">
        <div class="container">
            <p class="title">Log ind</p>
            <form action="login" method="post">
                <div class="form-control">
                    <input type="text" placeholder="Username" id="username" name="username">
                    <i class="fas fa-user"></i>
                </div>
                <div class="form-control">
                    <input type="password" placeholder="Password" id="password" name="password">
                    <i class="fas fa-unlock"></i>
                </div>
                <button class="submit">Login</button>
            </form>
        </div>
    </section>

</section>

<script>
    var sideButtons = document.querySelectorAll('.side button');
    sideButtons.forEach(btn => btn.addEventListener('click', () => {
        document.body.classList.toggle('signup');
    }))
</script>

</body>
</html>


<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/scripts/changeCupcakeImage.js" type="text/javascript"></script>
