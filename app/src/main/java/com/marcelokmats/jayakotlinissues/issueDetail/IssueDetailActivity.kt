package com.marcelokmats.jayakotlinissues.issueDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.marcelokmats.jayakotlinissues.R
import com.marcelokmats.jayakotlinissues.api.IssueDetail
import com.marcelokmats.jayakotlinissues.api.User
import com.marcelokmats.jayakotlinissues.base.BaseActivity
import com.marcelokmats.jayakotlinissues.util.DateUtil
import com.marcelokmats.jayakotlinissues.util.ISSUE_NUMBER
import com.marcelokmats.jayakotlinissues.util.ImageUtil
import com.marcelokmats.jayakotlinissues.util.USER
import kotlinx.android.synthetic.main.issue_detail_activity.*

class IssueDetailActivity : BaseActivity<IssueDetailPresenter>(), IssueDetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.issue_detail_activity)

        presenter.onViewCreated()
        this.setupToolbar()
    }

    override fun showProgressBar() {
        content.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        tvErrorMessage.visibility = View.GONE
    }

    override fun showLoadError(messageId : Int) {
        content.visibility = View.GONE
        progressBar.visibility = View.GONE
        tvErrorMessage.visibility = View.VISIBLE

        tvErrorMessage.text = getString(messageId)
    }

    override fun instantiatePresenter(): IssueDetailPresenter = IssueDetailPresenter(this)

    override fun showIssueDetail(issueDetail: IssueDetail) {
        content.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        tvErrorMessage.visibility = View.VISIBLE

        tvTitle.text  = issueDetail.title
        tvDescription.text = issueDetail.body
        tvCreationDate.text = issueDetail.createDate?.let { DateUtil.formatDate(it) }

        btOpen.setOnClickListener { issueDetail.htmlUrl?.let(this::openUrl) }

    }

    override fun getIssueNumber() = intent.getStringExtra(ISSUE_NUMBER)

    private fun openUrl(url : String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun setupToolbar() {
        val user = intent.getParcelableExtra<User>(USER)

        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (collapsingToolbar as CollapsingToolbarLayout).title = user?.login ?: ""
        ImageUtil.setupImage(this, user?.avatar_url ?: "", ivAvatar)
    }
}
