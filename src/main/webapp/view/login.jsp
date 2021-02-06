<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <title>Social sigin</title>
    
    <style>
    
    body {
    padding: 0;
    margin: 0;
    overflow: hidden;
}

#container {
    background-image: url("https://images.pexels.com/photos/1363876/pexels-photo-1363876.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
    width: 100%;
    height:auto;
  
}

#formcontainer {
    width: 450px;
    height: 550px;
    background-color: white;
    position: relative;
    top: 60px;
    left: 35%;
    box-shadow: 0 0 20px black;
    border-radius: 8px
}

#img1 {
    width: 450px;
    height: 150px;
    border-top-left-radius: 8px;
    border-top-right-radius: 8px
}

#cross {
    width: 20px;
    height: 20px;
    position: relative;
    top: -145px;
    left: 94%
}

#text {
    font-size: 25px;
    font-family: 'Roboto Slab', serif;
    text-align: center;
    position: relative;
    top: -20px
}

#email,
#password {
    width: 350px;
    height: 1px;
    position: relative;
    top: -30px;
    left: 12%;
    border-radius: 5px;
    border-width: 1px;
    border-color: gray;
    padding: 20px;
    font-size: 20px;
    outline: none;
    margin-bottom: 15px
}

#email:hover,
#password:hover {
    background-color: gainsboro
}

#email:focus,
#password:focus {
    box-shadow: 0 0 10px green;
    border: none
}

#check {
    position: relative;
    top: 12px;
    left: -66%
}

#text1 {
    font-size: 17px;
    font-family: 'Roboto Slab', serif;
    position: relative;
    top: -36px;
    left: 17%;
    color: gray
}

#forget {
    position: relative;
    top: -77px;
    left: 67%;
    color: darkblue;
    font-size: 14px;
    font-family: 'Roboto Slab', serif
}

#login {
    width: 350px;
    height: 40px;
    position: relative;
    left: 12%;
    top: -60px;
    cursor: pointer;
    font-size: 22px;
    font-family: 'Roboto Slab', serif;
    border-radius: 5px;
    border: none;
    background-color: rgb(0, 128, 0);
    color: white
}

#login:hover {
    background-color: rgb(0, 128, 0, 0.7)
}

#hrline1 {
    line-height: 1em;
    position: relative;
    top: -40px;
    left: -30%;
    width: 38%
}

#text2 {
    font-size: 16px;
    font-family: 'Roboto Slab', serif;
    position: relative;
    top: -70px;
    left: 40%;
    color: gray
}

#hrline2 {
    position: relative;
    top: -95px;
    left: 31%;
    width: 37%
}

#facebook1,
#google1 {
    width: 15px;
    height: 15px;
    position: relative;
    left: -10%;
    top: 1px
}

#facebook,
#google {
    width: 140px;
    height: 40px;
    position: relative;
    top: -80px;
    left: 12%;
    border-radius: 5px;
    border-width: 1px;
    border-color: black;
    font-size: 17px;
    background-color: white
}

#facebook:hover {
    background-color: #2c27bc;
    color: white;
    transition: 0.5s
}

#google {
    position: relative;
    left: 26.5%
}

#google:hover {
    background-color: #ce6c46;
    color: white;
    transition: 0.5s
}

#text3 {
    position: relative;
    top: -66px;
    left: 32%;
    font-size: 15px;
    font-family: 'Roboto Slab', serif;
    color: gray
}

#signup {
    position: relative;
    top: -106px;
    left: 55%;
    color: black;
    font-size: 15px;
    font-family: 'Roboto Slab', serif
}

@media only screen and (max-width:830px) {
    #formcontainer {
        position: relative;
        left: 20%
    }
}

@media only screen and (max-width:620px) {
    #formcontainer {
        position: relative;
        left: 10%
    }
}

@media only screen and (max-width:530px) {
    #formcontainer {
        position: relative;
        left: 4%
    }
}
    </style>
  </head>
  <body>
   
   
  
   
   
    <div id="container">
        <div id="formcontainer"> <img id="img1" src="https://images.pexels.com/photos/414645/pexels-photo-414645.jpeg?cs=srgb&dl=background-beverage-brown-414645.jpg&fm=jpg"> <a href="#"><img id="cross" src="https://image.flaticon.com/icons/svg/148/148766.svg"></a>
            <p id="text">Login to your Account</p> <input id="email" type="email" type="tel" placeholder="Email / Phone"> <input id="password" type="password" placeholder="Password"> <input id="check" type="checkbox">
            <p id="text1">Remember Me</p> <a id="forget" href="#">Forget Password ?</a> <button id="login" type="submit">Log In</button>
            <hr id="hrline1">
            <p id="text2">or login with</p>
            <hr id="hrline2"> <button id="facebook" onclick="submitTwitterForm()"><img id="facebook1" src="https://www.flaticon.com/premium-icon/icons/svg/3256/3256016.svg">Linkedin</button> <button id="google"  onclick="submitGoogleForm()"><img id="google1" src="https://image.flaticon.com/icons/svg/281/281764.svg">Google</button>
            <p id="text3">Not a Member?</p> <a id="signup" href="#">Signup Now</a>
        </div>
    </div>
   
   	

   
   <form id="twitterForm" action="${pageContext.request.contextPath}/TwitterLogin" method="POST">
	</form>
   <form id="googleForm" action="${pageContext.request.contextPath}/GoogleLogin" method="POST">
	</form>
  
    
   <script>

   function submitTwitterForm(){
   	$("#twitterForm").submit();
   }
   function submitGoogleForm(){
   	$("#googleForm").submit();
   }
   
   </script>
  </body>
</html>