package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import Info.AnswerInfo;
import Info.QuestionInfo;

public class AnswerDao {
	public List<AnswerInfo> display(int id) throws SQLException {//��ʾ�ش��б�
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "";
        sql = "select * from answer where question_id = " +id;
        try{
        	pstmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet res=pstmt.executeQuery();//��������� res
            List<AnswerInfo> Answers= new ArrayList<>();
            while(res.next())
            {
            	
            	int answer_id=res.getInt("answer_id");
            	int question_id=res.getInt("question_id");
            	String user_id=res.getString("user_id");
            	String answer_content=res.getString("answer_content");
            	int answer_score=res.getInt("answer_score");
            	
            	AnswerInfo answer=new AnswerInfo(answer_id,question_id,user_id,answer_content,answer_score);
            	Answers.add(answer);	
            }
           
            return Answers;
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
	public List<AnswerInfo> SearchByUser_id(String user_id) throws SQLException {//��ʾ�ش��б�
		Connection con = null;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        
        String sql = "";
        sql = "select * from answer where user_id = " + "'"+user_id+"'";
        try{
        	con=c.getCinnection();//�������ݿ�
        	pstmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet res=pstmt.executeQuery();//��������� res
            List<AnswerInfo> Answers= new ArrayList<>();
            while(res.next())
            {
            	
            	int answer_id=res.getInt("answer_id");
            	int question_id=res.getInt("question_id");
            	String auser_id=res.getString("user_id");
            	String answer_content=res.getString("answer_content");
            	int answer_score=res.getInt("answer_score");
            	
            	AnswerInfo answer=new AnswerInfo(answer_id,question_id,auser_id,answer_content,answer_score);
            	Answers.add(answer);	
            }
            return Answers;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally
        {
            if(pstmt!= null) 
          	  pstmt.close(); 		
            if(con!= null) 
              con.close(); 
          }
	}
	
	public AnswerInfo SearchByAnswer_id(int Answer_id) throws SQLException {
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "";
        sql = "select * from answer where answer_id = " +Answer_id;
        try{
        	pstmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet res=pstmt.executeQuery();//��������� res
            if(res.next())
            {
            	int answer_id=res.getInt("answer_id");
            	int question_id=res.getInt("question_id");
            	String auser_id=res.getString("user_id");
            	String answer_content=res.getString("answer_content");
            	int answer_score=res.getInt("answer_score");
            	AnswerInfo answer=new AnswerInfo(answer_id,question_id,auser_id,answer_content,answer_score);
            	return answer;
            }
            else {
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
	
	public boolean HadGood(int ans_id,String user_id) throws SQLException {
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "";
        System.out.println("ccccc");
		 try {//�ж�֮ǰ�û��Ƿ���޹�
			 	System.out.println(ans_id+"===="+user_id);
	        	sql = "select * from goodanswer where answer_id = "+ ans_id +" and user_id = "+"'"+user_id+"'";
				pstmt = (PreparedStatement) con.prepareStatement(sql);
				ResultSet res=pstmt.executeQuery();//��������� res
				
				if(res.next()) {
					
					return true;
				}
				else{
					System.out.println("aakkkkkkk");
					return false;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
	        {
	            if(pstmt!= null) 
	          	  pstmt.close(); 		
	            if(con!= null) 
	              con.close(); 
	          }
		 return false;
	}
	public AnswerInfo GetGood(int ans_id,String user_id) throws SQLException{//��ʾ�ش��б�
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "";
        try{
        	sql = "select * from answer where answer_id = " +ans_id;
        	pstmt = (PreparedStatement) con.prepareStatement(sql);
        	ResultSet res=pstmt.executeQuery();//��������� res
            if(res.next())
            {
            	AnswerInfo answer=new AnswerInfo(res.getInt("answer_id"),res.getInt("question_id"),res.getString("user_id"),res.getString("answer_content"),res.getInt("answer_score")+1);
            	sql = "update answer set answer_score="+answer.getAnswer_score()+" where answer_id ="+ ans_id;
            	if(pstmt!= null) 
                	  pstmt.close(); 
            	pstmt = (PreparedStatement) con.prepareStatement(sql);
            	pstmt.executeUpdate(sql);
            	if(pstmt!= null) 
                	  pstmt.close(); 
            	sql = "insert into goodanswer (answer_id,user_id) values(?,?)";
            	pstmt = (PreparedStatement) con.prepareStatement(sql);
                pstmt.setInt(1, ans_id);
                pstmt.setString(2, user_id);
            	pstmt.executeUpdate();
            	System.out.println(sql+"  ��������");
            	return answer;
            }
            else {
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
	public void Offer(AnswerInfo answer,String user_id,int question_id) throws SQLException {
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        System.out.println("adao.offer+==="+user_id);
        String sql = "";
        sql = "insert into answer (user_id,answer_content,question_id) values(?,?,?)";
    	try {
			pstmt = (PreparedStatement) con.prepareStatement(sql); 
			pstmt.setString(1, user_id);
	        pstmt.setString(2, answer.getAnswer_content());
	        pstmt.setInt(3, question_id);
	    	pstmt.executeUpdate();
	    	System.out.println("�ش�ɹ�");
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
	
}
