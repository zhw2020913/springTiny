package java.us.codecraft.tinyioc.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/9 16:12
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
