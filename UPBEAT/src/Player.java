import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Player {
    int budget;
    Box citycenter;
    Box location;
    protected Mapp oneone = new Mapp();

    HashSet<Mapp>Own = new HashSet<>();
    private void puty(Mapp x){
        Own.add(x);
    }
    public void Invest(int invest){
      oneone.Budget = oneone.Budget+invest;
        if(oneone.Budget>0){
            Own.add(oneone);
        }else if(oneone.Budget < 0){
            Own.remove(oneone);
        }
    }

    public static void main(String[] args) {
        Player p1 = new Player();
        Mapp x = null;
        p1.puty(x);
        System.out.println(p1.Own.isEmpty());
        p1.Invest(1);
        System.out.println(p1.Own);
    }
}
