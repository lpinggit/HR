package com.bluedot.util;

import java.util.Comparator;

import com.bluedot.po.Employee;
import com.bluedot.po.Fun;
import com.bluedot.po.Menu;

public class MyComparator implements Comparator<Object>{

	public int compare(Object object1, Object object2) {
		if(object1 instanceof Employee && object2 instanceof Employee)
		{
			int empId1=((Employee)object1).getEmpId();
		    int empId2=((Employee)object2).getEmpId();
		    int result=empId1-empId2;
		    if(result<0)
			{
				return -1;
			}
			else if(result>0)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		if(object1 instanceof Menu && object2 instanceof Menu)
		{
			int menuId1=((Menu)object1).getMenuId();
		    int menuId2=((Menu)object2).getMenuId();
		    int result=menuId1-menuId2;
		    if(result<0)
			{
				return -1;
			}
			else if(result>0)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		if(object1 instanceof Fun && object2 instanceof Fun)
		{
			int funId1=((Fun)object1).getFunId();
		    int funId2=((Fun)object2).getFunId();
		    int result=funId1-funId2;
		    if(result<0)
			{
				return -1;
			}
			else if(result>0)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		return 0;
	}

}
