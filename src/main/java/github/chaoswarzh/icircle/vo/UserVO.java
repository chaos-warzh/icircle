package github.chaoswarzh.icircle.vo;

import github.chaoswarzh.icircle.enums.RoleEnum;
import github.chaoswarzh.icircle.po.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {

    private Integer id;

    private String name;

    private String phone;

    private String password;

    private Integer storeId;

    private String address;

    private RoleEnum role;

    private Date createTime;

    private String storeName;

    private List<String> deliveryAddresses;

    private List<String> deliveryPhoneNumbers;

    public User toPO(){
        User user=new User();
        user.setId(this.id);
        user.setAddress(this.address);
        user.setName(this.name);
        user.setPhone(this.phone);
        user.setRole(this.role);
        user.setStoreId(this.storeId);
        user.setPassword(this.password);
        user.setCreateTime(this.createTime);
        user.setDeliveryAddresses(this.deliveryAddresses);
        user.setDeliveryPhoneNumbers(this.deliveryPhoneNumbers);
        return user;
    }
}
