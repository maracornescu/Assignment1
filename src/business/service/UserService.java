package business.service;

import java.util.ArrayList;
import java.util.List;

import business.model.UserModel;
import dataAccess.dbmodel.UserDto;
import dataAccess.repository.IUserRepository;
import dataAccess.repository.UserRepository;

public class UserService implements IUserService{
	
	private final IUserRepository repository;
	
	public UserService() {
		this.repository = new UserRepository();	
	}
	
	private UserModel mapper(UserDto user) {
		UserModel model = new UserModel();
		
		model.setUserId(user.getUserId());
		model.setUsername(user.getUsername());
		model.setPassword(user.getPassword());
		model.setIsAdmin(user.getIsAdmin());
		
		return model;
	}
	
	public List<UserModel> findAllUsers() {
		List<UserDto> user = repository.findAllUsers();	
		List<UserModel> result = new ArrayList<UserModel>();
		for(UserDto u:user) {
			UserModel m = mapper(u);
			result.add(m);
		}
		return result;
	}
	
	public UserModel findUser(int id) {
		UserDto user = repository.findUser(id);
		UserModel result = mapper(user);		
		return result;
	}
	
	public void deleteUser(int id) {
		repository.deleteUser(id);
	}
	
	public void updateUser(int id, UserModel model) {
		
		UserDto user = new UserDto();
		user.setUserId(model.getUserId());
		user.setUsername(model.getUsername());
		user.setPassword(model.getPassword());
		user.setIsAdmin(model.getIsAdmin());
		
		repository.updateUser(id, user);
	}
	
	public void insertUser(UserModel model) {
		
		UserDto user = new UserDto();
		user.setUserId(model.getUserId());
		user.setUsername(model.getUsername());
		user.setPassword(model.getPassword());
		user.setIsAdmin(model.getIsAdmin());
		
		repository.insertUser(user);
	}
	
}
