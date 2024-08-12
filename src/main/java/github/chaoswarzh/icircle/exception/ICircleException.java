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

    public static ICircleException circleNotExists() {
        return new ICircleException("兴趣圈不存在!");
    }

    public static ICircleException postNotExists() {
        return new ICircleException("帖子不存在!");
    }

    public static ICircleException commentNotExists() {
        return new ICircleException("评论不存在!");
    }

    public static ICircleException illegalSortedKey() {
        return new ICircleException("排序关键字不合法!");
    }

    public static ICircleException noPrivilege() {
        return new ICircleException("没有执行此操作的权限!");
    }

    public static ICircleException outputError() {
        return new ICircleException("输出流错误!");
    }
}
