import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


/**
 * @ContextConfiguration: 指定Spring的配置文件所在的位置
 * @RunWith: 指定由谁来运行测试
 */
//使用这个，开启可以传送 IOC 容器本身
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MVCDemo1 {

    @Autowired
    private WebApplicationContext webApplicationContext;


    //MockMVC ：可以虚拟一个mvc请求方便测试
    //此处只是定义，要想使用需要创建初始化方法进行配置
    MockMvc mockMvc;

    //注意：这里是 junit 的注解，不是 aspectj 的
    @Before
    public void initMock() {
        //使用 MockMvcBuilders.webAppContextSetup()，传入SpringMVC的ioc
        //需要使用到@WebApplicationContext注解，然后才可以自动注入ioc
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    //使用方法
    @Test
    public void test1() throws Exception {
        /**
         * perform: 指定请求的方式和参数
         * MockMvcRequestBuilders.xxx :可以具体指明发哪种请求
         * param：指明请求中的参数
         * addReturn：获取返回值
         */
        System.out.println("hello");
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/emps");
        MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder.param("pn", "1")).andReturn();
        PageInfo pageInfo = (PageInfo) result.getRequest().getAttribute("pageInfo");
        System.out.println(pageInfo);

        System.out.println("当前页数：" + pageInfo.getPageNum());
        System.out.println("总页码：" + pageInfo.getPages());
        System.out.println("总记录数:" + pageInfo.getTotal());
        System.out.println("需要在页面显示的连续页码数:" + pageInfo.getNavigatepageNums());
    }


    public WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }
}
