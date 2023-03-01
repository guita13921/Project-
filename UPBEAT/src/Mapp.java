import java.util.ArrayList;

public class Mapp {
  protected int Budget = 0;
  protected int m;
  protected int n;
  protected
  ArrayList<Box> map = new ArrayList<>();

  public void MappArea(int n, int m) {
    this.n = n;
    this.m = m;
  }

  public void GenerateMap() {
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        map.add(new Box(n, m));

      }
    }
  }
  }