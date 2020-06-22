/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde;

import mfklcp.monografia.thejugde.Configuration.Properties.ProvidersProperties;
import mfklcp.monografia.thejugde.Configuration.Properties.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(
        {
            ProvidersProperties.class,
            StorageProperties.class
        }
)
public class TheJugdeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheJugdeApplication.class, args);
    }
}
