package by.netcracker.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class AbstractEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    protected int id;

    public AbstractEntity() {}

}
