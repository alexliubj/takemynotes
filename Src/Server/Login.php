<?php

$user_input = empty($_POST)?$_GET:$_POST;
$table = 'NUsers';
$username = $user_input['username'];
$pwd = $user_input['pwd'];

//connect to database
mysql_connect("localhost", "root", "password.123") or
  die("Could not connect: " . mysql_error());

//select a database
mysql_select_db("mynotes");

//select all category
$start = ($page - 1) * $limit;
$result = mysql_query("SELECT idUsers,Password from $table where Name = '$username'");
print_r($result);
$rst = array(
        idUsers=> '',
        Password => '',
);

//output all query
$arr_category = array();
$i = 0;
$ret = 0;
$userid;
while ($row = mysql_fetch_array($result)) {
  $i++;
echo ($i);
echo('#');
echo(md5('2'));
echo('#');
echo(md5(2));
echo('#');
echo md5($pwd);
echo ('#');
echo $row['Password'];
  if($row['Password'] ==md5($pwd))
  {
$userid=$row['idUsers'];
    $ret = 1;
    break;
  }
}

if($ret ==1)
{
$arr_all = array(
  'result' => $userid,
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


