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
        <a href="#">Owners<em>PORTAL</em></a>
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
                    Add Packages
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
            <h1>ADD<br><em>PACKAGES</em></h1>

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
                                    if (isset($_REQUEST['package'])){

                                        $package = stripslashes($_REQUEST['package']);

                                        $package = mysqli_real_escape_string($con,$package);

                                        $boatname = stripslashes($_REQUEST['boatname']);

                                        $boatname = mysqli_real_escape_string($con,$boatname);

                                        $description = stripslashes($_REQUEST['description']);

                                        $description = mysqli_real_escape_string($con,$description);



                                        $startdate = stripslashes($_REQUEST['startdate']);

                                        $startdate = mysqli_real_escape_string($con,$startdate);

                                        $enddate = stripslashes($_REQUEST['enddate']);

                                        $enddate = mysqli_real_escape_string($con,$enddate);

                                        $rate = stripslashes($_REQUEST['rate']);

                                        $rate = mysqli_real_escape_string($con,$rate);

                                        $image = stripslashes($_REQUEST['image']);

                                        $image = mysqli_real_escape_string($con,$image);


                                        $query = "INSERT into `packages` (package,boatname,description,startdate,enddate,rate,image)
                VALUES ('$package','$boatname','$description','$startdate','$enddate','$rate','$image')";
                                        $result = mysqli_query($con,$query);
                                        if($result){
                                            echo "<div class='form'>
                         <h3> Successfully added.</h3>
                             <br/>Click here to <a href='packages.php'>New</a></div>";
                                        }
                                    }else{
                                        ?>









                                        <div class="form">
                                            <form name="registration" action="" method="post">

                                                <table align="center" class="col-md-12">
                                                    <tr>
                                                        <td><h5><label>Package Name</label></h5></td>
                                                        <td><select name = "package"  class="form-control" />
                                                            <option value="">--select--</option>
                                                            <option value="fire">Honeymoon</option>
                                                            <option value="2">Business</option>
                                                            <option value="3">Family</option>
                                                            <option value="4">Student</option>
                                                            <option value="5">Week end</option>
                                                            <option value="6">Top Deal</option>
                                                            <option value="7">VIP</option>
                                                            <option value="8">Group with Dj </option>
                                                            <option value="9">Backwater Magic</option>

                                                            </select></td>

                                                    <tr>
                                                        <td><h5><label>Houseboat Name</label></h5></td>
                                                        <td><input type="text"   class="form-control" name="boatname" placeholder="" required  width="20px"/></td>
                                                    </tr>
                                                      <tr>
                                                        <td><h5><label>description</label></h5></td>
                                                        <td><textarea type="text" name="description"  class="form-control" placeholder="" required   ></textarea>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><h5><label>Start Date</label></h5></td>
                                                        <td><input type="date"   class="form-control" name="startdate" placeholder="" required  width="20px"/></td>
                                                    </tr>

                                                    <tr>
                                                        <td><h5><label>End Date</label></h5></td>
                                                        <td><input type="date"   class="form-control" name="enddate" placeholder="" required  width="20px"/></td>
                                                    </tr>


                                                    <tr>
                                                        <td><h5><label>Rate</label></h5></td>
                                                        <td><input type="text" name="rate"  class="form-control" placeholder="" required /></td>
                                                    </tr>


                                                    <tr>
                                                        <td><h5><label>image</label></h5></td>
                                                        <td><input type="file" name="image"  accept="image/jpeg" placeholder="" required /></td>
                                                    </tr>





                                                    <tr>
                                                        <td></td>
                                                        <td><h5><input type="submit" name="submit" value="ADD" class="bu" /></h5></td>
                                                    </tr>


                                                </table>
                                            </form>
                                        </div>














                                    <?php } ?>

                                    </li>
                                    <li>
                                        <div class="item">
                                            <img src="img/honey.jpg" alt="">
                                            <div class="text-content">
                                                <h4>Honeymoon</h4>
                                                <span>Boat</span>
                                                <p> Honeymoon Houseboats are designed as per the boats structure and modified into luxurious amenities with skilled labours as a four, five-star featured apartments with high class amenities like Jacuzzi, with full time centralized air – condition from check in time to check out time..  </p>

                                                <div class="accent-button button">
                                                    <a href="#contact">Continue Reading</a>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="item">
                                            <img src="img/busienes.jpg" alt="">
                                            <div class="text-content">
                                                <h4>Business</h4>
                                                <span>Boat</span>
                                                <p>Business Meetings Corporate Luxury Houseboat Backwater Package Podium Audio Visual Presentation Facilities Public Address System Lighting Arrangement.</p>
                                                <div class="accent-button button">
                                                    <a href="#contact">Continue Reading</a>
                                                </div>
                                            </div>
                                        </div>


                                </div>
                                </li>
                            </ul>
                        </div>
                        <div id="tab4">

                                <li>
                                    <ul>
                                        <div class="item">

                                            <?php
                                            require('db.php');
                                            $result = mysqli_query($con,"SELECT * FROM packages");
                                            ?>

                                            <!DOCTYPE html>
                                            <html>
                                            <head>
                                                <title>Delete  data</title>
                                            </head>
                                            <body>

                                            <table border="2px" class="col-md-12" style="margin-bottom: 80px" >
                                                <tr >
                                                    <td>package name</td>
                                                    <td>Boat Name</td>
                                                    <td>Description</td>
                                                    <td>Start date</td>
                                                    <td>End Date</td>
                                                    <td>Action</td>
                                                </tr>
                                                <?php
                                                $i=0;
                                                while($row = mysqli_fetch_array($result)) {
                                                    ?>
                                                    <tr class="<?php if(isset($classname)) echo $classname;?>">
                                                        <td><?php echo $row["package"]; ?></td>
                                                        <td><?php echo $row["boatname"]; ?></td>
                                                        <td><?php echo $row["description"]; ?></td>
                                                        <td><?php echo $row["startdate"]; ?></td>
                                                        <td><?php echo $row["enddate"]; ?></td>
                                                        <td><a href="viewregister.php?id=<?php echo $row["pacid"]; ?>">Delete</a></td>
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




