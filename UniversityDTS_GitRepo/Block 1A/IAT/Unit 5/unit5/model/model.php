<?php

include_once("model/account.php");

class Model {

    private $server;
    private $dbname;
    private $username;
    private $password;
    private $pdo;
	# define the constructor which has four arguments for $server, $dbname, $username, $password.
	# The $pdo field should be assigned as null
    public function __construct($server, $dbname, $username, $password) {
        $this->server = $server;
        $this->dbname = $dbname;
        $this->username = $username;
        $this->password = $password;
        $this->pdo = null;
    }

    #Define a Connect() function to create the $pdo as a PDO object based on the four fields $server, $dbname, $username, $password.
	#Using the try/catch structure to handle the database connection error
    public function connect() {
        $charset = 'utf8mb4';
        $dsn = "mysql:host={$this->server};dbname={$this->dbname};charset={$charset}";
        $opt = [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION];
        try {
    		$pdo = new PDO($dsn, $this->username, $this->password, $opt);
    	} catch (PDOException $ex) {
    		echo "<p>Database error, details: $ex->getMessage()</p>";
    	}
    }

    #method to get an account by id, returns an account object
	#it querys database and create an object account if the id exists in database;
	#return null otherwise
    public function getAccountById($id) {
        // create statement object
        $sql = "SELECT id, balance FROM savings WHERE id = ?";
        $stmt = $pdo->prepare($sql);

        // execute the query
        $stmt->execute([$id]);

        // process the result
        if ($stmt->rowcount() > 0) {
            $rowFields = $stmt->fetch(PDO::FETCH_ASSOC);
            $balance = $rowFields['balance'];
            return new Account($id, $balance);
        }
        else {
            return null;
        }
	}

	#method to withdraw money from account
	#returns the new balance after withdraw amount from account; return null if failure
	#it update balance of user id in the database
	#should check whether amount is less than or equal to current balance
    public function withdraw($id, $amount) {
        // create statement object
        $sql = "SELECT balance FROM savings WHERE id = ?";
        $stmt = $pdo->prepare($sql);

        // execute the query
        $stmt->execute([$id]);

        // process the result
        if ($stmt->rowcount() > 0) {
            $balance = $stmt->fetch(PDO::FETCH_ASSOC)['balance'];
            if ($amount <= $balance) {
                $newBalance = $balance - $amount;

                // create statement object
                $sql = "UPDATE savings SET balance = ? WHERE id = ?";
                $stmt = $pdo->prepare($sql);

                // execute the query
                $stmt->execute([$newBalance, $id]);

                return $newBalance;
            }
        }

        return null;
    }


	#method to deposit amount to account id
	#returns the new balance after depositing amount to account; return null if failure
	#it update balance of user id in the database
    public function deposit($id, $amount) {


	}
}
?>
