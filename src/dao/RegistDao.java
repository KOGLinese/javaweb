package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegistDao {
    
    public int judge(String id) {//�ж�id �Ƿ��ظ�
    	Connection con;//�������Ӷ���
    	PreparedStatement pstmt;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "select * from user where user_id = " +"'" +id+ "'";
        System.out.println("-----");
        try{
        	pstmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet res=pstmt.executeQuery();//��������� res
            if(res.next()) {//�ж��Ƿ����
            	return 0;
            }
            else return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        
    }
    public void regist(String id,String pwd,String name) {//��������
    	Connection con;
    	PreparedStatement pstmt;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();
        String sql="insert into user (user_id,user_pwd,user_name) value(?,?,?)";
        int i=0;
        try{
            pstmt = (PreparedStatement) con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            pstmt.setString(3, name);
            i=pstmt.executeUpdate();
            pstmt.close();
            con.close();
            System.out.println("����ɹ�");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
