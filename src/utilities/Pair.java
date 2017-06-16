package utilities;

public class Pair{
    private int i;
    private int j;

    public Pair(int l, int r){
        this.i = l;
        this.j = r;
    }

    public int geti(){ return i; }

    public int getj(){ return j; }

    public void seti(int l){ this.i = l; }

    public void setj(int r){ this.j = r; }
}
