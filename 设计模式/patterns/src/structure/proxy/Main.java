package structure.proxy;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/1 10:49
 */
public class Main {
    public static void main(String[] args) {
        CarDriver bin = new Haobin();
        CarDriver proxy = new ProxyCarDriver(bin);
        proxy.driveCar();
    }
}
