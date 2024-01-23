package lv.fitness_app.console_ui;

import lv.fitness_app.services.AddUserService;

import java.util.Scanner;

public class AddUserUIAction implements UIAction {

    private AddUserService addUserService;

    public AddUserUIAction(AddUserService addUserService) {
        this.addUserService = addUserService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String userName = scanner.nextLine();
        System.out.println("Enter user password: ");
        String userPassword = scanner.nextLine();
        addUserService.execute(userName, userPassword);
        System.out.println("You are successfully added!");
    }

}
