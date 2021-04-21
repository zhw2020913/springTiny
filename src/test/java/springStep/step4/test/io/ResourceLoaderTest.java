package springStep.step4.test.io;

import org.junit.Assert;
import org.junit.Test;
import springStep.step4.code.io.Resource;
import springStep.step4.code.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 14:49
 */
public class ResourceLoaderTest {
    @Test
    public void test()  {//测试说明根据
        ResourceLoader resourceLoader = new ResourceLoader();
        Resource resource = resourceLoader.getResource("tinyioc4.xml");
        try {
            InputStream inputStream = resource.getInputStream();
            Assert.assertNotNull(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }



    }
}
