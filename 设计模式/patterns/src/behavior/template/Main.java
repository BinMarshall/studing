package behavior.template;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/30 14:12
 */
public class Main {
    public static void main(String[] args) {
        AbstractCadillac atsl = new CadillacATSL();
        atsl.run();
        AbstractCadillac xt5 = new CadillacXT5();
        xt5.run();
    }
}
