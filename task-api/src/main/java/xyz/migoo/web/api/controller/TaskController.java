package xyz.migoo.web.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.migoo.web.api.annotation.CurrentUser;
import xyz.migoo.web.api.annotation.LoginRequired;
import xyz.migoo.web.api.service.TaskService;
import xyz.migoo.web.dto.ScheduleDTO;
import xyz.migoo.web.dto.ScheduleDetailDTO;
import xyz.migoo.web.pojo.ResponseBean;
import xyz.migoo.web.vo.UserVO;

/**
 * @author xiaomi
 */
@RestController
@RequestMapping(value = "/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    /**
     * 获取日程列表
     */
    @LoginRequired
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public ResponseBean getSchedule(@CurrentUser UserVO userVO, ScheduleDTO scheduleDTO){
        scheduleDTO.setUserId(userVO.getId());
        return taskService.getSchedule(scheduleDTO);
    }

    /**
     * 获取日程明细
     * @param scheduleDetailDTO 明细对象
     */
    @LoginRequired
    @RequestMapping(value = "/schedule/detail", method = RequestMethod.GET)
    public ResponseBean getScheduleDetail(ScheduleDetailDTO scheduleDetailDTO){
        return taskService.getScheduleDetail(scheduleDetailDTO);
    }

    /**
     * 添加日程
     */
    @LoginRequired
    @RequestMapping(value = "/schedule/add", method = RequestMethod.POST)
    public void addSchedule(@CurrentUser UserVO userVO, @RequestBody ScheduleDTO scheduleDTO){
        scheduleDTO.setUserId(userVO.getId());
        taskService.addSchedule(scheduleDTO);
    }

    /**
     * 编辑日程
     */
    @LoginRequired
    @RequestMapping(value = "/schedule/edit", method = RequestMethod.PUT)
    public void editSchedule(@RequestBody ScheduleDTO scheduleDTO){
        taskService.editSchedule(scheduleDTO);
    }

    /**
     * 添加日程明细
     */
    @LoginRequired
    @RequestMapping(value = "/schedule/detail/add", method = RequestMethod.POST)
    public void addScheduleDetail(@RequestBody ScheduleDetailDTO detailDTO){
        taskService.addScheduleDetail(detailDTO);
    }


    /**
     * 编辑日程明细
     */
    @LoginRequired
    @RequestMapping(value = "/schedule/detail/edit", method = RequestMethod.PUT)
    public void editScheduleDetail(@RequestBody ScheduleDetailDTO detailDTO){
        taskService.editScheduleDetail(detailDTO);
    }
}
