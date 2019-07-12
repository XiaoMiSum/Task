package xyz.migoo.web.dto;

/**
 * @author xiaomi
 */
public class ScheduleDetailDTO {
    private Long id;
    private Long scheduleId;
    private String title;
    private Integer status;
    private Integer level;
    private Integer isDelete;
    private Integer count;

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

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "ScheduleDetailDTO{" +
                "id=" + id +
                ", scheduleId=" + scheduleId +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", level=" + level +
                ", isDelete=" + isDelete +
                '}';
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
