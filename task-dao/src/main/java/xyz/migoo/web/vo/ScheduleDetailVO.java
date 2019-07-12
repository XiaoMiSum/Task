package xyz.migoo.web.vo;

/**
 * @author xiaomi
 */
public class ScheduleDetailVO{

    private Long id;
    private String title;
    private Integer status;
    private Integer level;
    private Integer isDelete;

    public ScheduleDetailVO(Long id, String title, Integer status, Integer level, Integer isDelete) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.level = level;
        this.isDelete = isDelete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "ScheduleDetailVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", level=" + level +
                ", isDelete=" + isDelete +
                '}';
    }
}
