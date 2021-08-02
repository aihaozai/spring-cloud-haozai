package spring.cloud.base.resource.starter.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.lang.reflect.Type;

/**
 * @author haozai
 * @description 序列化接口适配器
 * @date 2021/8/1  14:28
 */
public  class InterfaceAdapter<T> implements JsonDeserializer<T> {

    @Override
    public T deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context) throws JsonParseException {
        return context.deserialize(elem,this.typeForInterfaceType(interfaceType) );
    }

    private Type typeForInterfaceType(Type interfaceType) {
        if(Authentication.class.getName().equals(interfaceType.getTypeName())){
            return UsernamePasswordAuthenticationToken.class;
        }else  if(GrantedAuthority.class.getName().equals(interfaceType.getTypeName())){
            return SimpleGrantedAuthority.class;
        }
        throw new JsonParseException("no '" + interfaceType.getTypeName() + "' class name found ");
    }

}
