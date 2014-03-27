<?php

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
$cid = $user_input['classid'];
$table = "Notes";
$page = $user_input['page'];
$limit = $user_input['limit'];
$sort_by = $user_input['sortby'];

//connect db
mysql_connect("localhost", "root", "password.123") or
  die("Could not connect: " . mysql_error());

//select one notes
mysql_select_db("mynotes");

//sql
$start = ($page - 1) * $limit;
if ($sort_by == 'time')
{
$result = mysql_query("SELECT * FROM $table WHERE CateId=$cid ORDER BY `DateCreate` DESC limit $start,$limit");
}
else if ($sort_by == 'no')
{
  $result = mysql_query("SELECT * FROM $table WHERE CateId=$cid order by `idNotes` DESC limit $start,$limit");

}
else
{
  $result = mysql_query("SELECT * FROM $table WHERE CateId=$cid limit $start,$limit");
}

$rst = array(
        idNotes => '',
        NoteName=> '',
        CateId => '',
        Description => '',
        Price=> '',
        pubUserId => '',
        DateCreate => '',
        Status => '',
        URL => '',
        NoteImg=> '',
);

//out put array
$arr_notes = array();
$i = 0;
while ($row = mysql_fetch_array($result)) {
  $i++;
  $rst['idNotes'] = $row['idNotes'];
  $rst['NoteName'] = $row['NoteName'];
  $rst['CateId'] = $row['CateId'];
  $rst['Description'] = $row['Description'];
  $rst['Price'] = $row['Price'];
  $rst['pubUserId'] = $row['pubUserId'];
  $rst['DateCreate'] = $row['DateCreate'];
  $rst['Status'] = $row['Status'];
  $rst['URL'] = $row['URL'];
  $rst['NoteImg'] = $row['NoteImg'];

  array_push($arr_notes, $rst);
}

$arr_all = array(
  'result' => "succ",
  'data' => $arr_notes,
);

$output = json_encode($arr_all);

print_r($output);

//release
mysql_free_result($result);

//echo("<br />time used:<br />");
//echo runtime(1);
//echo("<br />");

?>
