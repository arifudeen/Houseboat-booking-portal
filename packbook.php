<?php
require_once 'DBconnect.php';

$response = array();
$stmt = $conn->prepare("SELECT pacid,package,description,rate,image FROM packages;");
$stmt->execute();

$stmt->bind_result($pid, $package,$description,$rate,$image);
$view = array();
while($stmt->fetch()){
    $temp = array();
    $temp['pid'] = $pid;
    $temp['package'] = $package;
    $temp['description'] = $description;
     $temp['rate'] = $rate;
    $temp['image'] = "http://192.168.18.16/khb/images1/".$image;
    array_push($view, $temp);
}
echo json_encode($view);