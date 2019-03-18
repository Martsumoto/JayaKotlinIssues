package com.marcelokmats.jayakotlinissues.issueDetail

import com.marcelokmats.jayakotlinissues.api.IssueDetail
import com.marcelokmats.jayakotlinissues.base.BaseView

interface IssueDetailView : BaseView {

    fun showIssueDetail(issueDetail: IssueDetail)

}
