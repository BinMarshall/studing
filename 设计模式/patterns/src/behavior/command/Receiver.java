package behavior.command;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/29 17:07
 */
public interface Receiver {
    public void execute();
}
class Husband implements Receiver {
    public void execute() {
        System.out.println("丈夫正在洗衣服");
    }
}
class Son implements Receiver {
    public void execute() {
        System.out.println("儿子正在拖地板");
    }
}
class Daughter implements Receiver {
    public void execute() {
        System.out.println("女儿正在擦桌子");
    }
}
