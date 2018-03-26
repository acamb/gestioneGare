package acambieri.sanbernardo.gestionegare;


import acambieri.sanbernardo.gestionegare.dao.GareDao;
import acambieri.sanbernardo.gestionegare.dao.UpdateDao;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author andrea AC
 *         Date: 12/08/2017
 */
@ControllerAdvice
@Scope("request")
public class GlobalExceptionHandler {
    
    @Autowired
    private GareDao gareDao;

    @Autowired
    private UpdateDao updateDao;
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleConflict(HttpServletRequest req, Exception e) {
        gareDao.rollback();
        updateDao.rollback();
        LoggerFactory.getLogger(getClass()).error(Utils.exceptionToString(e));
        ResponseEntity<Object> response = new ResponseEntity<Object>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
    
    
}
