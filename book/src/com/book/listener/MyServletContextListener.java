package com.book.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyServletContextListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

/*

Listener监听器：
Javaweb 三大组件：servlet，listener，filter，他们都是javaee的规范，是接口

listener的作用是，监听某种事务的变化，然后通过回调函数，反馈给程序

ServletContextListener监听器：
可以监听ServletContext对象的创建和销毁：
Servlet对象在web工程启动时创建，在web工程停止时销毁
两个方法：
	1，contextInitialized(ServletContextEvent sce)
		在ServletContext对象创建之后调用，做初始化
	2, contextDestoryed(ServletContextEvent sce)
		在ServletContext对象销毁后调用
如何监听：
	1，编写一个类去实现该接口
	2，实现两个回调方法
	3，在web.xml中配置

*/

    // Public constructor is required by servlet spec
    public MyServletContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        System.out.println("servletcontext对象创建");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        System.out.println("servletcontext对象销毁");
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }
}
