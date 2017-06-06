import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
 
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.RandomStringUtils;

import util.StringUtil;
 
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
         
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String conteudo = "";
        String name = "" ;
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                 String FILE_PATH =  getServletContext().getInitParameter("file-upload");
 
                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
                ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
     
                List<FileItem> multiparts = fileUpload.parseRequest(request);
                for(FileItem item: multiparts) {
                    if(!item.isFormField()) {
                        String fileName = item.getName();
                        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
                        name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
                        if(ext.equals("jpg")){
                             conteudo += "\n<img src=";
                             conteudo +="http://localhost:8080/data/";
                             conteudo += name+">";
                              System.out.println("conteudo ??? "+conteudo);

                        }else if(ext.equals("mp4")){
                            conteudo +="<video controls>";
                            conteudo +="<source src=";
                            conteudo +="http://localhost:8080/data/";
                            conteudo += name;
                            conteudo +=" >";
                        }
                        item.write(new File(String.format("%s%s", FILE_PATH, name)));
                    }else{
                        conteudo += (String)item.getString(); 
                        System.out.println("lol "+conteudo);
                    }
                }

                System.out.println("Arquivo carregado com sucesso");
                conteudo+="\n<end>";
                System.out.println("conteudo ??? "+conteudo);
                conteudo = StringUtil.FormatPost(conteudo);
                System.out.println("conteudo ??? "+conteudo);
                conteudo = StringUtil.toPercentEncode(conteudo);
                response.sendRedirect("http://localhost:8080/netflix/us/createpost.jsp?conteudo="
                    +conteudo);

            } catch (Exception ex) {
                System.out.println(" Upload de arquivo falhou devido a "+ ex);
            }
 
        }  
    }


   

   

}