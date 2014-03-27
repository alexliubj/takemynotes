<?php

$user_input = empty($_POST)?$_GET:$_POST;
$table = 'Category';
$NoteName = $user_input['NoteName'];
$CateId = $user_input['CateId'];
$Description = $user_input['Description'];
$Price = $user_input['Price'];
$PubUserId = $user_input['PubUserId'];
$Status = $user_input['Status'];

//connect to database
mysql_connect("localhost", "root", "password.123") or
  die("Could not connect: " . mysql_error());

//select a database
mysql_select_db("mynotes");

$sql = "insert into mynotes.Notes (NoteName,CateId,Description,Price,DateCreate,pubUserId)
values ('$NoteName','$CateId','$Description','$Price',now(),'$PubUserId')";

$result = mysql_query($sql);


$arr_all = array(
  'result' => "succ",
);

$output = json_encode($arr_all);
print_r($output);

?>


