import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class Player {
    int budget;
    Box citycenter;

    String name;
    static Citycrew man;
    Mapp mapP;



  public Player(String name,Mapp mapP){
      this.name = name;
      this.mapP = mapP;
      this.citycenter = mapP.getBox(mapP,1,1);
      man = new Citycrew(mapP,1, 1);
  }
    static Box box1 = new Box(1, 1);

    static LinkedList<Box>OwnBox = new LinkedList<>();
    protected void getBox(){
        if(box1.owner == this){
            OwnBox.add(box1);
        }
    }


    public static void main(String[] args) {
        Mapp demo = new Mapp(5,5);
            Player p1 = new Player("hum",demo);
            box1.ChangeDeposit(23,p1);
            System.out.println(box1.owner.name);
            System.out.println(box1.deposit);
            p1.getBox();

            System.out.println(OwnBox.size());
            System.out.println(man.up == demo.getBox(demo,1,1));

        }
    }

















//    protected Mapp oneone = new Mapp();
//
//    LinkedList<Mapp>Own = new LinkedList<>();
//    public void puty(Mapp x){
//        Own.add(x);
//    }
//    public void Invest(int invest){
//        oneone.Budget = oneone.Budget+invest;
//        if(oneone.Budget>0){
//            String x =oneone.toString();
//            Own.add(oneone);
//        }else if(oneone.Budget < 0){
//            Own.remove(oneone);
//        }
//    }
//
//    public static void main(String[] args) {
//        Player p1 = new Player();
//        Mapp x = null;
//        p1.puty(x);
//        System.out.println(p1.Own.isEmpty());
//        p1.Invest(1);
//        System.out.println(p1.Own.element());
//        System.out.println(p1.Own.peek());
//    }
//}
