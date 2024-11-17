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
        //1. Ҫ�������ݿ⣬��Ҫ���������ݿ⣬Ҳ��Ҫ����������������ڵ�λ��
        //   ʹ��DataSource �������� MySQL ��������λ��
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/db_test1?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("959452");

        //2. �����������ݿ��������������
        Connection connection = dataSource.getConnection();
        System.out.println(connection);//com.mysql.jdbc.JDBC4Connection@3eb07fd3


        //3. �������Ϳ���дsql�����
        //   ����һ���������,������student���������һ������
        //   ֱ����String ��ʽ��SQL �����У�����Ҫ����һ���������
        Scanner scanner = new Scanner(System.in);
        System.out.println("������ѧ�ţ�");
        int num = scanner.nextInt();
        System.out.println("������������");
        String name = scanner.next();
        //����һ�ֲ���ѧ��д��
        //String sql = "insert into student values(" + num + ", '"+ name +"')";

        //����һ�ֿ�ѧ��д��
        //ʹ�� �� ��Ϊռλ��������ʹ�� statement ������� �� �����滻
        String sql = "insert into student values(?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, num);
        statement.setString(2, name);


        //4. ִ��SQL ��� insert, update, delete ����ͨ�� executeUpdate ��ִ�е�
        //   select ����ͨ�� executeQuery ��ִ�еģ�
        //   executeQuery �ķ���ֵ��һ�� ���� ��ʾ������ʽӰ���˼���!
        //   statement.executeUpdate();ʹ����������в���
        int n = statement.executeUpdate();
        System.out.println("n = " + n);

        //5. �Ͽ����ӣ��ͷ���Դ ----- ע��󴴽������ͷ�
        statement.close();
        connection.close();
    }
}


















