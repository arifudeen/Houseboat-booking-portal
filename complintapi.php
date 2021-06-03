<?php
require_once 'DBconnect.php';

$response = array();
if (isTheseParametersAvailable(array('complint','tid'))) {
    $complint = $_POST['complint'];
    $tid=$_POST['tid'];


    $stmt = $conn->prepare("INSERT INTO tour_complints (complint,tid) VALUES (?,?)");
    $stmt->bind_param("ss",$complint,$tid);
    if ($stmt->execute() )
    {

        $stmt1 = $conn->prepare("SELECT tid FROM tour_complints WHERE tid=?" );
        $stmt1->bind_param("s",$tid);
        if($stmt1->execute())
        {
            $response['error'] = false;
            $response['message'] = 'Complaint registered successfully...';
        }
        else
        {
            $response['error'] = true;
            $response['message'] = 'Something went wrong';
        }
    }
}
else
{
    $response['error'] = true;
    $response['message'] = 'Required parameters are not available';
}

echo json_encode($response);


function isTheseParametersAvailable($params)
{
    foreach($params as $param)
    {
        if (!isset($_POST[$param]))
        {
            return false;
        }
    }

    return true;
}
