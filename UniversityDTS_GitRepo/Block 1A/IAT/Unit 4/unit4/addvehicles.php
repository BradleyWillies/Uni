<html>
	<head>
    	<meta charset="UTF-8">
    	<title>Add Vehicles</title>
    </head>
	<body>
		<h1>Add Vehicle</h1>
		<form method="post" action="addvehicles.php">
  			Registration: <input type="text" name="reg"/><br/>
  			Make: 
  			<select name="make">
  				<option value="BMW" selected="selected">BMW</option>
  				<option value="MAN">MAN</option>
  				<option value="Audi">Audi</option>
  				<option value="Mercedes">Mercedes</option>
  			</select><br/>
  			Daily rate: <input type="number" min="0" value="0" step=".01"/><br/>
  			Choose category:<br/>
  				car<input type="radio" value="car" name="category"/>
  				truck<input type="radio" value="truck" name="category"/>
  			<br/>
  			<input type="hidden" name="submitted" value="true" />
  			<input type="submit" name="submit" value="Go"/>
  			<input type="reset" value="Reset"/>
  		</form>
	</body>
</html>