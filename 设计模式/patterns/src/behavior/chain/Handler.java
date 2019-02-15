package behavior.chain;

/**
 * 责任链模式的优点：将请求和处理解耦
 * 责任链模式的缺点：
 * 1、性能问题，每个请求都从头遍历到尾，特别当链条比较长时
 * 2、调试不方便
 * 责任链模式最佳实践：通过融合模板方法模式，各个实现类只需实现请求处理，
 *    而请求传递等其他事情就由父类实现，这样符合单一职责原则
 */
public abstract class Handler {
    Handler handler = null;
    public abstract boolean canHandle(Trouble trouble);
    public abstract void done(Trouble trouble);
    public void handle(Trouble trouble){
        if(canHandle(trouble)){ done(trouble);
        }else if(this.handler != null){ this.handler.handle(trouble);
        }else{ System.out.println("no one can do."); }
    }
    public void setHandler(Handler handler) { this.handler = handler; }
}
class HandlerA extends Handler{
    public boolean canHandle(Trouble trouble) { return trouble.getNum() == 1 ? true : false; }
    public void done(Trouble trouble) { System.out.println("I am HandlerA. I finish it."); }
}
class HandlerB extends Handler{
    public boolean canHandle(Trouble trouble) { return trouble.getNum() == 2 ? true : false; }
    public void done(Trouble trouble) { System.out.println("I am HandlerB. I finish it."); }
}
class HandlerC extends Handler{
    public boolean canHandle(Trouble trouble) { return trouble.getNum() == 3 ? true : false; }
    public void done(Trouble trouble) { System.out.println("I am HandlerC. I finish it."); }
}

