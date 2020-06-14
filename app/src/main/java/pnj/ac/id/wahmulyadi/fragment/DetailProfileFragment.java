package pnj.ac.id.wahmulyadi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pnj.ac.id.wahmulyadi.R;

public class DetailProfileFragment extends Fragment {

    TextView txtNim,txtNama,txtJurusan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_layout,container,false);
//        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtNim=view.findViewById(R.id.Nim);
        txtNama =view.findViewById(R.id.Nama);
        txtJurusan= view.findViewById(R.id.Jurusan);
        Bundle data= getArguments();

        txtNim.setText(data.getString("nim",""));
        txtNama.setText(data.getString("nama",""));
        txtJurusan.setText(data.getString("jurusan",""));



    }
}
