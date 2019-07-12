package xyz.migoo.web.vo;

/**
 * @author kogome
 */
public class UserVO {
    private Long id;
    private String userName;
    private String password;
    private String salt;
    private Integer isDelete;
    private String remark;

    public UserVO(){}

    public UserVO(Long id, String userName, String password, String salt, Integer isDelete, String remark) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.isDelete = isDelete;
        this.remark = remark;
    }

    public UserVO(Long id, String userName, String password, String salt) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"salt\":\"")
                .append(salt).append('\"');
        sb.append(",\"isDelete\":")
                .append(isDelete);
        sb.append(",\"remark\":\"")
                .append(remark).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
