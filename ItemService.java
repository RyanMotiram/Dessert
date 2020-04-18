package service;


import entity.Items;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@Stateless
public class ItemService {
    @PersistenceContext(unitName = "itemPersistenceUnit")
    private EntityManager manager;

    public Collection<? extends entity.Items> getAll() {
        return manager.createNamedQuery("findAllItems", Items.class).getResultList();
    }


    public Items findById(long id) {
        return manager.find(Items.class, id);
    }

    @Transactional
    public void update(Items item) {
        manager.getTransaction().begin();
        manager.merge(item);
        manager.getTransaction().commit();
    }

    @Transactional
    public void create(Items item) {
        manager.getTransaction().begin();
        manager.persist(item);
        manager.getTransaction().commit();
    }

    @Transactional
    public void delete(Items item) {
        manager.getTransaction().begin();
        if (!manager.contains(item)) {
            item = manager.merge(item);
        }

        manager.remove(item);
        manager.getTransaction().commit();
    }


}
