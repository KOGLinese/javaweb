package dao;
import java.sql.*;
public class LoginDao {
	
	public String judge(String id,String pwd) {
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        System.out.println(id);
        String sql = "select * from user where user_id = " +"'" +id+ "'";
        //String sql = "select * from user ";
        try{
        	pstmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet res=pstmt.executeQuery();//��������� res
            if(res.next())
            {
            	System.out.println("acccccccccca");
            	String user_pwd= res.getString("user_pwd");
            	String user_id= res.getString("user_id");
            	String user_name= res.getString("user_name");
            	System.out.println(user_id);
            	System.out.println(user_pwd);
            	if(user_pwd.equals(pwd)) {
            		System.out.println("asdasda");
            		return user_name;
            	}
            	else {
            		return "#";
            	}
            }
            else {
            	return "#";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
		return "#";
	}
}
