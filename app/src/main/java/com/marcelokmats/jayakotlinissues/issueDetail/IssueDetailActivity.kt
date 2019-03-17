package com.marcelokmats.jayakotlinissues.issueDetail

import android.os.Bundle
import com.marcelokmats.jayakotlinissues.R
import com.marcelokmats.jayakotlinissues.base.BaseActivity
import org.jetbrains.anko.longToast

class IssueDetailActivity : BaseActivity<IssueDetailPresenter>(), IssueDetailView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.issues_list_activity)

        presenter.onViewCreated()
    }

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTimeoutError() {
        longToast("Timeout error")
    }

    override fun instantiatePresenter(): IssueDetailPresenter = IssueDetailPresenter(this)

}
