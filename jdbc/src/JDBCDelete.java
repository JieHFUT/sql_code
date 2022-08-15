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
 * Date: 2022-08-16
 * Time: 0:12
 */
public class JDBCDelete {
    public static void main(String[] args) throws SQLException {
        //  实现数据库的删除操作


        //1. 构造数据库
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/db_test1?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("959452");

        //2. 和数据库建立连接
        Connection connection = dataSource.getConnection();

        //3. 用户输入要修改的id
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要删除的同学的学号：");
        int id = scanner.nextInt();


        //4. sql 语句
        String sql = "delete from student where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        System.out.println(statement);


        //5. 执行sql
        int n = statement.executeUpdate();
        System.out.println("n = " + n);

        //6. 释放资源
        statement.close();
        connection.close();
    }
}
