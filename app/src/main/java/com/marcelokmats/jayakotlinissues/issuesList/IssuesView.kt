package com.marcelokmats.jayakotlinissues.issuesList

import com.marcelokmats.jayakotlinissues.api.Issue
import com.marcelokmats.jayakotlinissues.base.BaseView

interface IssuesView : BaseView {
    fun onIssueClick(issue: Issue)

    fun showIssueList(issues : List<Issue>)
}
