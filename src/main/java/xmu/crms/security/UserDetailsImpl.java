package xmu.crms.security;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 实现 Spring Security的 UserDetail实体
 * 是Spring Security从数据库拿出的User 实体
 * @author LiuXuezhang
 */
public class UserDetailsImpl {

    private BigInteger id;
    private String openid;
    private String phone;
    private String password;
    private Integer type;
    private String number;
    private String name;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    
    public String getPassword() {
        return this.password;
    }

    
    public String getUsername() {
        return null;
    }

    
    public boolean isAccountNonExpired() {
        return false;
    }

    
    public boolean isAccountNonLocked() {
        return false;
    }

    
    public boolean isCredentialsNonExpired() {
        return false;
    }

    
    public boolean isEnabled() {
        return false;
    }


    public String getName() {
        return name;
    }

    
    public String toString() {
        return "UserDetailsImpl{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
