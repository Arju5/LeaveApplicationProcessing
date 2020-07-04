package com.sa4105.javaca2.service;

import java.util.ArrayList;
import java.util.List;

import com.sa4105.javaca2.model.Role;

public interface RoleService {
	
	public ArrayList<Role> findAll();
	public boolean createRole(Role role);
	public boolean editRole(Role role);
	public void deleteRole(Role role);
	public boolean saveRole(Role role);
    public Role findRoleById(int id);
    public Role findRoleByRoleName(String roleName);
}
