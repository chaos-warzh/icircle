package github.chaoswarzh.icircle.exception;

import github.chaoswarzh.icircle.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * exception msg -> ResultVO
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ICircleException.class)
    public ResultVO<String> handleAIExternalException(ICircleException e) {
        e.printStackTrace();
        return ResultVO.buildFailure(e.getMessage());
    }
}
