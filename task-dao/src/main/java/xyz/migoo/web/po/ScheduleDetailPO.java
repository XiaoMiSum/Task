package xyz.migoo.web.po;

import java.util.Date;

/**
 * @author xiaomi
 */
public class ScheduleDetailPO {

    private Long id;
    private Long scheduleId;
    private String title;
    private Integer status;
    private Integer level;
    private Integer isDelete;
    private Date createAt;
    private Date modifyAt;

    public ScheduleDetailPO(){

    }

    public ScheduleDetailPO(Long id, String title, Integer status, Integer level) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
