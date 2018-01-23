package com.rest.api.marketplace;

import com.rest.api.marketplace.transports.InMemTransport;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarketplaceConfiguration {

    public static final String TRANSPORT_SELECTOR = InMemTransport.NAME;

}
