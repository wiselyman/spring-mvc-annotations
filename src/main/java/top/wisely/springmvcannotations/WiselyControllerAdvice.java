package top.wisely.springmvcannotations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@ControllerAdvice
public class WiselyControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String defaultExceptionHandler(HttpServletRequest request, Exception exception) {
        return "服务器异常";
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> IOExceptionnHandler(HttpServletRequest request, Exception exception) {
       return new ResponseEntity<String>("服务器IO异常",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @InitBinder
    public void dataBinding(WebDataBinder binder) {

        binder.registerCustomEditor(Dog.class, new DogPropertiesEditor());
    }

}
