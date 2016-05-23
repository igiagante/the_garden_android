package com.example.igiagante.thegarden.creation.plants.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.igiagante.thegarden.R;
import com.example.igiagante.thegarden.core.domain.entity.Plant;
import com.example.igiagante.thegarden.core.ui.CountView;
import com.example.igiagante.thegarden.creation.plants.presentation.CreatePlantActivity;
import com.example.igiagante.thegarden.creation.plants.presentation.PlantBuilder;
import com.satsuware.usefulviews.LabelledSpinner;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment used for the main data plant.
 *
 * @author Ignacio Giagante, on 6/5/16.
 */
public class MainDataFragment extends CreationBaseFragment implements LabelledSpinner.OnItemChosenListener {

    public static final String PLANT_KEY = "PLANT";

    @Bind(R.id.name_of_plant_id)
    TextView mNameOfPlant;

    @Bind(R.id.genotype_id)
    TextView mGenotype;

    @Bind(R.id.ph_soil_id)
    CountView mPhSoil;

    @Bind(R.id.ec_soil_id)
    CountView mEcSoil;

    @Bind(R.id.size_id)
    CountView mSize;

    private LabelledSpinner spinner;

    private String mFloweringTime;

    private Plant mPlant;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.create_plant_fragment, container, false);

        ButterKnife.bind(this, fragmentView);

        spinner = (LabelledSpinner) fragmentView.findViewById(R.id.spinner_flowering_time_id);
        spinner.setItemsArray(R.array.flowering_time_array);
        spinner.setOnItemChosenListener(this);

        if(savedInstanceState != null) {
            mPlant = savedInstanceState.getParcelable(PLANT_KEY);
            setPlantValuesInView(fragmentView);
        }

        initDefaultValues();


        return fragmentView;
    }

    @Override
    public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
        if (labelledSpinner.getId() == R.id.spinner_flowering_time_id) {
            mFloweringTime = (String) adapterView.getAdapter().getItem(position);
        }
    }

    @Override
    public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            mPlant = createParcelable();
            outState.putParcelable(PLANT_KEY, mPlant);
        }
    }

    @Override
    protected void move() {
        PlantBuilder builder = ((CreatePlantActivity) getActivity()).getPlantBuilder();
        builder.addPlantName(mNameOfPlant.getText().toString());
        builder.addPhSoil(mPhSoil.getEditValue());
        builder.addEcSoil(mEcSoil.getEditValue());
        builder.addFloweringTime(mFloweringTime);
        builder.addGenotype(mGenotype.getText().toString());
        builder.addSize((int) mSize.getEditValue());
    }

    private void initDefaultValues() {

        EditText mPhSoilText = (EditText) mPhSoil.findViewById(R.id.count_input);
        mPhSoilText.setHint(R.string.ph_soil);
        mPhSoilText.setText(R.string.ph_soil_default_value);

        EditText mEcSoilText = (EditText) mEcSoil.findViewById(R.id.count_input);
        mEcSoilText.setHint(R.string.ec_soil);
        mEcSoilText.setText(R.string.ec_soil_default_value);

        EditText sizeText = (EditText) mSize.findViewById(R.id.count_input);
        sizeText.setHint(R.string.size);
        sizeText.setText(R.string.size_default_value);
    }

    private Plant createParcelable() {
        Plant plant = new Plant();
        plant.setName(mNameOfPlant.getText().toString());
        plant.setPhSoil(mPhSoil.getEditValue());
        plant.setEcSoil(mEcSoil.getEditValue());
        plant.setFloweringTime(mFloweringTime);
        plant.setGenotype(mGenotype.getText().toString());
        plant.setSize((int) mSize.getEditValue());
        return plant;
    }

    private void setPlantValuesInView(View view) {

        mNameOfPlant.setText(mPlant.getName());
        mPhSoil.setEditValue(mPlant.getPhSoil());
        mEcSoil.setEditValue(mPlant.getEcSoil());

        String [] list = getActivity().getResources().getStringArray(R.array.flowering_time_array);
        ArrayList<String> newList = new ArrayList<>(Arrays.asList(list));

        if(spinner != null) {
            spinner.setSelection(newList.indexOf(mPlant.getFloweringTime()));
        }

        mGenotype.setText(mPlant.getGenotype());
        mSize.setEditValue(mPlant.getSize());
    }
}