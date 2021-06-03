<?php

session_start();
require('db/db.php');


if(isset($_SESSION['role'])  && $_SESSION['role']=='admin' ){
  $username = ucfirst($_SESSION['username']);
}else{
  header("Location: ".$base_url."login.php");
}

?>


<?php include 'includes/inc_header.php';?>
<?php include 'includes/inc_navigations.php';?>
<?php include 'includes/inc_topbar.php';?>

<!-- // Body -->

<!-- Header -->
<div class="header bg-primary pb-6">
  <div class="container-fluid">
    <div class="header-body">
      <div class="row align-items-center py-4">
        <div class="col-lg-6 col-7">
          <h6 class="h2 text-white d-inline-block mb-0">Rating</h6>
          <!-- <nav aria-label="breadcrumb" class="d-none d-md-inline-block ml-md-4">
            <ol class="breadcrumb breadcrumb-links breadcrumb-dark">
              <li class="breadcrumb-item"><a href="#"><i class="fas fa-home"></i></a></li>
              <li class="breadcrumb-item"><a href="#">Dashboards</a></li>
              <li class="breadcrumb-item active" aria-current="page">Tourists & Bookings</li>
            </ol>
          </nav> -->
        </div>
        <div class="col-lg-6 col-5 text-right">
          <!-- <button data-toggle="modal" data-target="#contact_add_modal" class="btn btn-sm btn-neutral">New Contact</button> -->
          <!-- <a href="#" class="btn btn-sm btn-neutral">Filters</a> -->
        </div>
      </div>
      <!-- Card stats -->

    </div>
  </div>
</div>



<?php include 'pages/rating_list.php';?>

<?php include 'includes/inc_footer.php';?>


<script type="text/javascript">
  $('.nav-link').removeClass('active');
  $('#rating_menu').addClass('active');
</script>

