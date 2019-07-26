package com.qf.tesla.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * AtRole entity. @author MyEclipse Persistence Tools
 */

public class AtRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private String rolename;
	private String desc;
	private Set<AtPermission> atPermissions = new HashSet<AtPermission>();

	// Constructors

	/** default constructor */
	public AtRole() {
	}

	/** minimal constructor */
	public AtRole(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AtRole(Integer id, String rolename, String desc) {
		this.id = id;
		this.rolename = rolename;
		this.desc = desc;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<AtPermission> getAtPermissions() {
		return atPermissions;
	}

	public void setAtPermissions(Set<AtPermission> atPermissions) {
		this.atPermissions = atPermissions;
	}
	

}