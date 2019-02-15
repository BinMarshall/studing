package structure.facade;

/**
 * 外观模式优点：
 * 1、减少系统之间相互依赖，降低与各子系统的耦合
 * 2、提高安全性，只能通过门面访问子系统，隔一道防火墙
 * 外观模式缺点：不符合开闭原则，门面一旦发现错误，只能修改
 * 外观模式应用场景：
 * 1、为复杂模块或子系统提供对外访问接口
 * 2、子系统相对独立，外界对子系统只需黑箱操作
 * 3、预防低水平人员带来风险扩散
 * 外观模式最佳实战：
 * 1、门面不参与子系统内的业务逻辑
 */
public class Facade {
    private ClassA classA = new ClassA();
    private ClassB classB = new ClassB();
    private ClassC classC = new ClassC();
    private ClassD classD = new ClassD();
    public void sendOut(){
        classA.pickingGoods();
        classB.send();
    }
    public void deliver(){
        classC.transport();
        classD.deliver();
    }
}
