package Servlet;

import java.io.IOException;
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
					int uid=-1;
					String uname=null;
					Iterator<FileItem> iter=items.iterator();
					while(iter.hasNext()) {
						FileItem item=iter.next();
						String itemName=item.getFieldName();
						if(item.isFormField()) {//�ж��Ƿ�Ϊ��ͨ�ֶ�
							if(itemName.equals("uid")){//��������name�ж�
								uid = Integer.parseInt(item.getString());
							}else if(itemName.equals("uname")) {
								uname= item.getString();
							}
							else {
								System.out.println("else...");
							}
						}else {   //�ļ��ֶ�
							//�ļ��ϴ�
							String fileName = item.getName();
							String ext = fileName.substring(fileName.indexOf(".")+1);
							if(!ext.equals("png")||ext.equals("gif")||ext.equals("jpg")) {
								System.out.println("���������ϴ�ʧ��");
								return ;
							}
							//��ȡ�ļ����ݲ��ϴ�
							//�����ļ�·��
							//��ȡ������·��
							String path = request.getSession().getServletContext().getRealPath("uploadtest");
							File file= new File(path,fileName);
							//�����ϴ��ļ���С
						
							// upload.setSizeMax(20*1024);//��λ�ֽ�B--20kb
							item.write(file);//�ϴ�
							System.out.println(fileName+" success! ");
							return ;
						}
					}
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
