package com.bigdudeapps.pictureviewer.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.SearchView.OnQueryTextListener
import android.view.Menu
import android.view.MenuItem
import com.bigdudeapps.pictureviewer.R
import kotlinx.android.synthetic.main.activity_search.*
import com.bigdudeapps.pictureviewer.ui.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity(), OnQueryTextListener {

    companion object {
        const val QUERY_TEXT = "queryText"
        const val SEARCH_VIEW_STATE_OPEN = "searchViewState"
    }

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchAdapter: SearchRecyclerAdapter
    private lateinit var searchViewModel: SearchViewModel

    private var searchQuery = ""
    private var searchState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO add some sort of default logo or content on empty page for when users first open app and when coming back
        setContentView(R.layout.activity_search)
        setSupportActionBar(search_toolbar)

        if (savedInstanceState != null) {
            searchQuery = savedInstanceState.getString(QUERY_TEXT, "")
            searchState = savedInstanceState.getBoolean(SEARCH_VIEW_STATE_OPEN, false)
        }

        //TODO pagination/continuous scrolling, requires exploring pixa API a little more
        //TODO progress indicator or skeleton page while loading content
        recyclerView = search_recycler_view
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        searchAdapter = SearchRecyclerAdapter()
        recyclerView.adapter = searchAdapter

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        searchAdapter.setData(searchViewModel.searchImageData.value)

        //LiveData observer set up to be leveraged later if so desired
        searchViewModel.searchImageData.observe(this, Observer { imageList ->
            imageList?.let { searchAdapter.setData(it) }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_search, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_view_hint)
        searchView.setOnQueryTextListener(this)

        //Restore state of searchView (technically actionView)
        if (searchState) {
            searchItem.expandActionView()
            searchView.setQuery(searchQuery, false)
        }
        //TODO SearchView open animation rather than just popping to "expanded" mode
        //TODO SearchView is terrible, lesson learned. Should've just made custom
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                searchState = true
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                searchAdapter.clear()
                searchState = false
                return true
            }

        })

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchView.clearFocus()
        searchViewModel.setQuery(query)
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        //no op - could do live search but would be expensive
        return true
    }

    override fun onBackPressed() {
        if (searchAdapter.itemCount > 0) {
            searchAdapter.clear()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(QUERY_TEXT, searchView.query.toString())
        outState?.putBoolean(SEARCH_VIEW_STATE_OPEN, searchState)
    }
}
