package com.marcelokmats.jayakotlinissues.issuesList

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelokmats.jayakotlinissues.R
import com.marcelokmats.jayakotlinissues.api.Issue
import com.marcelokmats.jayakotlinissues.base.BaseActivity
import com.marcelokmats.jayakotlinissues.issueDetail.IssueDetailActivity
import com.marcelokmats.jayakotlinissues.util.ISSUE_NUMBER
import com.marcelokmats.jayakotlinissues.util.USER
import kotlinx.android.synthetic.main.issues_list_activity.*
import org.jetbrains.anko.longToast

class IssuesActivity : BaseActivity<IssuesPresenter>(), IssuesView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.issues_list_activity)

        presenter.onViewCreated()

        swipeContainer.setOnRefreshListener { presenter.onViewCreated() }
    }

    override fun showIssueList(issues: List<Issue>) {
        recyclerView.visibility = View.VISIBLE
        swipeContainer.isRefreshing = false
        tvErrorMessage.visibility = View.GONE

        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = IssuesAdapter(issues,
            this) {
            this.onIssueClick(it)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun showProgressBar() {
        recyclerView.visibility = View.GONE
        swipeContainer.isRefreshing = true
        tvErrorMessage.visibility = View.GONE
    }

    override fun showLoadError(messageId: Int) {
        recyclerView.visibility = View.GONE
        swipeContainer.isRefreshing = false
        tvErrorMessage.visibility = View.VISIBLE

        tvErrorMessage.text = getString(messageId)
    }

    override fun onIssueClick(issue: Issue) {
        startIssueDetailActivity(issue)
    }

    override fun instantiatePresenter(): IssuesPresenter = IssuesPresenter(this)

    private fun startIssueDetailActivity(issue: Issue) {
        val intent = Intent(this, IssueDetailActivity::class.java)
        intent.putExtra(ISSUE_NUMBER, issue.number)
        intent.putExtra(USER, issue.user)
        this.startActivity(intent)
    }

}
