package com.championdo.torneo.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role", uniqueConstraints = @UniqueConstraint(
		columnNames = {"role", "username"}))
public class UserRole {
	
	@Id
	@GeneratedValue
	@Column(name = "user_role_id", unique = true, nullable = false)
	private Integer userRoleId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username", nullable = false)
	private User user;
	
	@Column(name = "role", nullable = false, length = 45)
	private String role;
	
	public UserRole() {
		super();
	}

	public UserRole(User user, String role) {
		super();
		this.user = user;
		this.role = role;
	}

	public UserRole(Integer userRoleId, User user, String role) {
		super();
		this.userRoleId = userRoleId;
		this.user = user;
		this.role = role;
	}

	/**
	 * @return the userRoleId
	 */
	public Integer getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param userRoleId the userRoleId to set
	 */
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", role=" + role + "]";
	}
	
	

}
