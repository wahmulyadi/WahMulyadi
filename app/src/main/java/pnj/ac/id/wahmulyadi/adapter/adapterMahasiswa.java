package pnj.ac.id.wahmulyadi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pnj.ac.id.wahmulyadi.R;
import pnj.ac.id.wahmulyadi.model.MahasiswaData;

public class adapterMahasiswa extends ArrayAdapter<MahasiswaData> {

    Context context;
    int resource;

    public adapterMahasiswa(@NonNull Context context, int resource) {
        super(context, resource);
        this.resource=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Holder holder;
        if (convertView==null){
            holder = new Holder();
            convertView= LayoutInflater.from(context).inflate(resource,parent, false);
            holder.avatar=convertView.findViewById(R.id.avatar);
            holder.txtnim=convertView.findViewById(R.id.txtnim);
            holder.txtNama=convertView.findViewById(R.id.txtnama);
            holder.txtJurusan=convertView.findViewById(R.id.txtjurusan);
            convertView.setTag(holder);
        }else {
            holder=(Holder)convertView.getTag();
        }
        holder.txtnim.setText(getItem(position).getNim());
        holder.txtJurusan.setText(getItem(position).getJurusan());
        holder.txtNama.setText(getItem(position).getNama());

        return convertView;
    }
    class Holder{
        ImageView avatar;
        TextView txtnim,txtNama,txtJurusan;
    }
}
