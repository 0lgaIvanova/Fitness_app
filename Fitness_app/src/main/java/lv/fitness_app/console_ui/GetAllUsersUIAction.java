package lv.fitness_app.console_ui;

import lv.fitness_app.services.GetAllUsersService;

public class GetAllUsersUIAction implements UIAction {

    private GetAllUsersService getAllUsersService;

    public GetAllUsersUIAction(GetAllUsersService getAllUsersService) {
        this.getAllUsersService = getAllUsersService;
    }

    @Override
    public void execute() {
        System.out.println("User list: ");
        getAllUsersService.execute().forEach(System.out::println);
        System.out.println("User list end.");
    }
}
