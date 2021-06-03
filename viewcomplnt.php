<?php
require_once 'DBconnect.php';

$response = array();
$tourist_id = $_POST['t_id'];
$stmt = $conn->prepare("SELECT  c_id,tid,c_reply FROM tour_complints where tid='$tourist_id';");
$stmt->execute();

$stmt->bind_result($c_id, $tid,$c_reply);
while($stmt->fetch()){
    $response['c_id'] = $c_id;
    $response['tid'] = $tid;
    $response['c_reply'] = $c_reply;
}
echo json_encode($response);