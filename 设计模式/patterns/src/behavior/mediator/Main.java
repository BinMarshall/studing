package behavior.mediator;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/31 13:54
 */
public class Main {
    public static void main(String[] args) {
        AbstractMediator mediator = new ConcreteMediator();
        HouseOwner owner = new HouseOwner("乾隆", mediator);
        owner.ask("有人要租三室一厅吗？");
        System.out.println();
        Tenant tenant = new Tenant("嘉庆", mediator);
        tenant.ask("谁有一室一厅的房子？");
    }
}
