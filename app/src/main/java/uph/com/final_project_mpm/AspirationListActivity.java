package uph.com.final_project_mpm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AspirationListActivity extends AppCompatActivity {

    public void changeFragments(Fragment fragmentClass) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder_details, fragmentClass);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void logout(View view){

        changeFragments(new FragmentConfirm());

//        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aspiration_list);
        changeFragments(new FragmentList());
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        if (getSupportFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().popBackStack();
        }
        else
            this.finish();
    }
}
