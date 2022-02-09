package com.yjm.hospital.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import com.yjm.hospital.base.BaseRepository;
import com.yjm.hospital.base.dto.MessageCode;
import com.yjm.hospital.base.dto.Result;
import com.yjm.hospital.base.dto.StatusCode;
import com.yjm.hospital.base.util.CopyUtil;
import com.yjm.hospital.dao.UserDao;
import com.yjm.hospital.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 用户 服务层
 *
 * @author zzx
 * @date 2021-11-30
 */
@Service
public class UserService {


    @Resource
    private UserDao userDao;
    @Resource
    private BaseRepository baseRepository;
    @Resource
    private HttpServletRequest request;


    /**
     * 增加、编辑
     *
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public Result addOrUpdate(User user) {
        Result result = new Result(true, StatusCode.OK, MessageCode.SAVEOK, null);
        Date nowDate = DateUtil.date();
        Long userid = Convert.toLong(request.getAttribute("userid"));
        // 新增判断
        if (user.getUserid() == null) {
        } else {
            //2、查询出旧实体
            User qUser = findById(user.getUserid());
            //3、将除新实体中不为空的属性的数据从旧实体中赋值
            CopyUtil.copyNullProperties(qUser, user);
        }
        user.setCreateby(userid);
        user.setCreatetime(nowDate);
        userDao.save(user);
        return result;
    }


    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public User findById(Long id) {
        User user = userDao.findByUserid(id);
        return user;
    }

    /**
     * 分页查询
     *
     * @param user
     * @param page
     * @param size
     * @return
     */
    public Page<User> findList(User user, int page, int size) {
        Map<String, Object> whereParm = new HashMap<>();
        String sql = "";
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<User> all = baseRepository.findAll(sql, whereParm, pageRequest, new User());
        return all;
    }

    @PersistenceContext
    private EntityManager entityManager;
    public List<User> findList(User user) {
        Map<String, Object> whereParm = new HashMap<>();
        String sql = "select u.* from user u ";
//        List<User> all = entityManager.createNativeQuery(sql, User.class).getResultList();
        List<User> all = baseRepository.findAll(sql, whereParm, new User());
        return all;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        userDao.deleteByUserid(id, Convert.toLong(request.getAttribute("userid")), DateUtil.date());
    }

    public static void sort(int[] arr) {
        int temp;//定义一个临时变量
        for(int i=0;i<arr.length-1;i++){//冒泡趟数
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j+1]<arr[j]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {
    }
}
