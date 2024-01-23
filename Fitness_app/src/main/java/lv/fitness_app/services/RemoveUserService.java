package lv.fitness_app.services;

import lv.fitness_app.database.Database;

public class RemoveUserService {

    private Database database;

    public RemoveUserService(Database database) {
        this.database = database;
    }

    public void execute(Long bookId) {
        database.deleteById(bookId);
    }

    // public void execute(){
    //Scanner scanner = new Scanner(System.in);
      //  System.out.println("Enter your email: ");
    //String email = scanner.nextLine();
      //  System.out.println("Enter password: ");
    //String password = scanner.nextLine();
    //RemoveUserRequest request = new RemoveUserRequest(email, password);
  //  RemoveUserResponse response = removeUserService.execute(request);

//        response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
  //  } else {
    //    if (response.isUserRemoved()) {
      //      System.out.println("User removed successfully");
        //} else {
          //  System.out.println("User was not removed");
//        }
  //  }
//}
}
