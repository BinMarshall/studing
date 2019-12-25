package cn.bravolinks.erp.crm.server.model;

/**
 * Created by yanqin on 2017/3/27.
 */
public class Dept{
    private Integer id;//	部门id
    private String name;//	部门名称
    private Integer parentid;//	父亲部门id。根部门为1
    private Integer order;//在父部门中的次序值。order值小的排序靠前。

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
