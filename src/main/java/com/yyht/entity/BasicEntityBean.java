package com.yyht.entity;

import java.io.Serializable;
import java.util.Date;


import com.google.common.base.Objects;

/**
 * <p>
 * 实体模型的基类(实现了序列化接口),
 * 所有的Domain对象都需要从BaseEntity继承
 * </p>
 *
 * @author lonyee
 * @date 2016-07-14
 */
public abstract class BasicEntityBean implements Serializable {
    
	private static final long serialVersionUID = -1394178167652755500L;
	/**
     * 主键id
     */
    private Integer id = null;
    /**
     * 是否可用（软删除使用）
     */
    private Boolean usable = true;
    
    public Integer getId() {
        return (id==null || id<=0) ? null: id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	public Boolean getUsable() {
		return usable;
	}

	public void setUsable(Boolean usable) {
		this.usable = usable;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicEntityBean that = (BasicEntityBean) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
