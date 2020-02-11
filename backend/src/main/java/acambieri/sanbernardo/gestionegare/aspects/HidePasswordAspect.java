package acambieri.sanbernardo.gestionegare.aspects;

import acambieri.sanbernardo.gestionegare.controllers.responses.AuthResponse;
import acambieri.sanbernardo.gestionegare.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class HidePasswordAspect {

    /**
     *
     * Search for a User in the return value of the target method and if found replace the password with "[PROTECTED]"
     */
    @Around("@annotation(HidePassword)")
    public Object hideUserPassword(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result = joinPoint.proceed();
        User user=null;
        if(result instanceof ResponseEntity){
            Object o = ((ResponseEntity<User>)((ResponseEntity) result)).getBody();
            if(o instanceof AuthResponse){
                user =((AuthResponse) o).getUser();
            }
            else if(o instanceof User){
                user = (User)o;
            }
            else{
                user = null;
            }
        }
        else if(result instanceof List && ((List) result).size() > 0 && ((List) result).get(0) instanceof User){
            ((List<User>)result).forEach(u->u.setPassword("[PROTECTED]"));
        }
        else if(result instanceof User){
            user = (User)result;
        }
        if(user != null){
            user.setPassword("[PROTECTED]");
        }
        return result;
    }
}
