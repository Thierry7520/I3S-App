package com.example.i3sapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SeePlanningActivity extends AppCompatActivity {

    ListView listViewPlannings;

    DatabaseReference databasePlanning;

    List<Planning> planningList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_planning);

        planningList = new ArrayList<>();

        databasePlanning = FirebaseDatabase.getInstance().getReference();

        listViewPlannings = (ListView) findViewById(R.id.listViewPlannings);

        listViewPlannings.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Planning planning = databasePlanning.get(i);


                return false;
            }
        });

    }

    private void showUpdateDialog(String planningId, String planningGenre, String planningPostDate, String planningChannel,
                                  String planningType, String planningDescription, String palnningSponsoring, String planningHachtags)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SeePlanningActivity.this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);

        final Spinner spinnerGenres = (Spinner) dialogView.findViewById(R.id.spinnerGenres);
        final TextView editTextPostDate= (TextView) findViewById(R.id.modify_Post_Date);
        final TextView editTextChannel = (TextView) findViewById(R.id.modify_Channel);
        final TextView editTextType = (TextView) findViewById(R.id.modify_Type);
        final TextView editTextDescription = (TextView) findViewById(R.id.modify_Description);
        final TextView editTextSponsoring = (TextView) findViewById(R.id.modify_Sponsoring);
        final TextView editTextHachtags = (TextView) findViewById(R.id.modify_Hachtags);
        final Button buttonUpdate = (Button) findViewById(R.id.ButtonUpdate);

        dialogBuilder.setTitle("Updating Planning" + planningId);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


    }

    @Override
    protected void onStart() {
        super.onStart();

        databasePlanning.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot)
            {
                planningList.clear();

                for (DataSnapshot planningSnapshot: datasnapshot.getChildren())
                {
                    Planning planning = planningSnapshot.getValue(Planning.class);

                    planningList.add(planning);
                }

                PlanningList adapter = new PlanningList(SeePlanningActivity.this, planningList);
                listViewPlannings.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

    }
}