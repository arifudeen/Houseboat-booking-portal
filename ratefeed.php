<?php
require_once 'DBconnect.php';

$response = array();
if (isTheseParametersAvailable(array('rating','feedback','tid'))) {
    $rating = $_POST['rating'];
    $feedback = $_POST['feedback'];
    $tourist = $_POST['tid'];
   

    $stmt = $conn->prepare("INSERT INTO ratefeed (rating,feedback,tid) VALUES (?,?,?)");
    $stmt->bind_param("sss",$rating,$feedback,$tourist);
    if ($stmt->execute() )
    {

        $response['error'] = false;
        $response['message'] = "Feedback added successfully...";
    }
}
else
{
    $response['error'] = true;
    $response['message'] = "Required parameters not available...";
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