package com.ontimize.songslists.model.core.service;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ontimize.songslists.api.core.service.IUserService;
import com.ontimize.songslists.model.core.dao.UserDao;
import com.ontimize.songslists.model.core.dao.UserNickDao;
import com.ontimize.db.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;


@Service("UserService")
@Lazy
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserNickDao userNickDao;

	@Autowired
	private DefaultOntimizeDaoHelper daoHelper;

	public void loginQuery(Map<?, ?> key, List<?> attr) {
	}

	public EntityResult userQuery(Map<?, ?> keyMap, List<?> attrList) {
		keyMap.values();
		if (keyMap.isEmpty()) {
			System.out.println("hola");
			keyMap.put("user", this.daoHelper.getUser().getUsername());	
			return this.daoHelper.query(userNickDao, keyMap, attrList);
		}
		return this.daoHelper.query(userDao, keyMap, attrList);
	}

	public EntityResult userInsert(Map<?, ?> attrMap) {
		return this.daoHelper.insert(userDao, attrMap);
	}

	public EntityResult userUpdate(Map<?, ?> attrMap, Map<?, ?> keyMap) {
		return this.daoHelper.update(userDao, attrMap, keyMap);
	}

	public EntityResult userDelete(Map<?, ?> keyMap) {
		Map<Object, Object> attrMap = new HashMap<>();
		attrMap.put("delete_date_user", new Timestamp(Calendar.getInstance().getTimeInMillis()));
		return this.daoHelper.update(this.userDao, attrMap, keyMap);
	}

}
