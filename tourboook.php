<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    require_once 'DbConnect.php';
    // $user_id = $_POST['user_id'];
    // $image = $_POST['image'];
    // $doc_name = $_POST['doctor_name'];
    // $tr_date = $_POST['test_date'];
    // $tr_status = 1;
    // $img_no = 3;

   $t_name = $_POST['t_name'];
    $bdate = $_POST['bdate'];
    $cdate = $_POST['cdate'];
    $pay = $_POST['pay'];
    $pay_mode = $_POST['pay_mode'];
    $pacid = $_POST['pacid'];   
    $owner_name = $_POST['owner_name'];
    $boatname = $_POST['boatname'];
    $image = $_POST['image'];
    $response = array();

    
    $filename = $_FILES['image']['name'];
    $ext = pathinfo($filename, PATHINFO_EXTENSION);


    $name = round(microtime(true) * 1000) . '.'.$ext;
    $filedest = dirname(__FILE__) . "/UPLOADS/" . $name;

    $path = "UPLOADS/$name";

    $actual_path = "http://192.168.18.16/khb/$path";
    $sql1 = "select count(to_id) as cnt from tourbooking where bdate='$bdate' and pacid='$pacid'";
    $res_sql1 = $conn->query($sql1);
    $row_sql1 = $res_sql1->fetch_array();
    if ($row_sql1[0]==0) {
        $sql = "insert into tourbooking(t_name,bdate,cdate,pay,pay_mode,pacid,boatname,owner_name,image) values ('$t_name','$bdate','$cdate','$pay','$pay_mode','$pacid','$boatname','$owner_name','$actual_path') ";
        if (mysqli_query($conn, $sql)) {
            file_put_contents($path, base64_decode($image));
            $response['error'] = false;
            $response['message'] = 'Successfully uploaded.';
        }
        else
        {
            $response['error'] = true;
            $response['message'] = 'Something went wrong.';
        }
    }
    else
    {
        $response['error'] = true;
        $response['message'] = 'Already booked this boat.';
    }    
    mysqli_close($conn);
}
else
{
    $response['error'] = true;
    $response['message'] = 'Required parameters not available';
}

echo json_encode($response);