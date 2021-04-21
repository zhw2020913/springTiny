package java.us.codecraft.tinyioc.beans.io;

import java.net.URL;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/9 16:15
 */
public class ResourceLoader {
    public Resource getResource(String location){
        //在 java.net 包中包含专门用来处理 URL 的类 URL，可以获得 URL 的相关信息，例如 URL 的协议名和主机名等。
        //用于获得文件的路径
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
