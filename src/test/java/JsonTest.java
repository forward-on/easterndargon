import com.alibaba.fastjson.JSONObject;

/**
 * @Description:
 * @Date 2018-08-03 10:08
 * @Author ly
 */
public class JsonTest {

    public static void main(String[] args) {

        User user = new User();
        user.setUserId(1);
        user.setName("bj");
        Address address = new Address();
        address.setLocation("chaoyang");
        address.setArea("100");
        user.setAddress(address);
        String userStr = JSONObject.toJSONString(user);

        JSONObject jsonObject = JSONObject.parseObject(userStr);
        Address retAddr = jsonObject.getObject("address", Address.class);
        System.out.println(retAddr.getLocation());

        Addr addr = jsonObject.getObject("address", Addr.class);
        addr.setName(jsonObject.getString("name"));
        addr.setUserId(jsonObject.getInteger("userId"));
        System.out.println(addr);

    }

}
