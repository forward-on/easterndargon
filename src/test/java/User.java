/**
 * @Description:
 * @Date 2018-08-03 10:10
 * @Author ly
 */
public class User {

    private Integer userId;
    private String name;
    private Address address;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
