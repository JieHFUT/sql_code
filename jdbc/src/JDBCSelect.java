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

        // �������ݿ��еļ�¼
        // ������Һ�ǰ��Ĳ����Ͳ��Ǻ�һ�� ------ ����һ�������Ĺ���


        //1. ��������Դ
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/db_test1?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("959452");

        //2. �����ݿ⽨������
        Connection connection = dataSource.getConnection();

        //3. �û�����Ҫ�޸ĵ�id


        //4. sql ���
        String sql = "select * from student";
        PreparedStatement statement = connection.prepareStatement(sql);

        //5. ִ��sql
        //   �˴�Ҫʹ�õ��� execuQuery
        //   executeUpdate ֻ�ܷ���һ��int
        //   execuQuery ���ص���һ�� ResultSet ���󣬿��԰����������Ϊһ������ʱ��
        ResultSet resultSet = statement.executeQuery();


        //6. ���������ʱ���õ����������
        //   resultSet �򵥵ĵ���һ�������ڡ��������������Ķ���������
        //   next ���û�е���ĩβ���ͷ��� false������ͷ���true
        while(resultSet.next()){
            // ������Ϳ���ȡ��һ�е�����
            // ͨ�� ResultSet �����getxxx ����������ȡ�������ָ������

            // ȡid  id������  ��getInt
            int id = resultSet.getInt("id");
            // ȡname  name��String  getString
            String name = resultSet.getString("name");
            System.out.println(id + "��" + name);
        }


        //�ر����ӣ��ͷ���Դ
        resultSet.close();
        statement.close();
        connection.close();


    }
}
