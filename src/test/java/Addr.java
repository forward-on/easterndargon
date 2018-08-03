/**
 * @Description:
 * @Date 2018-08-03 10:17
 * @Author ly
 */
public class Addr {

    private Integer userId;
    private String name;
    private String location;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Addr{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
