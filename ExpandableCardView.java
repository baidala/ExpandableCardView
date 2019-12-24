package co.smartfarm.smartscale.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import co.smartfarm.smartscale.R;

/**
 * https://stackoverflow.com/questions/52560400/custom-expandable-card-with-child-views
 * */

public class ExpandableCardView extends CardView {

    private Context context;

    public ExpandableCardView(@NonNull Context context) {
        this(context, null);
    }

    public ExpandableCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        // Get control after layout is complete.
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Remove listener so it won't be called again
                getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // Get the view we want to insert into the LinearLayout called "card_body" and
                // remove it from the custom CardView.
                /*View childView = getChildAt(0);*/
                removeAllViews();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.expandable_card_view, ExpandableCardView.this, true);

                // Insert the view into the LinearLayout.
                /*((LinearLayout) findViewById(R.id.card_body)).addView(childView);*/

                // And the rest of the stuff...
                TextView headingTextView = findViewById(R.id.card_heading);
                headingTextView.setText("THE HEADING");

                // set collapse/expand click listener
//                ImageView collapseExpandButton = findViewById(R.id.collapse_expand_card_button);
//                collapseExpandButton.setOnClickListener((View v) -> toggleCardBodyVisibility());
            }
        });
    }


    public void toggleCardBodyVisibility() {
        LinearLayout description = findViewById(R.id.card_body);
        ImageView imageButton = findViewById(R.id.collapse_expand_card_button);
        TextView headingTextView = findViewById(R.id.card_heading);

        if (description.getVisibility() == View.GONE) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View cardBodyView = inflater.inflate(R.layout.expanded_layout, ExpandableCardView.this, false);
            // Insert the view into the LinearLayout.
            ((LinearLayout) findViewById(R.id.card_body)).addView(cardBodyView);
            headingTextView.setVisibility(View.GONE);
            description.setVisibility(View.VISIBLE);
            imageButton.setImageResource(R.drawable.ic_arrow_up);
        } else {
            description.setVisibility(View.GONE);
            description.removeAllViews();
            imageButton.setImageResource(R.drawable.ic_arrow_down);
            headingTextView.setVisibility(View.VISIBLE);
        }
    }



}
