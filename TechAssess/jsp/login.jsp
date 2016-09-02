<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" integrity="sha384-   
            BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
        <link rel="stylesheet" href="css/login.css">
        <link href='css/fonts.css' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/style.css">
        <script src="js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="js/jssor.slider-21.1.5.mini.js"></script>
        <script src="js/parallex.js"></script>
    </head>
    <script>
        jQuery(document).ready(function ($) {
            
            var jssor_1_SlideoTransitions = [
              [{b:-1,d:1,o:-1},{b:0,d:1000,o:1}],
              [{b:1900,d:2000,x:-379,e:{x:7}}],
              [{b:1900,d:2000,x:-379,e:{x:7}}],
              [{b:-1,d:1,o:-1,r:288,sX:9,sY:9},{b:1000,d:900,x:-1400,y:-660,o:1,r:-288,sX:-9,sY:-9,e:{r:6}},{b:1900,d:1600,x:-200,o:-1,e:{x:16}}]
            ];
            
            var jssor_1_options = {
              $AutoPlay: true,
              $SlideDuration: 800,
              $SlideEasing: $Jease$.$OutQuint,
              $CaptionSliderOptions: {
                $Class: $JssorCaptionSlideo$,
                $Transitions: jssor_1_SlideoTransitions
              },
              $ArrowNavigatorOptions: {
                $Class: $JssorArrowNavigator$
              },
              $BulletNavigatorOptions: {
                $Class: $JssorBulletNavigator$
              }
            };
            
            var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);
            
            //responsive code begin
            //you can remove responsive code if you don't want the slider scales while window resizing
            function ScaleSlider() {
                var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1920);
                    jssor_1_slider.$ScaleWidth(refSize);
                }
                else {
                    window.setTimeout(ScaleSlider, 30);
                }
            }
            ScaleSlider();
            $(window).bind("load", ScaleSlider);
            $(window).bind("resize", ScaleSlider);
            $(window).bind("orientationchange", ScaleSlider);
            //responsive code end
        });
    </script>
    <c:if test="${LogInMessage != null}">
                <script type='text/javascript' language='javascript'>
                    alert("<c:out value='${LogInMessage}' />");
                    window.location="loginpage";
                </script>
            </c:if>
    <body>
        <div id="grid"></div>
        <div class="content">
        <div>
            <div>
                <h1 class="title">Tech Assess</h1>
            </div>
            <div id="jssor_1" class="jsso">
                <div data-u="slides" class="slides">
                    <div data-p="225.00" style="display: none;">
                        <img data-u="image" src="img/red.jpg" />
                    </div>
                    <div data-p="225.00" style="display: none;">
                        <img data-u="image" src="img/purple.jpg" />
                    </div>
                    <div data-p="225.00" data-po="80% 55%" style="display: none;">
                        <img data-u="image" src="img/blue.jpg" />
                    </div>
                </div>
                <!-- Bullet Navigator -->
                <div data-u="navigator" class="jssorb05" style="bottom:16px;right:16px;" data-autocenter="1">
                    <!-- bullet navigator item prototype -->
                    <div data-u="prototype" style="width:16px;height:16px;"></div>
                </div>
                <!-- Arrow Navigator -->
                <span data-u="arrowleft" class="jssora22l" style="top:0px;left:12px;width:35px;height:58px;" data-autocenter="2"></span>
                <span data-u="arrowright" class="jssora22r" style="top:6px;right:12px;width:35px;height:58px;" data-autocenter="2"></span>
            </div>
        </div>
        <center>
        <div class="center">
        <div class="inputform">
            <div class="form">
                <ul class="tab-group">
                    <li class="tab active"><a href="#signup">Sign Up</a></li>
                    <li class="tab"><a href="#login">Log In</a></li>
                </ul>
                <div class="tab-content">
                    <div id="signup">
                        <h1>Sign Up for Free</h1>
                        <form name="user" action="userRegisteration" method="post">
                            <div class="top-row">
                                <div class="field-wrap">
                                    <label>
                                    User Name<span class="req">*</span>
                                    </label>
                                    <input type="text" name="userName" required autocomplete="off" />
                                </div>
                                <div class="field-wrap">
                                 <label>
                                    MobileNumber<span class="req">*</span>
                                    </label>
                                    <input type="text" name="mobileNumber"required autocomplete="off"/>
                                </div>
                            </div>
                            <div class="field-wrap">
                                <label>
                                Email Address<span class="req">*</span>
                                </label>
                                <input type="email" name="emailId" required autocomplete="off"/>
                            </div>
                            <div class="field-wrap">
                                <label>
                                Password<span class="req">*</span>
                                </label>
                                <input type="password" name="password"required autocomplete="off"/>
                            </div>
                            <button type="submit" class="button button-block"/>CreateAccount</button>          
                        </form>
                    </div>
                    <div id="login">
                        <h1>Welcome Back!</h1>
                        <form action="AuthenticateLogin" method="post">
                            <div class="field-wrap">
                                <label>
                                EmailId<span class="req">*</span>
                                </label>
                                <input type="text" name="emailId"required autocomplete="off"/>
                            </div>
                            <div class="field-wrap">
                                <label>
                                Password<span class="req">*</span>
                                </label>
                                <input type="password" name="password"required autocomplete="off"/>
                            </div>
                            <p class="forgot"><a href="#">Forgot Password?</a></p>
                            <button class="button button-block"/>Log In</button>
                        </form>
                    </div>
                </div>
                <!-- tab-content -->
            </div>
            <!-- /form -->
            <script src='js/form.js'></script>
            <script src="js/index.js"></script>
        </div>
        <!--- input-form -->
        </div>
        <!--center-->
        <section class="footer">
            <div class="container">
                <hr>
                <div class="copyright">
                    <p>© 2016 TechAssess. All Rights Reserved | Design by <a href="#" target="_blank">Tech Assess</a></p>
                </div>
            </div>
        </section>
    </body>
</html>

