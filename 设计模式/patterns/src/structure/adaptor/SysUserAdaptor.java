package structure.adaptor;

import structure.decorator.AbstractDecorator;

/**
 * 适配器模式优点：
 * 1、该模式可以让两个没有任何关系的类在一起运行
 * 2、增加了类的透明性，具体实现都交给了源角色，
 *    这些对高层模块是透明的，他们不需要关心
 * 3、灵活性好，某天不想要适配器，删掉即可
 * 适配器模式缺点：
 * 适配器模式应用场景：当有动机修改一个投产中的接口时，
 *    就可使用该模式。另外，之所以能够使用该模式， 是
 *    因为我们在系统设计时严格遵守了依赖倒置原则和里氏
 *    替换原则， 否则即使增加了适配器类也无法解决问题。
 * 适配器模式实战：
 * 1、该模式是为了解决正在服役的项目问题，所以项目最初
 *    设计阶段不会用到该模式
 * 2、项目最初设计时一定要遵守依赖倒置原则和里氏替换原则，
 *    否则即使在适合使用适配器的场合下，也会改动非常大
 * 3、该模式是一个补偿模式，或补救模式，通常解决接口不相容
 *    的问题，在百分百完美的设计中是不可能使用到的
 */
public interface SysUserAdaptor extends
        AbstractSysUserA, AbstractSysUserB {
}
class SysUser implements SysUserAdaptor {
    public String getUserName() {
        return "用户姓名";
    }
    public String getUserAge() {
        return "用户年龄";
    }
    public String getUserAddr() {
        return "用户住址";
    }
    public String getUserIdcard() {
        return "用户身份证号";
    }
}
