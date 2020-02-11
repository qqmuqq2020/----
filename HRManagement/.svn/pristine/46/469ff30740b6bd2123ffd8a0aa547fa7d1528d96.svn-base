package util.quartz;

import hr.manage.service.impl.System_examine.Stm_examineImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

//定时执行的任务
public class MyJob {

    public void run(){
        ApplicationContext context = new ClassPathXmlApplicationContext("System-mybatis.xml");
        Stm_examineImpl stm_service = (Stm_examineImpl)context.getBean("Stm_Service");
        stm_service.Examine();
    }
}
