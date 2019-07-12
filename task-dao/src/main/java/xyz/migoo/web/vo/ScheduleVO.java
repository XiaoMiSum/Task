package xyz.migoo.web.vo;

import java.util.List;

/**
 * @author xiaomi
 */
public class ScheduleVO {

    private Long id;
    private String title;
    private Integer count;
    private Integer locked;
    private Integer level;
    private Integer isDelete;
    private List<ScheduleDetailVO> detail;

    public ScheduleVO(){
        
    }

    public ScheduleVO(Long id, String title, Integer count, Integer locked, Integer level, Integer isDelete) {
        this.id = id;
        this.title = title;
        this.locked = locked;
        this.count = count;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "ScheduleVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", locked=" + locked +
                '}';
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

    public List<ScheduleDetailVO> getDetail() {
        return detail;
    }

    public void setDetail(List<ScheduleDetailVO> detail) {
        this.detail = detail;
    }
}
