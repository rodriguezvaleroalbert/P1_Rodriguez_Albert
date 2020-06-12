/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cat.copernic.rodriguez.albert;

import android.content.res.TypedArray;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Mountain> mMountainsData;
    private MountainsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMountainsData = new ArrayList<>();
        mAdapter = new MountainsAdapter(this, mMountainsData);
        mRecyclerView.setAdapter(mAdapter);
        //obtenir les dades
        initializeData();
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | 
                    ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mMountainsData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                mMountainsData.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }

    private void initializeData() {
        String[] mountainsList = getResources()
                .getStringArray(R.array.mountains_titles);
        String[] mountainsInfo = getResources()
                .getStringArray(R.array.mountains_info);
        TypedArray mountainsImageResources = getResources()
                .obtainTypedArray(R.array.mountains_images);
        mMountainsData.clear();
        for (int i = 0; i < mountainsList.length; i++) {
            mMountainsData.add(new Mountain(mountainsList[i], mountainsInfo[i],
                    mountainsImageResources.getResourceId(i, 0)));
        }
        mountainsImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }

    //Codi per fer el reset de les dades
    public void resetMountains(View view) {
        initializeData();
    }
}
