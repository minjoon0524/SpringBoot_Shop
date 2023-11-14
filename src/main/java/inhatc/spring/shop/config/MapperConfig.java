package inhatc.spring.shop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class MapperConfig {

    @Bean // 객체를 만들어 쓸 수 있게 하겠다.
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
