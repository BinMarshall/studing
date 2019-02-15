package creational.factorymethod;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/11 14:01
 */
public interface AbstractCadillac {
    public void start();
}
class CadillacATSL implements AbstractCadillac {
    public void start() {
        System.out.println("I'm a Cadillac ATSL.");
    }
}
class CadillacXT5 implements AbstractCadillac {
    public void start() {
        System.out.println("I'm a Cadillac XT5.");
    }
}
