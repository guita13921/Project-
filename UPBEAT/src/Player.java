import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Player {
    int budget;
    Box citycenter;
    Box location;
    HashSet<Mapp>Own = new HashSet<>();
    public void puty(Mapp x){
        Own.add(x);
    }

    public static void main(String[] args) {
        Player p1 = new Player();
        Mapp x = null;
        p1.puty(x);
        System.out.println(p1.Own.isEmpty());
    }
}
