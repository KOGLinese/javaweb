package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conn {
    Connection con;
    static String DB_URL="jdbc:mysql://localhost:3306/answerquestion";//���ݿ�url answerquestionΪ���ݿ���
    static String USER="web";//�û���
    static String PASS="666";//����
    public Connection getCinnection(){
        try{//�������ݿ�������
            Class.forName("com.mysql.jdbc.Driver");
            
        }catch (ClassNotFoundException e){
            
            e.printStackTrace();
        }
        try{//ͨ���������ݿ��URL��ȡ���ݿ����Ӷ���
            con = DriverManager.getConnection(DB_URL,USER,PASS);
            
        }catch (SQLException e){
          
            e.printStackTrace();
        }
        return con;
    }
}