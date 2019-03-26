package rockPaperScissors;

import java.util.Scanner;

public class main {
	static String choice = null;
	static int computerScore = 0;
	static int playerScore = 0;
	static String computerChoice = null;
	static int randomComputerchoice = 0;

		// TODO Auto-generated constructor stub


	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		System.out.println("Rock Paper Scissors\n---------\nRyan Rasi\n---------\n");
		startGame();
	}
		public static void startGame ()	{
		do {
			System.out.println("Enter Rock, Paper or Scissors\nAlternatively enter exit to terminate");
	@SuppressWarnings("resource")
	Scanner starts = new Scanner(System.in);
	choice = starts.next().toLowerCase();
		} while  (!choice.equals("rock") && !choice.equals("paper") && !choice.equals("scissors") && !choice.equals("exit"));
	
	
	switch (choice) {
	case "rock":
		System.out.println("Rock Selected\n--------");
		break;
	case "paper":
		System.out.println("Paper Selected\n---------");
		break;
	case "scissors":
		System.out.println("Scissors Selected\n---------");
		break;
	case "exit":
		System.out.println("---------Application Exit---------");
		System.exit(0);
		}
	System.out.println("Rock\n\nPaper\n\nScissors\n---------");
	
	randomComputerchoice = (int) ( Math.random() * 3 + 1);
	
	switch (randomComputerchoice) {
	case 1:
		computerChoice = "rock";
		break;
	case 2:
		computerChoice = "paper";
		break;
	case 3:
		computerChoice = "scissors";
		break;
		}
	System.out.println("Computer chooses " + computerChoice + "\n---------");
	/////////////////////////////////////////////////Player wins
	if (choice.equals("rock") && (computerChoice.equals("scissors"))){
		System.out.println("Player Wins!");
		playerScore = +1;
	} else if (choice.equals("paper") && (computerChoice.equals("rock"))){
		System.out.println("Player Wins!");
		playerScore = +1;
	} else if (choice.equals("scissors") && (computerChoice.equals("paper"))){
		System.out.println("Player Wins!");
		playerScore = +1;
	/////////////////////////////////////////////////Computer wins
	} else if (computerChoice.equals("rock") && (choice.equals("scissors"))){
		System.out.println("Computer Wins!");
		computerScore = +1;
	} else if (computerChoice.equals("paper") && (choice.equals("rock"))){
		System.out.println("Computer Wins!");
		computerScore = +1;
	} else if (computerChoice.equals("scissors") && (choice.equals("paper"))){
		System.out.println("Computer Wins!");
		computerScore = +1;
	}
	if (choice.equals("rock") && (computerChoice.equals("rock"))){
		System.out.println("Draw encountered!");
	} else if (choice.equals("paper") && (computerChoice.equals("paper"))){
		System.out.println("Draw encountered!");
	} else if (choice.equals("scissors") && (computerChoice.equals("scissors"))){
		System.out.println("Draw encountered!");
	}
System.out.println("Player has a score of: " + playerScore + "\nComputer has a score of: " + computerScore);

do {
	System.out.println("\nEnter again to retry or exit to terminate application");
@SuppressWarnings("resource")
Scanner starts = new Scanner(System.in);
choice = starts.next().toLowerCase();
} while  (!choice.equals("again") && !choice.equals("exit"));

switch(choice) {
case "again":
	startGame();
	break;
case ("exit"):
	System.out.println("---------Application Exit---------");
	System.exit(0);
}
		}
	
		}

	