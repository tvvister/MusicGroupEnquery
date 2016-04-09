package com.maintwister.musicgroupfile;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maintwister.musicgroupfile.app.ViewModelActivity;
import com.maintwister.musicgroupfile.databinding.ActivitySingerListBinding;
import com.maintwister.musicgroupfile.model.ApplicationViewModel;
import com.maintwister.musicgroupfile.model.ICallback;
import com.maintwister.musicgroupfile.model.SingerInfoViewModel;

public class SingerListActivity extends ViewModelActivity<ApplicationViewModel> {

    public static final String CONNECTION_FAILED_DIALOG = "ConnectionFailedDialog";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        final ApplicationViewModel viewModel = getViewModel();
        ActivitySingerListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_singer_list);
        binding.setApplicationViewModel(viewModel);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        final RecyclerView recView = (RecyclerView) findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));

        loadSingers(viewModel, recView);
    }



    private ConnectionFailedDialog GetFailedDialog(final ApplicationViewModel viewModel, final RecyclerView recView) {
        ConnectionFailedDialog dialog = (ConnectionFailedDialog) getFragmentManager().findFragmentByTag(CONNECTION_FAILED_DIALOG);
        dialog = dialog == null ? new ConnectionFailedDialog() : dialog;
        dialog.setReplyCommand(new ICallback() {
            @Override
            public void handle(Object o) {
                loadSingers(viewModel, recView);
            }
        });
        return dialog;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        ConnectionFailedDialog dialog = (ConnectionFailedDialog) getFragmentManager().findFragmentByTag(CONNECTION_FAILED_DIALOG);
        if(dialog != null) {
            dialog.setReplyCommand(null);
            dialog.dismiss();}
        super.onSaveInstanceState(savedInstanceState);
    }

    private void loadSingers(final ApplicationViewModel viewModel, final RecyclerView recView) {

        viewModel.getSingerInfoViewModels(new ICallback<SingerInfoViewModel[]>() {
            @Override
            public void handle(SingerInfoViewModel[] singerInfoViewModels) {

                if (!viewModel.hasListLoadingError.get()) {
                    SingersRecyclerViewAdapter adapter = new SingersRecyclerViewAdapter(singerInfoViewModels,
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(SingerListActivity.this, SingerCardActivity.class);
                                    Bundle bundle = null;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                        ActivityOptions activityOptions = ActivityOptions.makeScaleUpAnimation(recView, v.getLeft(), v.getTop(), v.getWidth(),  v.getHeight());
                                        bundle = activityOptions.toBundle();
                                        SingerListActivity.this.startActivity(intent, bundle);
                                    } else SingerListActivity.this.startActivity(intent);
                                }
                            },
                            viewModel.selectedSingerInfoViewModel
                    );
                    recView.setAdapter(adapter);
                }
                else {
                    ConnectionFailedDialog dialog = GetFailedDialog(viewModel, recView);
                    if (dialog.isAdded()) dialog.dismiss();
                    dialog.show(getFragmentManager(), CONNECTION_FAILED_DIALOG);
                }
            }
        });
    }
}
