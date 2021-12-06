import java.util.Scanner;

public class Project {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to a fun math game. Refer to the following rules if you are new:");
		System.out.println("You will be playing against a CPU.");
		System.out.println("You will each take turns counting to a target(objective) number, starting from 0.");
		System.out.println("Both you and the CPU will be restricted to a certain range to count by.");
		System.out.println("For example, if the range is 1-3, then you can only count by a number in that range.");
		System.out.println("As each of you take turns counting, the first person to reach the objective wins.");
		System.out.println("Do not count above the objective, otherwise you lose.");
		System.out.println("You can accept rematches, and you can modify the rules before each game.");
		System.out.println("Please refer to the proposal if you are still confused.");

		// Game properties
		boolean newGame = true;
		boolean setRules = false;
		int userWins = 0;
		int cpuWins = 0;
		int rematch = 0;
		
		// Declare variables
		String firstTurn = "";
		int objective = 0;
		int maxTurn = 0;
		String confirmRules;
		int targetMultiple = 0;
		int startTarget  = 0;
		boolean lastTurn = false;
		int add = 0;
		int turnNum = 0;
		int turn = 0;
		int currentNum = 0;
				
		while (newGame == true) {
			
			setRules = false;
			while (setRules == false) {
				System.out.println(); // Format
				System.out.println("Rule settings:");

				System.out.print("Enter 'User' to go first; enter 'CPU' to go second: ");
				firstTurn = sc.next();

				// Invalid input
				while (!firstTurn.equals("User") && !firstTurn.equals("CPU")) {
					System.out.print("Invalid input. Please enter 'User' or 'CPU': ");
					firstTurn = sc.next();
				}

				System.out.print("Enter the number (objective) to count to (must be > 1): ");
				objective = sc.nextInt();

				// Invalid input
				while (objective <= 1) {
					System.out.print("Invalid input. Please enter an integer that is greater than 1: ");
					objective = sc.nextInt();
				}

				System.out.print("Highest number to count by (must be > 1): ");
				maxTurn = sc.nextInt();

				// Invalid input
				while (maxTurn <= 1) {
					System.out.print("Invalid input. Please enter an integer that is greater than 1: ");
					maxTurn = sc.nextInt();
				}

				System.out.println(); // Format
				
				// Confirm rules
				System.out.println("Please confirm rules:");
				if (firstTurn.equals("User")) {
					System.out.println("User goes first.");
				} else {
					System.out.println("CPU goes first.");
				}
				System.out.println("Objective (target): " + objective);
				System.out.println("Range: 1-" + maxTurn);
				
				System.out.print("Enter 'Yes' to confirm; enter 'No' to change rules: ");
				confirmRules = sc.next();
				
				// Invalid input
				while (!confirmRules.equals("Yes") && !confirmRules.equals("No")) {
					System.out.print("Invalid input. Please enter 'Yes' or 'No': ");
					confirmRules = sc.next();
				}
				
				if (confirmRules.equals("Yes")) {
					setRules = true;
				} else {
					setRules = false;
				}
				
			}

			// Determines if user goes first or not
			boolean userFirst;
			if (firstTurn.equals("User")) {
				userFirst = true;
			} else {
				userFirst = false;
			}

			targetMultiple = maxTurn + 1;
			startTarget = 0;

			for (int i = objective; i > 0; i -= targetMultiple) {
				startTarget = i;
			}

			turnNum = 0;
			currentNum = 0;
			
			System.out.println(); // Format
			
			for (int i = 0; i < objective; i += turn) {
				turn = 0;
				System.out.print("Current number: ");
				currentNum = i;
				System.out.println(currentNum);

				// User's turn
				if (userFirst == true && turnNum % 2 == 0) {
					System.out.print("Input how much you would like to add: ");
					turn = sc.nextInt();

					// Invalid input
					while (turn < 1 || turn > maxTurn) {
						System.out.print("Invalid input. Please enter an integer in the range 1-" + maxTurn + ": ");
						turn = sc.nextInt();
					}

					System.out.println("Uesr: +" + turn);

					lastTurn = true;

					// CPU's turn
				} else if (userFirst == true && turnNum % 2 != 0) {
					for (int j = startTarget; j <= objective; j += targetMultiple) {
						if (maxTurn >= j - i && j - i > 0) {
							add = j - i;
							j = objective; // End for loop
						} else {
							add = 1;
						}
					}

					turn += add;
					lastTurn = false;
					System.out.println("CPU: +" + add);

					// CPU's turn
				} else if (userFirst == false && turnNum % 2 == 0) {
					for (int j = startTarget; j <= objective; j += targetMultiple) {
						if (maxTurn >= j - i && j - i > 0) {
							add = j - i;
							j = objective; // End for loop
						} else {
							add = 1;
						}
					}

					turn += add;
					lastTurn = false;
					System.out.println("CPU: +" + add);

					// User's turn
				} else if (userFirst == false && turnNum % 2 != 0) {
					System.out.print("Input how much you would like to add: ");
					turn = sc.nextInt();

					// Invalid input
					while (turn < 1 || turn > maxTurn) {
						System.out.print("Invalid input. Please enter an integer in the range 1-" + maxTurn + ": ");
						turn = sc.nextInt();
					}

					System.out.println("User: +" + turn);

					lastTurn = true;
				}

				turnNum++;
				System.out.println();

			}

			System.out.println("Final number: " + (currentNum + turn));
			
			if (lastTurn == true && (currentNum + turn) == objective) {
				System.out.println("You win!");
				userWins++;
			} else {
				System.out.println("You lost.");
				cpuWins++;
			}
			
			System.out.println(); // Format
			
			System.out.println("Record: " + userWins + "-" + cpuWins);
			System.out.print("Enter '1' for rematch; enter '2' to quit: ");
			rematch = sc.nextInt();
			
			while (rematch != 1 && rematch != 2) {
				System.out.print("Invalid input. Please enter '1' or '2': ");
				rematch = sc.nextInt();
			}
			
			if (rematch == 1) {
				System.out.println("Setting new game.");
			} else if (rematch == 2) {
				newGame = false;
				System.out.println("Program closed.");
			}
			
		}

	}

}
