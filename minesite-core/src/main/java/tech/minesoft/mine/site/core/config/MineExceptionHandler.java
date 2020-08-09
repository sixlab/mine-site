package tech.minesoft.mine.site.core.config;

import tech.minesoft.mine.site.core.utils.Err;
import tech.minesoft.mine.site.core.utils.WebUtils;
import tech.minesoft.mine.site.core.vo.MineException;
import tech.minesoft.mine.site.core.vo.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

@Slf4j
@ControllerAdvice
public class MineExceptionHandler {

    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestPartException.class,
            MissingServletRequestParameterException.class,
            MultipartException.class
    })
    public ModelAndView handleParamException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error("参数异常", e);

        return errorMsg(request, response, ResultJson.error(Err.EXCEPTION, "exception.parse"));
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ModelAndView handleParamSupportException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error("参数类型不支持异常", e);

        return errorMsg(request, response, ResultJson.error(Err.EXCEPTION, "exception.type.not.support"));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ModelAndView handlerAccessDeniedException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error("AccessDeniedException", e);

        ResultJson result = ResultJson.error(Err.AUTH, "auth.access.denied");

        return errorMsg(request, response, result);
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error("ExceptionHandler", e);

        MineException mineException = getException(e);

        ResultJson result;
        if (null != mineException) {
            result = ResultJson.error(mineException.getCode(), mineException.getMsg());
        } else {
            result = ResultJson.error(Err.EXCEPTION, "exception.null");
        }

        return errorMsg(request, response, result);
    }

    private MineException getException(Exception e) {
        MineException mineException = null;

        if (e instanceof InvocationTargetException) {
            InvocationTargetException ee = ((InvocationTargetException) e);
            Throwable exception = ee.getTargetException();

            if (exception instanceof MineException) {
                mineException = ((MineException) exception);
            } else {
                mineException = MineException.error(Err.EXCEPTION, "Unknown：" + exception.getClass().getName());
            }
        } else if (e instanceof MineException) {
            mineException = ((MineException) e);
        } else {
            mineException = MineException.error(Err.EXCEPTION, "Unknown：" + e.getClass().getName());
        }

        return mineException;
    }

    private ModelAndView errorMsg(HttpServletRequest request, HttpServletResponse response, ResultJson result) {
        String method = request.getMethod();

        if ("get".equalsIgnoreCase(method)) {
            ModelAndView modelAndView = new ModelAndView("error");

            modelAndView.addObject("status", result.getStatus());
            modelAndView.addObject("message", result.getMessage());

            return modelAndView;
        } else {
            WebUtils.writeJson(response, result.toString());
            return null;
        }
    }
}
