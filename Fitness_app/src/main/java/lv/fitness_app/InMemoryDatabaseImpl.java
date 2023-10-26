package lv.fitness_app;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabaseImpl implements Database {

	private Long nextId = 1L;
	private List<User> users = new ArrayList<>();

	@Override
	public void save(User user) {
		user.setId(nextId);
		nextId++;
		users.add(user);
	}

	@Override
	public void deleteById(Long id) {
		users.stream()
				.filter(user -> user.getId().equals(id))
				.findFirst()
				.ifPresent(user -> users.remove(user));
	}

	@Override
	public List<User> getAllUsers() {
		return users;
	}
}
