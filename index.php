<?php

session_start();
require('db/db.php');

// exit(print_r($_SESSION));

if(isset($_SESSION['role'])){
  $username = ucfirst($_SESSION['username']);
}else{
  header("Location: ".$base_url."login.php");
}

?>


<?php include 'includes/inc_header.php';?>
<?php include 'includes/inc_navigations.php';?>
<?php include 'includes/inc_topbar.php';?>

<?php 

// Counts
$owners_count = mysqli_num_rows(mysqli_query($con,"SELECT * from register WHERE type=0"));

if($_SESSION['role']!='admin'){
  $owner_name = $_SESSION['username'];
  $hb_count = mysqli_num_rows(mysqli_query($con,"SELECT * FROM boatregister where owner='$owner_name'"));

  $booking_count = mysqli_num_rows(mysqli_query($con,"SELECT * FROM tourbooking where owner_name='$username' "));

}else{
  $hb_count = mysqli_num_rows(mysqli_query($con,"SELECT * FROM boatregister "));

  $booking_count = mysqli_num_rows(mysqli_query($con,"SELECT * FROM tourbooking "));

}

$t_count = mysqli_num_rows(mysqli_query($con,"SELECT * FROM tourist"));



?>




<!-- Header -->
<div class="header bg-primary pb-6">
  <div class="container-fluid">
    <div class="header-body">
      <div class="row align-items-center py-4">
        <div class="col-lg-6 col-7">
          <!-- <h6 class="h2 text-white d-inline-block mb-0">Default</h6> -->
          <nav aria-label="breadcrumb" class="d-none d-md-inline-block ml-md-4">
            <ol class="breadcrumb breadcrumb-links breadcrumb-dark">
              <li class="breadcrumb-item"><a href="#"><i class="fas fa-home"></i></a></li>
              <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
              <!-- <li class="breadcrumb-item active" aria-current="page">Default</li> -->
            </ol>
          </nav>
        </div>
        <!-- <div class="col-lg-6 col-5 text-right">
          <a href="#" class="btn btn-sm btn-neutral">New</a>
          <a href="#" class="btn btn-sm btn-neutral">Filters</a>
        </div> -->
      </div>
      <!-- Card stats -->
      <div class="row">

        <!-- Item Start -->


        <?php if($_SESSION['role']=='admin'){ ?>

          <div class="col-xl-3 col-md-6">
            <div class="card card-stats">
              <!-- Card body -->
              <div class="card-body">
                <div class="row">
                  <div class="col">
                    <h5 class="card-title text-uppercase text-muted mb-0">Total Owners</h5>
                    <span class="h2 font-weight-bold mb-0"><?= number_format($owners_count) ?></span>
                  </div>
                  <div class="col-auto">
                    <div class="icon icon-shape bg-gradient-red text-white rounded-circle shadow">
                      <i class="ni ni-active-40"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

        <?php } ?>

        <!-- Item end -->

        <div class="col-xl-3 col-md-6">
          <div class="card card-stats">
            <!-- Card body -->
            <div class="card-body">
              <div class="row">
                <div class="col">
                  <h5 class="card-title text-uppercase text-muted mb-0">Total <br>House Boats</h5>
                  <span class="h2 font-weight-bold mb-0"><?= number_format($hb_count) ?></span>
                </div>
                <div class="col-auto">
                  <div class="icon icon-shape bg-gradient-orange text-white rounded-circle shadow">
                    <i class="ni ni-chart-pie-35"></i>
                  </div>
                </div>
              </div>
             <!--  <p class="mt-3 mb-0 text-sm">
                <span class="text-success mr-2"><i class="fa fa-arrow-up"></i> 3.48%</span>
                <span class="text-nowrap">Since last month</span>
              </p> -->
            </div>
          </div>
        </div>

        <div class="col-xl-3 col-md-6">
          <div class="card card-stats">
            <!-- Card body -->
            <div class="card-body">
              <div class="row">
                <div class="col">
                  <h5 class="card-title text-uppercase text-muted mb-0">Total Tourists</h5>
                  <span class="h2 font-weight-bold mb-0"><?= number_format($t_count) ?></span>
                </div>
                <div class="col-auto">
                  <div class="icon icon-shape bg-gradient-green text-white rounded-circle shadow">
                    <i class="ni ni-money-coins"></i>
                  </div>
                </div>
              </div>
              <!-- <p class="mt-3 mb-0 text-sm">
                <span class="text-success mr-2"><i class="fa fa-arrow-up"></i> 3.48%</span>
                <span class="text-nowrap">Since last month</span>
              </p> -->
            </div>
          </div>
        </div>

        <?php if($_SESSION['role']=='admin'){ ?>

          <div class="col-xl-3 col-md-6">
            <div class="card card-stats">
              <!-- Card body -->
              <div class="card-body">
                <div class="row">
                  <div class="col">
                    <h5 class="card-title text-uppercase text-muted mb-0">Total Bookings</h5>
                    <span class="h2 font-weight-bold mb-0"><?= number_format($booking_count) ?></span>
                  </div>
                  <div class="col-auto">
                    <div class="icon icon-shape bg-gradient-info text-white rounded-circle shadow">
                      <i class="ni ni-chart-bar-32"></i>
                    </div>
                  </div>
                </div>
              <!-- <p class="mt-3 mb-0 text-sm">
                <span class="text-success mr-2"><i class="fa fa-arrow-up"></i> 3.48%</span>
                <span class="text-nowrap">Since last month</span>
              </p> -->
            </div>
          </div>
        </div>

      <?php } ?>

        <!-- <div class="col-xl-3 col-md-6">
          <div class="card card-stats">
            <div class="card-body">
              <div class="row">
                <div class="col">
                  <h5 class="card-title text-uppercase text-muted mb-0">Performance</h5>
                  <span class="h2 font-weight-bold mb-0">49,65%</span>
                </div>
                <div class="col-auto">
                  <div class="icon icon-shape bg-gradient-info text-white rounded-circle shadow">
                    <i class="ni ni-chart-bar-32"></i>
                  </div>
                </div>
              </div>
              <p class="mt-3 mb-0 text-sm">
                <span class="text-success mr-2"><i class="fa fa-arrow-up"></i> 3.48%</span>
                <span class="text-nowrap">Since last month</span>
              </p>
            </div>
          </div>
        </div> -->
      </div>
    </div>
  </div>
</div>
<!-- Page content -->



<?php include 'includes/inc_footer.php';?>
