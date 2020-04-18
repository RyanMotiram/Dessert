import entity.Items;
import service.ItemService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class EditItemView implements Serializable {
    private String item;
    private int quantity;
    private String type;

    @EJB
    private ItemService itemService;
    private transient Items itemsToUpdate;

    public void populateView(long itemId) {
        itemsToUpdate = itemService.findById(itemId);
        this.setItem(itemsToUpdate.getItem());
        this.setQuantity(itemsToUpdate.getQuantity());
        this.setType(itemsToUpdate.getType());
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String save() {
        Items createdItem = new Items(item, quantity, type);
        if (itemsToUpdate != null) {
            createdItem.setId(itemsToUpdate.getId());
            itemService.update(createdItem);
        } else {
            itemService.create(createdItem);
        }
        nullifyFields();
        return "/desserts.xhtml?faces-redirect=true";
    }

    private void nullifyFields() {
        itemsToUpdate = null;
        this.setItem(null);
        this.setQuantity(0);
        this.setType(null);
    }


}
