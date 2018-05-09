package com.bludeodt.dict;

public interface WordDict {
	// 关于用户的.do
	String USER_LOGIN = "userlogin.do";
	String USER_LOGIN_OUT = "userlogout.do";
	String USER_LOGIN_CHECK = "userloginCheck.do";
	String ADD_USER = "addUser.do";
	String SAVE_USER = "saveUser.do";
	String VIEW_USER = "viewUser.do";
	String GRANT_USER = "grantUser.do";
	String SAVE_GRANT_USER = "saveGrantUser.do";
	String DELETE_USER = "deleteUser.do";
	String USER_ALTER_PASSWORD = "useralterPassword.do";
	String SHOW_USER_DETAILS = "showUserDetails.do";
	String SAVE_USER_DETAILS = "saveUserDetails.do";
	String MODIFY_USER_DETAILS ="modifyUserDetails.do";
	String USER_PASSWORD_UPDATED = "userpasswordUpdated.do";

	// 关于角色的.do
	String ADD_ROLE = "addRole.do";
	String SAVE_ROLE = "saveRole.do";
	String VIEW_ROLE = "viewRole.do";
	String GRANT_ROLE = "grantRole.do";
	String SAVE_ROLE_GRANT = "saveRoleGrant.do";
	String DELETE_ROLE="deleteRole.do";

	// 关于被推荐人
	String ADD_RECOMMENDER = "addRecommender.do";
	String SAVE_RECOMMENDER = "saveRecommender.do";

	// 发布招聘信息
	String PUBLISH_RECRUIT_INFO = "publishRecruitInfo.do";
}
