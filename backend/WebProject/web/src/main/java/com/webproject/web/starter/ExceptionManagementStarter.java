package com.webproject.web.starter;
import com.webproject.web.excepiton.BaseException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@EntityScan (basePackages = {"com.webproject.web"})
@EnableJpaRepositories (basePackages = {"com.webproject.web"})
@ComponentScan (basePackages = {"com.webproject.web"})
@SpringBootApplication
public class ExceptionManagementStarter {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> handleBaseException(BaseException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.internalServerError().body("Beklenmeyen hata: " + ex.getMessage());
    }
}
