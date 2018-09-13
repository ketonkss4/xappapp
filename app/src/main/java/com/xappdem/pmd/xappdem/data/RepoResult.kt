package com.xappdem.pmd.xappdem.data

import java.io.Serializable

/**
 */
data class RepoResult(
        val author: String,
        val forks: String,
        val stars: String,
        val description: String,
        val currentPeriodStars: String,
        val name: String,
        val language: String,
        val url: String
) : Serializable