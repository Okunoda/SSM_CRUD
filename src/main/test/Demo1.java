import com.southSong.DAO.EmployeeDao;
import com.southSong.controller.EmployeeController;
import com.southSong.pojo.Employee;
import com.southSong.service.EmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo1 {



    @Test
    public void test(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.southSong.config");
        EmployeeDao bean = applicationContext.getBean(EmployeeDao.class);
        Employee employee = new Employee();
        employee.setId(100);
        System.out.println(bean.getEmpById(employee));
        EmployeeDao employeeDao = (EmployeeDao) applicationContext.getBean("employeeDao");
        System.out.println(employeeDao);

        EmployeeService employeeServiceImpl = (EmployeeService) applicationContext.getBean("employeeServiceImpl");
        System.out.println(employeeServiceImpl.getEmpById(100));
    }


}
