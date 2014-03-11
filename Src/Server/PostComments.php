<?php

$user_input = empty($_POST)?$_GET:$_POST;
$table = 'Comments';
$userid = $user_input['userid'];
$noteid = $user_input['noteid'];
$comments = $user_input['comments'];


//connect to database
mysql_connect("localhost", "root", "") or
  die("Could not connect: " . mysql_error());

//select a database
mysql_select_db($table );

$sql = "insert into Comments(noteid,userid,comments,date_time)
values ($noteid,$userid,$comments,now())";

$result = mysql_query($sql);


$arr_all = array(
  'result' => "succ",
);

$output = json_encode($arr_all);
print_r($output);

?>

