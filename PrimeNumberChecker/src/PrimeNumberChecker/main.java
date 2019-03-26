package PrimeNumberChecker;

import java.util.Scanner;

public class main {

	static int low = 1; //Starting min
    static int high = 100000;	//Starting max
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Prime number checker\n---------Ryan Rasi---------");
		System.out.println("Starts at 1 and increments but asks user input every 100000 results to continue");

		primeNumberSolver();
	}
	public static void primeNumberSolver() {
        
        while (low < high) { //Looks for values between the two min and max limiters
            boolean flag = false;

            for(int i = 2; i <= low/2; ++i) {
                if(low % i == 0) {
                    flag = true;
                    break;
                }
            }

            if (!flag)
                System.out.print(low + " "); //Prints the prime number

            ++low;
        }
        choice();
	}
	public static void choice() {
	String decision = null;
		do {
	System.out.println("\nDo you wish to continue? (Y or N)"); //Checks for approval before continuing
	@SuppressWarnings("resource")
	Scanner choice = new Scanner(System.in);
	decision = choice.next().toLowerCase();
		} while  (!decision.equals("y") && (!decision.contentEquals("n")));
	switch (decision) {
	case ("y"):
		high = high + 100000;		//Increment to increase the max limit by
		primeNumberSolver();
		break;
	case ("n"):
		System.out.println("Application terminated");
		System.exit(0);
		break;
	}
}

}
