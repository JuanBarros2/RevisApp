package com.revisapp.schedule;

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

public class MatterAdapter extends RecyclerView.Adapter<MatterAdapter.ViewHolderMatter> {
    List<Matter> matters;

    public MatterAdapter(List<Matter> matters) {
        this.matters = matters;
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

    @Override
    public int getItemCount() {
        return matters.size();
    }

    public class ViewHolderMatter extends RecyclerView.ViewHolder {

        private TextView init;
        private TextView end;
        private TextView name;

        public ViewHolderMatter(View view) {
            super(view);
            init = (TextView) view.findViewById(R.id.init_field);
            end = (TextView) view.findViewById(R.id.end_field);
            name = (TextView) view.findViewById(R.id.name_field);
        }
    }
}
