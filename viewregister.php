<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Houseboat Portal</title>

    <!--

    Sentra Template

    https://templatemo.com/tm-518-sentra

    -->
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="apple-touch-icon" href="apple-touch-icon.png">

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/fontAwesome.css">
    <link rel="stylesheet" href="css/light-box.css">
    <link rel="stylesheet" href="css/owl-carousel.css">
    <link rel="stylesheet" href="css/templatemo-style.css">

    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">

    <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
</head>

<body>



<header class="nav-down responsive-nav hidden-lg hidden-md">
    <button type="button" id="nav-toggle" class="navbar-toggle" data-toggle="collapse" data-target="#main-nav">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <!--/.navbar-header-->
    <div id="main-nav" class="collapse navbar-collapse">
        <nav>
            <ul class="nav navbar-nav">
                <li><a href="#top">Home</a></li>
                <li><a href="#featured">Featured</a></li>
                <li><a href="#projects">Recent Projects</a></li>
                <li><a href="#video">Presentation</a></li>
                <li><a href="#blog">Blog Entries</a></li>
                <li><a href="#contact">Contact Us</a></li>
            </ul>
        </nav>
    </div>
</header>

<div class="sidebar-navigation hidde-sm hidden-xs">
    <div class="logo">
        <a href="#">ADMIN<em>PORTAL</em></a>
    </div>
    <nav>
        <ul>
            <li>
                <a href="#top">
                    <span class="rect"></span>
                    <span class="circle"></span>
                    Home
                </a>
            </li>
            <li>
                <a href="#featured">
                    <span class="rect"></span>
                    <span class="circle"></span>
                    houseboats
                </a>
            </li>
            <li>
                <a href="#projects">
                    <span class="rect"></span>
                    <span class="circle"></span>
                    Gallery
                </a>
            </li>
            <li>
                <a href="#video">
                    <span class="rect"></span>
                    <span class="circle"></span>
                    video
                </a>
            </li>

            <li>
                <a href="#blog">
                    <span class="rect"></span>
                    <span class="circle"></span>
                    Verify Owners
                </a>
            </li>


            <li>
                <a href="#contact">
                    <span class="rect"></span>
                    <span class="circle"></span>
                    Login
                </a>
            </li>
        </ul>
    </nav>
    <ul class="social-icons">
        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
        <li><a href="#"><i class="fa fa-rss"></i></a></li>
        <li><a href="#"><i class="fa fa-behance"></i></a></li>
    </ul>
</div>

<div class="slider">
    <div class="Modern-Slider content-section" id="top">
        <!-- Item -->
        <div class="item item-1">
            <div class="img-fill">
                <div class="image"></div>
                <div class="info">
                    <div>
                        <h1>HOUSEBOAT<br>BOOKING PORTAL</h1>
                        <p>HOUSEBOAT BOOKING PORTAL is an tourist agent which provides the facilities for booking<br>
                            They op-erate their business in alappuzha,kumarakam in kerala </p>
                        <div class="white-button button">
                            <a href="#featured">Discover More</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- // Item -->
        <!-- Item -->
        <div class="item item-2">
            <div class="img-fill">
                <div class="image"></div>
                <div class="info">
                    <div>
                        <h1>HOUSEBOAT<br>BOOKING PORTAL</h1>
                        <p>agent which provides the facilities for booking view
                            houseboat catogaries, rooms and facilites, price/rate etc<br>tourist.</p>

                        <div class="white-button button">
                            <a href="#featured">About us</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- // Item -->
        <!-- Item -->

        <!-- // Item -->
    </div>
</div>


