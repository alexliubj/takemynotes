<?php

$user_input = empty($_POST)?$_GET:$_POST;
$table = 'Category';
$UserId = $user_input['UserId'];
$NoteId = $user_input['NoteId'];
//connect to database
mysql_connect("localhost", "root", "") or
  die("Could not connect: " . mysql_error());

//select a database
mysql_select_db("mynotes");
$sql = "insert into FavNotes values ($UserId,$NoteId);"

$result = mysql_query("SELECT * FROM $table ORDER BY idCategory");

$arr_all = array(
  'result' => "succ",
);

$output = json_encode($arr_all);
print_r($output);

?>

