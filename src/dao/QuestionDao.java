package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import Info.QuestionInfo;

public class QuestionDao {
	public List<QuestionInfo> display(String id) throws SQLException {//��ʾ�����б�
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "";
        if(id=="#") sql="select * from question";
        else {
        	sql = "select * from question where user_id = " +"'" +id+ "'";
        }
        try{
        	pstmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet res=pstmt.executeQuery();//��������� res
            List<QuestionInfo> questions= new ArrayList<>();
            while(res.next())
            {
            	int question_id=res.getInt("question_id");
            	String user_id=res.getString("user_id");
            	String question_title=res.getString("question_title");
            	String question_content=res.getString("question_content");
            	String part_content=res.getString("part_content");
            	int question_answers=res.getInt("question_answers");
            	QuestionInfo question=new QuestionInfo(question_id,user_id,question_title,question_content,question_answers,part_content);
            	questions.add(question);	
            }
            
            return questions;
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
	
	public List<QuestionInfo> search(String squestion_title) throws SQLException {//��ʾ�����б�
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String title="%";
        for(int i=0;i < squestion_title.length();i++) {
       	 String tmp=squestion_title.substring(i,i+1);
       	 System.out.println(tmp);
        	title=title+"%"+tmp; 
       }
        title=title+"%";
        String sql = "";
        System.out.println(title);
        sql = "select * from question where question_title like " +"'" +title+ "'";
        
        try{
        	pstmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet res=pstmt.executeQuery();//��������� res
            List<QuestionInfo> questions= new ArrayList<>();
            while(res.next())
            {
            	int question_id=res.getInt("question_id");
            	String user_id=res.getString("user_id");
            	String question_title=res.getString("question_title");
            	String question_content=res.getString("question_content");
            	String part_content=res.getString("part_content");
            	int question_answers=res.getInt("question_answers");
            	QuestionInfo question=new QuestionInfo(question_id,user_id,question_title,question_content,question_answers,part_content);
            	questions.add(question);	
            }
            
            return questions;
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
	
	
	public QuestionInfo serach_question(int id) throws SQLException {//��������id��ѯ����
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "";
        sql = "select * from question where question_id = " +id;
        
        try{
        	pstmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet res=pstmt.executeQuery();//��������� res
            
            if(res.next())
            {
            	
            	int question_id=res.getInt("question_id");
            	String user_id=res.getString("user_id");
            	String question_title=res.getString("question_title");
            	String question_content=res.getString("question_content");
            	int question_answers=res.getInt("question_answers");
            	String part_content=res.getString("part_content");
            	System.out.println(question_id);
            	QuestionInfo question=new QuestionInfo(question_id,user_id,question_title,question_content,question_answers,part_content);
            	return question;
            }
            return null;
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
	public void Offer(QuestionInfo question,String user_id) throws SQLException {
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "";
        sql = "insert into question (user_id,question_title,question_content,part_content) values(?,?,?,?)";
    	try {
			pstmt = (PreparedStatement) con.prepareStatement(sql); pstmt.setString(1, user_id);
	        pstmt.setString(2, question.getQuestion_title());
	        pstmt.setString(3, question.getQuestion_content());
	        pstmt.setString(4, question.getPart_content());
	    	pstmt.executeUpdate();
	    	System.out.println("���ʳɹ�");
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
	public void Add_answer(int question_id) throws SQLException {
		Connection con;//�������Ӷ���
    	PreparedStatement pstmt = null;//����Ԥ�����������
    	Conn c=new Conn();
        con=c.getCinnection();//�������ݿ�
        String sql = "";
        QuestionInfo q=new QuestionInfo();
        q=this.serach_question(question_id);
        int ant=q.getQuestion_answers()+1;
        sql = "update question set question_answers="+ant+" where question_id ="+ question_id;
    	try {
			pstmt = (PreparedStatement) con.prepareStatement(sql);
	    	pstmt.executeUpdate();
	    	System.out.println("���»ش���");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally
        {
            if(pstmt!= null) 
          	  pstmt.close(); 		
            if(con!= null) 
              con.close(); 
          }
	}
}
