<?php
	$host = 'localhost';
	$db = 'test';
	$user = 'root';
	$pass = '';
	$charset = 'utf8mb4';
	$dsn = "mysql:host={$host};dbname={$db};charset={$charset}";
	$opt = [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION];
	$pdo = new PDO($dsn, $user, $pass, $opt);
?>