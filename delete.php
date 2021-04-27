<?php

require('db.php');

$sql = "DELETE FROM register WHERE id='" . $_GET["id"] . "'";
if (mysqli_query($con, $sql)) {
    echo "Record deleted successfully";
} else {
    echo "Error deleting record: " . mysqli_error($conn);
}
mysqli_close($con);
?>