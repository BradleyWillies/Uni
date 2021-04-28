<html>
	<head>
    	<meta charset="UTF-8">
    	<title>Calculator</title>
    </head>
	<body>
		<?php
			// get the numbers
			$num1 = $_GET["num1"];
			$num2 = $_GET["num2"];

			// validation
			if (!empty($num1) && !empty($num2)) {
				$addition = $num1 + $num2;
				$subtraction = $num1 - $num2;
				$multiplication = $num1 * $num2;
				$division = $num1 / $num2;
				echo 	"<p>Your first input: {$num1}<br/>
						Your second input: {$num2}<br/>
						Addition result: {$addition}<br/>
						Subtraction result: {$subtraction}<br/>
						Multiplication result: {$multiplication}<br/>
						Division result: {$division}";
			} else {
				echo "<p><b>ERROR</b><br/>Please enter any numbers other than 0</p>";
			}
		?>
	</body>
</html>