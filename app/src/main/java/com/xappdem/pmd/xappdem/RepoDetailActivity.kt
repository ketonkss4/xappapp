package com.xappdem.pmd.xappdem

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.xappdem.pmd.xappdem.data.RepoResult
import android.content.Intent
import android.net.Uri

/**
 * This class is a simple dumb view that shows additional details
 * not seen in the RepoListView. Also launches browser intent to
 * allow user to go directly to the repository online
 */
class RepoDetailActivity : AppCompatActivity() {
    companion object {
        const val REPO_KEY = "REPO_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repo_detail_view)
        val repoData = getRepoData()
        setRepoViewData(repoData)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            launchBrowserIntent(repoData.url)
        }
    }

    private fun setRepoViewData(repoData: RepoResult) {
        val repoTitleTv = findViewById<TextView>(R.id.repo_title)
        repoTitleTv.text = repoData.name
        val authorTv = findViewById<TextView>(R.id.author)
        authorTv.text = repoData.author
        val descriptionTv = findViewById<TextView>(R.id.description)
        descriptionTv.text = repoData.description
        val starsTv = findViewById<TextView>(R.id.stars)
        starsTv.text = repoData.stars
        val forksTv = findViewById<TextView>(R.id.forks)
        forksTv.text = repoData.forks
        val langTv = findViewById<TextView>(R.id.language)
        langTv.text = repoData.language
    }

    private fun launchBrowserIntent(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun getRepoData(): RepoResult {
        assert(intent.hasExtra(REPO_KEY))
        return intent.getSerializableExtra(REPO_KEY) as RepoResult
    }
}
