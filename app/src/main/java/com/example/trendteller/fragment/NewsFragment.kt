package com.example.trendteller.fragment

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trendteller.adapter.NewsAdapter
import com.example.trendteller.databinding.FragmentNewsBinding
import com.example.trendteller.model.ArticlesItem
import com.example.trendteller.model.Response
import com.example.trendteller.model.Source
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class NewsFragment : Fragment() {
    // Declare variables
    private lateinit var binding: FragmentNewsBinding
    private lateinit var articles: MutableList<ArticlesItem>
    private lateinit var progressDialog: ProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater)
        articles = mutableListOf()

        // Execute AsyncTask to fetch news data
        FetchNewsTask().execute()

        // Show progress dialog
        progressDialog = ProgressDialog(activity)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        return binding.root

    }


    inner class FetchNewsTask : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void?): String {
            // Clear articles list before fetching new data
            articles.clear()

            // Fetch data from API
            val url =
                URL("https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json")
            val connection = url.openConnection() as HttpURLConnection
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = StringBuilder()

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }

            return response.toString()
        }

        // Parse JSON response and update UI
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            result?.let {
                val response = parseNewsData(it)
                rvNewsAdapter(response.articles)
                // Dismiss dialog here
                progressDialog?.dismiss()
            }
        }
    }


    // Parse JSON response into a list of ArticlesItem objects
    private fun parseNewsData(jsonString: String): Response {
        return try {
            val jsonObject = JSONObject(jsonString)
            val articlesArray = jsonObject.getJSONArray("articles")

            val articlesList = mutableListOf<ArticlesItem>()
            for (i in 0 until articlesArray.length()) {
                val articleObject = articlesArray.getJSONObject(i)
                val sourceObject = articleObject.getJSONObject("source")
                val source = Source(
                    name = sourceObject.optString("name"),
                    id = sourceObject.optString("id")
                )
                val article = ArticlesItem(
                    publishedAt = articleObject.optString("publishedAt"),
                    author = articleObject.optString("author"),
                    urlToImage = articleObject.optString("urlToImage"),
                    description = articleObject.optString("description"),
                    source = source,
                    title = articleObject.optString("title"),
                    url = articleObject.optString("url"),
                    content = articleObject.optString("content")
                )
                articlesList.add(article)
            }

            Response(articles = articlesList)
        } catch (e: Exception) {
            e.printStackTrace()
            Response()
        }
    }

    // Set up RecyclerView
    fun rvNewsAdapter(Data: List<ArticlesItem?>?) {
        var adapter = NewsAdapter(activity, Data)
        var layoutManager = LinearLayoutManager(activity)
        binding.rvNews.layoutManager = layoutManager
        binding.rvNews.adapter = adapter

    }

}