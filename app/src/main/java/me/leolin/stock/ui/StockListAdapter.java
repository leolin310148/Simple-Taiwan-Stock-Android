package me.leolin.stock.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import me.leolin.stock.R;
import me.leolin.stock.data.dto.StockDto;

import java.util.List;

/**
 * @author leolin
 */
public class StockListAdapter extends RecyclerView.Adapter<StockListAdapter.ViewHolder> {

    private List<StockDto> stockDtos;

    private StockListAdapter() {

    }

    public StockListAdapter(List<StockDto> stockDtos) {
        this.stockDtos = stockDtos;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_stock_simple, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        StockDto stockDto = stockDtos.get(i);
        viewHolder.getTextViewStockId().setText(stockDto.getId());
        viewHolder.getTextViewStockName().setText(stockDto.getName());
    }

    @Override
    public int getItemCount() {
        return stockDtos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewStockId;
        private TextView textViewStockName;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewStockId = (TextView) itemView.findViewById(R.id.textViewStockId);
            textViewStockName = (TextView) itemView.findViewById(R.id.textViewStockName);
        }

        public TextView getTextViewStockId() {
            return textViewStockId;
        }

        public TextView getTextViewStockName() {
            return textViewStockName;
        }
    }

}
