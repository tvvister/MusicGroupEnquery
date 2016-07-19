package com.maintwister.musicgroupfile;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maintwister.musicgroupfile.app.ViewModelFragment;
import com.maintwister.musicgroupfile.databinding.FragmentSingerListBinding;
import com.maintwister.musicgroupfile.model.ApplicationViewModel;
import com.maintwister.musicgroupfile.model.ICallback;
import com.maintwister.musicgroupfile.model.SingerInfoViewModel;

/**
 * Created by Andrey on 19.07.2016.
 */
public class SingerListFragment extends ViewModelFragment<ApplicationViewModel> {
    public static final String CONNECTION_FAILED_DIALOG = "ConnectionFailedDialog";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSingerListBinding binding = FragmentSingerListBinding.inflate(inflater);
        final ApplicationViewModel viewModel = getViewModel();
        binding.setApplicationViewModel(viewModel);
        View root = binding.getRoot();
        final RecyclerView recView = (RecyclerView) root.findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        loadSingers(viewModel, recView);
        return root ;
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
                                    android.app.FragmentManager fm = getFragmentManager();
                                    SingerCardFragment singerCardFragment = (SingerCardFragment) fm.findFragmentByTag("SingerCardFragment");

                                    if (singerCardFragment == null) {
                                        singerCardFragment = new SingerCardFragment();
                                        singerCardFragment.setRetainInstance(true);
                                        //fm.beginTransaction().add(singerCardFragment, "SingerCardFragment").commit();
                                    }
                                    getFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.fragment_container, singerCardFragment, "SingerCardFragment")
                                            .addToBackStack("SingerCardFragment")
                                            .commit();
//                                    Intent intent = new Intent(SingerListActivity.this, SingerCardActivity.class);
//                                    Bundle bundle = null;
//                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                                        ActivityOptions activityOptions = ActivityOptions.makeScaleUpAnimation(recView, v.getLeft(), v.getTop(), v.getWidth(),  v.getHeight());
//                                        bundle = activityOptions.toBundle();
//                                        SingerListActivity.this.startActivity(intent, bundle);
//                                    } else SingerListActivity.this.startActivity(intent);
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
