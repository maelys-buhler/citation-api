/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Client of synchronized HTTP request
 * Date: May 2024
 */

package ch.hearc.mbu.ponctualquote.remote;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.logging.Logger;

@Configuration
public class SimpleQuoteServiceConfiguration {
    final Logger LOGGER = Logger.getLogger(SimpleQuoteServiceConfiguration.class.getName());

    @Autowired
    EurekaClient eurekaClient;

    @Bean
    public RestClient simpleQuoteServiceClient() {
        String simpleQuoteServiceUrl = getSimpleQuoteServiceUrl();
        return RestClient.create(simpleQuoteServiceUrl);
    }

    private String getSimpleQuoteServiceUrl() {
        LOGGER.info("Resolving simple quote service location");

        InstanceInfo simpleQuoteServiceUrl = eurekaClient
                .getApplication("simple-quote")
                .getInstances()
                .get(0);

        String hostName = simpleQuoteServiceUrl.getHostName();
        int port = simpleQuoteServiceUrl.getPort();
        LOGGER.info("Simple quote service, host: " + hostName + ", port: " + port);
        return "http://" + hostName + ":" + port;
    }

}
