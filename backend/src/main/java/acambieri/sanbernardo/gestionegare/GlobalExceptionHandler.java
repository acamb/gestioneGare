package acambieri.sanbernardo.gestionegare;


import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author andrea AC
 *         Date: 12/08/2017
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleConflict(HttpServletRequest req, Exception e) {
        LoggerFactory.getLogger(getClass()).error(Utils.exceptionToString(e));
        ResponseEntity<Object> response;
        if(e instanceof BadCredentialsException){
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else {
            response = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    
}
