package com.cs646.pwang.stravaplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.chart.datatype.AbstractChartDataType;
import com.cs646.pwang.stravaplus.chart.datatype.ChartDataTypeFactory;

public class PerformanceFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private String[] options;

    public PerformanceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_performance, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        options = getActivity().getResources().getStringArray(R.array.performance_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                options
        );

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        ChartFragment chartFragment = new ChartFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        AbstractChartDataType dataType = ChartDataTypeFactory.getDataType(options[position]);

        Bundle data = new Bundle();
        data.putSerializable("dataType", dataType);
        chartFragment.setArguments(data);

        fragmentTransaction.replace(R.id.content_fragment, chartFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }
}
