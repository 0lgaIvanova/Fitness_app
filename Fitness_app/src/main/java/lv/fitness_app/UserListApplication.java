package lv.fitness_app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserListApplication {

	public static void main(String[] args) {
		Database database = new InMemoryDatabaseImpl();
		while (true) {
			printProgramMenu();
			int menuNumber = getMenuNumberFromUser();
			executeSelectedMenuItem(database, menuNumber);
		}
	}
		private static void printProgramMenu() {
			System.out.println();
			System.out.println("Program menu:");
			System.out.println("1. Register new user");
			System.out.println("2. Login");
			System.out.println("3. Delete user from database");
			System.out.println("4. Show all users in the list");
			System.out.println("5. Exit");
			System.out.println("");

		}

			private static int getMenuNumberFromUser() {
				System.out.println("Enter menu item number to execute:");
				Scanner scanner = new Scanner(System.in);
				return Integer.parseInt(scanner.nextLine());
			}

			private static void executeSelectedMenuItem(Database database, int selectedMenu) {
				switch (selectedMenu) {
					case 1: {
						addNewUserAction(database);
						break;
					}
					case 2: {
						removeUserAction(database);
						break;
					}
					case 3: {
						printAllUsersAction(database);
						break;
					}
					case 4: {
						exitProgramAction();
						break;
					}
				}
			}

			private static void exitProgramAction() {
				System.out.println("Good by!");
				System.exit(0);
			}

			private static void printAllUsersAction(Database database) {
				System.out.println("User list: ");
				database.getAllUsers().forEach(System.out::println);
				System.out.println("User list end.");
			}

			private static void removeUserAction(Database database) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter user id to remove: ");
				Long userId = Long.parseLong(scanner.nextLine());
				database.deleteById(userId);
				System.out.println("Your user was removed from list.");
			}

			private static void addNewUserAction(Database database) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter user id: ");
				Long userId = Long.valueOf(scanner.nextLine());
				System.out.println("Enter user name: ");
				String userName = scanner.nextLine();
				System.out.println("Enter user password: ");
				String userPassword = scanner.nextLine();
				User user = new User(userId, userName, userPassword);
				database.save(user);
				System.out.println("Your user was added to list.");
			}

	}
