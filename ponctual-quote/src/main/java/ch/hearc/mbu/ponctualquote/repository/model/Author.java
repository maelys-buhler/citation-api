/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Author Model
 * Date: May 2024
 */

package ch.hearc.mbu.ponctualquote.repository.model;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String name;

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}