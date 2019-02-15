package behavior.mediator;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/31 11:21
 */
public abstract class Person {
    protected String name;
    protected AbstractMediator mediator;
    public Person(String name, AbstractMediator mediator){
        this.name = name;
        this.mediator = mediator;
    }
    public abstract void ask(String message);
    public abstract void reply(String message);
}
class HouseOwner extends Person { //房主
    public HouseOwner(String name, AbstractMediator mediator) {
        super(name, mediator);
    }
    public void ask(String message) {
        System.out.println("房主：" + name + ";询问：" + message);
        this.mediator.contact(message, this);
    }
    public void reply(String message) {
        System.out.println("房主：" + name + ";收到：" + message);
    }
}
class Tenant extends Person { //租客
    public Tenant(String name, AbstractMediator mediator) {
        super(name, mediator);
    }
    public void ask(String message) {
        System.out.println("租客：" + name + ";询问：" + message);
        this.mediator.contact(message, this);
    }
    public void reply(String message) {
        System.out.println("租客：" + name + ";收到：" + message);
    }
}
