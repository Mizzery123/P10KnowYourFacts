package sg.edu.rp.c346.p10knowyourfacts;

import android.graphics.Color;
import android.os.Bundle;



import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag1 extends Fragment {

    Button btnColor;
    ImageView iv, iv2, iv3;
    LinearLayout linearLayout1;
    Integer i = 0;

    public Frag1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frag1, container, false);

        btnColor = view.findViewById(R.id.btnColor);
        linearLayout1 = view.findViewById(R.id.linearlayout1);
        iv = view.findViewById(R.id.iv);
        iv2 = view.findViewById(R.id.iv2);
        iv3 = view.findViewById(R.id.iv3);

        String imageURL = "https://wtffunfact.com/wp-content/uploads/2019/07/5600-Year-Old-Fake-Islands.png";

        String imageURL2 = "https://wtffunfact.com/wp-content/uploads/2019/07/wtf-fun-fact-human-composting.png";

        String imageURL3 = "https://wtffunfact.com/wp-content/uploads/2019/07/wtf-fun-fact-boosting-banned.png";

        Picasso.with(getActivity()).load(imageURL).into(iv);
        Picasso.with(getActivity()).load(imageURL2).into(iv2);
        Picasso.with(getActivity()).load(imageURL3).into(iv3);

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( i == 0){
                    linearLayout1.setBackgroundColor(Color.GRAY);
                    i = 1;
                } else if ( i == 1) {

                    linearLayout1.setBackgroundColor(Color.GREEN);
                    i = 2;
                } else if ( i == 2){
                    linearLayout1.setBackgroundColor(Color.CYAN);
                    i = 3;
                } else {
                    linearLayout1.setBackgroundColor(Color.RED);
                    i = 0;
                }
            }
        });

        return view;

    }


}
