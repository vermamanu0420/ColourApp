package com.example.colourloversapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.colourloversapp.R;
import com.example.colourloversapp.viewmodel.ColourListViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.colourGridView)
    RecyclerView recyclerView;

    @BindView(R.id.errorMessage)
    TextView errorMessage;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    @BindView(R.id.searchButton)
    Button searchButton;

    @BindView(R.id.searchTextview)
    TextView searchTerm;


    private ColourListViewModel viewModel;

    ColourRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ColourListViewModel.class);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new ColourRecyclerViewAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        observerViewModel();

        searchButton.setOnClickListener(view -> viewModel.fetchColour(searchTerm.getText().toString(), "json", 20 ));

        searchTerm.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                searchButton.performClick();
            }
            return false;
        });

    }

    private void observerViewModel() {
        viewModel.mutableColourList.observe(this, items -> {
            errorMessage.setVisibility(View.GONE);
            adapter.updateColours(items);
            loadingView.setVisibility(View.GONE);

            if (items == null || items.size() == 0) {
                errorMessage.setVisibility(View.VISIBLE);
                errorMessage.setText(R.string.noDataFoundMsg);
            }
        });

        viewModel.ColourLoadError.observe(this, isError -> {
            if (isError != null) {
                errorMessage.setVisibility(isError ? View.VISIBLE : View.GONE);
                errorMessage.setText(R.string.loadingDataErrorMsg);
            }

        });

        viewModel.loading.observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });
    }
}