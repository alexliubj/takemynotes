<?php

$user_input = empty($_POST)?$_GET:$_POST;
$table = 'Nusers';
$username = $user_input['username'];
$pwd = $user_input['pwd'];

//connect to database
mysql_connect("localhost", "root", "") or
  die("Could not connect: " . mysql_error());

//select a database
mysql_select_db("mynotes");

//select all category
$start = ($page - 1) * $limit;
$result = mysql_query("SELECT idUsers,Password from $table where Name = $username");

$rst = array(
        idUsers=> '',
        Password => '',
);

//output all query
$arr_category = array();
$i = 0;
$ret = 0;
while ($row = mysql_fetch_array($result)) {
  $i++;
  if($row['Password'] == $pwd)
  {
    $ret = 1;
    break;
  }
}

if($ret ==1)
{
$arr_all = array(
  'result' => "succ",
);
}
else
{
  $arr_all = array(
  'result' => "fail",
);
}
$output = json_encode($arr_all);
print_r($output);


?>

