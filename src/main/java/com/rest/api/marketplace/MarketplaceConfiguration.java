package com.rest.api.marketplace;

import com.rest.api.marketplace.daos.MarketplaceDaoRepository;
import com.rest.api.marketplace.daos.ProjectDao;
import com.rest.api.marketplace.models.*;
import com.rest.api.marketplace.transports.InMemTransport;
import com.rest.api.marketplace.transports.MongoTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarketplaceConfiguration {

    @Bean
    public MongoTransport mongoTransport(){
        return new MongoTransport();
    }

    @Bean
    public InMemTransport inMemTransport(){
        return new InMemTransport();
    }

    @Bean
    public MarketplaceDaoRepository daoRepository() {
        MarketplaceDaoRepository daoRepositoryObj = new MarketplaceDaoRepository();
        daoRepositoryObj.register(Project.class, ProjectDao.class);
//        daoRepositoryObj.register(Bid.class);
//        daoRepositoryObj.register(Buyer.class);
//        daoRepositoryObj.register(Project.class);
//        daoRepositoryObj.register(Seller.class);
//        // future release item
//        daoRepositoryObj.register(Review.class);
        return daoRepositoryObj;
    }

    public static final String TRANSPORT_SELECTOR = InMemTransport.NAME;


}
