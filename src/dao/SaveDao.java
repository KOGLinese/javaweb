package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Info.SaveInfo;

public class SaveDao {
	public SaveInfo judge(int answer_id,String user_id) throws SQLException {//�ж��б����Ƿ����
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "";
        sql = "select * from save where user_id = " + "'"+user_id+"' and answer_id = "+answer_id;
        try{
        	pstmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet res=pstmt.executeQuery();//��������� res
            if(res.next())
            {
            	int sanswer_id=res.getInt("answer_id");
            	int question_id=res.getInt("question_id");
            	String auser_id=res.getString("user_id");
            	SaveInfo save=new SaveInfo(sanswer_id,question_id,auser_id);
            	return save;
            }
            else 
            {
            	return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally
        {
            if(pstmt!= null) 
          	  pstmt.close(); 		
            if(con!= null) 
              con.close(); 
          }
	}
	public void addsave(int answer_id,String user_id) throws SQLException {
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "";
        sql = "insert into save (answer_id,user_id) values(?,?)";
    	try {
    		if(judge(answer_id,user_id)==null) {
    			pstmt = (PreparedStatement) con.prepareStatement(sql); 
    			pstmt.setInt(1, answer_id);
    			pstmt.setString(2, user_id);
    			pstmt.executeUpdate();
    			System.out.println("�ղسɹ�");
    		}
    		else {
    			System.out.println("���ղ�");
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
        {
            if(pstmt!= null) 
          	  pstmt.close(); 		
            if(con!= null) 
              con.close(); 
          }
	}
	public void deletesave(int answer_id,String user_id) throws SQLException {
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "";
        sql = "delete from save where answer_id="+answer_id+" and user_id="+"'"+user_id+"'";
    	try {
			pstmt = (PreparedStatement) con.prepareStatement(sql); 
	        pstmt.executeUpdate();
	    	System.out.println("ɾ���ɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
        {
            if(pstmt!= null) 
          	  pstmt.close(); 		
            if(con!= null) 
              con.close(); 
          }
	}
	public List<SaveInfo> SearcByUser_id(String user_id) throws SQLException {//��ʾ�ش��б�
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "";
        sql = "select * from save where user_id = " + "'"+user_id+"'";
        try{
        	pstmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet res=pstmt.executeQuery();//��������� res
            List<SaveInfo> Saves= new ArrayList<>();
            while(res.next())
            {
            	
            	int answer_id=res.getInt("answer_id");
            	int question_id=res.getInt("question_id");
            	String auser_id=res.getString("user_id");
            	
            	SaveInfo save=new SaveInfo(answer_id,question_id,auser_id);
            	Saves.add(save);
            }
            return Saves;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally
        {
            if(pstmt!= null) 
          	  pstmt.close(); 		
            if(con!= null) 
              con.close(); 
          }
	}
}
