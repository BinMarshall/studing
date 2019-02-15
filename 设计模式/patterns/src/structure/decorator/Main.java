package structure.decorator;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/1 9:51
 */
public class Main {
    public static void main(String[] args) {
        AbstractComponent azhu = new AZhu();
        azhu = new DuanZhengChun(new XiaoFeng(azhu));
        azhu.disguise();
        azhu = new XiaoFeng(new DuanZhengChun(azhu));
        azhu.disguise();
    }
}
