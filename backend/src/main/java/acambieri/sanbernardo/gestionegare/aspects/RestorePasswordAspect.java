package acambieri.sanbernardo.gestionegare.aspects;

import acambieri.sanbernardo.gestionegare.model.User;
import acambieri.sanbernardo.gestionegare.repositories.UserRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RestorePasswordAspect {

    @Autowired
    private UserRepository userRepository;

    /**
     * Restore the password of the user passed as argument to the target method from "[PROTECTED]" to the real
     * password before executing the target method
     */
    @Before("@annotation(RestorePassword)")
    public void restorePassword(ProceedingJoinPoint joinPoint){
        User user = (User)joinPoint.getArgs()[0];
        user.setPassword(userRepository.findByUsername(user.getUsername()).getPassword());
    }
}
