package net.shangye.manage.test.testproperties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component("PushPropertiesDemo")
@PropertySource(value = {"classpath:/config/huawei.properties"})
public class PushPropertiesDemo {

    @Autowired
    private ApnsProperties apnsProperties;

    /**
     * 华为配置TokenServer
     */
    @Value("${hw.tokenServer}")
    private String hwTokenServer;

    /**
     * 华为配置PushOpenUrl
     */
    @Value("${hw.pushOpenUrl}")
    private String hwPushOpenUrl;

}