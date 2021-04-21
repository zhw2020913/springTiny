package springStep.step10.test.beans.io;

import org.junit.Assert;
import org.junit.Test;
import springStep.step10.code.beans.io.Resource;
import springStep.step10.code.beans.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 11:28
 */
public class ResourceLoaderTest {
    @Test
    public void test() throws IOException {
        ResourceLoader resourceLoader = new ResourceLoader();
        Resource resource = resourceLoader.getResource("tinyioc9.xml");
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
    }
}
