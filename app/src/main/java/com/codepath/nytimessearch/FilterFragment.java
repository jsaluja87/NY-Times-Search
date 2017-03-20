package com.codepath.nytimessearch;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.codepath.nytimessearch.databinding.FragmentFilterBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FilterFragment extends DialogFragment {

    final static String PARSED_DATE_FORMAT="yyyyMMdd";
    private CheckBox deskArtValue;
    private CheckBox deskSportsValue;
    private CheckBox deskStyleValue;
    private Spinner sortOrder;
    private DatePicker datePicker;
    private Button saveButton;
    String sortingOrder;

    public OnFragmentInteractionListener mListener;

    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment newInstance() {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentFilterBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter, container, false);
        View mView = binding.getRoot();
        dataBindFragmentValues(binding);
        this.setCancelable(false);

        sortOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 sortingOrder = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        saveButton.setOnClickListener(v -> {
            mListener = (OnFragmentInteractionListener) getActivity();

            Calendar cal = Calendar.getInstance();
            cal.set(FilterFragment.this.datePicker.getYear(), FilterFragment.this.datePicker.getMonth(), FilterFragment.this.datePicker.getMonth());
            SimpleDateFormat format = new SimpleDateFormat(PARSED_DATE_FORMAT);
            String dateString = format.format(cal.getTime());
            String newsDeskOptions =  format_news_desk_options();

            mListener.onFinishEditDialog(sortingOrder, dateString, newsDeskOptions);
            dismiss();
        });
        return mView;
    }

    public void dataBindFragmentValues(FragmentFilterBinding binding) {
        deskArtValue = binding.FilterActArtcheckBoxId;
        deskSportsValue = binding.FilterActSportscheckBoxId;
        deskStyleValue = binding.FilterActStylecheckBoxId;
        sortOrder = binding.FilterActSpinnerId;
        this.datePicker = binding.FilterActDateId;
        saveButton = binding.FilterButtonId;
    }


    public String format_news_desk_options() {
        String newsDeskOptions = null;
        if((deskArtValue.isChecked()) || (deskSportsValue.isChecked()) || (deskStyleValue.isChecked())) {
            newsDeskOptions = "news_desk:(";
            if (deskArtValue.isChecked()) {
                newsDeskOptions = newsDeskOptions + "\"Arts\" ";
            }
            if (deskSportsValue.isChecked()) {
                newsDeskOptions = newsDeskOptions + "\"Sports\" ";
            }
            if (deskStyleValue.isChecked()) {
                newsDeskOptions = newsDeskOptions + "\"Fashion & Style\" ";
            }
            newsDeskOptions = newsDeskOptions + ")";
        }
        return newsDeskOptions;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener {
        void onFinishEditDialog(String sortingOrder, String inputDate, String deskOptions);
    }
}
