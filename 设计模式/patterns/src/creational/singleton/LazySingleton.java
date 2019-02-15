package creational.singleton;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/11 11:24
 */
/** 懒汉式单例：不愿付出，如果有了就不给 */
public class LazySingleton {
    private static LazySingleton  instance = null;
    /** 如需线程安全需要采用synchronized */
    public static synchronized LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}

