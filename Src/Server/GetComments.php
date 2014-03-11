<?php

$user_input = empty($_POST)?$_GET:$_POST;
$table = 'comments';
$noteid = $user_input['noteid'];

//connect to database
mysql_connect("localhost", "root", "") or
  die("Could not connect: " . mysql_error());

//select a database
mysql_select_db($table);

$sql = "select * from comments n join user u on n.userid = u.userid where n.noteid = $noteid";

$result = mysql_query($sql);


$arr_all = array(
  'result' => "succ",
);

$output = json_encode($arr_all);
print_r($output);

?>

