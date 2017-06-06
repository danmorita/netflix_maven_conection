package util;

public class StringUtil{

	public static  String toPercentEncode(String orig){
        String encode = orig;
        encode = encode.replaceAll(" ","%20");
        encode = encode.replaceAll("<","%3C");
        encode = encode.replaceAll(">","%3E");
        encode = encode.replaceAll("/","%2F");
        encode = encode.replaceAll("\n","%0A");
        return encode;
    }
    public static  String toHtmlEncode(String orig){
        String encode = orig;
        encode = encode.replaceAll("%20"," ");
        encode = encode.replaceAll("%3C","<");
        encode = encode.replaceAll("%3E",">");
        encode = encode.replaceAll("%2F","/");
        encode = encode.replaceAll("%0A","\n");
        return encode;
    }
    public static String FormatPost(String post){
        String formated = "";
        String line = post;
        int fim;
        System.out.println("post"+post);
        do{

            fim = post.indexOf("<end>");
            System.out.println("fim "+fim);
            if(fim != 0){
                int nexttag = post.indexOf("<");

                if(nexttag != -1 ){
                    if(nexttag == 0 ){
                        //encontrar o final da tag 
                        System.out.println("next tag "+nexttag);
                        int pos = post.indexOf(">");
                        if(post.charAt(nexttag+1) != 'i' && post.charAt(nexttag+1) != 'e'){
                            pos = post.indexOf(">",pos+1);
                        }
                        
                        System.out.println("next char "+post.charAt(nexttag+1));
                        if(post.charAt(nexttag+1) != 'e'){
                           line = post.substring(0,pos+1);
                            System.out.println("linha"+line);
                            formated += line+"\n";
                            post = post.substring(pos+1);
                        }
                        
                    }else{
                         System.out.println("next tag "+nexttag);
                        line = post.substring(0,nexttag);
                        post = post.substring(nexttag);
                        if(line.length()>1)
                            formated+="<p>"+line+"</p>\n";
                    }
                }
            }
            

        }while(fim > 0);

       // formated = formated.substring(0,formated.indexOf("<end"));
        return formated;
    }
}