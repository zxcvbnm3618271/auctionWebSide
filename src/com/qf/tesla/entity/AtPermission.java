package com.qf.tesla.entity;



/**
 * AtPermission entity. @author MyEclipse Persistence Tools
 */

public class AtPermission  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String permissionname;
     private String desc;


    // Constructors

    /** default constructor */
    public AtPermission() {
    }

	/** minimal constructor */
    public AtPermission(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public AtPermission(Integer id, String permissionname, String desc) {
        this.id = id;
        this.permissionname = permissionname;
        this.desc = desc;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionname() {
        return this.permissionname;
    }
    
    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public String getDesc() {
        return this.desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
   








}