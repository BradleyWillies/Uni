<!DOCTYPE html>
<html>
<head>
  <title>Unit 3 Basic PHP Programing - Tasks </title>
</head>

<body>
	<h1>Unit 3 tasks</h1>

	<!-- Task 1: String-->
	<!-- write your solution to Task 1 here -->
	<div class="section">
		<h2>Task 1 : String</h2>
		<?php
            $stringVar = "I love programming";
         ?>
         <ul>
                <li>First letter is: <?= substr($stringVar, 0, 1); ?></li>
                <li>Length of string is: <?= strlen($stringVar); ?></li>
                <li>Last letter is: <?= substr($stringVar, strlen($stringVar) - 1, 1); ?></li>
                <li>First 6 letters are: <?= substr($stringVar, 0, 6); ?></li>
                <li>In capital: <?= strtoupper($stringVar); ?></li>
         </ul>
	</div>

	<!-- Task 2: Array and image-->
	<!-- write your solution to Task 2 here -->
	<div class="section">
		<h2>Task 2 : Array and image</h2>
        <?php
            $picArray = array("earth.jpg", "flower.jpg", "plane.jpg", "tiger.jpg");
            $rand = rand()%sizeof($picArray);
         ?>
         <img
            src="images/<?= $picArray[$rand]; ?>"
            alt="<?= rtrim($picArray[$rand], ".jpg") ?>"
            width="20%"
         >
	</div>

	<!-- Task 3: Function definition dayinmonth  -->
	<!-- write your solution to Task 3 here -->
	<div class="section">
		<h2>Task 3 : Function definition</h2>
        <?php
            function daysInMonth ($monthNum) {
                $monthsWith30 = array(4, 6, 9, 11);
                $monthsWith31 = array(1, 3, 5, 7, 8, 10, 12);
                switch ($monthNum) {
                    case 2 :
                        return 28;
                    case in_array($monthNum, $monthsWith30) :
                        return 30;
                    case in_array($monthNum, $monthsWith31) :
                        return 31;
                    default :
                        return 0;
                }
            }
         ?>
         <ul>
             <?php for ($i = 1 ; $i <= 12 ; $i++) { ?>
                    <li>Month <?= $i ?> => <?= daysInMonth($i); ?> days.</li>
             <?php } ?>
         </ul>
	</div>



	<!-- Task 4: Favorite Artists from a File (Files) -->
	<!-- write your solution to Task 4 here -->
	<div class="section">
		<h2>Task 4: My Favorite Artists from a file</h2>
        <?php
            $artists = file('favorite.txt', FILE_IGNORE_NEW_LINES);
        ?>
        <ol>
            <?php foreach ($artists as $artist) { ?>
                <li><a href="http://www.mtv.com/artists/<?= str_replace(" ", "-", strtolower($artist)); ?>/"><?= $artist ?></a></li>
            <?php } ?>
        </ol>
	</div>

	<!-- Task 6: Directory operations -->
	<!-- write your solution to Task 6 here -->
	<div class="section">
		<h2>Task 6 : Directory operations</h2>



	</div>

	<!-- Task 6 optional: Directory operations -->
	<!-- write your solution to Task 6 optional here -->
	<div class="section">
		<h2>Task 6 optional: Directory operations optional</h2>



	</div>
	</div



    <!-- Task 5: including external files -->
	<!-- write your solution to Task 5 here -->
	<div class="section">
		<h2>Task 5: including external files</h2>
        <?php include("footer.php"); ?>
	</div>

</body>
</html>
