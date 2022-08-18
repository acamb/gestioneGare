package acambieri.sanbernardo.gestionegare.mappers;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

public interface IMapper<T,K>{

    default void toDto(T source,K destination){
        copyFields(source,destination);
    }

    default void toEntity(K source,T destination){
        copyFields(source,destination);
    }

    default void copyFields(Object source,Object dest){
        PropertyAccessor sourceAccessor = PropertyAccessorFactory.forBeanPropertyAccess(source);
        PropertyAccessor destinationAccessor = PropertyAccessorFactory.forBeanPropertyAccess(dest);
        for(Field f : source.getClass().getDeclaredFields()){
            if(Arrays.stream(f.getDeclaredAnnotations()).map(Annotation::annotationType).anyMatch(a-> a.equals(Mapped.class))){
                destinationAccessor.setPropertyValue(f.getName(),sourceAccessor.getPropertyValue(f.getName()));
            }
        }
    }

}
