package com.revature.project1.models;

public class User {

	
		private int userId;
		private String username;
		private String password;
		private String firstName;
		private String lastName;
		private String email;
		private int roleId;
		private String roleName;
		
		public User() {
			// TODO Auto-generated constructor stub
		}

		public User(int userId, String username, String password, String firstName, String lastName, String email,
				int roleId, String roleName) {
			super();
			this.userId = userId;
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.roleId = roleId;
			this.roleName = roleName;
		}

		
		
		public User(int userId, String username, String password, String firstName, String lastName, String email,
				int roleId) {
			super();
			this.userId = userId;
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.roleId = roleId;
		}

		public User(String username, String password, String firstName, String lastName, String email, int roleId) {
			super();
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.roleId = roleId;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getRoleId() {
			return roleId;
		}

		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		public int getUserId() {
			return userId;
		}

		@Override
		public String toString() {
			return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
					+ firstName + ", lastName=" + lastName + ", email=" + email + ", roleId=" + roleId + ", roleName="
					+ roleName + "]";
		}
		
		
		
		
		
		
}
