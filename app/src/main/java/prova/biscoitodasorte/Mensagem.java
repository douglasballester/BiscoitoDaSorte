package prova.biscoitodasorte;

/**
 * Created by Douglas Ballester on 13/11/2017.
 */

public class Mensagem {
    private String autor;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Mensagem(String mensagem, String autor){
        this.msg = mensagem;
        this.autor = autor;
    }
}
