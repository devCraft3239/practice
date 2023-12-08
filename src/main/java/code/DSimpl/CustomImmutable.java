package code.DSimpl;

import java.io.Serializable;

class Adreess{
    private String street;
    private String city;
    private String state;
    private String country;

    public Adreess(String street, String city, String state, String country){
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getStreet(){
        return this.street;
    }

    public String getCity(){
        return this.city;
    }

    public String getState(){
        return this.state;
    }

    public String getCountry(){
        return this.country;
    }
}
public final class CustomImmutable implements Cloneable, Serializable {
    private final int id;
    private final String name;
    private final Adreess adreess;

    public CustomImmutable(int id, String name, Adreess adreess){
        this.id = id;
        this.name = name;
        if (adreess == null)
            this.adreess = null;
        else
            this.adreess = new Adreess(adreess.getStreet(), adreess.getCity(), adreess.getState(), adreess.getCountry());
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Adreess getAdreess(){
        // return copy of adreess
        return new Adreess(this.adreess.getStreet(), this.adreess.getCity(), this.adreess.getState(), this.adreess.getCountry());
    }

    @Override
    public Object clone(){
        return new IllegalAccessError("Clone not supported");
    }

    public Object readResolve(){
        return this;
    }
}
