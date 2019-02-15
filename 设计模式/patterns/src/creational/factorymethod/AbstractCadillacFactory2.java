package creational.factorymethod;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/11 14:27
 */
public interface AbstractCadillacFactory2 {
    public AbstractCadillac makeCadillac();
}
class CadillacATSLFactory implements AbstractCadillacFactory2 {
    public AbstractCadillac makeCadillac() {
       return new CadillacATSL();
    }
}
class CadillacXT5Factory implements AbstractCadillacFactory2 {
    public AbstractCadillac makeCadillac() {
        return new CadillacXT5();
    }
}
