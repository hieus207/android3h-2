<?php
  include_once "Question.php";
  $table = 'test';
  $conn = mysqli_connect('localhost','id17150368_hieus207','Hieuhieu_0011','id17150368_thiblx');
  if(!$conn)
  {
  die('khong ket noi dc');
  }

  function getTest($id=""){
    global $conn;
    global $table;
    $add="";
    if($id!=""&&$id!=-1) $add=" WHERE Idtest=$id"; 
    if($id==-1) $add=" ORDER BY RAND () LIMIT 20";
    $sql="SELECT * FROM $table".$add;
    $rs=mysqli_query($conn,$sql);
    $result=mysqli_fetch_all($rs,MYSQLI_ASSOC);
    return $result;
  }

  function getQS($id){
    global $conn;
    $table="question";
    $sql="SELECT * FROM $table WHERE Idquestion=$id";
    $rs=mysqli_query($conn,$sql);
    $result=mysqli_fetch_all($rs,MYSQLI_ASSOC);
    return $result;
  }
  function getAllTest(){
    global $conn;
    global $table; 
    $sql="SELECT DISTINCT Idtest FROM $table";
    $rs=mysqli_query($conn,$sql);
    $result=mysqli_fetch_all($rs,MYSQLI_ASSOC);
    return $result;
  }
  function login($usn,$pass){
    global $conn;
    $table="users";
    $sql="SELECT * FROM $table WHERE Username='".$usn."' AND Password='".$pass."'";
    $rs=mysqli_query($conn,$sql);
    $result=mysqli_fetch_all($rs,MYSQLI_ASSOC);
    return $result;
  }
  function getUser($usn){
    global $conn;
    $table="users";
    $sql="SELECT * FROM $table WHERE Username='".$usn."'";
    $rs=mysqli_query($conn,$sql);
    $result=mysqli_fetch_all($rs,MYSQLI_ASSOC);
    return $result;
  }
  function createUser($usn,$passwd,$name){
    global $conn;
    $table="users";
    $sql="SELECT * FROM $table WHERE Username='".$usn."'";
    $sql="INSERT INTO $table (`Iduser`, `Username`, `Password`, `Name`, `Permission`) VALUES (NULL, '".$usn."', '".$passwd."', '".$name."', '')";
    mysqli_query($conn,$sql);
  }
// LAY INFO TEST
if(isset($_GET['action'])&&$_GET['action']=="getTest"){
  $listqs=array();
  $Tests=getTest($_GET['idtest']); 
  $idTest="";
  $time="";
  foreach($Tests as $test ){
    $idTest = $test['Idtest'];
    array_push($listqs,$test['Listquestion']);
    $time = $test['Time'];
  }
  // print_r($Tests);
  $listQS = [];
  
  foreach($listqs as $qs){
    $ques=getQS($qs);
    $question=$ques[0];
    $rs=new Question($question['Idquestion'],$question['Questionform'],$question['Content'],$question['Image'],$question['Da1'],$question['Da2'],$question['Da3'],$question['Da4'],$question['Dadung']);
  //   print_r($question[0]);
    
    array_push($listQS,$rs);
  //   break;
  }
  // print_r($listQS);
  $array1 = array(
      'Idtest' => $idTest,
      'Time' => $time,
      'Listquestion' => $listQS
  );
  echo (json_encode($array1,JSON_UNESCAPED_UNICODE)); 
}
// LAY TEST RANDOM
if(isset($_GET['action'])&&$_GET['action']=="getTestRand"){
  $listqs=array();
  $Tests=getTest(-1); 
  $idTest="";
  $time="";
  foreach($Tests as $test ){
    $idTest = $test['Idtest'];
    array_push($listqs,$test['Listquestion']);
    $time = $test['Time'];
  }
  // print_r($Tests);
  $listQS = [];
  
  foreach($listqs as $qs){
    $ques=getQS($qs);
    $question=$ques[0];
    $rs=new Question($question['Idquestion'],$question['Questionform'],$question['Content'],$question['Image'],$question['Da1'],$question['Da2'],$question['Da3'],$question['Da4'],$question['Dadung']);
  //   print_r($question[0]);
    
    array_push($listQS,$rs);
  //   break;
  }
  // print_r($listQS);
  $array1 = array(
      'Idtest' => $idTest,
      'Time' => $time,
      'Listquestion' => $listQS
  );
  echo (json_encode($array1,JSON_UNESCAPED_UNICODE)); 
}
// LAY ID ALL TEST
  if(isset($_GET['action'])&&$_GET['action']=="getAllTest"){
    // $listqs=array();
    $Tests=getAllTest(); 
    $idTest="";
    // $time=""; 
    $array1 = array(
      'allTest' => $Tests
  );
  echo (json_encode($array1,JSON_UNESCAPED_UNICODE)); 

}

// LOGIN
  if(isset($_POST['Username'])&&isset($_POST['Password'])&&!isset($_POST['Name'])){
    $user=login($_POST['Username'],$_POST['Password']);
    if(sizeof($user)>0){
      $array1 = array(
        'Iduser' => $user[0]['Iduser'],
        'Username' => $user[0]['Username'],
        'Name' => $user[0]['Name'],
        'Permission' => $user[0]['Permission']
      );
      echo (json_encode($array1,JSON_UNESCAPED_UNICODE)); 
    }
//    echo sizeof(login($_POST['Username'],$_POST['Password']));
  }
// REGISTER
if(isset($_POST['Username'])&&isset($_POST['Password'])&&isset($_POST['Name'])){
  if(sizeof(getUser($_POST['Username']))==0){
    creatUser($_POST['Username'],$_POST['Password'],$_POST['Name']);
    $array1 = array(
        'Username' => $_POST['Username'],
      );
      echo (json_encode($array1,JSON_UNESCAPED_UNICODE)); 
    }
  }
  
//    echo sizeof(login($_POST['Username'],$_POST['Password']));
?>
