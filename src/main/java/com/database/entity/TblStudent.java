package com.database.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author cxw
 * @since 2019-09-25
 */
public class TblStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private String id;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 年龄
     */
    @TableField("age")
    private String age;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 数据新增用户id
     */
    @TableField("datainsuserid")
    private String datainsuserid;

    /**
     * 数据新增时间
     */
    @TableField("datainstime")
    private LocalDateTime datainstime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDatainsuserid() {
        return datainsuserid;
    }

    public void setDatainsuserid(String datainsuserid) {
        this.datainsuserid = datainsuserid;
    }

    public LocalDateTime getDatainstime() {
        return datainstime;
    }

    public void setDatainstime(LocalDateTime datainstime) {
        this.datainstime = datainstime;
    }

    @Override
    public String toString() {
        return "TblStudent{" +
        "id=" + id +
        ", name=" + name +
        ", age=" + age +
        ", sex=" + sex +
        ", datainsuserid=" + datainsuserid +
        ", datainstime=" + datainstime +
        "}";
    }
}