package structure.proxy;

/**
 * 代理模式优点：职责清晰、扩展性高、智能化
 * 代理模式缺点：
 * 代理模式应用场景：自身不想参与其中，但事情还要完成
 * 代理模式最佳实战：动态代理
 * 1、动态代理是指实现阶段不用关心代理谁，而在运行阶段
 *    才指定代理哪个对象。典型代表：AOP
 * 2、
 */
/** 歌舞升平，欢畅酒醉，自驾不能，代驾而归 */
public interface CarDriver {
    void driveCar();
}
class Haobin implements CarDriver {
    public void driveCar() {
        System.out.println("郝彬会开车，只是喝酒了");
    }
}
class ProxyCarDriver implements CarDriver {
    CarDriver driveCar = null;
    public ProxyCarDriver(CarDriver driveCar){
        this.driveCar = driveCar;
    }
    public void driveCar() {
        System.out.println("我是代驾，我替郝彬开车");
        this.driveCar.driveCar();
    }
}