<div class="page-content">
    <section id="blog" class="content-section">
        <div class="section-heading">
            <h1>view<br><em>registered owners</em></h1>

        </div>
        <div class="section-content">
            <div class="tabs-content">
                <div class="wrapper">
                    <ul class="tabs clearfix" data-tabgroup="first-tab-group">
                        <li><a href="#tab1" class="active">KERALA</a></li>
                        <li><a href="#tab2">HOUSEBOAT</a></li>
                        <li><a href="#tab3">BOOKING</a></li>
                        <li><a href="#tab4">PORTAL</a></li>
                    </ul>
                    <section id="first-tab-group" class="tabgroup">
                        <div id="tab1">
                            <ul>
                                <div>

                                    <?php
                                    require('db.php');
                                    $result = mysqli_query($con,"SELECT * FROM register");
                                    ?>

                                    <!DOCTYPE html>
                                    <html>
                                    <head>
                                        <title>Delete  data</title>
                                    </head>
                                    <body>

                                    <table border="2px" class="col-md-12" style="margin-bottom: 80px" >
                                        <tr >
                                            <td>boat name</td>
                                            <td>compny Name</td>
                                            <td>owner Name</td>
                                            <td>mobile</td>
                                            <td>Email</td>
                                            <td>Action</td>
                                        </tr>
                                        <?php
                                        $i=0;
                                        while($row = mysqli_fetch_array($result)) {
                                            ?>
                                            <tr class="<?php if(isset($classname)) echo $classname;?>">
                                                <td><?php echo $row["boatname"]; ?></td>
                                                <td><?php echo $row["username"]; ?></td>
                                                <td><?php echo $row["owner"]; ?></td>
                                                <td><?php echo $row["mobile"]; ?></td>
                                                <td><?php echo $row["email"]; ?></td>
                                                <td><a href="viewregister.php?id=<?php echo $row["id"]; ?>">Delete</a></td>
                                            </tr>
                                            <?php
                                            $i++;
                                        }
                                        ?>
                                    </table>

                                    </body>
                                    </html>
                                </div>



                                </div>
                        </div>







</li>
                                    <li>
                                        <div class="item">
                                            <img src="img/honey.jpg" alt="">
                                            <div class="text-content">
                                                <h4>KHB Portal</h4>
                                                <span>KHBP</span>
                                                <p> Since we have many new visitors here this year (welcome!), I’ve rounded up 29 of the most popular vegetarian main dishes on the menu. Keep in mind that all of  recipes are vegetarian .Boat provides very healthy neat vegetarian food as tourist needed all types of countinental dishes are there in the houseboats  </p>

                                                <div class="accent-button button">
                                                    <a href="#contact">Continue Reading</a>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="item">
                                            <img src="img/portfolio.jpg" alt="">
                                            <div class="text-content">
                                                <h4>Non Vegetarian</h4>
                                                <span>NON-VEG</span>
                                                <p>Boat provides variety of foods non-vegetarian dishes to help you get started. From mutton and pork to chicken and fish, there's something for everyone Boat provides very healthy neat non-vegetarian food as tourist needed all types of countinental dishes are there in the houseboats</p>
                                                <div class="accent-button button">
                                                    <a href="#contact">Continue Reading</a>
                                                </div>
                                            </div>
                                        </div>


                                </div>
                                </li>
                            </ul>
                        </div>

                </div>
            </div>
        </div>
    </section>
    <section class="footer">
        <p>Copyright &copy; 2019

            . Design: BEATZ D ARIF</p>
    </section>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')</script>

<script src="js/vendor/bootstrap.min.js"></script>

<script src="js/plugins.js"></script>
<script src="js/main.js"></script>

<script>
    // Hide Header on on scroll down
    var didScroll;
    var lastScrollTop = 0;
    var delta = 5;
    var navbarHeight = $('header').outerHeight();

    $(window).scroll(function(event){
        didScroll = true;
    });

    setInterval(function() {
        if (didScroll) {
            hasScrolled();
            didScroll = false;
        }
    }, 250);

    function hasScrolled() {
        var st = $(this).scrollTop();

        // Make sure they scroll more than delta
        if(Math.abs(lastScrollTop - st) <= delta)
            return;

        // If they scrolled down and are past the navbar, add class .nav-up.
        // This is necessary so you never see what is "behind" the navbar.
        if (st > lastScrollTop && st > navbarHeight){
            // Scroll Down
            $('header').removeClass('nav-down').addClass('nav-up');
        } else {
            // Scroll Up
            if(st + $(window).height() < $(document).height()) {
                $('header').removeClass('nav-up').addClass('nav-down');
            }
        }

        lastScrollTop = st;
    }
</script>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>

</body>
</html>




