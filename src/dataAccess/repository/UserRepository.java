package dataAccess.repository;

import java.util.List;
import dataAccess.dbmodel.UserDto;


public class UserRepository extends GenericRepository<UserDto> implements IUserRepository{
	
	public UserDto findUser(int id) {
		UserRepository user = new UserRepository();
		return user.findById(id);
	}
	
	public void deleteUser(int id) {
		UserRepository user = new UserRepository();
		user.deleteById(id);
	}
	
	
	public void updateUser(int id, UserDto u) {
		UserRepository user = new UserRepository();
		user.update(id,u);
	}
	
	public void insertUser(UserDto u) {
		UserRepository user = new UserRepository();
		user.insert(u);
	}
	
	public List<UserDto> findAllUsers() {
		UserRepository user = new UserRepository();
		List<UserDto> users = user.findAll();
		return users;
	}
}
