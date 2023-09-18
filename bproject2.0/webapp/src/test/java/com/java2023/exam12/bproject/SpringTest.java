package com.java2023.exam12.bproject;

import com.java2023.exam12.bproject.dao.SysUserDao;
import com.java2023.exam12.bproject.dto.PermissionDTO;
import com.java2023.exam12.bproject.dto.UserDTO;
import com.java2023.exam12.bproject.service.PermService;
import com.java2023.exam12.bproject.service.UserService;
import com.java2023.exam12.bproject.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@SpringBootTest
public class SpringTest {
    @Resource
    UserService userService;
    @Resource
    SysUserDao userDao;
    @Test
    public void contextLoads() {
        UserDTO userDTO = userService.findByName("test5");
        System.out.println(userDTO.toString());
    }
    @Resource
    PermService permService;
    @Test
    public void permService(){
        List<PermissionDTO> list = permService.list();
        System.out.println(list.toString());
    }
}
