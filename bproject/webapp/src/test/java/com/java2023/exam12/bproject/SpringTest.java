package com.java2023.exam12.bproject;

import com.alibaba.excel.EasyExcel;
import com.java2023.exam12.bproject.entity.Salary;
import com.java2023.exam12.bproject.service.UserService;
import com.java2023.exam12.bproject.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@SpringBootTest
public class SpringTest {
    @Resource
    UserService userService;
    @Test
    public void contextLoads() {
        UserVo userVo = new UserVo();
        userVo.setUsername("小小");
        userVo.setPassword("123");
        userService.addUser(userVo);
    }
    @Test
    public void testFill(){
        //1，获取模板文件
        InputStream templateInputstream = Thread.currentThread()
                .getContextClassLoader().getResourceAsStream("templatesalary.xlsx");
        //2，输出文件
        String fileName = "e:/"+ "simpleFill" + System.currentTimeMillis() + ".xlsx";

        //3,根据数据填充模板
        EasyExcel.write(fileName).withTemplate(templateInputstream)
                .sheet().doFill(new Salary("张三",10000,50));


    }
}
