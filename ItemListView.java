import entity.Items;
import service.ItemService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ItemListView {
    private List<Items> items;

    @EJB
    private ItemService itemService;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
        items.addAll(itemService.getAll());
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public String deleteItem(long id) {
        itemService.delete(itemService.findById(id));
        return "/desserts.xhtml?faces-redirect=true";
    }


    public String redirectToEditItems() {
        return "/editdessert.xhtml?faces-redirect=true";
    }

}

