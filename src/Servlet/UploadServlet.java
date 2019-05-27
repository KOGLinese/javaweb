package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Info.UserInfo;
import dao.UserDao;





/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String user_id =  (String)request.getSession().getAttribute("user_id");
		String user_name = null;
		String user_sex = null;
		String user_describe = null;
		UserDao udao =new UserDao();
		UserInfo buser = null;
		try {
			buser = udao.SerachByUser_id(user_id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String user_img_url=buser.getUser_img_url();
		
		String fileName=buser.getUser_img_url();
		request.setCharacterEncoding("utf-8");//����ͳһ
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		//�ϴ�	
		try {
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if(isMultipart) {//�ж�ǰ̨��form�Ƿ���Multipart
					FileItemFactory factory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(factory);
					//��ȡǰ̨��Ϣ
					List<FileItem> items =upload.parseRequest(request);
					//����Items
					File file= null;
					Iterator<FileItem> iter=items.iterator();
					while(iter.hasNext()) {
						FileItem item=iter.next();
						String itemName=item.getFieldName();
						if(item.isFormField()) {//�ж��Ƿ�Ϊ��ͨ�ֶ�
							if(itemName.equals("user_name")){//��������name�ж�
								user_name = item.getString();
								user_name =new String(user_name.getBytes("iso-8859-1"),"utf-8");
							}else if(itemName.equals("user_sex")) {
								user_sex= item.getString();
								user_sex =new String(user_sex.getBytes("iso-8859-1"),"utf-8");
							}else if(itemName.equals("user_describe")) {
								user_describe=item.getString();
								user_describe =new String(user_describe.getBytes("iso-8859-1"),"utf-8");
							}
							
						}else {   //�ļ��ֶ�
							//�ļ��ϴ�
							fileName = item.getName();
							user_img_url= item.getName();
							System.out.println(fileName);
							
							user_img_url =new String(user_img_url.getBytes("iso-8859-1"),"utf-8");
							String ext = fileName.substring(fileName.indexOf(".")+1);
							if(!ext.equals("png")||ext.equals("gif")||ext.equals("jpg")) {
								System.out.println("���������ϴ�ʧ��");
								return ;
							}
							
							//��ȡ�ļ����ݲ��ϴ�
							//�����ļ�·��
							//��ȡ������·��
							String path = request.getSession().getServletContext().getRealPath("img");
							file= new File(path,fileName);
							//�����ϴ��ļ���С
							// upload.setSizeMax(20*1024);//��λ�ֽ�B--20kb
							item.write(file);//�ϴ�
							System.out.println(fileName+" succcccccess! ");
							
						}
					}
					
					UserInfo user=new UserInfo();
					user.setUser_id(user_id);
					user.setUser_name(user_name);
					user.setUser_sex(user_sex);
					user.setUser_describe(user_describe);
					user.setUser_img_url(user_img_url);
					udao.update(user);
					System.out.println(fileName+" succssssssess! ");
					response.sendRedirect("user.jsp");
				}
		} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
