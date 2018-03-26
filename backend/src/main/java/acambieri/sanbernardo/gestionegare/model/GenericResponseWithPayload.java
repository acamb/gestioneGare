package acambieri.sanbernardo.gestionegare.model;

public class GenericResponseWithPayload<T> {

    private T payload;

    public GenericResponseWithPayload(){

    }

    public GenericResponseWithPayload(T payload){
        this.payload=payload;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
