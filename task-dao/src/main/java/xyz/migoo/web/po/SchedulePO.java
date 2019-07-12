package xyz.migoo.web.po;

import java.util.Date;

/**
 * @author xiaomi
 */
public class SchedulePO {

    private Long id;
    private Long userId;
    private String title;
    private Integer locked;
    private Integer level;
    private Integer isDelete;
    private Integer count;
    private Date createAt;
    private Date modifyAt;

    public SchedulePO(){

    }

    public SchedulePO(Long id, String title, Integer count, Integer locked, Integer level) {
        this.id = id;
        this.title = title;
        this.locked = locked;
        this.count = count;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevle(Integer level) {
        this.level = level;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
