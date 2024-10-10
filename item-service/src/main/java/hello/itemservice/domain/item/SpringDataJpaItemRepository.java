package hello.itemservice.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SpringDataJpaItemRepository extends JpaRepository<Item, Long>, ItemRepository {

    @Transactional
    @Modifying
    @Query("UPDATE Item i SET i.itemName = :#{#item.itemName}, i.price = :#{#item.price}, i.quantity = :#{#item.quantity} WHERE i.id = :id")
    void update(@Param("id") Long id, @Param("item") Item item);

}
