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
 * Time: 23:25
 */
public class JDBCUpdate {
    public static void main(String[] args) throws SQLException {
        //  实现数据库的修改操作，和插入非常相似


        //1. 构造数据库
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/db_test1?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("959452");

        //2. 和数据库建立连接
        Connection connection = dataSource.getConnection();

        //3. 用户输入要修改的id
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要修改的同学的学号：");
        int id = scanner.nextInt();
        System.out.println("请输入你要将其修改为的名字：");
        String name = scanner.next();
        //next读到空白处 --- 空格，翻页符，制表符，换行符，垂直制表符........
        //nextLine读到换行符

        //4. sql 语句
        String sql = "update student set name = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,name);
        statement.setInt(2,id);
        System.out.println(statement);


        //5. 执行sql
        int n = statement.executeUpdate();
        System.out.println("n = " + n);

        //6. 释放资源
        statement.close();
        connection.close();









    }
}
