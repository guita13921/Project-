public class Box {
    private final int row;
    private final int column;
    private long deposit = 0;
    Player owner = null;


    protected Box(int row,int column){
        this.row = row;
        this.column = column;
    }

    protected void ChangeDeposit(long input){
        deposit = deposit + input;
    }
    protected void ChangeOwner(Player player){
        owner = player;
    }

    protected int box_row_show(){
        return row;
    }

    protected int box_column_show(){
        return column;
    }

    protected long box_deposit_show(){
        return deposit;
    }
}

