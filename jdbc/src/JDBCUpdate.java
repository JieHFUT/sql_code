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
        //  ʵ�����ݿ���޸Ĳ������Ͳ���ǳ�����


        //1. �������ݿ�
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/db_test1?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("959452");

        //2. �����ݿ⽨������
        Connection connection = dataSource.getConnection();

        //3. �û�����Ҫ�޸ĵ�id
        Scanner scanner = new Scanner(System.in);
        System.out.println("��������Ҫ�޸ĵ�ͬѧ��ѧ�ţ�");
        int id = scanner.nextInt();
        System.out.println("��������Ҫ�����޸�Ϊ�����֣�");
        String name = scanner.next();
        //next�����հ״� --- �ո񣬷�ҳ�����Ʊ�������з�����ֱ�Ʊ��........
        //nextLine�������з�

        //4. sql ���
        String sql = "update student set name = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,name);
        statement.setInt(2,id);
        System.out.println(statement);


        //5. ִ��sql
        int n = statement.executeUpdate();
        System.out.println("n = " + n);

        //6. �ͷ���Դ
        statement.close();
        connection.close();









    }
}
