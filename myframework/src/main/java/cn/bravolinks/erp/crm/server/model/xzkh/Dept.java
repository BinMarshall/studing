package cn.bravolinks.erp.crm.server.model.xzkh;

/**
 * Created by yanqin on 2017/3/27.
 */
public class Dept{
    private Integer id;//	部门id
    private String name;//	部门名称
    private Integer parentid;//	父亲部门id。根部门为1
    private Integer order;//在父部门中的次序值。order值小的排序靠前。
    private String gsdm; //公司代码
    private String userid; //userid
    
    private String dm; //公司代码1
    private String dm2; //公司代码2
    private String mc; //公司mc
    
    
    
    

    public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getDm2() {
		return dm2;
	}

	public void setDm2(String dm2) {
		this.dm2 = dm2;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
