<?php
	$host = 'localhost';
	$db = 'carent';
	$user = 'root';
	$pass = '';
	$charset = 'utf8mb4';
	$dsn = "mysql:host={$host};dbname={$db};charset={$charset}";
	$opt = [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION];
	try {
		$pdo = new PDO($dsn, $user, $pass, $opt);
	} catch (PDOException $ex) {
		echo "<p>Database error, details: $ex->getMessage()</p>";
	}
?>