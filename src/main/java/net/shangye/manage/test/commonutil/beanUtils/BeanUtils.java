package net.shangye.manage.test.commonutil.beanUtils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * * <p>Title: 获取spring上下文中的bean对象工具类 </p >
 * * <p>Description: </p >
 * * <p>Copyright: Copyright (c) 2019</p >
 * * <p>Company: Luculent</p >
 * *
 * * @author wangsongru
 * * @version 7.0
 * * @createTime 2021-11-01 17:22:00
 */
@Component
public class BeanUtils implements ApplicationContextAware {

    /**
     * Spring上下文
     */
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }


    /**
     * 根据class对象获取bean对象，提前判断null
     *
     * @param cls class对象
     * @return T 返回bean
     * @author wangsongru
     * @createTime 2021/7/2 10:33
     */
    public static <T> T getBean(Class<T> cls) {
        return Objects.requireNonNull(getBeanFrom(cls), "从spring上下文中获取Bean失败" + cls.getName());
    }

    /**
     * 根据名称获取bean对象，提前判断null
     *
     * @param name 名称
     * @return Object 返回bean
     * @author wangsongru
     * @createTime 2021/7/2 10:33
     */
    public static Object getBean(String name) {
        return Objects.requireNonNull(getBeanFrom(name), "从spring上下文中获取Bean失败" + name);
    }


    /**
     * 根据Class对象获取一个bean对象
     *
     * @param cls class对象
     * @return T 返回bean
     * @author wangsongru
     * @createTime 2020-10-07 11:06:18
     */
    private static <T> T getBeanFrom(Class<T> cls) {
        if (context == null) {
            return null;
        }
        return context.getBean(cls);
    }

    /**
     * 根据名称获取一个bean对象
     *
     * @param name bean名称
     * @return Object 返回bean
     * @author wangsongru
     * @createTime 2020-10-07 11:07:02
     */
    private static Object getBeanFrom(String name) {
        if (context == null) {
            return null;
        }
        if (context.containsBean(name)) {
            return context.getBean(name);
        }
        return null;
    }

}