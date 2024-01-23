package lv.fitness_app;

import lv.fitness_app.console_ui.*;
import lv.fitness_app.database.Database;
import lv.fitness_app.database.InMemoryDatabaseImpl;
import lv.fitness_app.services.AddUserService;
import lv.fitness_app.services.GetAllUsersService;
import lv.fitness_app.services.RemoveUserService;

import java.util.Scanner;

public class UserListApplication {

	private static Database database = new InMemoryDatabaseImpl();
	private static AddUserService addUserService = new AddUserService(database);
	private static RemoveUserService removeUserService = new RemoveUserService(database);
	private static GetAllUsersService getAllUsersService = new GetAllUsersService(database);
	private static UIAction addUserUIAction = new AddUserUIAction(addUserService);
	private static UIAction removeUserUIAction = new RemoveUserUIAction(removeUserService);
	private static UIAction getAllUsersUIAction = new GetAllUsersUIAction(getAllUsersService);
	private static UIAction exitUIAction = new ExitUIAction();

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
					addUserUIAction.execute();	;
						break;
					}
					case 2: {
						removeUserUIAction.execute();
						break;
					}
					case 3: {
						getAllUsersUIAction.execute();
						break;
					}
					case 4: {
						exitUIAction.execute();
						break;
					}
				}
			}
	}
