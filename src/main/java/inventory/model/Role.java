package inventory.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Role implements java.io.Serializable {

	private Integer id;
	private String roleName;
	private String description;
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	private Set auths = new HashSet(0);
	private Set userRoles = new HashSet(0);

	public Role() {
	}

	public Role(String roleName, String description, int activeFlag, Date createDate, Date updateDate) {
		this.roleName = roleName;
		this.description = description;
		this.activeFlag = activeFlag;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public Role(String roleName, String description, int activeFlag, Date createDate, Date updateDate, Set auths,
			Set userRoles) {
		this.roleName = roleName;
		this.description = description;
		this.activeFlag = activeFlag;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.auths = auths;
		this.userRoles = userRoles;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Set getAuths() {
		return this.auths;
	}

	public void setAuths(Set auths) {
		this.auths = auths;
	}

	public Set getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
	}

}
