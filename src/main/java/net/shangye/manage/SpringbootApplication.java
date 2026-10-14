package net.shangye.manage;

import net.shangye.manage.test.commonutil.beanUtils.BeanUtils;
import net.shangye.manage.test.testproperties.PushPropertiesDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableScheduling
@SpringBootConfiguration
@SpringBootApplication
@Controller
@ServletComponentScan
public class SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class,args);
//        PushPropertiesDemo pp = BeanUtils.getBean(PushPropertiesDemo.class);
//        String active = pp.getApnsProperties().getActive();
//        Integer iosPersonalConnectTimeout = pp.getApnsProperties().getIosPersonalConnectTimeout();
//        String iosPersonalKeyId = pp.getApnsProperties().getIosPersonalKeyId();
//        String iosPersonalTeamId = pp.getApnsProperties().getIosPersonalTeamId();
//        Integer iosCompanyConnectTimeout = pp.getApnsProperties().getIosCompanyConnectTimeout();
//        String iosCompanyKeyId = pp.getApnsProperties().getIosCompanyKeyId();
//        String iosCompanyTeamId = pp.getApnsProperties().getIosCompanyTeamId();
//        System.out.println("iosPersonalConnectTimeout:"+iosPersonalConnectTimeout);
//        System.out.println("iosPersonalKeyId:"+iosPersonalKeyId);
//        System.out.println("iosPersonalTeamId:"+iosPersonalTeamId);
//        System.out.println("iosCompanyConnectTimeout:"+iosCompanyConnectTimeout);
//        System.out.println("iosCompanyKeyId:"+iosCompanyKeyId);
//        System.out.println("iosCompanyTeamId:"+iosCompanyTeamId);
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void welcome(HttpServletResponse response) throws IOException {
        response.sendRedirect("/index.html");
    }

}
