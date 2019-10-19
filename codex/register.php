<?php

$user_name = $_POST["user_name"];
$pass_word = $_POST["pass_word"];
$category = $_POST["category"];

$password = md5($pass_word);

$sql = "INSERT INTO users(username,pass_word,category) values('$user_name','$password','$category')";
$result = mysqli_query($db, $sql);

?>