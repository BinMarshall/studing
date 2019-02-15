package structure.adaptor;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/1 11:42
 */
public class Main {
    public static void main(String[] args) {
        SysUser user = new SysUser();
        System.out.println(user.getUserName()+";"+user.getUserAge()+";"+user.getUserIdcard()+";"+user.getUserAddr());
    }
}
