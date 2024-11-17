import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: JIE
 * Date: 2022-08-15
 * Time: 18:47
 */
public class JDBCInsert {

    public static void main (String[] args) throws SQLException {
        //1. 要操作数据库，就要连接上数据库，也就要描述清楚服务器所在的位置
        //   使用DataSource 类来描述 MySQL 服务器的位置
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/db_test1?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("959452");

        //2. 接下来和数据库服务器建立连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);//com.mysql.jdbc.JDBC4Connection@3eb07fd3


        //3. 接下来就可以写sql语句了
        //   例如一个插入操作,例如向student表里面插入一个数据
        //   直接用String 格式的SQL 还不行，还需要搭配一个特殊的类
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学号：");
        int num = scanner.nextInt();
        System.out.println("请输入姓名：");
        String name = scanner.next();
        //这是一种不科学的写法
        //String sql = "insert into student values(" + num + ", '"+ name +"')";

        //这是一种科学的写法
        //使用 ？ 作为占位符，后续使用 statement 对象针对 ？ 进行替换
        String sql = "insert into student values(?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, num);
        statement.setString(2, name);


        //4. 执行SQL 语句 insert, update, delete 都是通过 executeUpdate 来执行的
        //   select 则是通过 executeQuery 来执行的；
        //   executeQuery 的返回值是一个 整数 表示这个表达式影响了几行!
        //   statement.executeUpdate();使用这个来进行操作
        int n = statement.executeUpdate();
        System.out.println("n = " + n);

        //5. 断开连接，释放资源 ----- 注意后创建的先释放
        statement.close();
        connection.close();
    }
}


















