package SahafManagement.Component;

import SahafManagement.Component.LogAspect;
import SahafManagement.Repository.ILogRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
LogAspect sınıfının, @RestController ile açıklama eklenmiş sınıflara yapılan yöntem çağrılarını engelleryerek
 request ve responselar ile ilgili bilgileri günlüğe kaydeden bir tavsiyesi vardır.
 */

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
    @Bean
    public LogAspect logAspect(ILogRepository iLogRepository) {
        return new LogAspect(iLogRepository);
    }
}
