/*
 * This file is generated by jOOQ.
 */
package db.jooq.vivino.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Wines implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final String name;
    private final String rate;

    public Wines(Wines value) {
        this.id = value.id;
        this.name = value.name;
        this.rate = value.rate;
    }

    public Wines(
        String id,
        String name,
        String rate
    ) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    /**
     * Getter for <code>vivino.wines.id</code>.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Getter for <code>vivino.wines.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for <code>vivino.wines.rate</code>.
     */
    public String getRate() {
        return this.rate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Wines (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(rate);

        sb.append(")");
        return sb.toString();
    }
}