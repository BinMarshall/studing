package behavior.command;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/29 17:19
 */
public class Main {
    public static void main(String[] args) {
        CommandFactory factory = new CommandFactory(args[0]);
        Invoker invoker = new Invoker(factory.createCommand());
        invoker.invoke();
        ClassLoader cl = new ClassLoader() {
        };
    }
}
