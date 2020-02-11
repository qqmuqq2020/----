package hr.manage.controller.staffs.picture;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("serial") 
public class Upload extends HttpServlet{
	
	
	@SuppressWarnings("unchecked")  
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		
        String savePath = this.getServletConfig().getServletContext().getRealPath("");  
        savePath = savePath + "/uploads/";   //路径名称
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        JSONObject js=new JSONObject();//创建json数组
        File f1 = new File(savePath);    									//输出1
  		//输出路径名称
        if (!f1.exists()) {  
            f1.mkdirs();  
        }  
        DiskFileItemFactory fac = new DiskFileItemFactory();  
        ServletFileUpload upload = new ServletFileUpload(fac);  
        upload.setHeaderEncoding("utf-8");   //转码
        List fileList = null;  
        try {  
            fileList = upload.parseRequest(request);  
        } catch (FileUploadException ex) {  
            return;  
        }  
        Iterator<FileItem> it = fileList.iterator();  
        String name = "";  
        String extName = "";  
        while (it.hasNext()) {  
            FileItem item = it.next();  
            if (!item.isFormField()) {  
                name = item.getName();  
                long size = item.getSize();  
                String type = item.getContentType();  
                //System.out.println(size + " " + type);  
                if (name == null || name.trim().equals("")) {  
                    continue;  
                }  
                // 扩展名格式：  
                if (name.lastIndexOf(".") >= 0) {  
                    extName = name.substring(name.lastIndexOf("."));  
                }  
                File file = null;  
                do {  
                    // 生成文件名：  
                    name = (int)(System.currentTimeMillis()/100)+"_"+(int)(Math.random()*100)+extName; 
                    file = new File(savePath + name);   //数据库存这个就可以
                } while (file.exists());  
                File saveFile = new File(savePath + name);  
                try {  
                    item.write(saveFile);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        js.put("path", savePath); 
        js.put("fileName", name);
       
        out.print(js.toString());
        System.out.println(name);
    }  
	 
}
