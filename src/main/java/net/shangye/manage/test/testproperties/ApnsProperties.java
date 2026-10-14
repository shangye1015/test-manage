package net.shangye.manage.test.testproperties;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = {"classpath:/config/apns.properties"})
public class ApnsProperties {

    /**
     * 苹果激活证书
     */
    @Value("${active}")
    private String active;

    /**
     * 苹果配置
     */
    @Value("${mode}")
    private String mode;

    /**
     * ios keyId详细在ios开发者页面的keys中寻找
     */
    @Value("${ios.personal.key.id}")
    private String iosPersonalKeyId;

    /**
     * ios teamId详细在ios开发者页面寻找
     */
    @Value("${ios.personal.team.id}")
    private String iosPersonalTeamId;

    /**
     * ios连接apple notification service的超时时间
     */
    @Value("${ios.personal.connect.timeout}")
    private Integer iosPersonalConnectTimeout;

    /**
     * ios keyId详细在ios开发者页面的keys中寻找
     */
    @Value("${ios.company.key.id}")
    private String iosCompanyKeyId;

    /**
     * ios teamId详细在ios开发者页面寻找
     */
    @Value("${ios.Company.team.id}")
    private String iosCompanyTeamId;

    /**
     * ios连接apple notification service的超时时间
     */
    @Value("${ios.Company.connect.timeout}")
    private Integer iosCompanyConnectTimeout;
}