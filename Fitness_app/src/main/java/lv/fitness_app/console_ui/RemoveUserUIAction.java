package lv.fitness_app.console_ui;

import lv.fitness_app.database.Database;
import lv.fitness_app.services.RemoveUserService;

import java.util.Scanner;

public class RemoveUserUIAction implements UIAction {


    private RemoveUserService removeUserService;

    public RemoveUserUIAction(RemoveUserService removeUserService) {
        this.removeUserService = removeUserService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user id to remove: ");
        Long userId = Long.parseLong(scanner.nextLine());
        removeUserService.execute(userId);
        System.out.println("Your user was removed from list.");
    }
}
