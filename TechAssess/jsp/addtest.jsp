

<html>
    <head>
        <title>CreatExam</title>
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
    <body>
        <div id="grid"></div>
        <div class="content">
        <center>
        <div class="center">
        <div class="inputform">
            <div class="form">
                <div class="tab-content" style="margin:25px">
                    <div id="signup">
                        <h1 style="color:white">CreateNewExam</h1>
                        <form action="/" method="post">
                            <div class="top-row">
                                <div class="field-wrap">
                                    <label>
                                    Exam Name<span class="req">*</span>
                                    </label>
                                    <input type="text" name="examName" required autocomplete="off" />
                                </div>
                                <div class="field-wrap">
                                 <label>
                                    Duration<span class="req">*</span>
                                    </label>
                                    <input type="text" name="duration"required autocomplete="off"/>
                                </div>
                            </div>
                            <div class="field-wrap">
                                <label>
                                NoOfValidDays<span class="req">*</span>
                                </label>
                                <input type="text" name="validDay"required autocomplete="off"/>
                            </div>
                            <button type="submit" class="button button-block"/>CreateExam</button>          
                        </form>
                    </div>
                    <div id="login">

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


