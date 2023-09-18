import com.alibaba.excel.EasyExcel;
import com.java2023.exam12.bproject.entity.Salary;
import com.java2023.exam12.bproject.entity.Student;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ExcelTest {
    //构造数据
   @Test
    public void testExcel(){
       List<Student> list = new ArrayList<Student>();
       for(int i=0;i<10;i++){
           Student student = new Student();
           student.setId(i+1);
           student.setName("学生"+i);
           student.setScore(i+60);
           list.add(student);
       }
       File file = new File("e:/"+System.currentTimeMillis()+".xlsx");
       //将list输出成excel文件
       //第一个参数是指定输出的文件,第二个参数是输出的关联类
       EasyExcel.write(file,Student.class)
               .sheet("模板1")
               .doWrite(list);
   }

}
