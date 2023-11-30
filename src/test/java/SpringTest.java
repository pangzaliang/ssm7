import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.web.client.RestTemplate;
import z.z.SpringConfig;

@SpringJUnitWebConfig(SpringConfig.class)
public class SpringTest {

    @Autowired
    private RestTemplate restTemplate;


    @Test
    public void test01 () {
        String forObject = restTemplate.getForObject("http://www.baidu.com", String.class);
        System.out.println(forObject);

        String forObject11 = restTemplate.getForObject("http://www.baidu.com", String.class);
        System.out.println(forObject11);
    }
}
