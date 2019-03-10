package com.roche.vnv.cocktail;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;
    private final String name;
    private final String message;

    public User() {
        id = 0;
        name = "";
        message = "";
    }

    public User(long id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /** hasCode()
     Este método viene a complementar al método equals y sirve para comparar objetos de una forma más rápida en estructuras Hash ya que
     únicamente nos devuelve un número entero. Cuando Java compara dos objetos en estructuras de tipo hash
     primero invoca al método hashcode y luego el equals. Si los métodos hashcode de cada objeto devuelven diferente
     hash no seguirá comparando y considerará a los objetos distintos. En el caso en el que ambos objetos compartan el
     mismo hashcode Java invocará al método equals() y revisará a detalle si se cumple la igualdad. De esta forma las
     búsquedas quedan simplificadas en estructuras hash.
     **/

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
