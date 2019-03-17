package com.marcelokmats.jayakotlinissues.issuesList

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelokmats.jayakotlinissues.R
import com.marcelokmats.jayakotlinissues.api.Issue
import com.marcelokmats.jayakotlinissues.base.BaseActivity
import com.marcelokmats.jayakotlinissues.issueDetail.IssueDetailActivity
import kotlinx.android.synthetic.main.issues_list_activity.*
import org.jetbrains.anko.longToast

class IssuesActivity : BaseActivity<IssuesPresenter>(), IssuesView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.issues_list_activity)

        presenter.onViewCreated()
    }

    override fun showIssueList(issues: List<Issue>) {
        recyclerView.adapter = IssuesAdapter(issues,
            this) {
            longToast("Click issue ${it.title}")
            this.startIssueDetailActivity()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTimeoutError() {
        longToast("Timeout error")
    }

    override fun onIssueClick(issue: Issue) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun instantiatePresenter(): IssuesPresenter = IssuesPresenter(this)

    fun startIssueDetailActivity() {
        val intent = Intent(this, IssueDetailActivity::class.java)
        this.startActivity(intent)
    }

}
