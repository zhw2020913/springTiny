package springStep.step8.code.beans.io;

import java.net.URL;

/**
 * @author yihua.huang@dianping.com
 */
public class ResourceLoader {

    public Resource getResource(String location){
        //根据地址生成一个urL返回一个resource对象
        URL url = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(url);
    }
}
