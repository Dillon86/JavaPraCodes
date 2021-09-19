package com.home;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 程序入口
 */
public class App 
{
    public static void main( String[] args ) throws InstantiationException, IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException {
        Resource r=new ClassPathResource("Hello.xlass");
        Class<?> c = new CustomClassLoader(r.getFile()).findClass("Hello").newInstance().getClass();
        Method hello = c.getDeclaredMethod("hello");
        hello.invoke(c.newInstance());
    }
}
