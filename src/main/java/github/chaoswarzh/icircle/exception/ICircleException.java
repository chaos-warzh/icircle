package github.chaoswarzh.icircle.exception;

public class ICircleException extends RuntimeException {

    public ICircleException(String message) {
        super(message);
    }

    public static ICircleException phoneAlreadyExists() {
        return new ICircleException("手机号已经存在!");
    }

    public static ICircleException notLogin() {
        return new ICircleException("未登录!");
    }

    public static ICircleException phoneOrPasswordError() {
        return new ICircleException("手机号或密码错误!");
    }

    public static ICircleException fileUploadFail() {
        return new ICircleException("文件上传失败!");
    }

    public static ICircleException nameAlreadyExists() {
        return new ICircleException("名称已经存在!");
    }

    public static ICircleException userNotExists() {
        return new ICircleException("用户不存在!");
    }

    public static ICircleException storeNotExists() {
        return new ICircleException("店铺不存在!");
    }

    public static ICircleException productNotExists() {
        return new ICircleException("商品不存在!");
    }

    public static ICircleException stockNotEnough() {
        return new ICircleException("商品库存不足!");
    }

    public static ICircleException orderNotExists() {
        return new ICircleException("订单不存在!");
    }

    public static ICircleException commentNotExists() {
        return new ICircleException("评论不存在!");
    }

    public static ICircleException orderStatusError() {
        return new ICircleException("当前订单状态不允许此操作!");
    }

    public static ICircleException illegalScore() {
        return new ICircleException("评分不合法!");
    }

    public static ICircleException illegalSortedKey() {
        return new ICircleException("排序关键字不合法!");
    }

    public static ICircleException couponNotMatch() {
        return new ICircleException("优惠券不满足使用条件!");
    }

    public static ICircleException wrongUser() {
        return new ICircleException("恶意篡改了请求用户身份!");
    }

    public static ICircleException couponAlreadyClaimed() {
        return new ICircleException("优惠券已经领取过了!");
    }

    public static ICircleException couponNotAvailable() {
        return new ICircleException("优惠券未到可领取时间或已经过期!");
    }

    public static ICircleException couponNotEnough() {
        return new ICircleException("已经领完了!");
    }

    public static ICircleException couponManagerNotExists() {
        return new ICircleException("优惠券组不存在!");
    }

    public static ICircleException couponError() {
        return new ICircleException("优惠券系统出错!");
    }

    public static ICircleException invalidCoupon() {
        return new ICircleException("优惠券不可用!");
    }

    public static ICircleException noPrivilege() {
        return new ICircleException("没有执行此操作的权限!");
    }

    public static ICircleException outputError() {
        return new ICircleException("输出流错误!");
    }

    public static ICircleException invalidUserInformation() {
        return new ICircleException("用户信息不合法");
    }
}
