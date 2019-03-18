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
import kotlinx.android.synthetic.main.issues_list_activity.*
import org.jetbrains.anko.longToast

class IssuesActivity : BaseActivity<IssuesPresenter>(), IssuesView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.issues_list_activity)

        presenter.onViewCreated()
    }

    override fun showIssueList(issues: List<Issue>) {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
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
        progressBar.visibility = View.VISIBLE
        tvErrorMessage.visibility = View.GONE
    }

    override fun showLoadError(messageId: Int) {
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
        tvErrorMessage.visibility = View.VISIBLE

        tvErrorMessage.text = getString(messageId)
    }

    override fun onIssueClick(issue: Issue) {
        longToast("Click issue ${issue.title}")
        startIssueDetailActivity(issue)
    }

    override fun instantiatePresenter(): IssuesPresenter = IssuesPresenter(this)

    private fun startIssueDetailActivity(issue: Issue) {
        val intent = Intent(this, IssueDetailActivity::class.java)
        intent.putExtra(ISSUE_NUMBER, issue.number)
        this.startActivity(intent)
    }

}
