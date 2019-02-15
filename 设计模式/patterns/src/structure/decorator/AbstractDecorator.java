package structure.decorator;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/1 9:46
 */
public abstract class AbstractDecorator implements AbstractComponent {
    protected AbstractComponent component;
    public AbstractDecorator(AbstractComponent component){
        this.component = component;
    }
}
class XiaoFeng extends AbstractDecorator {
    public XiaoFeng(AbstractComponent component) {
        super(component);
    }
    public void disguise() {
        this.component.disguise();
        System.out.println("我现在易容成了萧峰");
    }
}
class DuanZhengChun extends AbstractDecorator {
    public DuanZhengChun(AbstractComponent component) {
        super(component);
    }
    public void disguise() {
        this.component.disguise();
        System.out.println("我现在易容成了段正淳");
    }
}
