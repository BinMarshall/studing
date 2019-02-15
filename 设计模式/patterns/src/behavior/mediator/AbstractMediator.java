package behavior.mediator;

import java.util.Random;

/**
 * 中介者模式优点：减少了依赖，把一对多依赖变成了一对一
 * 中介者模式缺点：随同事类的增多，中介者会变得臃肿、复杂
 * 中介者模式使用场景：该模式并不容易使用，并非有多个依赖关系时就可以使用该模式，
 *    使用不当会导致其缺点放大，因此需量力而行。该模式适用于多个对象之间紧密耦合
 *    的情况，紧密耦合的标准：在类图中出现了蜘蛛网结构，在这种结构下使用该模式，
 *    可把蜘蛛网梳理成星型结构，是关系变得清晰。
 * 中介者模式最佳实践：
 * 1、N个对象之间产生了互相依赖的关系（N>2)
 * 2、多个对象有依赖关系，但是依赖的行为尚不确定或者有发生改变的可能
 * 3、产品研发。如开发一个MVC框架，对于项目开发不合适，因为项目以交付投产为目标
 */
/** 房主要出租，租客要租房 */
public interface AbstractMediator {
    public void contact(String message, Person person);
}
class ConcreteMediator implements AbstractMediator {
    private HouseOwner owner1 = new HouseOwner("乾隆", this);
    private HouseOwner owner2 = new HouseOwner("康熙", this);
    private Tenant tenant1 = new Tenant("嘉庆", this);
    private Tenant tenant2 = new Tenant("雍正", this);
    public void contact(String message, Person person) {
        Random random = new Random();
        if(person instanceof HouseOwner){
            if (random.nextInt(2) == 0) {
                tenant1.reply(message);
            } else {
                tenant2.reply(message);
            }
        }else if(person instanceof Tenant){
            if (random.nextInt(2) == 0) {
                owner1.reply(message);
            } else {
                owner2.reply(message);
            }
        }else{
            System.out.println("该用户未注册");
        }
    }
}
