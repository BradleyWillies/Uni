<html>
	<head>
    	<meta charset="UTF-8">
    	<title>Add Vehicles</title>
    </head>
	<body>
		<h1>Add Vehicle</h1>
		
  		<?php
			// connect to db
			require_once('connectdb.php');
			

			// check form is submitted
			if (!empty($_POST['submitted']) &&
				!empty($_POST['reg']) &&
				!empty($_POST['make']) &&
				!empty($_POST['rate']) &&
				!empty($_POST['category'])) {
				// get form variables
				$reg = $_POST['reg'];
				$make = $_POST['make'];
				$rate = $_POST['rate'];
				$category = $_POST['category'];

				// validation
				$errors = '';
				$errorFlag = false;
				if (!is_string($reg)) {
					$errors = $errors . "ERROR: " . htmlspecialchars($reg) . " is not a valid string<br/>";
					$errorFlag = true;
				}
				if (!is_numeric($rate) || $rate < 0) {
					$errors = $errors . "ERROR: " . htmlspecialchars($rate) . " is not a valid number<br/>";
					$errorFlag = true;
				}
				if ($errorFlag) {
					echo "<p>$errors</p>";
				} else {
					// SQL insert
					try {
						// $reg = $pdo->quote($reg);
						// $make = $pdo->quote($make);
						// $rate = $pdo->quote($rate);
						// $category = $pdo->quote($category);
						$sql = "INSERT INTO vehicles 
								(reg_no, category, brand, dailyrate)
								VALUES
								(:reg, :category, :make, :rate)";
						$stmt = $pdo->prepare($sql);
						$stmt->bindParam(':reg', $reg, PDO::PARAM_STR);
						$stmt->bindParam(':category', $category, PDO::PARAM_STR);
						$stmt->bindParam(':make', $make, PDO::PARAM_STR);
						$stmt->bindParam(':rate', $rate, PDO::PARAM_STR);
						$stmt->execute();
						echo "<p>Vehicle added to database!</p>";
						echo "<a href='http://localhost/unit4/addvehicles.php'>Refresh</a>";
					} catch (PDOException $ex) {
						echo "<p>Error inserting into database, error details: $ex->getMessage()</p>";
					}
				}
				
			} else {
				?>
				<form method="post" action="addvehicles.php">
		  			Registration: <input type="text" name="reg" required/><br/>
		  			Make: 
		  			<select name="make" required>
		  				<option value="BMW" selected="selected">BMW</option>
		  				<option value="MAN">MAN</option>
		  				<option value="Audi">Audi</option>
		  				<option value="Mercedes">Mercedes</option>
		  			</select><br/>
		  			Daily rate: <input type="number" required name="rate"  value="1" step=".01"/><br/>
		  			Choose category:<br/>
		  				car<input type="radio" value="car" name="category" required/>
		  				truck<input type="radio" value="truck" name="category" required/>
		  			<br/>
		  			<input type="hidden" name="submitted" value="true" />
		  			<input type="submit" name="submit" value="Go"/>
		  			<input type="reset" value="Reset"/>
		  		</form>
		  		<?php
			}
		?>
	</body>
</html>