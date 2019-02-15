package behavior.template;

/**
 * 模板方法优点：
 * 1、封装不变部分，扩展可变部分
 * 2、提取公共代码，便于维护
 * 3、行为由父类控制，由子类实现
 * 模板方法缺点：暂无
 * 模板方法应用场景：
 * 1、多个子类有公共的处理逻辑
 * 2、重要、复杂的方法可设计为模板方法，周边的细节由子类实现
 * 3、重构时，把相同代码提取到父类中
 * 模板方法最佳实践：
 * 1、为防止恶意操作，一般模板方法都加上final，避免被重写
 * 2、抽象模板中的基本方法尽量设计为protected类型，符合迪米特法则，
 *    不需要暴露的属性或方法尽量不要设计为public类型
 * 3、把子类传递到父类的有参构造中，然后进行调用
 * 4、使用反射的方式调用
 * 5、父类调用子类的静态方法
 */
public abstract class AbstractCadillac {
    protected abstract void start();
    protected abstract void stop();
    protected abstract void alarm();
    public final void run(){
        this.start();
        this.alarm();
        this.stop();
    }
}
class CadillacATSL extends AbstractCadillac {
    protected void start() {
        System.out.println("ATSL can start");
    }
    protected void stop() {
        System.out.println("ATSL can stop");
    }
    protected void alarm() {
        System.out.println("ATSL can alerm");
    }
}
class CadillacXT5 extends AbstractCadillac {
    protected void start() {
        System.out.println("XT5 can start");
    }
    protected void stop() {
        System.out.println("XT5 can stop");
    }
    protected void alarm() {
        System.out.println("XT5 can alerm");
    }
}
