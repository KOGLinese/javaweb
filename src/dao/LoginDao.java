package dao;

import java.sql.*;
import Info.UserInfo;

public class LoginDao {
	
	public UserInfo judge(String id,String pwd) {
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        System.out.println(id);
        String sql = "select * from user where user_id = " +"'" +id+ "'";
       
        try{
        	pstmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet res=pstmt.executeQuery();//��������� res
            if(res.next())
            {
            	UserInfo user = new UserInfo();
            	user.setUser_id(res.getString("user_id"));
            	user.setUse_question_num(res.getString("user_question_num"));
            	user.setUser_answer_num(res.getString("user_answer_num"));
            	user.setUser_name(res.getString("user_name"));
            	user.setUser_img_url(res.getString("user_img_url"));
            	user.setUser_pwd(res.getString("user_pwd"));
            	user.setUser_follows(res.getInt("user_follows"));
            	user.setUser_score(res.getInt("user_score"));
            	user.setUser_sex(res.getString("user_sex"));
            	
            	if(user.getUser_pwd().equals(pwd)) {
            		return user;
            	}
            	else {
            		return null;
            	}
            }
            else {
            	return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
		return null;
	}
}
