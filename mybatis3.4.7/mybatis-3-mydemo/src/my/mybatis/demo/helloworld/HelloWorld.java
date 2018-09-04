package my.mybatis.demo.helloworld;

import java.io.Reader;

/**
 * 功能描述：
 * 作者姓名： Hao Bin - marshall
 * 创建时间：2018/8/22 23:29
 */
public class HelloWorld {
    public static void main(String[] args) {
        String resource = "org/mybatis/internal/example/Configuration.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new  SqlSessionFactoryBuilder().build(reader);

            SqlSession session = sqlMapper.openSession();
            try {
                User user = (User) session.selectOne("org.mybatis.internal.example.mapper.UserMapper.getUser", 1);
                System.out.println(user.getLfPartyId() + "," + user.getPartyName());
            } finally {
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
