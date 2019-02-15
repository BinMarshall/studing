package behavior.memento;

/**
 * 备忘录模式优点：
 * 备忘录模式缺点：
 * 备忘录模式应用场景：
 * 1、需要保存和恢复数据的相关场景
 * 2、提供一个可回滚的操作
 * 3、需要监控的副本场景
 * 4、实现数据库连接的事务管理
 * 备忘录模式最佳实践：
 * 1、对于多备份的备忘录，可以在CareTaker中使用集合存放Memento
 * 2、有了备忘录模式，就尽量别使用数据库的临时表做缓存备份了
 */
/** 备忘录本身 */
public class Memento {
    private String state;
    public Memento(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
}
