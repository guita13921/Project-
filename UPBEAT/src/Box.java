public class Box {
    protected final int row;
    protected final int column;
    protected long deposit = 0;
    Player owner = null;



    protected Box(int row,int column){
        this.row = row;
        this.column = column;
    }

    protected void ChangeDeposit(long input,Player Player){
        deposit = deposit + input;
        if(deposit == 0){
            this.owner = null;
        }else if(deposit >0){
            return;
        }else if(deposit < 0 ){
            ChangeOwner(Player);
            this.deposit = Math.abs(deposit);
        }
    }
    private void ChangeOwner(Player Player){//มันน่าจะไม่ต้องรับมั้ง
        this.owner = Player;//อันนีอยากให้เปลี่ยนเป็นคนที่ลงทุน //หาวิธีเปลี่ยนเจ้าของ
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

