package com.vaibhav.vbadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: " + R.layout.inner_layout);
        SampleModel model = new SampleModel();
        model.name = "vaibhav";
        model.desc = "bhoom";

        SampleModel model1 = new SampleModel();
        model1.name = "Jia";
        model1.desc = "dhoom";

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<SampleModel> list = new ArrayList<SampleModel>();
        list.add(model);
        list.add(model1);

        EasyAdapter<SampleModel, ViewHolder> adapter = new EasyAdapter<SampleModel, ViewHolder>(this, SampleModel.class, ViewHolder.class);
        recyclerView.setAdapter(adapter);
        adapter.setList(list);

    }


}

@SerializedViews(resourceId = R.layout.inner_layout)
class ViewHolder extends RecyclerView.ViewHolder {

    @EasyField(mappingId = "name")
    public TextView title;

    public ViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
    }
}
