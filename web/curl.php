
<?php


$title = $_POST['title'];

$content = $_POST['content'];

$imageurl = $_POST['imageurl'];



$ch = curl_init();
curl_setopt($ch, CURLOPT_URL,"https://fcm.googleapis.com/fcm/send");
curl_setopt($ch, CURLOPT_POST, 1);

$vars = '{
  "data": {
 "title": "'.$title.'",
 "content" : "'.$content.'",
 "imageUrl": "'.$imageurl.'", 
 "gameUrl": "https://h5.4j.com/Ninja-Run/index.php?pubid=noad"
 },
 "to": "/topics/sunny"
}';

curl_setopt($ch, CURLOPT_POSTFIELDS,$vars);  //Post Fields


$headers = [
    'Content-Type: application/json; charset=utf-8',
    'Authorization : key=YOUR_KEY_HERE'
];

curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

$server_output = curl_exec ($ch);

curl_close ($ch);

print  $server_output ;

?>