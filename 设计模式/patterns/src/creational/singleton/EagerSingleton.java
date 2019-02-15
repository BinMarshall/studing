package creational.singleton;

/**
 * 单例模式优点：
 * 1、在内存中只有一个实例，减少内存开销
 * 2、单例模式可以在系统设置全局访问点，优化和共享访问资源
 * 单例模式缺点：
 * 1、单例模式一般没有接口，很难扩展，若要扩展，只能修改代码
 * 2、单例模式与单一职责原则有冲突，一个类应该只实现一个逻辑，
 *    而不关心它是否是单例的
 * 单例模式应用场景：
 * 1、当一个对象需要频繁创建、销毁时，可用该模式
 * 2、当一个对象的产生需要较多资源时，如读取配置，则可通过
 *    在应用启动时直接产生一个单例对象，然后永驻内存
 * 3、要求生成唯一序列号的环境
 * 4、在整个项目中需要一个共享访问点或共享数据
 * 5、需要定义大量的静态常量和静态方法的环境
 * 单例模式最佳实战：
 * 1、Spring容器中的Bean默认都是单例的
 */
/** 饿汉式单例：能吃多少给多少 */
public class EagerSingleton {
    /** 天生就是线程安全的 */
    private static EagerSingleton instance = new EagerSingleton();
    public static EagerSingleton getInstance(){
        return instance;
    }
}

