package springStep.step10.code.beans.io;

import java.net.URL;


public class ResourceLoader {

    public Resource getResource(String location){
        //根据地址生成一个urL返回一个resource对象
        URL url = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(url);
    }
}
