package behavior.chain;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/30 9:57
 */
public class Main {
    public static void main(String[] args) {
        Handler handlerA = new HandlerA();
        Handler handlerB = new HandlerB();
        Handler handlerC = new HandlerC();
        handlerB.setHandler(handlerC);
        handlerA.setHandler(handlerB);
        handlerA.handle(new Trouble(4));
    }
}

