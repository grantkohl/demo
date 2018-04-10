package demo;

/**
 * 类加载器
 * Bootstrap ClassLoader 启动类
 * Extension ClassLoader 扩展类加载器
 * Application ClassLoader 应用程序类加载器
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader cl = ClassLoaderDemo.class.getClassLoader();
        while (cl != null) {
            System.out.println(cl.getClass().getName());
            cl = cl.getParent();
        }
        //如果ClassLoader是Bootstrap ClassLoader，返回值为null
        System.out.println(String.class.getClassLoader());

        ClassLoader cl2 = ClassLoader.getSystemClassLoader();
        System.out.println(cl2);//系统默认类加载器
        try {
            Class<?> cls = cl2.loadClass("java.util.ArrayList");
            ClassLoader actualLoader = cls.getClassLoader();
            System.out.println(actualLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
