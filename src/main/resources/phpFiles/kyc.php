<?php
// Prepare the form data
$client_code = $_POST['client_code'];
$route_key = $_POST['route_key'];
$api_num = $_POST['api_num'];
$image_num = $_POST['image_num'];
$pageLink = $_POST['pageUrl'];
$formData = array(
    "client_code" => strval($client_code),
    "route_key" => $route_key,
    "api_num" => strval($api_num),
    "image_num" => strval($image_num)
);

// Set up the request headers
$headers = array(
    "Content-Type: application/x-www-form-urlencoded",
    "Connection: Keep-Alive",
    "Host: dev-ap-dtrust.double-std.com",
    "Accept: application/x-www-form-urlencoded; charset=UTF-16",
    "Authorization: Basic ZHRydXN0dXNlcjpkdHJ1c3QwNzAz"
);

// Prepare the POST request
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $pageLink);
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($formData));
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

// Execute the request
$response = curl_exec($ch);

// Check for errors and print the response
if (curl_errno($ch)) {
    echo 'Curl error: ' . curl_error($ch);
} else {
    echo $response;
}

// Close the cURL session
curl_close($ch);
?>
