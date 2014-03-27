<?php

$user_input = empty($_POST)?$_GET:$_POST;
$table = 'Nusers';
$username = $user_input['Name'];
$img = $user_input['UserImg'];
$email = $user_input['Email'];
$pswd = $user_input['Password'];
$addre = $user_input['Address'];
$ctiy = $user_input['City'];
$prov = $user_input['Province'];

if($img ==NULL)
$img='';
if($city== null)
$city = '';
if($prov ==null)
$prov ='';
if($addre == null)
$addre = '';

//connect to database
mysql_connect("localhost", "root", "password.123") or
  die("Could not connect: " . mysql_error());

//select a database
mysql_select_db("mynotes");
$sql = "insert into NUsers(Name,UserImg,Email,Password,DateRegister,Address,City,Province)
values('$username','$img','$email',md5('$pswd'),Now(),'$addre','$ctiy','$prov')";
$result = mysql_query($sql);
$arr_all = array(
  'result' => "succ",
);
$fail_all = array(
  'result' => "succ",
);
$output;
if($result==1)
{
$output = json_encode($arr_all);
}
else
{
$output = json_encode($fail_all);
}
print_r($output);
?>


