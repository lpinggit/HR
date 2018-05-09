package com.bluedot.service.impl;

import java.util.Set;

import com.bluedot.dao.MenuDao;
import com.bluedot.dao.impl.MenuDaoImpl;
import com.bluedot.po.Menu;
import com.bluedot.service.MenuService;

public class MenuServiceImpl implements MenuService {

	MenuDao menuDao;
	public MenuServiceImpl(){
		menuDao=new MenuDaoImpl();
	}
	public Set<Menu> getAllMenu() {

		return menuDao.getAllMenu();
	}

}
