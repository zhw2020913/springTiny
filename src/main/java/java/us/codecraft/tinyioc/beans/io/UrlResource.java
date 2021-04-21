package java.us.codecraft.tinyioc.beans.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/9 16:16
 */
public class UrlResource implements Resource{
    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = url.openConnection();
        //connect()方法由子类实现本地与服务器的连接方式。一般使用TCP socket，但也可以使用其他其他机制来建立。
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
