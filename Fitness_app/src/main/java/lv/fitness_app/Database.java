package lv.fitness_app;

import java.util.List;

public interface Database {

	void save(User user);

	void deleteById(Long id);

	List<User> getAllUsers();

}
