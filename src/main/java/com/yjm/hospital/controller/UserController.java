package com.yjm.hospital.controller;

import com.yjm.hospital.base.dto.MessageCode;
import com.yjm.hospital.base.dto.PageResult;
import com.yjm.hospital.base.dto.Result;
import com.yjm.hospital.base.dto.StatusCode;
import com.yjm.hospital.entity.User;
import com.yjm.hospital.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zzx
 */
@Api(tags = "共通Controller", value = "共通Controller")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService iUserService;


    /**
     * 分页+多条件查询
     *
     * @param user 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "分页+多条件查询", notes = "分页+多条件查询")
    @RequestMapping(value = "/findList/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody User user, @PathVariable int page, @PathVariable int size) {
        Result result;
        try {
            Page<User> pageList = iUserService.findList(user, page, size);
            result = new Result(true, StatusCode.OK, MessageCode.QUERYOK, new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            result = new Result(false, StatusCode.ERROR, e.getMessage());
        }
        return result;

    }

    @ApiOperation(value = "分页+多条件查询", notes = "分页+多条件查询")
    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Result findSearch(@RequestBody User user) {
        Result result;
        try {
            List<User> all = iUserService.findList(user);
            result = new Result(true, StatusCode.OK, MessageCode.QUERYOK, all);
        } catch (Exception e) {
            result = new Result(false, StatusCode.ERROR, e.getMessage());
        }
        return result;

    }


    /**
     * 增加
     *
     * @param user
     */
    @ApiOperation(value = "增加、编辑", notes = "")
    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public Result addOrUpdate(@RequestBody User user) {
        Result result;
        try {
            result = iUserService.addOrUpdate(user);
        } catch (Exception e) {
            result = new Result(false, StatusCode.ERROR, e.getMessage());
        }
        return result;
    }

    /**
     * findById
     */
    @ApiOperation(value = "根据ID查询", notes = "")
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable Long id) {
        Result result;
        try {
            result = new Result(true, StatusCode.OK, "查询成功", iUserService.findById(id));
        } catch (Exception e) {
            result = new Result(false, StatusCode.ERROR, e.getMessage());
        }
        return result;
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除", notes = "")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Long id) {
        Result result;
        try {
            iUserService.deleteById(id);
            result = new Result(true, StatusCode.OK, "删除成功");
        } catch (Exception e) {
            result = new Result(false, StatusCode.ERROR, e.getMessage());
        }
        return result;
    }
}
