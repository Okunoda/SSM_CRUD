import com.southSong.DAO.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Demo1 {



    @Test
    public void test(){
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.southSong.config");
//        EmployeeDao bean = applicationContext.getBean(EmployeeDao.class);
//        Employee employee = new Employee();
//        employee.setId(100);
//        System.out.println(bean.getEmpById(employee));
//        EmployeeDao employeeDao = (EmployeeDao) applicationContext.getBean("employeeDao");
//        System.out.println(employeeDao);
//
//        EmployeeService employeeServiceImpl = (EmployeeService) applicationContext.getBean("employeeServiceImpl");
//        System.out.println(employeeServiceImpl.getEmpById(100));
    }

    @Test
    public void test1() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("D:\\project\\SSM-CRUD\\src\\main\\resources\\mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Test
    public void test2(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.southSong.Config");
        EmployeeMapper bean = (EmployeeMapper) ctx.getBean(EmployeeMapper.class);
        System.out.println(bean.selectByPrimaryKeyWithDept(100));
        SqlSessionTemplate ctxBean = (SqlSessionTemplate) ctx.getBean(SqlSessionTemplate.class);
        EmployeeMapper mapper = (EmployeeMapper) ctxBean.getMapper(EmployeeMapper.class);
        System.out.println(mapper.selectByPrimaryKeyWithDept(100));

    }
}
