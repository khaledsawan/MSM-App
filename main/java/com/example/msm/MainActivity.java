package com.example.msm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Service.MSMFireBaseMethod;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements AppBarListener


{

    private AppBarConfiguration mAppBarConfiguration;
    private TextView tv_AppBar;

    SharedPreferences sharedPreferences;

    @Override
    protected void onResume() {
        MSMFireBaseMethod.Check_Connect();
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_AppBar = findViewById(R.id.toolbar_title);


        MSMFireBaseMethod.setContext(getBaseContext());







//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                ArrayList<Integer> servicesList = new ArrayList();
//                ArrayList<Integer> advertisementList = new ArrayList();
//
//                servicesList.add(1);
//                servicesList.add(2);
//                advertisementList.add(1);
//
////                AdminConnect.setItem(new Admin("4","4"));
//
//                AdvertisementsConnect.setItem(new Advertisements("asd","zcc"));
//
////                ServicesConnect.setItem(new Services("1T","1S","1C"));
////                CenterConnect.setItem(new Center("walidwalid","123","Homs"));
////                CustomerConnect.setItem(new Customer("walid@gmail.com","Walid","Munawwar","123456789","Male","0932318088",1,"5/12/2000",85,2500,servicesList,advertisementList));
////                RequestConnect.setChangeSpeedRequest(new Request("","","1","",getBaseContext()));
////                RequestConnect.responseOnChangeSpeedRequest("1",true);
//            }
//        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_login,R.id.nav_Visitor_Main,R.id.nav_Customer_Main,R.id.nav_Center_Main,R.id.nav_Admin_Main,R.id.nav_Connect_With_Us,R.id.nav_Who_Are_We,R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void onNewFragmentOpen(String fragment_Name) {
        tv_AppBar.setText(fragment_Name);
    }

    private void checkLogin() {
        sharedPreferences = getSharedPreferences("LogIN", Context.MODE_PRIVATE);
        NavigationView navigationView;
        navigationView = (NavigationView)  findViewById(R.id.nav_view);

        String Type = sharedPreferences.getString("type", "Is not logged in");

        switch (Type) {
            case "Is not logged in": {

                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.nav_login).setVisible(true);
                nav_Menu.findItem(R.id.nav_Customer_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Visitor_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Center_Main).setVisible(false);


                nav_Menu.findItem(R.id.nav_Admin_Main).setVisible(false);

                navigationView.getMenu().getItem(0).setChecked(true);



                break;
            }
            case "Visitor": {
                Menu nav_Menu = navigationView.getMenu();


                nav_Menu.findItem(R.id.nav_login).setVisible(false);
                nav_Menu.findItem(R.id.nav_Visitor_Main).setVisible(true);
                nav_Menu.findItem(R.id.nav_Customer_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Center_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Admin_Main).setVisible(false);

                navigationView.getMenu().getItem(1).setChecked(false);




                break;
            }
            case "Customer": {

                Menu nav_Menu = navigationView.getMenu();


                nav_Menu.findItem(R.id.nav_login).setVisible(false);
                nav_Menu.findItem(R.id.nav_Visitor_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Customer_Main).setVisible(true);
                nav_Menu.findItem(R.id.nav_Center_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Admin_Main).setVisible(false);

                navigationView.getMenu().getItem(2).setChecked(true);



                break;
            }
            case "Center": {
                Menu nav_Menu = navigationView.getMenu();

                nav_Menu.findItem(R.id.nav_login).setVisible(false);
                nav_Menu.findItem(R.id.nav_Visitor_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Customer_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Center_Main).setVisible(true);
                nav_Menu.findItem(R.id.nav_Admin_Main).setVisible(false);

                navigationView.getMenu().getItem(3).setChecked(true);

                break;
            }
            case "Admin": {


                Menu nav_Menu = navigationView.getMenu();

                nav_Menu.findItem(R.id.nav_login).setVisible(false);
                nav_Menu.findItem(R.id.nav_Visitor_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Customer_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Center_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Admin_Main).setVisible(true);

                navigationView.getMenu().getItem(4).setChecked(true);

                break;


            }
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        checkLogin();
    }
}