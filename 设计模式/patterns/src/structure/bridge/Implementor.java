package structure.bridge;

public interface Implementor {
    public void doSomeThing();
    public void doAnyThing();
}
class ConcreteImplementor1 implements Implementor {
    public void doSomeThing() {
        System.out.println("One Do SomeThing");
    }
    public void doAnyThing() {
        System.out.println("One Do AnyThing");
    }
}
class ConcreteImplementor2 implements Implementor {
    public void doSomeThing() {
        System.out.println("Two Do SomeThing");
    }
    public void doAnyThing() {
        System.out.println("Two Do AnyThing");
    }
}
