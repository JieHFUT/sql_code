import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: JIE
 * Date: 2022-08-16
 * Time: 0:32
 */
public class JDBCSelect {
    public static void main(String[] args) throws SQLException {

        // 查找数据库中的记录
        // 这个查找和前面的操作就不是很一样 ------ 多了一个遍历的过程


        //1. 创建数据源
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/db_test1?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("959452");

        //2. 和数据库建立连接
        Connection connection = dataSource.getConnection();

        //3. 用户输入要修改的id


        //4. sql 语句
        String sql = "select * from student";
        PreparedStatement statement = connection.prepareStatement(sql);

        //5. 执行sql
        //   此处要使用的是 execuQuery
        //   executeUpdate 只能返回一个int
        //   execuQuery 返回的是一个 ResultSet 对象，可以把这个对象视为一个”临时表“
        ResultSet resultSet = statement.executeQuery();


        //6. 遍历这个临时表，拿到里面的数据
        //   resultSet 简单的当成一个类似于”迭代器“这样的东西来看待
        //   next 如果没有到达末尾，就返回 false，否则就返回true
        while(resultSet.next()){
            // 在这里就可以取这一行的数据
            // 通过 ResultSet 里面的getxxx 方法，来获取到这里的指定的列

            // 取id  id是整数  就getInt
            int id = resultSet.getInt("id");
            // 取name  name是String  getString
            String name = resultSet.getString("name");
            System.out.println(id + "：" + name);
        }


        //关闭连接，释放资源
        resultSet.close();
        statement.close();
        connection.close();


    }
}
