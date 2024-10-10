package hello.itemservice.domain.item;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemRepositoryTest {

    private final ItemRepository itemRepository;
    private final ApplicationContext ac;
    private final EntityManager em;

    @Autowired
    public ItemRepositoryTest(
//            @Qualifier("springDataJpaItemRepository") ItemRepository itemRepository,
            @Qualifier("inMemoryItemRepository") ItemRepository itemRepository,
            ApplicationContext ac,
            EntityManager em) {
        this.itemRepository = itemRepository;
        this.ac = ac;
        this.em = em;
    }

    @BeforeEach
    void beforeEach() {
        itemRepository.clearStore();
    }

    @Test
    void beanTest() {
        Map<String, ItemRepository> beansOfType = ac.getBeansOfType(ItemRepository.class);
        beansOfType.forEach((k, v) -> {
            System.out.print("k = " + k + ", ");
            System.out.println("v = " + v);
        });
        System.out.println(itemRepository.getClass());
    }

    @Test
    @Transactional
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId()).get();

        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    @Transactional
    void findAll() {
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    @Transactional
    void updateItem() {
        //given
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);

        //when
        Long itemId = savedItem.getId();
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);
        em.flush();
        em.clear();

        Item findItem = itemRepository.findById(itemId).get();

        //then
        assertThat(findItem.getId()).isEqualTo(item.getId());
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}