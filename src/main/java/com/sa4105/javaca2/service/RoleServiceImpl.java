package com.sa4105.javaca2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.LeaveStatus;
import com.sa4105.javaca2.model.Role;
import com.sa4105.javaca2.repo.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository rrepo;
	@Override
	public ArrayList<Role> findAll() {
		ArrayList<Role> list = (ArrayList<Role>) rrepo.findAll();
		return list;
	}

	@Override
	public boolean createRole(Role role) {
		if (rrepo.save(role)!=null) return true;
		return false;
	}

	@Override
	public void deleteRole(Role role) {
		rrepo.delete(role);
	}

	@Override
	public boolean editRole(Role role) {
		if (rrepo.save(role)!=null) return true;
		return false;
	}
	
	@Override
    public Role findRoleById(int id) {
		return rrepo.findById(id).get();
    }

    @Override
    public boolean saveRole(Role role) {
        if(rrepo.save(role) != null) return true; else return false;
    }
    
    @Override
    public Role findRoleByRoleName(String roleName) {
		return rrepo.findByroleName(roleName);
    }

}
