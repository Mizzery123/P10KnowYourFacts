package sg.edu.rp.c346.p10knowyourfacts;

import android.graphics.Color;
import android.os.Bundle;



import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag2 extends Fragment {

    Button btnColor2;
    LinearLayout linearLayout2;

    Integer i = 0;


    public Frag2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag2, container, false);

        btnColor2 = view.findViewById(R.id.btnColor2);
        linearLayout2 = view.findViewById(R.id.linearlayout2);

        btnColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( i == 0){
                    linearLayout2.setBackgroundColor(Color.YELLOW);
                    i = 1;
                } else {

                    linearLayout2.setBackgroundColor(Color.MAGENTA);
                    i = 0;
                }

            }
        });

        return view;
    }

}