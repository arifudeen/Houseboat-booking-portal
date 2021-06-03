<?php

require_once 'DBconnect.php';

$response = array();

if (isset($_GET['apicall']))
{
    switch ($_GET['apicall']) {
        case 'signup':

            if (isTheseParametersAvailable(array('sname', 'sphone', 'password'))) {
                $sname = $_POST['sname'];
                $smobile = $_POST['sphone'];
                $password = $_POST['password'];
                $status = "pending";

                $stmt = $conn->prepare("SELECT * from tourist where tmobile = ?");
                $stmt->bind_param("s", $smobile);
                $stmt->execute();
                $stmt->store_result();

                if ($stmt->num_rows > 0) {
                    $response['error'] = true;
                    $response['message'] = 'User Already Registered';
                    $stmt->close();
                } else {
                    $stmt = $conn->prepare("INSERT INTO tourist ( name,tmobile, password,status) VALUES (?,?,?,?)");
                    $stmt->bind_param("ssss", $sname, $smobile, $password,$status);


                    if ($stmt->execute()) {
                        $stmt = $conn->prepare("SELECT tid, name  , tmobile from tourist where tmobile=?");
                        $stmt->bind_param("s", $smobile);
                        $stmt->execute();
                        $stmt->bind_result($tid, $sname, $smobile);
                        $stmt->fetch();

                        $user = array
                        (
                            'tid' => $tid,
                            'name' => $sname,
                            'mobile' => $smobile,

                        );

                        $stmt->close();

                        $response['error'] = false;
                        $response['message'] = 'Tourist registered Successfully';
                        $response['user'] = $user;
                    }
                }
            } else {
                $response['error'] = true;
                $response['message'] = 'required parameters are not available';

            }
            break;
        case "login":
            if (isTheseParametersAvailable(array('mobile','password')))
            {
                $mobile = $_POST['mobile'];
                $password = $_POST['password'];
                $stmt = $conn->prepare("SELECT * from tourist where tmobile = ? and password=?");
                $stmt->bind_param("ss",$mobile,$password);
                $stmt->execute();
                $stmt->store_result();
                if ($stmt->num_rows > 0)
                {
                    $stmt = $conn->prepare("SELECT tid, name  , tmobile from tourist where tmobile=?");
                    $stmt->bind_param("s", $mobile);
                    $stmt->execute();
                    $stmt->bind_result($tid, $sname, $mobile);
                    $stmt->fetch();

                    $user = array
                    (
                        'tid' => $tid,
                        'name' => $sname,
                        'mobile' => $mobile,

                    );

                    $response['error'] = false;
                    $response['message'] = 'Succesfully logged in';
                    $response['user'] = $user;
                    $stmt->close();
                }
                else{
                    $response['error'] = true;
                    $response['message'] = 'No user found';
                    $stmt->close();
                }

            }
            break;
        default:
            $response['error'] = true;
            $response['message'] = 'Invalid operation Called';
    }

}
else
{
    $response['error'] = true;
    $response['message'] = 'Invalid API Call';
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