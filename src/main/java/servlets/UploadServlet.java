import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.sql.SQLException;
import org.apache.commons.io.IOUtils;
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
import util.Base64Util;
 
import controller.ControllerArquivo;
import model.Arquivo;
 
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
                // for(FileItem item: multiparts) {
                //     if(!item.isFormField()) {
                //         String fileName = item.getName();
                //         String ext = fileName.substring(fileName.lastIndexOf(".")+1);
                //      // name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
                //         System.out.println("item.get() " + item.get());
                //         String encoded = Base64Util.ToBase64(item.get());
                //         Arquivo file = new Arquivo(encoded,ext);
                //         if(ControllerArquivo.SaveFile(file)){
                //              if(ext.equals("jpg")){
                //              conteudo += "\n<img src=";
                //              conteudo +="http://localhost:8080/netflix/upload?item=";
                //              conteudo += file.getId()+">";
                //               // System.out.println("conteudo ??? "+conteudo);

                //                 }else if(ext.equals("mp4")){
                //                     conteudo +="<video controls>";
                //                     conteudo +="<source src=";
                //                     conteudo +="http://localhost:8080/netflix/upload?item=";
                //                     conteudo += file.getId();
                //                     conteudo +=" >";
                //                 }
                //         }else{
                //             System.out.println("erro ao salvar o arquivo");
                //         }

                       
                //     }else{
                //         conteudo += (String)item.getString(); 
                //         // System.out.println("lol "+conteudo);
                //     }
                // }
                //Salva no diretorio
                for(FileItem item: multiparts) {
                    if(!item.isFormField()) {
                        String fileName = item.getName();
                        System.out.println("fileName ??? "+fileName);
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

                // System.out.println("Arquivo carregado com sucesso");
                conteudo+="\n<end>";
                // System.out.println("conteudo ??? "+conteudo);
                conteudo = StringUtil.FormatPost(conteudo);
                // System.out.println("conteudo ??? "+conteudo);
                conteudo = StringUtil.toPercentEncode(conteudo);
                Arquivo a = ControllerArquivo.findLast();
                response.getWriter().println(conteudo);
                // response.sendRedirect("http://localhost:8080/netflix/us/createpost.jsp?conteudo="
                //     +conteudo);

            } catch (Exception ex) {
                System.out.println(" Upload de arquivo falhou devido a "+ ex);
            }
 
        }  
    }


        protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        //response.setHeader("Content-Disposition","inline");

        String id  = request.getParameter("item");
        try{
            Arquivo file = ControllerArquivo.LoadById(id);
            if(file.getExtensao().equals("jpg")){
                response.setContentType("image/jpeg");
            }else{
                response.setContentType("video/mp4");
            }
            response.setContentType("image/jpeg");
            byte[] fileInBytes = Base64Util.ToFile(file.getConteudo());
            System.out.println("fileInBytes " + fileInBytes);
            InputStream is = new ByteArrayInputStream(fileInBytes);
            OutputStream os = response.getOutputStream();

            IOUtils.copy(is, os);
            os.flush();
            os.close();
        }catch(Exception err){
            //erro ao carregar o arquivo;
        }
        
        
    }


   

}