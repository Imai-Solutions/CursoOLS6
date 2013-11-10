package com.imaisolutions.p2_actionbar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jesus_000 on 9/11/13.
 */
public class Task1_Activity  extends Activity implements SearchView.OnQueryTextListener {
    SearchView searchView;
    private TextView res;
    PlaceHolderFragment fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            fr= new PlaceHolderFragment(R.layout.fragment_f2);
            getFragmentManager().beginTransaction()
                    .add(R.id.container, (Fragment)fr,"FR2")
                    .commit();
            }



    }

    private ShareActionProvider mShareActionProvider;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task1_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();

        SearchManager searchManager =(SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo info = searchManager.getSearchableInfo(getComponentName());

        configurarSearchView();

        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider)shareItem.getActionProvider();
        //Con Support: reemplazar por
        // (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        mShareActionProvider.setShareIntent(getShareIntent());
        return true;
    }

    public Intent getShareIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "News for you!");
        return  intent;
    }


    private void configurarSearchView() {
      SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            List<SearchableInfo> searchables = searchManager.getSearchablesInGlobalSearch();

            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
            for (SearchableInfo inf : searchables) {
                if (inf.getSuggestAuthority() != null
                        && inf.getSuggestAuthority().startsWith("applications")) {
                    info = inf;
                }
            }
            searchView.setSearchableInfo(info);
        }
        searchView.setOnQueryTextListener(this);
    }

    public boolean onQueryTextChange(String newText) {
        if (fr!=null)
            fr.setText("Consulta = " + newText);
        return false;
    }

    public boolean onQueryTextSubmit(String query) {
        if (fr!=null)
             fr.setText("Consulta = " + query + " : envíada");
        return false;
    }

    protected boolean isAlwaysExpanded() {
        return false;
    }

}
