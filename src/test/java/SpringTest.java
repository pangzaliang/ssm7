import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.web.client.RestTemplate;
import z.z.SpringConfig;
import z.z.service.cacheService.TestCacheDemo;

@SpringJUnitWebConfig(SpringConfig.class)
public class SpringTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestCacheDemo testCacheDemo;


    @Test
    public void test01 () {
//        String forObject = restTemplate.getForObject("https://www.baidu.com", String.class);
//        System.out.println(forObject);
//
//        String forObject11 = restTemplate.getForObject("https://www.baidu.com", String.class);
//        System.out.println(forObject11);
        System.out.println(testCacheDemo.list());
        System.out.println(testCacheDemo.list());
        System.out.println(testCacheDemo.list());
        System.out.println(testCacheDemo.list());

        System.out.println(testCacheDemo.list2());
        System.out.println(testCacheDemo.list2());
        System.out.println(testCacheDemo.list2());
        System.out.println(testCacheDemo.list2());


    }
}
