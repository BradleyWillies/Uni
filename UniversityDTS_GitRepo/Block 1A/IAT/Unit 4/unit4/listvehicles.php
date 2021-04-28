<html>
	<head>
    	<meta charset="UTF-8">
    	<title>Vehicles</title>
    </head>
	<body>
		<?php
			// connect to db
			require_once('connectdb.php');

			// create statement object
			$sql = "SELECT * FROM vehicles";
			$stmt = $pdo->prepare($sql);

			// execute the query
			$stmt->execute();

			// process the result
			echo "<table border='1' style='border-collapse:collapse'>";
			for ($i = 0 ; $i < $stmt->rowCount() ; $i++) {
				$rowFields = $stmt->fetch(PDO::FETCH_ASSOC);
				if ($i == 0) {
					echo "<tr>";
					foreach ($rowFields as $key => $value) {
						echo "<th>$key</th>";
					}
					echo "</tr>";
				}
				echo "<tr>";
				foreach ($rowFields as $key => $value) {
					echo "<td>$value</td>";
				}
				echo "</tr>";
			}
			echo "</table>";

			// close the connection
			$pdo=null;
		?>
	</body>
</html>