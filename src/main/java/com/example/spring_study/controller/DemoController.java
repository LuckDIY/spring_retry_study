package com.example.spring_study.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController implements BeanDefinitionRegistryPostProcessor {


    @GetMapping("ok")
    public String ok(){
        return "ok";
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        System.out.println("打印=----------------");
    }

    @Data
    @AllArgsConstructor
//    @NoArgsConstructor
    public static class User{
        private String name;
    }

    public static void aVoid() {
        //创建工厂
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        GenericBeanDefinition userDefinition = new GenericBeanDefinition();
        userDefinition.setBeanClass(User.class);

        //set方法填充参数
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name","哈哈哈");
        userDefinition.setPropertyValues(propertyValues);

        //构造函数填充参数
        ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
        constructorArgumentValues.addIndexedArgumentValue(0,"有参构造哈哈哈");
        userDefinition.setConstructorArgumentValues(constructorArgumentValues);

        factory.registerBeanDefinition("user", userDefinition);
        Object user = factory.getBean("user");
        System.out.println(user);
    }

    public static void main(String[] args) {

    }


}
