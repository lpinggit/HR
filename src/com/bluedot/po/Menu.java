package com.bluedot.po;

import java.util.Set;
import java.util.TreeSet;

import com.bluedot.util.MyComparator;

public class Menu {
	private int menuId;
	private int parentId;
	private String menuName;
	private String menuUri;
	private Set<Fun> funSet = new TreeSet<Fun>(new MyComparator());

	public Set<Fun> getFunSet() {
		return funSet;
	}

	public void setFunSet(Set<Fun> funSet) {
		this.funSet = funSet;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUri() {
		return menuUri;
	}

	public void setMenuUri(String menuUri) {
		this.menuUri = menuUri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((funSet == null) ? 0 : funSet.hashCode());
		result = prime * result + menuId;
		result = prime * result
				+ ((menuName == null) ? 0 : menuName.hashCode());
		result = prime * result + ((menuUri == null) ? 0 : menuUri.hashCode());
		result = prime * result + parentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (funSet == null) {
			if (other.funSet != null)
				return false;
		} else if (!funSet.equals(other.funSet))
			return false;
		if (menuId != other.menuId)
			return false;
		if (menuName == null) {
			if (other.menuName != null)
				return false;
		} else if (!menuName.equals(other.menuName))
			return false;
		if (menuUri == null) {
			if (other.menuUri != null)
				return false;
		} else if (!menuUri.equals(other.menuUri))
			return false;
		if (parentId != other.parentId)
			return false;
		return true;
	}

	
}
