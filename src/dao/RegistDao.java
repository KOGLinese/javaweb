package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Info.QuestionInfo;


public class RegistDao {
    
    public int judge(String id) throws SQLException {//�ж�id �Ƿ��ظ�
    	Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "select * from user where user_id = " +"'" +id+ "'";
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
        }finally
        {
            if(pstmt!= null) 
          	  pstmt.close(); 		
            if(con!= null) 
              con.close(); 
          }
        
    }
    public void regist(String id,String pwd,String name) throws SQLException {//��������
    	Connection con;
    	PreparedStatement pstmt = null;//����Ԥ�����������
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
            
            System.out.println("����ɹ�");
        }catch (Exception e){
            e.printStackTrace();
        }finally
        {
            if(pstmt!= null) 
          	  pstmt.close(); 		
            if(con!= null) 
              con.close(); 
          }
    }
    
}
