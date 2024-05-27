/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Main Function
 * Date: May 2024
 */

package ch.hearc.mbu.ponctualquote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PonctualQuoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PonctualQuoteApplication.class, args);
    }

}
