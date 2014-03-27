<?php

//Calculate time
function runtime($mode = 0)
{
  static $t;
  if (!$mode)
  {
    $t = microtime();
        return;
  }

  $t1 = microtime();
  list($m0,$s0) = split("       ", $t);
  list($m1,$s1) = split("       ", $t1);

//  return sprintf("%.6f ms", ($s1 + $m1 - $s0 - $m0) * 1000);
}

runtime();
$user_input = empty($_POST)?$_GET:$_POST;
$table = 'Category';

//connect to database
mysql_connect("localhost", "root", "password.123") or
  die("Could not connect: " . mysql_error());

//select a database
mysql_select_db("mynotes");

//select all category
$start = ($page - 1) * $limit;
$result = mysql_query("SELECT * FROM $table ORDER BY idCategory");

$rst = array(
        idCategory=> '',
        CategoryName => '',
        CateImg=> '',
);

//output all query
$arr_category = array();
$i = 0;
while ($row = mysql_fetch_array($result)) {
  $i++;
  $rst['idCategory'] = $row['idCategory'];
  $rst['CategoryName'] = $row['CategoryName'];
  $rst['CateImg'] = $row['CateImg'];
 array_push($arr_category, $rst);
}

$arr_all = array(
  'result' => "succ",
  'data' => $arr_category,
);

$output = json_encode($arr_category);

print_r($output);
//release mem
//mysql_free_result($result);

//echo("<br />time used:<br />");
//echo runtime(1);
//echo("<br />");

?>


