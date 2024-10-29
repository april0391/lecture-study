package hello.itemservice.config;

import hello.itemservice.repository.v2.ItemQueryRepositoryV2;
import hello.itemservice.repository.v2.ItemRepositoryV2;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV2_kim;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Configuration
public class TradeoffConfig {

    private final EntityManager em;
    private final ItemRepositoryV2 repository;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV2_kim(repository, query());
    }

    @Bean
    public ItemQueryRepositoryV2 query() {
        return new ItemQueryRepositoryV2(em);
    }

}
