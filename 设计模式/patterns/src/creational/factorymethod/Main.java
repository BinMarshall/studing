package creational.factorymethod;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/11 14:09
 */
public class Main {
    public static void main(String[] args) {
        AbstractCadillacFactory factory = new CadillacFactory();
        AbstractCadillac atsl = factory.makeCadillac(CadillacATSL.class);
        atsl.start();
        AbstractCadillac xt5 = factory.makeCadillac(CadillacXT5.class);
        xt5.start();
    }
}
