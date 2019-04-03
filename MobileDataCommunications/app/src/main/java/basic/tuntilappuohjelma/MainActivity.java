package basic.tuntilappuohjelma;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView navigation;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        Fragment fragment;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_tahtava_lista:
                    fragment = new tehtava_lista();
                    loadFragment(fragment);

                    return true;
                case R.id.navigation_uusi_tehtava:
                    fragment = new uusi_tehtava();


                    loadFragment(fragment);
                    return true;
                case R.id.navigation_export:

                    fragment = new export();
                    loadFragment(fragment);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_uusi_tehtava);
        loadFragment(new uusi_tehtava());



        if (DataHandler.getInstance().getTaskList() == null && DataHandler.getInstance().getExportlist() == null){
            DataHandler.getInstance().setTaskList(new ArrayList<String>());
            DataHandler.getInstance().setExportList(new ArrayList<String[]>());
            DataHandler.getInstance().getExportlist().add(new String[]{"AloitusAika","LopetusAika","TyönKuvaus"});
        }


        }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
