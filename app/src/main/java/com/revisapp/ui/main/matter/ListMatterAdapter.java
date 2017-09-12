package com.revisapp.ui.main.matter;

import android.curso.revisapp.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.revisapp.domain.Matter;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by juan_ on 06/09/2017.
 */

public class ListMatterAdapter extends RecyclerView.Adapter<ListMatterAdapter.ViewHolderMatter> {
    private List<Matter> matters;
    private OnClickMatterListener listener;

    public ListMatterAdapter(List<Matter> matters, OnClickMatterListener listener) {
        this.matters = matters;
        this.listener = listener;
    }

    @Override
    public ViewHolderMatter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_matter, parent, false);
        ViewHolderMatter holder = new ViewHolderMatter(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderMatter holder, int position) {
        Matter item  = matters.get(position) ;

        Calendar init_schedule = Calendar.getInstance();
        init_schedule.setTimeZone(TimeZone.getDefault());
        init_schedule.setTimeInMillis(item.getInit());
        Calendar end_schedule = Calendar.getInstance();
        end_schedule.setTimeZone(TimeZone.getDefault());
        end_schedule.setTimeInMillis(item.getEnd());

        holder.name.setText(item.getName());

        holder.init.setText(String.format("%02d:%02d",
                init_schedule.get(Calendar.HOUR_OF_DAY),
                init_schedule.get(Calendar.MINUTE)));
        holder.end.setText(String.format("%02d:%02d",
                end_schedule.get(Calendar.HOUR_OF_DAY),
                end_schedule.get(Calendar.MINUTE)));

    }

    public void insertMatter(Matter matter){
        matters.add(matter);
        notifyItemInserted(matters.size() - 1);
    }


    public void removeMatter(Matter matter){
        int index = matters.indexOf(matter);
        matters.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return matters.size();
    }

    public void updateMatter(Matter oldMatter, Matter newMatter) {
        int index = matters.indexOf(oldMatter);
        if(index > 0){
            matters.set(index, newMatter);
            notifyItemChanged(index);
        }
    }

    public class ViewHolderMatter extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView init;
        private TextView end;
        private TextView name;

        public ViewHolderMatter(View view) {
            super(view);
            init = (TextView) view.findViewById(R.id.init_field);
            end = (TextView) view.findViewById(R.id.end_field);
            name = (TextView) view.findViewById(R.id.name_field);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Matter matter = matters.get(position);
                listener.onUpdateMatter(matter);
            }
        }
    }
}
