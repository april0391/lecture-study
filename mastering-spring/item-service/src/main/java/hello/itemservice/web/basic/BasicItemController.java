package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemDto;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepository;

    public BasicItemController(
//            @Qualifier("inMemoryItemRepository") ItemRepository itemRepository
            @Qualifier("springDataJpaItemRepository") ItemRepository itemRepository
    ) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String items(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemRepository.findById(itemId).orElseThrow());
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
//    @ResponseStatus(HttpStatus.CREATED)
    public String add(ItemDto itemDto) {
        Item item = new Item(itemDto.getItemName(), itemDto.getPrice(), itemDto.getQuantity());
        Item saved = itemRepository.save(item);
        return "redirect:/basic/items/" + saved.getId();
    }

    @PostMapping("/add")
    public String addV2(ItemDto itemDto, RedirectAttributes redirectAttributes) {
        Item item = new Item(itemDto.getItemName(), itemDto.getPrice(), itemDto.getQuantity());
        Item saved = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saved.getId());
        redirectAttributes.addFlashAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, ItemDto itemDto) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        item.setItemName(itemDto.getItemName());
        item.setPrice(itemDto.getPrice());
        item.setQuantity(itemDto.getQuantity());
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }



}
