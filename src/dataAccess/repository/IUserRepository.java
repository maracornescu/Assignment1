package dataAccess.repository;

import java.util.List;

import dataAccess.dbmodel.UserDto;

public interface IUserRepository {
	
	public UserDto findUser(int id);
	public void deleteUser(int id);
	public void updateUser(int id, UserDto u);
	public void insertUser(UserDto u);
	public List<UserDto> findAllUsers();
	
}
